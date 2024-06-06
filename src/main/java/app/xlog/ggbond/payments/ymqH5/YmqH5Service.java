package app.xlog.ggbond.payments.ymqH5;

import app.xlog.ggbond.payments.ymqH5.model.prePayRequest;
import app.xlog.ggbond.payments.ymqH5.model.prePayResponse;
import app.xlog.ggbond.utils.NoVerifySSLClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class YmqH5Service {
    private IYmqH5Api ymqScanCodeApi;
    private String merchantKey;

    public YmqH5Service(String merchantKey) throws NoSuchAlgorithmException, KeyManagementException {
        this.merchantKey = merchantKey;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://open.yunmianqian.com/")
                .client(NoVerifySSLClient.getClient())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        this.ymqScanCodeApi = retrofit.create(IYmqH5Api.class);
    }

    public prePayResponse prePay(prePayRequest prePayRequest) throws IOException {
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
        return payResponse;
    }
}
