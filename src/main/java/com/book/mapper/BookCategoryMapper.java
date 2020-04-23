package com.book.mapper;

import com.book.pojo.BookCategory;

import java.util.List;

public interface BookCategoryMapper {
    /**
     * 获取所有书籍的类型
     * @return
     */
    public List<BookCategory> listBookCategory();

}
