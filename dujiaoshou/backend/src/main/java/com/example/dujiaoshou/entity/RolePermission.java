\1
/**
 * RolePermission
 * 模块：领域实体（JPA）
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="role_permissions", uniqueConstraints = @UniqueConstraint(columnNames = {"role_id","permission_id"}))
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RolePermission {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(optional=false) @JoinColumn(name="role_id") private Role role;
  @ManyToOne(optional=false) @JoinColumn(name="permission_id") private Permission permission;
}
