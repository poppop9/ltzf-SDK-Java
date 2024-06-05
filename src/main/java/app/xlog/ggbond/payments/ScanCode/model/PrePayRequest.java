package app.xlog.ggbond.payments.ScanCode.model;

import cn.hutool.crypto.SecureUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

// 这里是对方传过来的JSON数据对象，所以没有sign，对方不会去计算签名，计算签名这一步应该由我们这个SDK来做
@Data
public class PrePayRequest {
    private String pid;
    private String type;
    @JsonProperty("out_trade_no")
    private String outTradeNo;
    @JsonProperty("notify_url")
    private String notifyUrl;
    @JsonProperty("return_url")
    private String returnUrl;
    private String name;
    private String money;
    private String clientip;
    private String sign;
    @JsonProperty("sign_type")
    private String signType;

    // 签名不可能用户来传，用户只用传入商户密钥，我们来生成签名
    // 而且由于先执行构造方法，再将json数据映射到这个类，所以签名算法不能放在构造方法中
    // 我们只能自己写一个方法来生成签名
    public String createSign(String MerchantKey) {
        // 签名算法字段的顺序不能错，而且signType不参与签名
        String sign = "clientip=" + getClientip() +
                "&money=" + getMoney() +
                "&name=" + getName() +
                "&notify_url=" + getNotifyUrl() +
                "&out_trade_no=" + getOutTradeNo() +
                "&pid=" + getPid() +
                "&return_url=" + getReturnUrl() +
                "&type=" + getType() +
                MerchantKey;

        return SecureUtil.md5(sign);
    }
}
