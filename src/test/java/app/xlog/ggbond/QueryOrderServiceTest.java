package app.xlog.ggbond;

import app.xlog.ggbond.queryOrder.QueryOrderService;
import app.xlog.ggbond.queryOrder.model.QueryOrderRequest;
import app.xlog.ggbond.queryOrder.model.QueryOrderResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
public class QueryOrderServiceTest {
    @Test
    public void testQueryOrderService() throws NoSuchAlgorithmException, KeyManagementException, IOException {
        QueryOrderRequest request = new QueryOrderRequest(
                2769,
                "Uxy57Z1Y1JK2y92VjVXV27a717j9V17U",
                "2024060310025336284"
        );

        QueryOrderService queryOrderService = new QueryOrderService();
        QueryOrderResponse response = queryOrderService.queryOrder(request);

        System.out.println(response);
    }
}
