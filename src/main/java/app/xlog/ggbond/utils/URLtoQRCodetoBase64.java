package app.xlog.ggbond.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class URLtoQRCodetoBase64 {
    public static String urlToBase64(String url) {
        byte[] imageBytes = QrCodeUtil.generatePng(url, 300, 300);
        return Base64.encode(imageBytes);
    }
}
