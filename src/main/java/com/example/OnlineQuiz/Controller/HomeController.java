package com.example.OnlineQuiz.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
    @RequestMapping("/")
public class HomeController {

    @GetMapping
    public String getHome(){
        return "Backend Running";
    }
}
