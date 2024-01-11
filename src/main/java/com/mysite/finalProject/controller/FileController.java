package com.mysite.finalProject.controller;

import com.mysite.finalProject.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;


    //이미지 업로드하기
    @PostMapping("/upload")
    public String upload(
            @RequestParam("file")MultipartFile file
            ){
        String url = fileService.upload(file);
        return url;
    }

    //이미지 가져오기
    @GetMapping(value ="{fileName}",produces = {MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_JPEG_VALUE})  //매개변수로 가져옴 , 타입지정
    public Resource getImage(
            @PathVariable("fileName") String fileName
    ){
        Resource resource = fileService.getImage(fileName);
        return resource;
    }
}
