package com.mysite.finalProject.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    @Value("${file.path}")
    private String filePath;
    @Value("${file.url}")
    private String fileUrl;

    //이미지 업로드
    @Override
    public String upload(MultipartFile file) {
        if(file.isEmpty()) return null;//빈 파일이라면  null 리턴
        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));//확장자 가져오기
        String uuid = UUID.randomUUID().toString();//랜덤 uuid만듬
        String saveFileName = uuid + extension;
        String savePath = filePath + saveFileName;

        try {
        file.transferTo(new File(savePath));
        }catch (Exception exception){
            return null;
    }

        String url = fileUrl + saveFileName;
        return url;
    }

    //이미지 가져오기
    @Override
    public Resource getImage(String fileName) {

        Resource resource = null;

        try{
            resource = new UrlResource("file:"+filePath+fileName);
        }catch (Exception exception){
            exception.printStackTrace();
            return null;
        }
        return resource;
    }
}
