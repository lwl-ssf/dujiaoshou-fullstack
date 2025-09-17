\1
/**
 * OrderDelivery
 * 模块：领域实体（JPA）
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderDelivery {
    private Long id;

    private Order order;

  /** For cardDelivery, this is the delivered code */
    private String content;
}
