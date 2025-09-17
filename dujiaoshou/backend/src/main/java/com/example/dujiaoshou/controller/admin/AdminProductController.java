\1
/**
 * AdminProductController
 * 模块：后台管理控制器
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import com.example.dujiaoshou.entity.Product;
import com.example.dujiaoshou.mapper.ProductMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
public class AdminProductController {
  private final ProductMapper mapper;

  @PreAuthorize("hasAuthority('PERM_PRODUCT_MANAGE')")
  @GetMapping public List<Product> list(){ return mapper.selectAll(); }
  @PreAuthorize("hasAuthority('PERM_PRODUCT_MANAGE')")
  @PostMapping public Product create(@Valid @RequestBody Product p){ return mapper.insert(p); return p; }
  @PutMapping("/{id}") public Product update(@PathVariable Long id, @Valid @RequestBody Product p){
    Product old = java.util.Optional.ofNullable(mapper.selectById(id)).orElseThrow();
    old.setName(p.getName()); old.setDescription(p.getDescription());
    old.setPrice(p.getPrice()); old.setCardDelivery(p.isCardDelivery()); old.setStock(p.getStock());
    mapper.update(old); return old;
  }
  @DeleteMapping("/{id}") public void delete(@PathVariable Long id){ mapper.delete(id); }
}
