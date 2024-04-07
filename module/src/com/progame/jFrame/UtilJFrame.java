package com.progame.jFrame;

import com.progame.ArrayListAll;
import com.progame.User.User;
import com.progame.book.Book;
import com.progame.book.lendBack;
import com.progame.book.lendBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.*;

import static com.progame.ArrayListAll.*;


public class UtilJFrame {

    //创建数据库的连接
    public static Connection connection;

    private UtilJFrame() {
    }

    //去除按钮默认背景与边框的方法
    public static void deleteBorder(JButton jButton) {
        //去除按钮的默认边框
        jButton.setBorderPainted(false);
        //去除按钮的默认背景
        jButton.setContentAreaFilled(false);
    }

    //给界面添加按钮
    public static JButton createJButton(String str, int x, int y, int width, int height, MouseListener mouse, JFrame jFrame) {
        //给页面添加按钮
        JButton jbutton = new JButton();
        jbutton.setIcon(new ImageIcon(str));
        jbutton.setBounds(x, y, width, height);
        deleteBorder(jbutton);
        jbutton.addMouseListener(mouse);
        jFrame.getContentPane().add(jbutton);

        return jbutton;
    }

    //给界面添加弹框
    public static void createJDialog(String jLabelStr) {
        //首先创建管理容器并且增加文字
        JLabel jLabel = new JLabel(jLabelStr);
        jLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 28));
        //设置管理容器的大小和位置
        jLabel.setBounds(50, 5, 300, 150);
        JDialog jDialog = new JDialog();
        //将管理容器添加到弹框的隐藏容器里面
        jDialog.getContentPane().add(jLabel);
        //设置弹框的大小
        jDialog.setSize(300, 100);
        //设置标题
        jDialog.setTitle(jLabelStr);
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);
        jDialog.setVisible(true);
    }

    //给界面添加管理容器用来添加文字
    public static void createJLabelText(String str, int x, int y, int width, int height, String bookStr, int size, JFrame jFrame) {
        JLabel searchResult = new JLabel(str + bookStr);
        searchResult.setBounds(x, y, width, height);
        searchResult.setFont(new Font("Arial Unicode MS", Font.BOLD, size));
        jFrame.getContentPane().add(searchResult);
    }

    //给界面添加管理容器用来添加图片
    public static void createJLabelImage(String str, int x, int y, int width, int height, JFrame jFrame) {
        JLabel jlabel = new JLabel(new ImageIcon(str));
        jlabel.setBounds(x, y, width, height);
        jFrame.getContentPane().add(jlabel);
    }

    //给界面添加文本框
    public static JTextField createJTextField(int x, int y, int width, int height, JFrame jFrame) {

        JTextField jTextField = new JTextField();
        jTextField.setBounds(x, y, width, height);
        jFrame.getContentPane().add(jTextField);

        return jTextField;
    }

    //给界面添加密码框
    public static JPasswordField createJPasswordField(int x, int y, int width, int height, JFrame jFrame) {

        JPasswordField jPasswordField = new JPasswordField();
        jPasswordField.setBounds(x, y, width, height);
        jFrame.getContentPane().add(jPasswordField);

        return jPasswordField;
    }

    //查找图书编号所对应的图书
    public static Book containsId(ArrayList<Book> list, String id) {
        if (list.isEmpty()) {
            return null;
        }
        for (Book book : list) {
            if (book.getBook_id().equals(id)) {
                //如果索引相等则返回i
                return book;
            }
        }
        return null;
    }

    //查找图书名称所对应的图书
    public static Book containsBookName(ArrayList<Book> list, String id) {
        if (list.isEmpty()) {
            return null;
        }
        for (Book book : list) {
            if (book.getBook_name().equals(id)) {
                //如果索引相等则返回i
                return book;
            }
        }
        return null;
    }

    //查找用户名所对应的用户
    public static User containsName(ArrayList<User> list, String id) {
        if (list.isEmpty()) {
            return null;
        }
        for (User user : list) {
            if (user.getUser_Name().equalsIgnoreCase(id)) {
                //如果索引相等则返回
                return user;
            }
        }
        return null;
    }

    //随机生成验证码
    public static String createCode() {
        ArrayList<Character> codeList = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            codeList.add((char) ('a' + i));
            codeList.add((char) ('A' + i));
        }

        StringBuilder result = new StringBuilder();
        //打乱集合中的数据
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(52);
            result.append(codeList.get(index));
        }

        int num = r.nextInt(9) + 1;

        result.append(num);

        char[] arr = result.toString().toCharArray();

        int index = r.nextInt(4);

        char temp = arr[index];
        arr[index] = arr[4];
        arr[4] = temp;

        result = new StringBuilder(new String(arr));

        return result.toString();
    }

    //保存用户信息到集合
    public static void saveUser() throws SQLException {
        connection.setAutoCommit(false);

        String deleteSQL = "TRUNCATE TABLE userTable";
        try (PreparedStatement p2 = connection.prepareStatement(deleteSQL)) {
            System.out.println("清空了");
            p2.executeUpdate();
        }

        String insertSQL = "INSERT INTO userTable(name,password)" +
                "VALUES (?,?)";
        try {

            for (User user : userList) {
                try (PreparedStatement p = connection.prepareStatement(insertSQL)) {

                    p.setString(1, user.getUser_Name());
                    p.setString(2, user.getUser_Password());

                    p.executeUpdate();
                }
            }
            //提交事务
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }

    //保存图书信息到集合
    public static void saveBook() throws SQLException {
        connection.setAutoCommit(false);

        String deleteSQL = "TRUNCATE TABLE bookTable";
        try (PreparedStatement p2 = connection.prepareStatement(deleteSQL)) {
            System.out.println("清空了");
            p2.executeUpdate();
        }

        String insertSQL = "INSERT INTO bookTable(id,theme,name,price,isDamage,isLend,LendTime,backTime)" +
                "VALUES (?,?,?,?,?,?,?,?)";
        try {
            SortBook();
            for (Book book : bookList) {
                try (PreparedStatement p = connection.prepareStatement(insertSQL)) {

                    p.setString(1, book.getBook_id());
                    p.setString(2, book.getBook_theme());
                    p.setString(3, book.getBook_name());
                    p.setInt(4, book.getBook_price());
                    p.setString(5, book.getIsDamage());
                    p.setString(6, book.getIs_lend());
                    p.setString(7, book.getLendTime());
                    p.setString(8, book.getBackTime());

                    p.executeUpdate();
                }

            }
            //提交事务
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }

    }

    //保存反馈信息到集合
    public static void saveFeedback() throws SQLException {
        connection.setAutoCommit(false);

        String deleteSQL = "TRUNCATE TABLE feedbackTable";
        try (PreparedStatement p2 = connection.prepareStatement(deleteSQL)) {
            p2.executeUpdate();
        }

        String insertSQL = "INSERT INTO feedbackTable(feedbackStr)" +
                "VALUES (?)";
        try {

            for (String str : feedbackList) {
                try (PreparedStatement p = connection.prepareStatement(insertSQL)) {

                    p.setString(1, str);

                    p.executeUpdate();
                }
            }
            //提交事务
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }

    }

    //保存预约到集合
    public static void saveYuYue() throws SQLException {
        connection.setAutoCommit(false);

        String deleteSQL = "TRUNCATE TABLE yuYueTable";
        try (PreparedStatement p2 = connection.prepareStatement(deleteSQL)) {
            p2.executeUpdate();
        }

        String insertSQL = "INSERT INTO yuYueTable(yuYue)" +
                "VALUES (?)";
        try {

            for (String str : yuYueList) {
                try (PreparedStatement p = connection.prepareStatement(insertSQL)) {

                    p.setString(1, str);

                    p.executeUpdate();
                }
            }
            //提交事务
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }

    }

    //保存借阅和归还记录到集合
    public static void saveLendBack() throws SQLException {
        connection.setAutoCommit(false);

        String deleteSQL = "TRUNCATE TABLE userBook";
        try (PreparedStatement p2 = connection.prepareStatement(deleteSQL)) {
            p2.executeUpdate();
        }

        String insertSQL = "INSERT INTO userBook(username,bookName,nowTime)" +
                "VALUES (?,?,?)";
        try {

            for (lendBack ub : lendBackList) {
                try (PreparedStatement p = connection.prepareStatement(insertSQL)) {

                    p.setString(1, ub.getUsername());
                    p.setString(2, ub.getBookName());
                    p.setString(3, ub.getNowTime());

                    p.executeUpdate();
                }
            }
            //提交事务
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }

    }

    //保存借阅数据到集合
    public static void saveLendBook() throws SQLException {
        connection.setAutoCommit(false);
        String deleteSQL = "TRUNCATE TABLE lendBook";
        try (PreparedStatement p2 = connection.prepareStatement(deleteSQL)) {
            p2.executeUpdate();
        }
        String insertSQL = "INSERT INTO lendBook(username,bookName)" +
                "VALUES (?,?)";
        try {

            for (lendBook ub : lendBookList) {
                try (PreparedStatement p = connection.prepareStatement(insertSQL)) {

                    p.setString(1, ub.getUsername());
                    p.setString(2, ub.getBookName());

                    p.executeUpdate();
                }
            }
            //提交事务
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }

    //读取数据
    public static void getData() {
        System.out.println("数据库连接成功!");
        String url = "jdbc:mysql://localhost:3306/book";
        String username = "root";
        String password = "160814";

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            connection.setAutoCommit(false);

            //读取图书
            String selectSQLBook = "SELECT * FROM bookTable";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectSQLBook)) {
                try (ResultSet r = selectStatement.executeQuery()) {
                    while (r.next()) {
                        String id = r.getString("id");
                        String theme = r.getString("theme");
                        String name = r.getString("name");
                        int price = r.getInt("price");
                        String isDamage = r.getString("isDamage");
                        String isLend = r.getString("isLend");
                        String lendTime = r.getString("lendTime");
                        String backTime = r.getString("backTime");
                        ArrayListAll.bookList.add(new Book(id, theme, name, price, isDamage, isLend, lendTime, backTime));
                    }
                    SortBook();
                    r.close();
                    selectStatement.close();
                }
            }

            //读取用户
            String selectSQLUser = "SELECT * FROM userTable";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectSQLUser)) {
                try (ResultSet r = selectStatement.executeQuery()) {
                    while (r.next()) {
                        String id = r.getString("name");
                        String u_password = r.getString("password");
                        ArrayListAll.userList.add(new User(id, u_password));
                    }
                    r.close();
                    selectStatement.close();
                }
            }

            //读取反馈记录
            String selectSQLFeedback = "SELECT * FROM feedbackTable";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectSQLFeedback)) {
                try (ResultSet r = selectStatement.executeQuery()) {
                    while (r.next()) {
                        String feedback = r.getString("feedbackStr");
                        feedbackList.add(feedback);
                    }
                    r.close();
                    selectStatement.close();

                }
            }

            //读取反馈记录
            String selectSQLYuYue = "SELECT * FROM yuYueTable";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectSQLYuYue)) {
                try (ResultSet r = selectStatement.executeQuery()) {
                    while (r.next()) {
                        String yuYue = r.getString("yuYue");
                        yuYueList.add(yuYue);
                    }
                    r.close();
                    selectStatement.close();
                }
            }

            //读取借阅和归还记录
            String selectSQLLend = "SELECT * FROM userBook";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectSQLLend)) {
                try (ResultSet r = selectStatement.executeQuery()) {
                    while (r.next()) {
                        String userName = r.getString("username");
                        String bookName = r.getString("bookName");
                        String time = r.getString("nowTime");
                        lendBackList.add(new lendBack(userName, bookName, time));
                    }
                    r.close();
                    selectStatement.close();
                }
            }

            //读取用户与书记录
            String selectSQLLendBook = "SELECT * FROM lendBook";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectSQLLendBook)) {
                try (ResultSet r = selectStatement.executeQuery()) {
                    while (r.next()) {
                        String userName = r.getString("username");
                        String bookName = r.getString("bookName");
                        lendBookList.add(new lendBook(userName, bookName));
                    }
                    r.close();
                    selectStatement.close();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //给图书排序
    public static void SortBook() {
        //匿名内部类
        //bookList.sort(new Comparator<Book>() {
        //    @Override
        //    public int compare(Book o1, Book o2) {
        //        return Integer.parseInt(o1.getBook_id()) - Integer.parseInt(o2.getBook_id());
        //    }
        //});

        //或者lambda表达式
        bookList.sort((o1, o2) -> {
            return Integer.parseInt(o1.getBook_id()) - Integer.parseInt(o2.getBook_id());
        });
    }

}
