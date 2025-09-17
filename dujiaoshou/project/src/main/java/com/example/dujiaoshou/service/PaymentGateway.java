\1
/**
 * PaymentGateway.java
 * 模块：业务接口/服务（Service）
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import java.math.BigDecimal;

public interface PaymentGateway {
  /** Create a payment URL or QR code payload for redirection/scan */
  String createPayment(String orderSn, BigDecimal amount, String currency);
  /** Verify callback/notification signature */
  boolean verifySignature(String payload, String signature);
  /** Payment channel id, e.g. ALIPAY/WECHAT/STRIPE */
  String channel();
}
