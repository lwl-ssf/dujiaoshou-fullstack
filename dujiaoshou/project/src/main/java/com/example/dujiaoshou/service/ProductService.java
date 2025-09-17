\1
/**
 * ProductService.java
 * 模块：业务接口/服务（Service）
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import com.example.dujiaoshou.domain.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
  List<Product> list();
  Optional<Product> get(Long id);
  Product create(Product p);
  Product update(Long id, Product p);
  void delete(Long id);
}
