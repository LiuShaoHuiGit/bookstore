package com.book.service.impl;

import com.book.mapper.BookCartMapper;
import com.book.pojo.BookCart;
import com.book.service.inter.BookCartInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCartImpl implements BookCartInter {
    @Autowired
    private BookCartMapper bookCartMapper;
    /**
     * 根据用户id删除所有
     * @param uid
     * @return
     */
    public int delCartByUId(String uid){
        return bookCartMapper.delCartByUId(uid);
    }
    /**
     * 根据购物id删除
     * @param cartId
     * @return
     */
    @Override
    public int delCartByCartId(Integer cartId){
        return bookCartMapper.delCartByCartId(cartId);
    }

    /**
     * 添加购物车
     * @param bookCart
     * @return
     */
    @Override
    public int addCart(BookCart bookCart){
        return bookCartMapper.addCart(bookCart);
    }

    /**
     * 获取当前用户下所有购物商品
     * @param uid
     * @return
     */
    @Override
    public List<BookCart> listCart(String uid){
        return bookCartMapper.listCart(uid);
    }
}
