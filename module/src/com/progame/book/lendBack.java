package com.progame.book;

public class lendBack {
    private String username;
    private String bookName;

    private String nowTime;


    public lendBack() {
    }

    public lendBack(String username, String bookName, String nowTime) {
        this.username = username;
        this.bookName = bookName;
        this.nowTime = nowTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getNowTime() {
        return nowTime;
    }

    public void setNowTime(String nowTime) {
        this.nowTime = nowTime;
    }

    public String toString() {
        return "lendBack{username = " + username + ", bookName = " + bookName + ", nowTime = " + nowTime + "}";
    }
}
