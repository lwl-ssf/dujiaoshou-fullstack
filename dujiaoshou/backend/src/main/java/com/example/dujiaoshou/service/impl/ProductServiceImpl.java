\1
/**
 * ProductServiceImpl
 * 模块：业务实现（Service Impl）
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import com.example.dujiaoshou.entity.Product;
import com.example.dujiaoshou.mapper.ProductMapper;
import com.example.dujiaoshou.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor @Transactional
public class ProductServiceImpl implements ProductService {
  private final ProductMapper mapper;

  @Override public List<Product> list(){ return mapper.selectAll(); }
  @Override public Optional<Product> get(Long id){ return java.util.Optional.ofNullable(mapper.selectById(id)); }
  @Override public Product create(Product p){ mapper.insert(p); return p; }
  @Override public Product update(Long id, Product p){
    return repo.findById(id).map(old -> {
      old.setName(p.getName());
      old.setDescription(p.getDescription());
      old.setPrice(p.getPrice());
      old.setCardDelivery(p.isCardDelivery());
      old.setStock(p.getStock());
      mapper.update(old); return old;
    }).orElseThrow();
  }
  @Override public void delete(Long id){ mapper.delete(id); }
}
