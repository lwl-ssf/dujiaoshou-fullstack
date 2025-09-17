\1
/**
 * OrderServiceImpl
 * 模块：业务实现（Service Impl）
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import com.example.dujiaoshou.entity.*;
import com.example.dujiaoshou.mapper.InventoryCardMapper;
import com.example.dujiaoshou.mapper.OrderMapper;
import com.example.dujiaoshou.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Service @RequiredArgsConstructor @Transactional
public class OrderServiceImpl implements OrderService {
  private final OrderMapper orders;
  private final InventoryCardMapper cards;

  @Override
  public Order createOrder(Product product, int quantity, String currency, String channel) {
    java.math.BigDecimal amount = product.getPrice().multiply(java.math.BigDecimal.valueOf(quantity));
    Order order = Order.builder()
        .sn(java.util.UUID.randomUUID().toString().replaceAll("-", "").substring(0, 24))
        .status(Order.Status.PENDING)
        .amount(amount)
        .currency(currency)
        .paymentChannel(channel)
        .createdAt(java.time.OffsetDateTime.now())
        .build();

    for (int i=0;i<quantity;i++) {
      OrderItem item = OrderItem.builder()
          .order(order)
          .product(product)
          .quantity(1)
          .unitPrice(product.getPrice())
          .build();
      order.getItems().add(item);
    }
    order = orders.insert(order);

    // Try to reserve inventory
    java.util.List<Long> ids = new java.util.ArrayList<>();
    for (int i=0; i<quantity; i++) {
      com.example.dujiaoshou.entity.InventoryCard card = cards.selectFirstAvailableByProductForUpdate(product.getId());
      if (card != null) {
        cards.updateStatusAndOrder(card.getId(), "RESERVED", order.getId());
      }
    }
    return order;
  }

  @Override public java.util.Optional<Order> findBySn(String sn){ return java.util.Optional.ofNullable(orders.findBySn(sn)); }

  @Override
  public Order markPaid(String sn) {
    Order order = java.util.Optional.ofNullable(orders.findBySn(sn)).orElseThrow();
    order.setStatus(Order.Status.PAID);
    order.setPaidAt(OffsetDateTime.now());
    return orders.insert(order);
  }

  @Override
  public Order deliver(String sn) {
    Order order = java.util.Optional.ofNullable(orders.findBySn(sn)).orElseThrow();
    if (order.getStatus() != Order.Status.PAID) throw new IllegalStateException("Order not paid");
    for (OrderItem item : order.getItems()) {
      java.util.List<com.example.dujiaoshou.domain.InventoryCard> reserved = cards.findByOrderId(order.getId());
      com.example.dujiaoshou.domain.InventoryCard match = reserved.stream().filter(c -> c.getStatus()==com.example.dujiaoshou.domain.InventoryCard.Status.RESERVED).findFirst().orElse(null);
      if (match == null) {
        match = cards.findFirstByProductAndStatusOrderByIdAsc(item.getProduct(), com.example.dujiaoshou.domain.InventoryCard.Status.AVAILABLE).orElseThrow();
      }
      match.setStatus(com.example.dujiaoshou.domain.InventoryCard.Status.SOLD);
      cards.updateStatusAndOrder(match.getId(), "SOLD", order.getId());
      OrderDelivery d = OrderDelivery.builder().order(order).content(match.getCode()).build();
      order.getDeliveries().add(d);
    }
    order.setStatus(Order.Status.DELIVERED);
    order.setDeliveredAt(OffsetDateTime.now());
    return orders.insert(order);
  }
}
