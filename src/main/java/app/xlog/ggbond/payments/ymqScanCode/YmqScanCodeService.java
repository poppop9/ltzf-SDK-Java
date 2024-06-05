package app.xlog.ggbond.payments.ymqScanCode;

import app.xlog.ggbond.payments.ymqScanCode.model.prePayRequest;
import app.xlog.ggbond.payments.ymqScanCode.model.prePayResponse;
import app.xlog.ggbond.utils.NoVerifySSLClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class YmqScanCodeService {
    private IYmqScanCodeApi ymqScanCodeApi;
    private String merchantKey;

    public YmqScanCodeService(String merchantKey) throws NoSuchAlgorithmException, KeyManagementException {
        this.merchantKey = merchantKey;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://open.yunmianqian.com/")
                .client(NoVerifySSLClient.getClient())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        this.ymqScanCodeApi = retrofit.create(IYmqScanCodeApi.class);
    }

    public void prePay(prePayRequest prePayRequest) throws IOException {
        Call<prePayResponse> call = ymqScanCodeApi.prePayment(
                prePayRequest.getAppId(),
                prePayRequest.getOutOrderSn(),
                prePayRequest.getName(),
                prePayRequest.getPayWay(),
                prePayRequest.getPrice(),
                prePayRequest.getAttach(),
                prePayRequest.getNotifyUrl(),
                prePayRequest.createSign(merchantKey)
        );

        prePayResponse payResponse = call.execute().body();
        System.out.println(payResponse);
    }
}
