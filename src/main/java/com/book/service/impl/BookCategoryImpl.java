package com.book.service.impl;

import com.book.mapper.BookCategoryMapper;
import com.book.pojo.BookCategory;
import com.book.service.inter.BookCategoryInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCategoryImpl implements BookCategoryInter {

    @Autowired
    private BookCategoryMapper bookCategoryMapper;
    /**
     * 获取所有书籍的类型
     * @return
     */
    @Override
    public List<BookCategory> listBookCategory() {
        return bookCategoryMapper.listBookCategory();
    }
}
