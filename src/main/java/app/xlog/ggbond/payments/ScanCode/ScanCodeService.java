package app.xlog.ggbond.payments.ScanCode;

import app.xlog.ggbond.payments.ScanCode.model.PrePayRequest;
import app.xlog.ggbond.payments.ScanCode.model.PrePayResponse;
import app.xlog.ggbond.utils.NoVerifySSLClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class ScanCodeService {
    private final String merchantKey;
    private final IScanCodeApi scanCodeApi;

    // 构造方法：传入密钥，实例化retrofit对象
    public ScanCodeService(String merchantKey) throws NoSuchAlgorithmException, KeyManagementException {
        this.merchantKey = merchantKey;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://open.yunmianqian.com/")
                .client(NoVerifySSLClient.getClient())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        this.scanCodeApi = retrofit.create(IScanCodeApi.class);
    }

    public PrePayResponse prePay(PrePayRequest prePayRequest) throws IOException {
        Call<PrePayResponse> call = this.scanCodeApi.prePayment(
                prePayRequest.getPid(),
                prePayRequest.getType(),
                prePayRequest.getOutTradeNo(),
                prePayRequest.getNotifyUrl(),
                prePayRequest.getReturnUrl(),
                prePayRequest.getName(),
                prePayRequest.getMoney(),
                prePayRequest.getClientip(),
                prePayRequest.createSign(merchantKey),
                prePayRequest.getSignType()
        );

        Response<PrePayResponse> response = call.execute();
        PrePayResponse prePayResponse = response.body();

        return prePayResponse;
    }
}
