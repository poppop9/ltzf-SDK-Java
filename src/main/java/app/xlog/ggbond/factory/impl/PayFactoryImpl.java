package app.xlog.ggbond.factory.impl;

import app.xlog.ggbond.factory.PayFactory;
import app.xlog.ggbond.payments.ScanCode.ScanCodeService;
import app.xlog.ggbond.payments.ymqScanCode.YmqScanCodeService;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class PayFactoryImpl implements PayFactory {

    private final String merchantKey;

    public PayFactoryImpl(String merchantKey) {
        this.merchantKey = merchantKey;
    }

    // 生成ScanCodeService对象
    @Override
    public ScanCodeService createScanCodeService() throws NoSuchAlgorithmException, KeyManagementException {
        return new ScanCodeService(merchantKey);
    }

    // 生成YmqScanCodeService对象
    @Override
    public YmqScanCodeService createYmqScanCodeService() throws NoSuchAlgorithmException, KeyManagementException {
        return new YmqScanCodeService(merchantKey);
    }
}
