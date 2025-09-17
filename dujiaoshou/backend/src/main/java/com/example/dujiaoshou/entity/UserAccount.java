\1
/**
 * UserAccount
 * 模块：领域实体（JPA）
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="users")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserAccount {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false, unique=true, length=64)
  private String username;

  @Column(nullable=false)
  private String passwordHash;

  @Column(nullable=false, length=16)
  private String role; // ADMIN / USER
}
