package com.ruoyi.plateRec.service.impl;

import com.ruoyi.plateRec.service.IFileService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

@Service
public class FileService implements IFileService {

    // 车牌的存放路径
    private static String localDir = "E:\\graduate\\park-system\\ruoyi-admin\\src\\main\\resources\\plate_img";

    // 将文件保存在服务器目录中
    @Override
    public String saveFile(MultipartFile file) {

        String uuid = UUID.randomUUID().toString();
        // 得到上传文件后缀
        String originalName = file.getOriginalFilename();
        String ext = "." + FilenameUtils.getExtension(originalName);
        // 新生成的文件名称
        String fileName = uuid + ext;
        System.out.println("新生成的文件名称 =================> " + fileName);

        // 得到新的文件File对象
        // 这个路径是用来记录车辆是否在停车场内
        File targetFile = new File(localDir, fileName);

        // 开始复制文件
        try {
            FileUtils.writeByteArrayToFile(targetFile, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileName;
    }

    // 车牌识别，在这里要调用 python 模型，然后把识别结果给文件重命名
    // 这里是车辆入库的时候进行调用
    @Override
    public String recPlateByFileName(MultipartFile file, String fileName) {
        // fileName是为了在文件夹中准确读取到对应的图片
        String ext = "." + FilenameUtils.getExtension(file.getOriginalFilename());
        // 进行识别
        String plate = recPlate(file, fileName);
        if (plate == null) {
            return "识别失败";
        }

        String recResult = plate + ext;
        System.out.println("经过识别后重命名的文件名为=============> " + recResult);

        // 识别完了后要用识别结果来重命名文件
        File oldfile = new File(localDir + "\\" + fileName);
        File newFile = new File(localDir + "\\" + recResult);

        if (oldfile.exists()) {
            boolean renamed = oldfile.renameTo(newFile);
            if (renamed) {
                System.out.println("文件重命名成功！");
                oldfile.delete();
            } else {
                System.out.println("文件重命名失败！");
            }
        } else {
            System.out.println("文件不存在！");
        }
        return plate;
    }

    // 车辆入库的时候调用
    @Override
    public String recPlate(MultipartFile file, String fileName) {
        String plate = null;
        // 构建一个 get 请求发送给识别模型
        // 当这个 flag 为 true 的时候说明是车辆入库，这样设计没啥好处，单纯是亡羊补牢
        String url = "http://127.0.0.1:8000/http?flag=true&fileName=" + fileName;
        HttpGet httpGet = new HttpGet(url);

        httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
        // httpClient实例化
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
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
            plate = new String(responseSB);

            reader.close();
            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (plate != null) {
            return plate.trim();
        } else {
            System.out.println();
            return null;
        }

//        String img_path = "E:/graduate/park-system/ruoyi-admin/src/main/resources/plate_img/" + fileName;
//        System.out.println("img_path=====================>" + img_path);
//        String plate = null;
//        try {
//            String[] args1 = new String[]{"python", "E:\\graduate\\Chinese_license_plate_detection_recognition-main\\java_demo.py", "--image_path", img_path};//第二个为python脚本所在位置，后面的为所传参数（得是字符串类型）
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
//                plate = line;
//            }
//            in.close();
//            proc.waitFor();
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        if (plate != null) {
//            return plate.trim();
//        } else {
//            return null;
//        }

    }


    // 车辆出库的时候调用
    @Override
    public String recPlate(String fileName) {
        String plate = null;
        // 构建一个 get 请求发送给识别模型
        String url = "http://127.0.0.1:8000/http?flag=false&fileName=" + fileName;
        HttpGet httpGet = new HttpGet(url);

        httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
        // httpClient实例化
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
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
            plate = new String(responseSB);

            reader.close();
            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (plate != null) {
            return plate.trim();
        } else {
            System.out.println();
            return null;
        }
//        String img_path = "C:/Users/Administrator/Desktop/download/" + fileName;
//        System.out.println("img_path=====================>" + img_path);
//        try {
//            String[] args1 = new String[]{"python", "E:\\graduate\\Chinese_license_plate_detection_recognition-main\\java_demo.py", "--image_path", img_path};//第二个为python脚本所在位置，后面的为所传参数（得是字符串类型）
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
//                plate = line;
//            }
//            in.close();
//            proc.waitFor();
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }


    }


    @Override
    public boolean deleteFile(String fileName) {

        File baseDir = new File(localDir);        // 创建一个File对象
        String tempName = null;

        File tempFile;
        File[] files = baseDir.listFiles();
        if (files.length == 0) {//该文件夹下没有文件，为空文件夹
            System.out.println("为空文件夹");
            return false;
        }
        for (int i = 0; i < files.length; i++) {
            tempFile = files[i];
            tempName = tempFile.getName();
            if (tempName.equals(fileName)) {
                System.out.println("找到了要出库的车图片 =========> " + fileName);
                tempFile.delete();
                System.out.println("删除成功！");
                return true;
            }
        }
        return false;
    }
}
