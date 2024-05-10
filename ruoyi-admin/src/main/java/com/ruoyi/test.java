package com.ruoyi;

import java.text.ParseException;

public class test {
    public static void main(String[] args) throws ParseException {

        //python E:\graduate\Chinese_license_plate_detection_recognition-main\java_demo.py E:/graduate/park-system/ruoyi-admin/src/main/resources/plate_img/yueA636YK.png

//        try {
//            String[] args1 = new String[]{"python", "E:\\graduate\\Chinese_license_plate_detection_recognition-main\\java_demo.py", "--image_path", "E:/graduate/park-system/ruoyi-admin/src/main/resources/plate_img/yueA636YK.png"};//第二个为python脚本所在位置，后面的为所传参数（得是字符串类型）
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


    }


}
