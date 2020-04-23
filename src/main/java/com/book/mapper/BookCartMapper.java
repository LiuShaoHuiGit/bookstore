package com.book.mapper;

import com.book.pojo.BookCart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookCartMapper {
    /**
     * 根据用户id删除所有
     * @param uid
     * @return
     */
    public int delCartByUId(@Param("uid") String uid);
    /**
     * 根据购物id删除
     * @param cartId
     * @return
     */
    public int delCartByCartId(@Param("cartId") Integer cartId);

    /**
     * 添加购物车
     * @param bookCart
     * @return
     */
    public int addCart(BookCart bookCart);

    /**
     * 获取当前用户下所有购物商品
     * @param uid
     * @return
     */
    public List<BookCart> listCart(@Param("uid") String uid);

}
