package com.mysite.finalProject.repository;

import com.mysite.finalProject.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
