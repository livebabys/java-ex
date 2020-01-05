package com.bb.bb.controller.common;

import com.bb.bb.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class UploadController {

    private String rootPath = "D:/javaproject/static/upload/";
    @PostMapping("upload")
    public Result uploadFile(@RequestParam("file")MultipartFile file) throws  Exception{
        if(file.isEmpty()){
            return new Result(999,"上传文件不能为空");
        }

        String uploadFilename = file.getOriginalFilename();

        String filename = this.getFileName() + uploadFilename.substring(uploadFilename.lastIndexOf('.'));

        File f = new File(this.rootPath);

        if(!f.exists()){
            if(!f.mkdirs()){
                return new Result(999,f.getPath() + "目录创建失败");
            }
        }
        String filepath = this.rootPath + filename;
        File dir = new File(filepath);

        file.transferTo(dir);

        Map<String,String> resultData = new HashMap<String,String>();
        resultData.put("path",filepath);

        return new Result<>(0,"success",resultData);
    }

    protected String getFileName(){

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss");

        String filename = sdf.format(d);

        Random r = new Random();

        filename += r.nextInt(100)+ "";

        return filename;
    }
}
