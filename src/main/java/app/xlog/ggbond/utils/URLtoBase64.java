package app.xlog.ggbond.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.extra.qrcode.QrCodeUtil;

public class URLtoBase64 {
    public static String urlToBase64(String url) {
        byte[] imageBytes = QrCodeUtil.generatePng(url, 300, 300);
        return Base64.encode(imageBytes);
    }
}
