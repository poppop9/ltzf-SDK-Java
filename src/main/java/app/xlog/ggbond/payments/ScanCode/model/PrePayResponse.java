package app.xlog.ggbond.payments.ScanCode.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

// 请求之后，返回的 JSON 数据会映射到这个类中
@Data
public class PrePayResponse {
    private int code;
    private String msg;
    @JsonProperty("trade_no")
    private String tradeNo;
    @JsonProperty("payurl")
    private String payUrl;
}
