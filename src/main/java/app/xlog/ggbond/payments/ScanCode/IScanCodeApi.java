package app.xlog.ggbond.payments.ScanCode;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IScanCodeApi {
    // 定义了一个POST请求，请求路径为mapi.php，以及各种传入的参数
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("mapi.php")
    Call<Object> advancePayment(@Field("pid") String pid,
                                @Field("type") String type,
                                @Field("out_trade_no") String outTradeNo,
                                @Field("notify_url") String notifyUrl,
                                @Field("name") String name,
                                @Field("money") String money,
                                @Field("clientip") String clientip,
                                @Field("sign") String sign,
                                @Field("sign_type") String signType);

}
