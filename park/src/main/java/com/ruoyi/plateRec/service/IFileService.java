package com.ruoyi.plateRec.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    String saveFile(MultipartFile file);

    // 用于识别车牌
    String recPlate(MultipartFile file, String fileName);

}
