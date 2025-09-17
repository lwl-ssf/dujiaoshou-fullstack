\1
/**
 * Product
 * 模块：领域实体（JPA）
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Entity @Table(name = "products")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Product {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false, unique=true, length=100)
  @NotBlank
  private String name;

  @Column(length=255)
  private String description;

  @Column(nullable=false, precision=12, scale=2)
  @NotNull
  private BigDecimal price;

  /** Whether this product delivers license cards (like dujiaoka) */
  @Column(nullable=false)
  private boolean cardDelivery;

  /** stock cached, not authoritative; authoritative is InventoryCard count */
  private Integer stock;
}
