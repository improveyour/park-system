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
        System.out.println(fileName);

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
    public String recPlate(MultipartFile file, String fileName) {
        // fileName是为了在文件夹中准确读取到对应的图片


        // 识别完了后要用识别结果来重命名文件

        return "粤bss002";
    }
}
