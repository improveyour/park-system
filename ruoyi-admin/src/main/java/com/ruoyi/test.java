package com.ruoyi;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;

import java.text.ParseException;

public class test {
    public static void main(String[] args) throws ParseException {
        QrCodeUtil.generate("12312", 400, 400, FileUtil.file("C:\\Users\\Administrator\\Desktop\\pay.png"));

    }


}
