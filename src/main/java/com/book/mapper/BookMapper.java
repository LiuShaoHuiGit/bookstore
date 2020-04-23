package com.book.mapper;

import com.book.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    /**
     * 根据cid获取 书籍
     * @param cid
     * @return
     */
    public List<Book> listBook(@Param("cid") Integer cid);

    /**
     * 根据bid获取 书籍
     * @param bid
     * @return
     */
    public Book getBook(@Param("bid") Integer bid);


}
