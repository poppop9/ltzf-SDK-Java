package app.xlog.ggbond.payments.ymqH5.model;

import cn.hutool.crypto.SecureUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class prePayRequest {
    @JsonProperty("app_id")
    private String appId; // 应用ID
    @JsonProperty("out_order_sn")
    private String outOrderSn; // 商家订单编号
    private String name; // 订单销售商品名称
    @JsonProperty("pay_way")
    private String payWay; // 支付方式
    private String price; // 订单价格（单位为分）
    private String attach; // 开发者自定义数据
    @JsonProperty("notify_url")
    private String notifyUrl; // 支付成功后的回调地址

    public String createSign(String MerchantKey) {
        String signString = getAppId() +
                getOutOrderSn() +
                getName() +
                getPayWay() +
                getPrice() +
                getAttach() +
                getNotifyUrl() +
                MerchantKey;

        return SecureUtil.md5(signString);
    }
}
