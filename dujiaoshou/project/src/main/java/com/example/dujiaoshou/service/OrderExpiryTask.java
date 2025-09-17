\1
/**
 * OrderExpiryTask
 * 模块：业务接口/服务（Service）
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import com.example.dujiaoshou.domain.InventoryCard;
import com.example.dujiaoshou.domain.Order;
import com.example.dujiaoshou.repository.InventoryCardRepository;
import com.example.dujiaoshou.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Component
public class OrderExpiryTask {

  private final OrderRepository orders;
  private final InventoryCardRepository cards;

  @Value("${app.shop.orderExpireMinutes:30}")
  private int expireMinutes;

  public OrderExpiryTask(OrderRepository orders, InventoryCardRepository cards) {
    this.orders = orders;
    this.cards = cards;
  }

  @Scheduled(fixedDelay = 60000) // 每分钟检查一次
  public void expirePendingOrders() {
    OffsetDateTime threshold = OffsetDateTime.now().minusMinutes(expireMinutes);
    List<Order> all = orders.findAll();
    for (Order o : all) {
      if (o.getStatus() == Order.Status.PENDING && o.getCreatedAt() != null && o.getCreatedAt().isBefore(threshold)) {
        o.setStatus(Order.Status.EXPIRED);
        orders.save(o);
        // release reservations
        List<InventoryCard> reserved = cards.findByOrderId(o.getId());
        for (InventoryCard c : reserved) {
          if (c.getStatus() == InventoryCard.Status.RESERVED) {
            c.setStatus(InventoryCard.Status.AVAILABLE);
            c.setOrderId(null);
            cards.save(c);
          }
        }
      }
    }
  }
}
