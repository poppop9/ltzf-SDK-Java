package app.xlog.ggbond;

import app.xlog.ggbond.payments.ymqScanCode.YmqScanCodeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import app.xlog.ggbond.payments.ymqScanCode.model.prePayRequest;


import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
public class YmqScanCodeServiceTest {
    @Test
    public void testPrePayment() throws NoSuchAlgorithmException, KeyManagementException, IOException {
        prePayRequest prePayRequest = new prePayRequest();
        prePayRequest.setAppId("2406058751");
        prePayRequest.setOutOrderSn("123456");
        prePayRequest.setName("test");
        prePayRequest.setPayWay("alipay");
        prePayRequest.setPrice("100");
        prePayRequest.setAttach("test");
        prePayRequest.setNotifyUrl("https://example.com");

        YmqScanCodeService ymqScanCodeService = new YmqScanCodeService("cbd4cc4d1e95abcd907354f7b017026e");
        ymqScanCodeService.prePay(prePayRequest);
    }
}
