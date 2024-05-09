package com.ruoyi.plateRec.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    String saveFile(MultipartFile file);

    // 通过文件名寻找图片并识别车牌
    String recPlateByFileName(MultipartFile file, String fileName);


    String recPlate(MultipartFile file);

    // 删除指定文件名的车牌照片
    boolean deleteFile(String fileName);

}
