package com.progame.book;

import java.util.Objects;

public class Book {
    //图书编号
    private String book_id;
    //图书主题
    private String book_theme;
    //图书名称
    private String book_name;
    //图书价格
    private int book_price;

    private String isDamage;

    //是否借阅 是为true 否为false
    private String is_lend;
    //借阅时间
    private String lendTime;
    //归还时间
    private String backTime;


    public Book() {
    }

    public Book(String book_id, String book_theme, String book_name, int book_price, String isDamage, String is_lend, String lendTime, String backTime) {
        this.book_id = book_id;
        this.book_theme = book_theme;
        this.book_name = book_name;
        this.book_price = book_price;
        this.isDamage = isDamage;
        this.is_lend = is_lend;
        this.lendTime = lendTime;
        this.backTime = backTime;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getBook_theme() {
        return book_theme;
    }

    public void setBook_theme(String book_theme) {
        this.book_theme = book_theme;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public int getBook_price() {
        return book_price;
    }

    public void setBook_price(int book_price) {
        this.book_price = book_price;
    }

    public String getIsDamage() {
        return isDamage;
    }

    public void setIsDamage(String isDamage) {
        this.isDamage = isDamage;
    }

    public String getIs_lend() {
        return is_lend;
    }

    public void setIs_lend(String is_lend) {
        this.is_lend = is_lend;
    }

    public String getLendTime() {
        return lendTime;
    }

    public void setLendTime(String lendTime) {
        this.lendTime = lendTime;
    }

    public String getBackTime() {
        return backTime;
    }

    public void setBackTime(String backTime) {
        this.backTime = backTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getBook_price() == book.getBook_price() && Objects.equals(getBook_id(), book.getBook_id()) && Objects.equals(getBook_theme(), book.getBook_theme()) && Objects.equals(getBook_name(), book.getBook_name()) && Objects.equals(getIsDamage(), book.getIsDamage()) && Objects.equals(getIs_lend(), book.getIs_lend()) && Objects.equals(getLendTime(), book.getLendTime()) && Objects.equals(getBackTime(), book.getBackTime());
    }


    public String toString() {
        return "Book{book_id = " + book_id + ", book_theme = " + book_theme + ", book_name = " + book_name + ", book_price = " + book_price + ", isDamage = " + isDamage + ", is_lend = " + is_lend + ", lendTime = " + lendTime + ", backTime = " + backTime + "}";
    }
}