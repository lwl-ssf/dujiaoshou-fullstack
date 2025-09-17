\1
/**
 * AdminAuthFilter
 * 模块：安全/鉴权
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AdminAuthFilter extends OncePerRequestFilter {

  @Value("${app.admin.token}")
  private String adminToken;

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String path = request.getRequestURI();
    return !path.startsWith("/api/admin/");
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String token = request.getHeader("X-Admin-Token");
    if (token == null || token.isEmpty() || !token.equals(adminToken)) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write("{"message":"Unauthorized"}");
      return;
    }
    filterChain.doFilter(request, response);
  }
}
