\1
/**
 * OrderItem
 * 模块：领域实体（JPA）
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity @Table(name="order_items")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderItem {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional=false) @JoinColumn(name="order_id")
  private Order order;

  @ManyToOne(optional=false) @JoinColumn(name="product_id")
  private Product product;

  private Integer quantity;

  @Column(precision=12, scale=2, nullable=false)
  private BigDecimal unitPrice;
}
