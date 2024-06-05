package app.xlog.ggbond.queryOrder.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryOrderResponse {
    private int code;
    private String msg;

    // 系统订单号
    @JsonProperty("trade_no")
    private String tradeNo;

    // 商户订单号
    @JsonProperty("out_trade_no")
    private String outTradeNo;

    @JsonProperty("api_trade_no")
    private String apiTradeNo;

    private String type;
    private int pid;
    private String addtime;
    private String endtime;
    private String name;
    private String money;
    private int status;
    private String param;
    private String buyer;
    private String payurl;
}