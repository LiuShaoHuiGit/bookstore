package com.book.controller;

import com.alibaba.fastjson.JSON;
import com.book.pojo.Book;
import com.book.service.impl.BookImpl;
import com.book.utils.RedisUtils;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {

    @Resource(name = "redisUtil")
    private RedisUtils redisUtil;

    @Autowired
    private BookImpl bookImpl;

    /**
     * 获取书籍
     * @param cid
     * @return
     */
    @RequestMapping(value = "listBook")
    @ResponseBody
    public String listBook(Integer cid){
        List<Book> books = bookImpl.listBook(cid);
        System.out.println(books);
        return JSON.toJSONString(books);
    }

    /**
     * 商品详情跳转
     * @param bid
     * @return
     */
    @RequestMapping(value = "getBook")
    public String getBook(Integer bid){
        return "details";
    }

    /**
     * 获取商品
     * @param bid
     * @return
     */
    @RequestMapping(value = "getBookId")
    @ResponseBody
    public String getBookId(String bid){
        System.out.println(bid.split("=")[1]);
        String b = bid.split("=")[1];
        Book book = bookImpl.getBook(Integer.parseInt(b));
        System.out.println(book);
        return JSON.toJSONString(book);
    }

    @RequestMapping(value = "fileImgAction")
    public String fileImg(@RequestParam("multipartFile") File multipartFile, HttpServletRequest request) throws  Exception{
        if(multipartFile != null){
            BufferedImage sourceImg = ImageIO.read( new FileInputStream( multipartFile ) );
            String name = multipartFile.getName();
            String fileTypes = name.substring((name.lastIndexOf(".")+1),name.length());
            if("jpg".equals(fileTypes)){
                System.out.println("正确");
//                InputStream inputStream = new FileInputStream(multipartFile);
                System.out.println(sourceImg.getWidth());
                System.out.println(sourceImg.getHeight());
//                System.out.println(sourceImg.getSize());
            }else{
                System.out.println("格式不正确！");
            }
        }else{
            System.err.println("请选择上传的图片");
        }
        return "fileImg";
    }


    @RequestMapping(value = "testJsons", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String testJson(HttpServletRequest request,Book book){
        Map<String,String> map = new HashMap<String,String>();
        ModelAndView mav= new ModelAndView();
        String name = request.getParameter("bname");
        List<Book> lists= bookImpl.listBook(1);
        for(Book list :lists){

        }
        mav.addObject("bookList",JSON.toJSONString(lists));
        mav.addObject("name",name);
        map.put("bookList",JSON.toJSONString(lists));
        System.out.println("bookList++++"+lists);
        map.put("name",name);
        return  JSON.toJSONString(mav);
    }












    private String getIP(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}
