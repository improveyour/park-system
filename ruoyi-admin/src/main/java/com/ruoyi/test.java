package com.ruoyi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //24小时制

        //2024-05-07 14:34:07
        //1715063647

        //日期格式转时间戳
        Date date1 = simpleDateFormat.parse("2024-05-07 14:34:07");
        long time1 = date1.getTime();
        System.out.println("日期格式转时间戳：" + time1 / 1000);
        System.out.println(System.currentTimeMillis() / 1000 - time1 / 1000);

    }


}
