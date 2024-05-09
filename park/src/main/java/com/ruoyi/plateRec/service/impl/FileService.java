package com.ruoyi.plateRec.service.impl;

import com.ruoyi.plateRec.service.IFileService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    @Override
    public String recPlateByFileName(MultipartFile file, String fileName) {
        // fileName是为了在文件夹中准确读取到对应的图片

        // todo 进行识别
        // 此处假设识别结果为 ： 粤bss002
        String ext = "." + FilenameUtils.getExtension(file.getOriginalFilename());
        String plate = "粤bss002";

        String recResult = "粤bss002" + ext;
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

    @Override
    public String recPlate(MultipartFile file) {
        return "粤bss002";
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
