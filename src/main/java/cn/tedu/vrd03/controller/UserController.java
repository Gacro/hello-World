package cn.tedu.vrd03.controller;

import cn.tedu.vrd03.entity.User;
import cn.tedu.vrd03.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {
@Autowired(required = false)
    UserMapper uMapper;
@RequestMapping("/login")
public int login(User user, String rem, HttpServletResponse response,HttpSession session){
    User u=uMapper.login(user.getUsername());
    if(u!=null){
        if (user.getPassword().equals(u.getPassword())){
            session.setAttribute("user",user);
            if (rem!=null){
                Cookie c1=new Cookie("username",user.getUsername());
                Cookie c2=new Cookie("password",user.getPassword());
                 response.addCookie(c1);
                 response.addCookie(c2);
                 c1.setMaxAge(60*60*30*24);
                 c2.setMaxAge(60*60*30*24);
            }
            return 1;
        }else{
            return 3;
        }

    }
    return 2;
}

@RequestMapping("/checklogin")
    public boolean checkLogin(HttpSession session){
    User user=(User)session.getAttribute("user");
    return user==null?false:true;
}
@RequestMapping("/logout")
    public  void  logout(HttpSession session){
    session.removeAttribute("user");
}
}
