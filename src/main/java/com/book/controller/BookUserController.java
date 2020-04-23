package com.book.controller;

import com.alibaba.fastjson.JSON;
import com.book.pojo.BookUser;
import com.book.service.inter.BookUserInter;
import com.book.utils.BookUserUtils;
import com.book.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@Controller
public class BookUserController{

    @Autowired
    private BookUserInter bookUserImpl;
    @Resource(name = "redisUtil")
    private RedisUtils redisUtilss;

    Logger logger = LoggerFactory.getLogger(BookUserController.class);

    /**
     * 注册
     * @param bookUser
     * @return
     */
    @RequestMapping(value = "toRegister")
    public String toRegister(BookUser bookUser){
        System.out.println("前期++"+bookUser);
        bookUser.setUid(BookUserUtils.getUUID());// 获取32位UUID当用户id;
        bookUser.setType(1);//1(激活状态),0(未激活状态)
        bookUser.setPassWord(BookUserUtils.getMD5(bookUser.getPassWord()));
        System.out.println("后期++"+bookUser);
        int num = bookUserImpl.insetBookUser(bookUser);
        System.out.println(num);
        return "login";
    }


    /**
     * 用户登录
     * @param bookUser
     * @return
     */
    @RequestMapping(value = "getBookUserByUserPass")
    public String getBookUserByUserPass(BookUser bookUser, HttpServletResponse response) {
        System.out.println(bookUser);
        bookUser.setPassWord(BookUserUtils.getMD5(bookUser.getPassWord()));
        BookUser bookUserByUserPass = bookUserImpl.getBookUserByUserPass(bookUser);
        logger.info("登录获取用户信息: "+JSON.toJSONString(bookUserByUserPass));
        if(bookUserByUserPass == null  ){
            return "login";
        }
        redisUtilss.setKey("bookUser", JSON.toJSONString(bookUserByUserPass));
        Cookie cookie = new Cookie("userName",bookUser.getUserName());
        response.addCookie(cookie);
        return  "forward:test.html";
    }

    /**
     * 发送email验证码
     * @param bookUser
     * @return
     */
    @RequestMapping(value = "toEmail")
    @ResponseBody
    public String toEmail(BookUser bookUser){
        System.out.println(bookUser);
        int code=0;
        try{
            code = BookUserUtils.getBookUserEmail(bookUser.getEmail());
            System.out.println(code);
       }catch (Exception e) {
           System.err.println("认证码发送失败");
        }
        return JSON.toJSONString(code);
    }

}
