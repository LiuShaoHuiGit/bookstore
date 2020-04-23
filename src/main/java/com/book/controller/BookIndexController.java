package com.book.controller;

import com.alibaba.fastjson.JSONObject;
import com.book.pojo.BookCategory;
import com.book.service.impl.BookCategoryImpl;
import com.book.service.inter.BookCategoryInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class BookIndexController {

    @Autowired
    private BookCategoryImpl bookCategoryImpl;

    @RequestMapping(value = "test")
    public String test2(Model model){

        return "index";
    }

    @RequestMapping(value = "listCategory" )
    @ResponseBody
    public String listCategory(HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        List<BookCategory> bookCategories = bookCategoryImpl.listBookCategory();
        System.out.println(JSONObject.toJSON(bookCategories).toString());
        return  JSONObject.toJSON(bookCategories).toString();
    }


}
