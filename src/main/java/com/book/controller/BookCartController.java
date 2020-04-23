package com.book.controller;

import com.alibaba.fastjson.JSON;
import com.book.pojo.BookCart;
import com.book.pojo.BookUser;
import com.book.service.impl.BookCartImpl;
import com.book.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class BookCartController {

    Logger logger = LoggerFactory.getLogger(BookCartController.class);


    @Autowired
    private BookCartImpl bookCartImpl;

    @Resource(name = "redisUtil")
    private RedisUtils redisUtilss;

    /**
     * 根据用户id删除所有
     * @return
     */
    @RequestMapping(value = "delCartByUId")
    @ResponseBody
    public String delCartByUId(){
        Object bookUser = redisUtilss.getKey("bookUser");
        BookUser bookUser1 = JSON.parseObject(bookUser.toString(), BookUser.class);
        int num = bookCartImpl.delCartByUId(bookUser1.getUid());
        return JSON.toJSONString(num);
    }
    /**
     * 根据购物id删除
     * @param cartId
     * @return
     */
    @RequestMapping("delCartByCartId")
    @ResponseBody
    public String delCartByCartId(Integer cartId){
        int num = bookCartImpl.delCartByCartId(cartId);
        return JSON.toJSONString(num);
    }

    /**
     * 添加购物车
     * @param bookCart
     * @return
     */
    @RequestMapping(value = "addCart")
    @ResponseBody
    public String addCart(BookCart bookCart){
        Object name = redisUtilss.getKey("bookUser");
        BookUser bookUser = JSON.parseObject(name.toString(),BookUser.class);
        bookCart.setUid(bookUser.getUid());
        int num = bookCartImpl.addCart(bookCart);
        return JSON.toJSONString(num);
    }

    /**
     * 获取当前用户下所有购物商品
     * @return
     */
    @RequestMapping(value = "listCart")
    @ResponseBody
    public String listCart(){
//        Object bookUser = redisUtilss.getKey("bookUser");
        BookUser bookUser1 = JSON.parseObject(redisUtilss.getKey("bookUser").toString(), BookUser.class);
        List<BookCart> bookCarts = bookCartImpl.listCart(bookUser1.getUid());
        return JSON.toJSONString(bookCarts);
    }



}
