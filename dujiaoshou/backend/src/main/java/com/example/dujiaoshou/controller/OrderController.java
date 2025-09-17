\1
/**
 * OrderController
 * 模块：前台/公共控制器
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import com.example.dujiaoshou.entity.Order;
import com.example.dujiaoshou.entity.Product;
import com.example.dujiaoshou.mapper.ProductMapper;
import com.example.dujiaoshou.service.OrderService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController @RequestMapping("/api/orders") @RequiredArgsConstructor
public class OrderController {
  private final OrderService orders;
  private final ProductMapper products;

  @PostMapping("/create")
  public Order create(@RequestParam Long productId, @RequestParam @Min(1) int qty,
                      @RequestParam(defaultValue="CNY") String currency,
                      @RequestParam(defaultValue="ALIPAY") String channel){
    Product p = java.util.Optional.ofNullable(products.selectById(productId)).orElseThrow();
    return orders.createOrder(p, qty, currency, channel);
  }

  @PostMapping("/{sn}/pay")
  public ResponseEntity<Map<String, String>> pay(@PathVariable String sn){
    // Stub: return a fake pay link
    return ResponseEntity.ok(Map.of("payUrl", "pay://"+sn));
  }

  @PostMapping("/{sn}/paid")
  public Order paid(@PathVariable String sn){ return orders.markPaid(sn); }

  @PostMapping("/{sn}/deliver")
  public Order deliver(@PathVariable String sn){ return orders.deliver(sn); }

  @GetMapping("/{sn}")
  public ResponseEntity<Order> get(@PathVariable String sn){
    return java.util.Optional.ofNullable(orders.findBySn(sn)).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }
}
