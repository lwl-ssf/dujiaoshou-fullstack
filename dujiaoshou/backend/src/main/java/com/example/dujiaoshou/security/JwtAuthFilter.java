\1
/**
 * JwtAuthFilter
 * 模块：安全/鉴权
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

  @Value("${app.jwt.secret}")
  private String secret;

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String path = request.getRequestURI();
    return path.startsWith("/api/auth/") || path.startsWith("/api/pay/") || path.startsWith("/api/products") || path.equals("/");
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String header = request.getHeader("Authorization");
    if (header != null && header.startsWith("Bearer ")) {
      try {
        String token = header.substring(7);
        Claims claims = JwtUtil.parse(token, secret);
        String username = claims.getSubject();
        List<String> authorities = (List<String>) claims.get("authorities");
        UsernamePasswordAuthenticationToken auth =
            new UsernamePasswordAuthenticationToken(username, null,
                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(auth);
      } catch (Exception e) {
        // ignore; unauthenticated
      }
    }
    filterChain.doFilter(request, response);
  }
}
