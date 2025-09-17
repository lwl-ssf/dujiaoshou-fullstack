\1
/**
 * Product
 * 模块：领域实体（JPA）
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Product {
    private Long id;

    @NotBlank
  private String name;

    private String description;

    @NotNull
  private BigDecimal price;

  /** Whether this product delivers license cards (like dujiaoka) */
    private boolean cardDelivery;

  /** stock cached, not authoritative; authoritative is InventoryCard count */
  private Integer stock;
}
