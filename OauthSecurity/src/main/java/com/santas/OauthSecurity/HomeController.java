package com.santas.OauthSecurity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home.html";
    }

    @GetMapping("/login/oauth2/code/kakao")
    public void test(@RequestParam String code) {
        System.out.println("code = " + code);
    }

}
