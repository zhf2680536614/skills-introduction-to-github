package com.progame.book;

public class lendBook {
    private String username;
    private String bookName;

    public lendBook() {
    }

    public lendBook(String username, String bookName) {
        this.username = username;
        this.bookName = bookName;
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

    public String toString() {
        return "lendBook{username = " + username + ", bookName = " + bookName + "}";
    }
}
