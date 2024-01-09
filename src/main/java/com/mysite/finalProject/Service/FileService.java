package com.mysite.finalProject.Service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String upload(MultipartFile file);
    Resource getImage(String fileName);
}
