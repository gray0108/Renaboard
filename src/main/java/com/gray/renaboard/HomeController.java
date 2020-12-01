package com.gray.renaboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home(){
        //model.addAttribute("greeting", "안녕무케!");
        return "home";
    }
}
