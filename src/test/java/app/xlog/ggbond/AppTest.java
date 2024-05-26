package app.xlog.ggbond;


import app.xlog.ggbond.utils.SignUtils;
import okhttp3.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Unit test for simple App.
 */

@SpringBootTest
public class AppTest {
    private final OkHttpClient client = new OkHttpClient();

    @Test
    public void testSign() {
        // 获取时间戳
        long timestamp = System.currentTimeMillis() / 1000;
        System.out.println(timestamp);

        Map<String, String> params = new HashMap<>();
        params.put("mch_id", "1673424392");
        params.put("out_trade_no", "zsbz20240526");
        params.put("total_fee", "0.01");
        params.put("body", "测试一下");
        params.put("timestamp", String.valueOf(timestamp));
        params.put("notify_url", "https://www.weixin.qq.com/wxpay/pay.php");

        String sign = SignUtils.createSign(params, "6d3e889f359fcb83d150e9553a9217b9");
        System.out.println(sign);
    }

    @Test
    public void testScanCodePayment() throws IOException {
        RequestBody body = RequestBody.create(
                MediaType.parse("application/x-www-form-urlencoded"),
                "mch_id=1673424392&out_trade_no=zsbz20240526&total_fee=0.01&body=测试一下&timestamp=1716741796&notify_url=https://www.weixin.qq.com/wxpay/pay.php&sign=8D25F57F499E8BF52E5805D8CA86DAF1"
        );

        Request request = new Request.Builder()
                .url("https://api.ltzf.cn/api/wxpay/native")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            System.out.println(response.body().string());
        }
    }
}
