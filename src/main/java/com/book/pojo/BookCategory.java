package com.book.pojo;

public class BookCategory {

    private Integer cid;
    private String categoryName;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "BookCategory{" +
                "cid=" + cid +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
