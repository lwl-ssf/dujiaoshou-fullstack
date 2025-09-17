\1
/**
 * InventoryCard
 * 模块：领域实体（JPA）
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="inventory_cards",
  indexes = { @Index(name="idx_card_code", columnList="code", unique=true),
              @Index(name="idx_card_status", columnList="status") })
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class InventoryCard {
  public enum Status { AVAILABLE, RESERVED, SOLD, INVALID }
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional=false) @JoinColumn(name="product_id")
  private Product product;

  @Column(nullable=false, unique=true, length=512)
  private String code;

  @Enumerated(EnumType.STRING) @Column(nullable=false, length=16)
  private Status status = Status.AVAILABLE;

  private Long orderId;
}
