\1
/**
 * UserRole
 * 模块：领域实体（JPA）
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="user_roles", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","role_id"}))
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserRole {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(optional=false) @JoinColumn(name="user_id") private UserAccount user;
  @ManyToOne(optional=false) @JoinColumn(name="role_id") private Role role;
}
