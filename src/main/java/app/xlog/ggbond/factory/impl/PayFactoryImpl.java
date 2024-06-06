package app.xlog.ggbond.factory.impl;

import app.xlog.ggbond.factory.PayFactory;
import app.xlog.ggbond.payments.ScanCode.ScanCodeService;
import app.xlog.ggbond.payments.ymqH5.YmqH5Service;
import app.xlog.ggbond.payments.ymqQRCode.YmqQRCodeService;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class PayFactoryImpl implements PayFactory {

    private final String merchantKey;

    public PayFactoryImpl(String merchantKey) {
        this.merchantKey = merchantKey;
    }

    // 生成ScanCodeService对象，最终会返回一个支付链接，打开之后可以扫码支付
    @Override
    public ScanCodeService createScanCodeService() throws NoSuchAlgorithmException, KeyManagementException {
        return new ScanCodeService(merchantKey);
    }

    // 生成YmqScanCodeService对象，最终会返回一个跳转支付链接，必需在微信或支付宝打开
    @Override
    public YmqH5Service createYmqH5Service() throws NoSuchAlgorithmException, KeyManagementException {
        return new YmqH5Service(merchantKey);
    }

    @Override
    public YmqQRCodeService createYmqQRCodeService() throws NoSuchAlgorithmException, KeyManagementException {
        return new YmqQRCodeService(merchantKey);
    }
}
