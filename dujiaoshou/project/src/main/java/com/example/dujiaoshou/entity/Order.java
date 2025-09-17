\1
/**
 * Order
 * 模块：领域实体（JPA）
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import lombok.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Order {
  public enum Status { PENDING, PAID, DELIVERED, EXPIRED, CANCELLED }

    private Long id;

    private String sn;

    private Status status = Status.PENDING;

    private BigDecimal amount;

    private String currency;

    private String paymentChannel; // e.g., ALIPAY, WECHAT, STRIPE

  private OffsetDateTime createdAt;
  private OffsetDateTime paidAt;
  private OffsetDateTime deliveredAt;

    private List<OrderItem> items = new ArrayList<>();

    private List<OrderDelivery> deliveries = new ArrayList<>();
}
