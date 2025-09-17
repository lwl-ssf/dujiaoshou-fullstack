\1
/**
 * ProductController
 * 模块：前台/公共控制器
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import com.example.dujiaoshou.entity.Product;
import com.example.dujiaoshou.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/products") @RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;

  @GetMapping public List<Product> list(){ return productService.list(); }

  @PostMapping public Product create(@Valid @RequestBody Product p){ return productService.create(p); }

  @GetMapping("/{id}") public ResponseEntity<Product> get(@PathVariable Long id){
    return productService.get(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}") public Product update(@PathVariable Long id, @Valid @RequestBody Product p){
    return productService.update(id, p);
  }

  @DeleteMapping("/{id}") public void delete(@PathVariable Long id){ productService.delete(id); }
}
