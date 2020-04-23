package com.book.service.impl;

import com.book.mapper.BookUserMapper;
import com.book.pojo.BookUser;
import com.book.service.inter.BookUserInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookUserImpl implements BookUserInter {

    @Autowired
    private  BookUserMapper bookUserMapper;


    /**
     * 用户登录
     * @param bookUser
     * @return
     */
    @Override
    public BookUser getBookUserByUserPass(BookUser bookUser) {
        return bookUserMapper.getBookUserByUserPass(bookUser);
    }

    /**
     * 用户注册
     * @param bookUser
     * @return
     */
    @Override
    public int insetBookUser(BookUser bookUser) {
        return bookUserMapper.insetBookUser(bookUser);
    }
}
