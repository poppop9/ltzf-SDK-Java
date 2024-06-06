package app.xlog.ggbond.factory;

import app.xlog.ggbond.payments.ScanCode.ScanCodeService;
import app.xlog.ggbond.payments.ymqH5.YmqH5Service;
import app.xlog.ggbond.payments.ymqQRCode.YmqQRCodeService;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public interface PayFactory {
    ScanCodeService createScanCodeService() throws NoSuchAlgorithmException, KeyManagementException;

    YmqH5Service createYmqH5Service() throws NoSuchAlgorithmException, KeyManagementException;

    YmqQRCodeService createYmqQRCodeService() throws NoSuchAlgorithmException, KeyManagementException;
}
