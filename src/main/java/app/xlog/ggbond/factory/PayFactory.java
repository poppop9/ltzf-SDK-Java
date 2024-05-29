package app.xlog.ggbond.factory;

import app.xlog.ggbond.payments.ScanCode.ScanCodeService;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public interface PayFactory {
    ScanCodeService createScanCodeService() throws NoSuchAlgorithmException, KeyManagementException;
}
