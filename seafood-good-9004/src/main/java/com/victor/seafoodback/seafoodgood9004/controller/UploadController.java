package com.victor.seafoodback.seafoodgood9004.controller;

import com.victor.seafoodback.entity.CommonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;

@RestController
public class UploadController {

    @RequestMapping("/uploadGoodPic")
    public CommonResult uploadGoodPic(@RequestParam(value = "file",required = false) MultipartFile file){
        if (file.isEmpty()) {
            return new CommonResult(444,"上传失败");
        }

        String fileName = file.getOriginalFilename();
        String filePath = "/Users/itinypocket/workspace/temp/";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            return new CommonResult(200,"上传成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new CommonResult(444,"上传失败");
    }
}
