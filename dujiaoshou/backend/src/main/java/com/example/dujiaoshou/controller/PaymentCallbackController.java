\1
/**
 * PaymentCallbackController
 * 模块：前台/公共控制器
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import com.example.dujiaoshou.entity.Order;
import com.example.dujiaoshou.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/pay")
@RequiredArgsConstructor
public class PaymentCallbackController {

  private final OrderService orderService;

  // 示例：/api/pay/callback/ALIPAY?sn=xxx
  @PostMapping("/callback/{channel}")
  public ResponseEntity<Map<String, Object>> callback(@PathVariable String channel, @RequestParam String sn) {
    // 真实情况需要验签，这里直接标记为已付
    Order order = orderService.markPaid(sn);
    return ResponseEntity.ok(Map.of("ok", true, "sn", order.getSn(), "status", order.getStatus().name(), "channel", channel));
  }
}
