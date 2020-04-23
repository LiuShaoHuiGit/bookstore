package com.book.service.inter;

import com.book.pojo.BookCategory;

import java.util.List;

public interface BookCategoryInter {
    /**
     * 获取所有书籍的类型
     * @return
     */
    public List<BookCategory> listBookCategory();

}
