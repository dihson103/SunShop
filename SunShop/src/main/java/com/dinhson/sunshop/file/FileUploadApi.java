package com.dinhson.sunshop.file;

import com.dinhson.sunshop.utils.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadApi {

    @PostMapping(value = "upload", consumes = { "multipart/form-data" })
    @ResponseStatus(HttpStatus.OK)
    public FileResponse upload(@RequestParam("file") MultipartFile multipartFile){
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        FileUtils.saveFile(fileName, multipartFile);
        fileName = "img/" + fileName;
        return new FileResponse(fileName);
    }
}
