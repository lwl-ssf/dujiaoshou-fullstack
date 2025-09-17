\1
/**
 * OrderService.java
 * 模块：业务接口/服务（Service）
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import com.example.dujiaoshou.domain.Order;
import com.example.dujiaoshou.domain.Product;
import java.math.BigDecimal;
import java.util.Optional;

public interface OrderService {
  Order createOrder(Product product, int quantity, String currency, String channel);
  Optional<Order> findBySn(String sn);
  Order markPaid(String sn);
  Order deliver(String sn);
}
