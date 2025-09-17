\1
/**
 * Order
 * 模块：领域实体（JPA）
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;

@Entity @Table(name="orders", indexes = @Index(name="idx_order_sn", columnList="sn", unique=true))
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Order {
  public enum Status { PENDING, PAID, DELIVERED, EXPIRED, CANCELLED }

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false, unique=true, length=64)
  private String sn;

  @Enumerated(EnumType.STRING) @Column(nullable=false, length=16)
  private Status status = Status.PENDING;

  @Column(nullable=false, precision=12, scale=2)
  private BigDecimal amount;

  @Column(length=3, nullable=false)
  private String currency;

  @Column(length=64)
  private String paymentChannel; // e.g., ALIPAY, WECHAT, STRIPE

  private OffsetDateTime createdAt;
  private OffsetDateTime paidAt;
  private OffsetDateTime deliveredAt;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<OrderItem> items = new ArrayList<>();

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<OrderDelivery> deliveries = new ArrayList<>();
}
