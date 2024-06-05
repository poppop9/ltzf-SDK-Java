package app.xlog.ggbond.payments.ymqScanCode;

import app.xlog.ggbond.payments.ymqScanCode.model.prePayResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IYmqScanCodeApi {
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("api/pay")
    Call<prePayResponse> prePayment(
            @Field("app_id") String appId,
            @Field("out_order_sn") String outOrderSn,
            @Field("name") String name,
            @Field("pay_way") String payWay,
            @Field("price") String price,
            @Field("attach") String attach,
            @Field("notify_url") String notifyUrl,
            @Field("sign") String sign
    );
}
