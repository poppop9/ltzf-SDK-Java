package app.xlog.ggbond;

import app.xlog.ggbond.payments.ymqH5.model.prePayRequest;
import app.xlog.ggbond.payments.ymqH5.model.prePayResponse;
import app.xlog.ggbond.factory.PayFactory;
import app.xlog.ggbond.factory.impl.PayFactoryImpl;
import app.xlog.ggbond.payments.ymqQRCode.YmqQRCodeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
public class YmqQRCodeServiceTest {
    @Test
    public void testPrePayment() throws NoSuchAlgorithmException, KeyManagementException, IOException {
        PayFactory payFactory = new PayFactoryImpl("cbd4cc4d1e95abcd907354f7b017026e");
        YmqQRCodeService ymqQRCodeService = payFactory.createYmqQRCodeService();

        prePayRequest prePayRequest = new prePayRequest();
        prePayRequest.setAppId("2406058751");
        prePayRequest.setOutOrderSn("123456");
        prePayRequest.setName("test");
        prePayRequest.setPayWay("alipay");
        prePayRequest.setPrice("100");
        prePayRequest.setAttach("test");
        prePayRequest.setNotifyUrl("https://example.com");

        String QRCodeBase64 = ymqQRCodeService.prepay(prePayRequest);
        System.out.println(QRCodeBase64);
    }
}
