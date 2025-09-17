\1
/**
 * InventoryCard
 * 模块：领域实体（JPA）
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import lombok.*;

  indexes = {               @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class InventoryCard {
  public enum Status { AVAILABLE, RESERVED, SOLD, INVALID }
    private Long id;

    private Product product;

    private String code;

    private Status status = Status.AVAILABLE;

  private Long orderId;
}
