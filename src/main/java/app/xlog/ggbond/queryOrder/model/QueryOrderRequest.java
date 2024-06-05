package app.xlog.ggbond.queryOrder.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QueryOrderRequest {
    private final String act = "order";
    private int pid;
    private String key;
    private String tradeNo;
//    private String outTradeNo;
}
