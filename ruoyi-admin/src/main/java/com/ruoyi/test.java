package com.ruoyi;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class test {
    public static void main(String[] args) throws ParseException, IOException {
        //python E:\graduate\Chinese_license_plate_detection_recognition-main\java_demo.py E:/graduate/park-system/ruoyi-admin/src/main/resources/plate_img/yueA636YK.png

//        try {
//            String[] args1 = new String[]{"python", "E:\\graduate\\Chinese_license_plate_detection_recognition-main\\java_demo.py", "--image_path", "E:/graduate/park-system/ruoyi-admin/src/main/resources/plate_img/d.png"};//第二个为python脚本所在位置，后面的为所传参数（得是字符串类型）
//            Process proc = Runtime.getRuntime().exec(args1);// 执行py文件
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "gb2312"));//解决中文乱码，参数可传中文
//            String line = null;
//            int i = 0;
//            while ((line = in.readLine()) != null) {
//                if (i == 0) {
//                    i++;
//                    continue;
//                }
//                System.out.println(line);
//            }
//            in.close();
//            proc.waitFor();
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }


        String url = "http://127.0.0.1:8000/http?flag=true&fileName=j.png";
        HttpGet httpGet = new HttpGet(url);

        httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
//        httpClient实例化
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 执行请求并获取返回
        CloseableHttpResponse response = httpClient.execute(httpGet);

        HttpEntity entity = response.getEntity();
        System.out.println("返回状态码：" + response.getStatusLine());

        // 显示结果
        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
        String line = null;
        StringBuffer responseSB = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            responseSB.append(line);
        }
        System.out.println("返回消息：" + responseSB);
        reader.close();

        httpClient.close();


    }
}
