package com.bb.bb.controller.common;

import com.bb.bb.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
public class UploadController {

    private String rootPath = "D:/javaproject/static/upload/";
    @PostMapping("upload")
    public Result uploadFile(@RequestParam("file")MultipartFile file) throws  Exception{
        if(file.isEmpty()){
            return new Result(999,"上传文件不能为空");
        }

        File f = new File(this.rootPath);

        if(!f.exists()){
            if(!f.mkdirs()){
                return new Result(999,f.getPath() + "目录创建失败");
            }
        }

        File dir = new File(this.rootPath + file.getOriginalFilename());

        file.transferTo(dir);

        return new Result<>('0',"success");
    }
}
