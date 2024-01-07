package com.mysite.finalProject.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/product")
@RestController

public class ProductController {

    @GetMapping("/list")
    public String list(String text, Model model){

        return "/list";
    }

}
