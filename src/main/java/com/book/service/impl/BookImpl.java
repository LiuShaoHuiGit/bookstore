package com.book.service.impl;

import com.book.mapper.BookMapper;
import com.book.pojo.Book;
import com.book.service.inter.BookInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookImpl implements BookInter {

    @Autowired
    private BookMapper bookMapper;

    /**
     * 根据id获取
     * @param cid
     * @return
     */
    @Override
    public List<Book> listBook(Integer cid) {
        return bookMapper.listBook(cid);
    }

    /**
     * 根据bid获取 书籍
     * @param bid
     * @return
     */
    @Override
    public Book getBook(Integer bid){
        return bookMapper.getBook(bid);
    }

}
