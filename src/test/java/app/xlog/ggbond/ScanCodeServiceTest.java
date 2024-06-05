package app.xlog.ggbond;

import app.xlog.ggbond.factory.impl.PayFactoryImpl;
import app.xlog.ggbond.payments.ScanCode.ScanCodeService;
import app.xlog.ggbond.payments.ScanCode.model.PrePayRequest;
import app.xlog.ggbond.payments.ScanCode.model.PrePayResponse;
import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
public class ScanCodeServiceTest {
    @Test
    public void testScanCodeService() throws NoSuchAlgorithmException, KeyManagementException, IOException {
        ScanCodeService scanCodeService2 = new PayFactoryImpl("9i2fDZNa3tJT3v29NIzmdKF9IvjbKBmD").createScanCodeService2();
        PrePayRequest prePayRequest = new PrePayRequest();
        prePayRequest.setPid("5241");
        prePayRequest.setType("alipay");
        prePayRequest.setOutTradeNo(RandomUtil.randomString(8));
        prePayRequest.setNotifyUrl("https://www.baidu.com");
        prePayRequest.setReturnUrl("https://www.baidu.com");
        prePayRequest.setName("测试商品");
        prePayRequest.setMoney("1.00");
        prePayRequest.setClientip("10.246.110.100");
        prePayRequest.setSignType("MD5");

        PrePayResponse prePayResponse = scanCodeService2.prePay(prePayRequest);
        System.out.println(prePayResponse);
    }
}
