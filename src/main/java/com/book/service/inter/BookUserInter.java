package com.book.service.inter;

import com.book.pojo.BookUser;

public interface BookUserInter {
    //用户登录
    public BookUser getBookUserByUserPass(BookUser bookUser);

    //用户注册
    public int insetBookUser(BookUser bookUser);
}
