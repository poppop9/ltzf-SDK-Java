package app.xlog.ggbond.queryOrder;

import app.xlog.ggbond.queryOrder.model.QueryOrderRequest;
import app.xlog.ggbond.queryOrder.model.QueryOrderResponse;
import app.xlog.ggbond.utils.NoVerifySSLClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class QueryOrderService {
    private final IQueryOrderApi queryOrderApi;

    public QueryOrderService() throws NoSuchAlgorithmException, KeyManagementException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pay.gm-pay.net/")
                .client(NoVerifySSLClient.getClient())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        this.queryOrderApi = retrofit.create(IQueryOrderApi.class);
    }

    public QueryOrderResponse queryOrder(QueryOrderRequest queryOrderRequest) throws IOException {
        Call<QueryOrderResponse> call = queryOrderApi.queryOrder(
                queryOrderRequest.getAct(),
                queryOrderRequest.getPid(),
                queryOrderRequest.getKey(),
                queryOrderRequest.getTradeNo()
        );

        return call.execute().body();
    }
}
