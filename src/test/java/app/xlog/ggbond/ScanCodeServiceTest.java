package app.xlog.ggbond;

import app.xlog.ggbond.factory.PayFactory;
import app.xlog.ggbond.factory.impl.PayFactoryImpl;
import app.xlog.ggbond.payments.ScanCode.ScanCodeService;
import app.xlog.ggbond.payments.ScanCode.model.PrePayRequest;
import app.xlog.ggbond.payments.ScanCode.model.PrePayResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
public class ScanCodeServiceTest {
    @Test
    public void testScanCodeService() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        PayFactory payFactory = new PayFactoryImpl("Uxy57Z1Y1JK2y92VjVXV27a717j9V17U");
        ScanCodeService scanCodeService = payFactory.createScanCodeService();

        PrePayRequest prePayRequest = new PrePayRequest();
        prePayRequest.setPid("2769");
        prePayRequest.setType("alipay");
        prePayRequest.setOutTradeNo("zsbz20240529-2");
        prePayRequest.setNotifyUrl("https://www.baidu.com");
        prePayRequest.setName("测试商品");
        prePayRequest.setMoney("0.3");
        prePayRequest.setClientIp("169.254.87.226");
        prePayRequest.setSignType("MD5");

        PrePayResponse prePayResponse = scanCodeService.prePay(prePayRequest);
        System.out.println(prePayResponse);
    }
}
