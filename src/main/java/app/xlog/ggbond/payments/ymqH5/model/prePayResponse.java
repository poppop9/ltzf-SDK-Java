package app.xlog.ggbond.payments.ymqH5.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class prePayResponse {
    private int code; // 响应状态码
    private String msg; // 响应消息
    private OrderData data; // 订单数据

    @Data
    public static class OrderData {
        @JsonProperty("order_sn")
        private String orderSn; // 订单编号
        @JsonProperty("out_order_sn")
        private String outOrderSn; // 商家订单编号
        @JsonProperty("pay_way")
        private String payWay; // 支付方式
        @JsonProperty("price")
        private int price; // 订单价格
        @JsonProperty("qr")
        private String qr; // 二维码链接
        @JsonProperty("qr_type")
        private String qrType; // 二维码类型
        @JsonProperty("qr_price")
        private int qrPrice; // 二维码价格
        @JsonProperty("pay_price")
        private int payPrice; // 实际支付价格
        @JsonProperty("expire_in")
        private int expireIn; // 二维码有效期（秒）
        @JsonProperty("expire_at")
        private String expireAt; // 二维码过期时间
        @JsonProperty("cloud_status")
        private String cloudStatus; // 云端状态
        @JsonProperty("server_time")
        private String serverTime; // 服务器时间
    }
}
