package com.book.service.inter;

import com.book.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookInter {
    /**
     * 根据id获取 书籍
     * @param cid
     * @return
     */
    public List<Book> listBook(Integer cid);
    /**
     * 根据bid获取 书籍
     * @param bid
     * @return
     */
    public Book getBook(Integer bid);


}
