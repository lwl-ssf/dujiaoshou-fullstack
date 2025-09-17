\1
/**
 * Role
 * 模块：领域实体（JPA）
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="roles", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Role {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable=false, length=64) private String name; // ADMIN / STAFF
  @Column(length=255) private String description;
}
