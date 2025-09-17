\1
/**
 * StripeGateway
 * 模块：业务接口/服务（Service）
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import com.example.dujiaoshou.service.payment.PaymentGateway;
import java.math.BigDecimal;

public class StripeGateway implements PaymentGateway {
  @Override public String createPayment(String orderSn, BigDecimal amount, String currency){
    return "https://checkout.stripe.example/session?sn="+orderSn;
  }
  @Override public boolean verifySignature(String payload, String signature){ return true; }
  @Override public String channel(){ return "STRIPE"; }
}
