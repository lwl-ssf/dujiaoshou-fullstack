\1
/**
 * AuthService
 * 模块：业务接口/服务（Service）
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import com.example.dujiaoshou.domain.*;
import com.example.dujiaoshou.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Transactional
public class AuthService {

  private final UserAccountRepository users;
  private final UserRoleRepository userRoles;
  private final RolePermissionRepository rolePerms;

  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  public UserAccount initAdminIfNeeded() {
    return users.findByUsername("admin").orElseGet(() -> {
      UserAccount u = UserAccount.builder()
          .username("admin")
          .passwordHash(encoder.encode("admin123"))
          .role("ADMIN")
          .build();
      return users.save(u);
    });
  }

  public boolean checkPassword(UserAccount u, String raw){ return encoder.matches(raw, u.getPasswordHash()); }

  public List<String> authorities(UserAccount u){
    List<UserRole> rs = userRoles.findByUser(u);
    return rs.stream()
        .flatMap(ur -> rolePerms.findByRole(ur.getRole()).stream())
        .map(rp -> "PERM_" + rp.getPermission().getCode())
        .distinct()
        .collect(Collectors.toList());
  }
}
