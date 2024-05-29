package app.xlog.ggbond.payments.ScanCode;

import app.xlog.ggbond.payments.ScanCode.model.PrePayRequest;
import app.xlog.ggbond.payments.ScanCode.model.PrePayResponse;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class ScanCodeService {
    private IScanCodeApi scanCodeApi;
    private String merchantKey;

    public ScanCodeService(IScanCodeApi scanCodeApi, String merchantKey) {
        this.scanCodeApi = scanCodeApi;
        this.merchantKey = merchantKey;
    }

    public PrePayResponse prePay(PrePayRequest prePayRequest) throws IOException {
        Call<PrePayResponse> call = scanCodeApi.prePayment(
                prePayRequest.getPid(),
                prePayRequest.getType(),
                prePayRequest.getOutTradeNo(),
                prePayRequest.getNotifyUrl(),
                prePayRequest.getName(),
                prePayRequest.getMoney(),
                prePayRequest.getClientIp(),
                prePayRequest.createSign(merchantKey),
                prePayRequest.getSignType()
        );

        Response<PrePayResponse> execute = call.execute();
        return execute.body();
    }
}
