package com.victor.seafoodback.controller;

import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.News;
import com.victor.seafoodback.service.AdvertService;
import com.victor.seafoodback.util.MyTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;

@RestController
@RequestMapping("/admin")
public class AdvertController {

    @Autowired
    private AdvertService advertService;

    @PostMapping("/getAllAdvert")
    public CommonResult getAllAdverts(){
        return advertService.getAllAdverts();
    }

    @PostMapping("/getAllNews")
    public CommonResult getAllNews(@RequestParam(value = "lowDate", required = false) String lowDate, @RequestParam(value = "highDate", required = false) String highDate,
                                   @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        return advertService.getAllNews(MyTimeUtil.dealDateFormat(lowDate), MyTimeUtil.dealDateFormat(highDate), pageNo, pageSize);
    }

    @PostMapping("/addNews")
    public CommonResult addNews(@RequestParam("content") String content) {
        return advertService.addNews(content);
    }

    @PostMapping("/deleteNews")
    public CommonResult deleteNews(@RequestParam("id") Integer id) {
        return advertService.deleteNews(id);
    }

    @PostMapping("/updateNews")
    public CommonResult updateNews(@RequestParam("content") String content,@RequestParam("id") Integer id) {
        return advertService.updateNews(content,id);
    }

    @PostMapping("/uploadLunbo")
    public CommonResult uploadGoodPic(@RequestParam(value = "file",required = false) MultipartFile file,@RequestParam("id")Integer id){
        if (file.isEmpty()) {
            return new CommonResult(444,"上传失败");
        }
        //创建输入输出流
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            //指定上传的位置为 d:/upload/
            String path = "C:/Users/victor/apache-tomcat-file/webapps/files/";
            //获取文件的输入流
            inputStream = file.getInputStream();
            //获取上传时的文件名
            String fileName = file.getOriginalFilename();
            //注意是路径+文件名
            File targetFile = new File(path + fileName);
            //如果之前的 String path = "d:/upload/" 没有在最后加 / ，那就要在 path 后面 + "/"

            //判断文件父目录是否存在
            if(!targetFile.getParentFile().exists()){
                //不存在就创建一个
                targetFile.getParentFile().mkdir();
            }

            //获取文件的输出流
            outputStream = new FileOutputStream(targetFile);
            //最后使用资源访问器FileCopyUtils的copy方法拷贝文件
            FileCopyUtils.copy(inputStream, outputStream);

            //更新到数据库
            advertService.updateAdvert(id,fileName);

            //告诉页面上传成功了
            return new CommonResult(200,"上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            //出现异常，则告诉页面失败
            return new CommonResult(200,"上传失败");
        } finally {
            //无论成功与否，都有关闭输入输出流
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
