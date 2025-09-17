\1
/**
 * AlipayGateway
 * 模块：业务接口/服务（Service）
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import com.example.dujiaoshou.service.payment.PaymentGateway;
import java.math.BigDecimal;

/** Stub for Alipay channel - replace with real SDK integration */
public class AlipayGateway implements PaymentGateway {
  @Override public String createPayment(String orderSn, BigDecimal amount, String currency){
    return "ALIPAY_PAY_URL?sn=" + orderSn + "&amount=" + amount + "&currency=" + currency;
  }
  @Override public boolean verifySignature(String payload, String signature){
    return true;
  }
  @Override public String channel(){ return "ALIPAY"; }
}
