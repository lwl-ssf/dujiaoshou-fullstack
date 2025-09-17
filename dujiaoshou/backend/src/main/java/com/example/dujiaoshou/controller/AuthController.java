\1
/**
 * AuthController
 * 模块：前台/公共控制器
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import com.example.dujiaoshou.entity.*;
import com.example.dujiaoshou.repository.UserAccountRepository;
import com.example.dujiaoshou.repository.RoleRepository;
import com.example.dujiaoshou.repository.PermissionRepository;
import com.example.dujiaoshou.repository.RolePermissionRepository;
import com.example.dujiaoshou.security.JwtUtil;
import com.example.dujiaoshou.service.AuthService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController @RequestMapping("/api/auth") @RequiredArgsConstructor
public class AuthController {

  private final UserAccountRepository users;
  private final RoleRepository roles;
  private final PermissionRepository perms;
  private final RolePermissionRepository rolePerms;
  private final AuthService authService;

  @Value("${app.jwt.secret}")
  private String jwtSecret;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginReq req){
    authService.initAdminIfNeeded();
    UserAccount u = users.findByUsername(req.getUsername()).orElse(null);
    if (u == null) return ResponseEntity.status(401).body(Map.of("message","用户不存在"));
    if (!authService.checkPassword(u, req.getPassword())) return ResponseEntity.status(401).body(Map.of("message","密码错误"));
    List<String> authorities = authService.authorities(u);
    String token = JwtUtil.generate(u.getUsername(), Map.of("authorities", authorities), jwtSecret, 86400000);
    return ResponseEntity.ok(Map.of("token", token, "authorities", authorities, "username", u.getUsername()));
  }

  @PostMapping("/seed")
  public Map<String, Object> seed(){
    // 初始化基础权限与角色，并将 admin 绑定 ADMIN 角色
    Permission p1 = perms.findByCode("PRODUCT_MANAGE").orElseGet(() -> perms.save(Permission.builder().code("PRODUCT_MANAGE").description("商品管理").build()));
    Permission p2 = perms.findByCode("ORDER_MANAGE").orElseGet(() -> perms.save(Permission.builder().code("ORDER_MANAGE").description("订单管理").build()));
    Permission p3 = perms.findByCode("INVENTORY_MANAGE").orElseGet(() -> perms.save(Permission.builder().code("INVENTORY_MANAGE").description("库存管理").build()));
    Role admin = roles.findByName("ADMIN").orElseGet(() -> roles.save(Role.builder().name("ADMIN").description("超级管理员").build()));
    Role staff = roles.findByName("STAFF").orElseGet(() -> roles.save(Role.builder().name("STAFF").description("运营人员").build()));
    // 绑定权限
    rolePerms.save(RolePermission.builder().role(admin).permission(p1).build());
    rolePerms.save(RolePermission.builder().role(admin).permission(p2).build());
    rolePerms.save(RolePermission.builder().role(admin).permission(p3).build());
    rolePerms.save(RolePermission.builder().role(staff).permission(p2).build());
    rolePerms.save(RolePermission.builder().role(staff).permission(p3).build());
    authService.initAdminIfNeeded();
    return Map.of("ok", true);
  }

  @Data
  public static class LoginReq {
    private String username;
    private String password;
  }
}
