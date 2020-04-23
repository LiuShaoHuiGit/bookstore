package com.book.pojo;

public class BookCart {

    private Integer cartId;
    private Integer bid;//购买book的id
    private Integer cont;//数量
    private Double subtotal;//小计
    private String uid;//用户id
    private Book book;//获取书籍
    private BookUser bookUser;//获取用户

    @Override
    public String toString() {
        return "BookCart{" +
                "cartId=" + cartId +
                ", bid=" + bid +
                ", cont=" + cont +
                ", subtotal=" + subtotal +
                ", uid='" + uid + '\'' +
                ", book=" + book +
                ", bookUser=" + bookUser +
                '}';
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getCont() {
        return cont;
    }

    public void setCont(Integer cont) {
        this.cont = cont;
    }

    public Double getSubtotal() {
        return cont*book.getPrice();
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BookUser getBookUser() {
        return bookUser;
    }

    public void setBookUser(BookUser bookUser) {
        this.bookUser = bookUser;
    }
}
