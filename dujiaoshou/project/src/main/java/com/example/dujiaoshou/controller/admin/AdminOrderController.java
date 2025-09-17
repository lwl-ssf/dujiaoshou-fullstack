\1
/**
 * AdminOrderController
 * 模块：后台管理控制器
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import com.example.dujiaoshou.entity.Order;
import com.example.dujiaoshou.repository.OrderRepository;
import com.example.dujiaoshou.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {
  private final OrderRepository repo;
  private final OrderService service;

  @PreAuthorize("hasAuthority('PERM_ORDER_MANAGE')")
  @GetMapping public List<Order> list(){ return repo.findAll(); }

  @PreAuthorize("hasAuthority('PERM_ORDER_MANAGE')")
  @PostMapping("/{sn}/deliver")
  public Order deliver(@PathVariable String sn){ return service.deliver(sn); }

  @PreAuthorize("hasAuthority('PERM_ORDER_MANAGE')")
  @PostMapping("/{sn}/cancel")
  public Order cancel(@PathVariable String sn){
    Order o = repo.findBySn(sn).orElseThrow();
    o.setStatus(Order.Status.CANCELLED);
    return repo.save(o);
  }
}
