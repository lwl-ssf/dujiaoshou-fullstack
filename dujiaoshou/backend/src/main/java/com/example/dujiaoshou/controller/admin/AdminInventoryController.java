\1
/**
 * AdminInventoryController
 * 模块：后台管理控制器
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import com.example.dujiaoshou.entity.InventoryCard;
import com.example.dujiaoshou.entity.Product;
import com.example.dujiaoshou.mapper.InventoryCardMapper;
import com.example.dujiaoshou.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("/api/admin/inventory")
@RequiredArgsConstructor
public class AdminInventoryController {
  private final InventoryCardMapper cards;
  private final ProductMapper products;

  @PreAuthorize("hasAuthority('PERM_INVENTORY_MANAGE')")
  @GetMapping("/{productId}")
  public Map<String, Object> stat(@PathVariable Long productId){
    Product p = java.util.Optional.ofNullable(products.selectById(productId)).orElseThrow();
    long avail = cards.countByProductAndStatus(p.getId(), "AVAILABLE");
    long reserved = cards.countByProductAndStatus(p.getId(), "RESERVED");
    long sold = cards.countByProductAndStatus(p.getId(), "SOLD");
    return Map.of("productId", productId, "available", avail, "reserved", reserved, "sold", sold);
  }

  @PreAuthorize("hasAuthority('PERM_INVENTORY_MANAGE')")
  @PostMapping(path="/{productId}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public Map<String, Object> upload(@PathVariable Long productId, @RequestPart("file") MultipartFile file) throws Exception {
    Product p = java.util.Optional.ofNullable(products.selectById(productId)).orElseThrow();
    int count = 0;
    try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
      String line;
      while ((line = br.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty()) continue;
        InventoryCard c = InventoryCard.builder().product(p).code(line).status(InventoryCard.Status.AVAILABLE).build();
        cards.insert(c);
        count++;
      }
    }
    return Map.of("ok", true, "inserted", count);
  }
}
