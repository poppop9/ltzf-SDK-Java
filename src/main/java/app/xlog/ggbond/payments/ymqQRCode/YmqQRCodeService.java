package app.xlog.ggbond.payments.ymqQRCode;

import app.xlog.ggbond.payments.ymqH5.model.prePayRequest;
import app.xlog.ggbond.payments.ymqH5.model.prePayResponse;
import app.xlog.ggbond.factory.PayFactory;
import app.xlog.ggbond.factory.impl.PayFactoryImpl;
import app.xlog.ggbond.payments.ymqH5.YmqH5Service;
import app.xlog.ggbond.utils.URLtoBase64;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class YmqQRCodeService {
    private final String merchantKey;

    public YmqQRCodeService(String merchantKey) {
        this.merchantKey = merchantKey;
    }

    public String prepay(prePayRequest prePayRequest) throws NoSuchAlgorithmException, KeyManagementException, IOException {
        PayFactory payFactory = new PayFactoryImpl(merchantKey);
        YmqH5Service ymqH5Service = payFactory.createYmqH5Service();

        prePayResponse payResponse = ymqH5Service.prePay(prePayRequest);
        String qr = payResponse.getData().getQr();

        return URLtoBase64.urlToBase64(qr);
    }
}
