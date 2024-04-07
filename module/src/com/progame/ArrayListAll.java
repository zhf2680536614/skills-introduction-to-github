package com.progame;

import com.progame.Manager.Manager;
import com.progame.User.User;
import com.progame.book.Book;
import com.progame.book.lendBook;
import com.progame.book.lendBack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArrayListAll {
    private ArrayListAll() throws SQLException {};
    //图书集合
    public static ArrayList<Book> bookList = new ArrayList<>();
    //用户集合
    public static ArrayList<User> userList = new ArrayList<>();

    //管理元集合
    public static ArrayList<Manager> managerList = new ArrayList<>();
    static{
        managerList.add(new Manager("ATEY","160814"));
    }

    //预约记录集合
    public static ArrayList<String> yuYueList = new ArrayList<>();

    //反馈集合
    public static ArrayList<String> feedbackList = new ArrayList<>();

    //临时用户名存储
    public static ArrayList<String> tempUserNameList = new ArrayList<>();

    //借阅与归还记录的集合体
    public static ArrayList<lendBack> lendBackList = new ArrayList<>();

    //借阅记录集合
    public static ArrayList<lendBook> lendBookList = new ArrayList<>();

}
