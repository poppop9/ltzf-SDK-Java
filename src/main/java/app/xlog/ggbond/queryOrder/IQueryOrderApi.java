package app.xlog.ggbond.queryOrder;

import app.xlog.ggbond.queryOrder.model.QueryOrderResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IQueryOrderApi {
    @GET("api.php")
    Call<QueryOrderResponse> queryOrder(@Query("act") String act,
                                        @Query("pid") int pid,
                                        @Query("key") String key,
                                        @Query("trade_no") String tradeNo);
}
