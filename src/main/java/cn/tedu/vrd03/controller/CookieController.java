package cn.tedu.vrd03.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
public class CookieController {
@RequestMapping("/cookie")
    public String cookie(HttpServletRequest request){
    Cookie[] cookies=request.getCookies();
    if (cookies!=null){
        for (Cookie cookie : cookies) {
            String name=cookie.getName();
            String value=cookie.getValue();
            System.out.println("name:"+name+"value:"+value);
        }
    }
    return "接收到了cookie";
}
}
