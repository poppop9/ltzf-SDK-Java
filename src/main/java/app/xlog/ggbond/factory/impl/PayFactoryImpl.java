package app.xlog.ggbond.factory.impl;

import app.xlog.ggbond.factory.PayFactory;
import app.xlog.ggbond.payments.ScanCode.IScanCodeApi;
import app.xlog.ggbond.payments.ScanCode.ScanCodeService;
import app.xlog.ggbond.utils.NoVerifySSLClient;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class PayFactoryImpl implements PayFactory {
    private final String merchantKey;

    // 构造PayFactoryImpl时，传入商户密钥
    public PayFactoryImpl(String merchantKey) {
        this.merchantKey = merchantKey;
    }

    // 创建ScanCodeService产品
    @Override
    public ScanCodeService createScanCodeService() throws NoSuchAlgorithmException, KeyManagementException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pay.gm-pay.net/")
                .client(NoVerifySSLClient.getClient())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        IScanCodeApi scanCodeApi = retrofit.create(IScanCodeApi.class);

        return new ScanCodeService(scanCodeApi, merchantKey);
    }
}
