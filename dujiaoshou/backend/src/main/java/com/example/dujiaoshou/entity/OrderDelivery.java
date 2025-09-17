\1
/**
 * OrderDelivery
 * 模块：领域实体（JPA）
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="order_deliveries")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderDelivery {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional=false) @JoinColumn(name="order_id")
  private Order order;

  /** For cardDelivery, this is the delivered code */
  @Column(length=1024)
  private String content;
}
