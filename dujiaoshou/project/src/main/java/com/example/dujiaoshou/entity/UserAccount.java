\1
/**
 * UserAccount
 * 模块：领域实体（JPA）
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserAccount {
    private Long id;

    private String username;

    private String passwordHash;

    private String role; // ADMIN / USER
}
