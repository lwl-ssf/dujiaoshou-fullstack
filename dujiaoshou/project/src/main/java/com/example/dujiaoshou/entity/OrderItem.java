\1
/**
 * OrderItem
 * 模块：领域实体（JPA）
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import lombok.*;
import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderItem {
    private Long id;

    private Order order;

    private Product product;

  private Integer quantity;

    private BigDecimal unitPrice;
}
