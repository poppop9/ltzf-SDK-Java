package app.xlog.ggbond;


import app.xlog.ggbond.utils.SignUtils;
import cn.hutool.crypto.SecureUtil;
import okhttp3.*;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

/**
 * Unit test for simple App.
 */

@SpringBootTest
public class AppTest {
    public AppTest() throws NoSuchAlgorithmException, KeyManagementException {
    }

    // 创建一个无需验证SSL的OkHttpClient
    public static OkHttpClient getClient() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustManagers = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }
        };

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustManagers, new SecureRandom());

        final OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustManagers[0])
                .hostnameVerifier((s, sslSession) -> true)
                .build();

        return client;
    }

    private final OkHttpClient client = getClient();

    // 易支付获取签名信息
    @Test
    public void testYZFSign() {
        String sign = "clientip=169.254.87.226&money=0.3&name=测试一下&notify_url=https://www.weixin.qq.com/wxpay/pay.php&out_trade_no=zsbz20240528&pid=2769&type=alipay" + "Uxy57Z1Y1JK2y92VjVXV27a717j9V17U";
        String signMD5 = SecureUtil.md5(sign);
        System.out.println(signMD5);
        // 8d5ee6d7c8f4673f50aac36690414d69
    }

    // 易支付测试扫码支付
    @Test
    public void testYZFScanCodePayment() throws IOException {
        RequestBody requestBody = new FormBody.Builder()
                .add("pid", "2769")
                .add("type", "alipay")
                .add("out_trade_no", "zsbz20240528")
                .add("notify_url", "https://www.weixin.qq.com/wxpay/pay.php")
                .add("name", "测试一下")
                .add("money", "0.3")
                .add("clientip", "169.254.87.226")
                .add("sign", "8d5ee6d7c8f4673f50aac36690414d69")
                .add("sign_type", "MD5")
                .build();

        Request request = new Request.Builder()
                .url("https://pay.gm-pay.net/mapi.php")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            System.out.println(response.body().string());
        }
    }

    // 蓝兔支付获取签名信息
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

    // 蓝兔支付测试扫码支付
    @Test
    public void testLTZFScanCodePayment() throws IOException {
        RequestBody body = new FormBody.Builder()
                .add("mch_id", "1673424392")
                .add("out_trade_no", "zsbz20240526")
                .add("total_fee", "0.01")
                .add("body", "测试一下")
                .add("timestamp", "1716902215")
                .add("notify_url", "https://www.weixin.qq.com/wxpay/pay.php")
                .add("sign", "E52C71F495E03B66DCCABBE1DA3FAC9E")
                .build();

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
