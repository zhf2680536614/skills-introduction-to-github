package com.progame.jFrame;

import com.progame.ArrayListAll;
import com.progame.User.User;
import com.progame.book.Book;
import com.progame.book.lendBack;
import com.progame.book.lendBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class ManagerJFrame extends JFrame implements MouseListener, ActionListener {

    //创建图书的集合
    ArrayList<Book> bookList = ArrayListAll.bookList;
    //创建用户集合
    ArrayList<User> userList = ArrayListAll.userList;

    //创建菜单
    JMenuBar jb = new JMenuBar();
    //创建项目
    JMenu menu = new JMenu("菜单");
    JMenu saveExit = new JMenu("退出程序");
    //创建条目
    JMenuItem yuYue = new JMenuItem("预约信息");
    JMenuItem reLogin = new JMenuItem("重新登录");
    JMenuItem register = new JMenuItem("注册");


    //----------------------------------增加管理界面按钮---------------------------------------------
    private JButton managerBook_Button, managerUser_Button, managerLend_Button, managerFeedback_Button;

    //-----------------------------------图书界面按钮------------------------------------------------
    private JButton addBook, searchBook, alterBook, damageBook, deleteBook, exitManageBook;
    //增加图书界面按钮
    private JButton againAdd, exitAddBook, rightAddBook;
    //添加添加图书编号框
    private JTextField bookId, bookTheme, bookName, bookLend, lendTime, backTime, bookPrice, BookIsDamage;
    private String id, theme, name, isDamage, isLend, lTime, bTime, price, new_id;
    //查询图书界面按钮
    private JButton rightSearchBookId, againSearchBook, exitSearchBook;
    //修改图书界面按钮
    private JButton rightAlterBookId, againAlterBook, rightAlterBook, exitAlterBook;
    //增加损耗统计界面按钮
    private JButton exitDamageBook;
    //添加删除图书界面按钮
    private JButton rightDeleteBookId, exitDeleteBook;

    //-----------------------------------用户界面按钮-----------------------------------------------
    private JButton addUser, searchUser, alterUser, deleteUser, exitManageUser;
    //添加用户界面按钮
    private JButton rightAddUser, exitAddUser, againAddUser;
    //添加用户界面文本框
    private JTextField userId;
    //添加用户界面密码框
    private JPasswordField userPassword;
    private String password;
    //添加查询用户界面按钮
    private JButton rightSearchUserId, againSearchUser, exitSearchUser;
    //增加修改用户界面按钮
    private JButton rightAlterUserId, againAlterUser, rightAlterUser, exitAlterUser;
    //增加删除用户界面按钮
    private JButton rightDeleteUserId, exitDeleteUser;

    //--------------------------------------借阅界面按钮--------------------------------------------
    private JButton lendCount, bookState, exitManageLend, exitLendBook;

    //-----------------------------------------反馈界面按钮-----------------------------------------
    private JButton searchFeedback, exitFeedback;

    //--------------------------------------添加编号框---------------------------------------------
    private JTextField searchId = new JTextField();

    JButton exitYuYue;

    //--------------------------------------------------------------------------------------------
    int newPrice;
    int sum = 0;
    int count = 0;

    //创建图书管理系统的主界面
    public ManagerJFrame() {
        this.getContentPane().removeAll();

        //设置界面的宽高
        this.setSize(766, 955);
        //设置界面的图标
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\图标.png"));
        //设置界面的标题
        this.setTitle("图书管理系统1.0");
        //设置界面至于屏幕最上方
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置界面的关闭方式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //初始化主菜单
        saveExit.addMouseListener(this);

        yuYue.addActionListener(this);
        reLogin.addActionListener(this);
        register.addActionListener(this);
        //将条目添加到项目中
        menu.add(yuYue);
        menu.add(reLogin);
        menu.add(register);
        //将项目添加到菜单中
        jb.add(menu);
        jb.add(saveExit);
        //将菜单添加至界面
        this.setJMenuBar(jb);

        //样式没有
        this.setLayout(null);

        //初始化界面
        initJFrame();

        //设置界面可视化
        this.setVisible(true);

        this.getContentPane().repaint();
    }

    //1初始化主界面
    public void initJFrame() {
        //清空界面
        this.getContentPane().removeAll();

        //初始化主菜单按钮
        managerBook_Button = UtilJFrame.createJButton("img\\图书管理划出.png", 80, 100, 296, 103, this, this);

        managerUser_Button = UtilJFrame.createJButton("img\\用户管理划出.png", 390, 300, 296, 103, this, this);

        managerLend_Button = UtilJFrame.createJButton("img\\借阅管理划出.png", 80, 500, 296, 103, this, this);

        managerFeedback_Button = UtilJFrame.createJButton("img\\反馈管理划出.png", 390, 700, 296, 103, this, this);

        //增加主菜单图片
        UtilJFrame.createJLabelImage("img\\main.png", -18, 0, 761, 1047, this);

        //刷新界面
        this.getContentPane().repaint();
    }

    //1.n所有管理页面的背景图片
    public void manageImg() {
        UtilJFrame.createJLabelImage("img\\图书管理界面.png", 0, 0, 777, 1106, this);
    }

    //1.1初始化图书管理界面
    public void initManageBook() {
        //清空界面
        this.getContentPane().removeAll();

        count = 0;
        sum = 0;

        //给页面添加按钮
        //添加增加按钮
        addBook = UtilJFrame.createJButton("img\\增加图书划出.png", 130, 30, 215, 79, this, this);

        //添加查找按钮
        searchBook = UtilJFrame.createJButton("img\\查询图书划出.png", 360, 180, 215, 79, this, this);

        //添加修改按钮
        alterBook = UtilJFrame.createJButton("img\\修改图书划出.png", 130, 330, 215, 79, this, this);

        //添加损耗按钮
        damageBook = UtilJFrame.createJButton("img\\损耗统计划出.png", 360, 480, 215, 79, this, this);

        //添加删除按钮
        deleteBook = UtilJFrame.createJButton("img\\删除图书划出.png", 130, 630, 215, 79, this, this);

        //增加退出按钮
        exitManageBook = UtilJFrame.createJButton("img\\退出界面划出.png", 360, 780, 215, 79, this, this);
        //初始化管理页面
        manageImg();
        //刷新界面
        this.getContentPane().repaint();
    }

    //---------------------------------------图书管理界面--------------------------------------------

    //1.1.1初始化增加图书的界面
    public void initAddBook() {
        this.getContentPane().removeAll();

        againAdd = UtilJFrame.createJButton("img\\继续添加划出.png", 55, 810, 208, 77, this, this);

        rightAddBook = UtilJFrame.createJButton("img\\确认提交划出.png", 278, 810, 208, 77, this, this);

        exitAddBook = UtilJFrame.createJButton("img\\退出添加划出.png", 500, 810, 208, 77, this, this);

        //添加编号框
        bookId = UtilJFrame.createJTextField(320, 35, 250, 40, this);

        //添加主题框
        bookTheme = UtilJFrame.createJTextField(320, 125, 250, 40, this);

        //添加名称框
        bookName = UtilJFrame.createJTextField(320, 215, 250, 40, this);

        //添加是否借阅框
        bookLend = UtilJFrame.createJTextField(320, 315, 250, 40, this);

        //添加借阅时间框
        lendTime = new JTextField("0000-00-00");
        lendTime.setBounds(320, 415, 250, 40);
        this.getContentPane().add(lendTime);
        //添加归还时间框
        backTime = new JTextField("0000-00-00");
        backTime.setBounds(320, 520, 250, 40);
        this.getContentPane().add(backTime);

        //添加价格框
        bookPrice = UtilJFrame.createJTextField(320, 630, 250, 40, this);

        //添加是否损耗框
        BookIsDamage = UtilJFrame.createJTextField(320, 735, 250, 40, this);

        UtilJFrame.createJLabelImage("img\\增加图书界面.png", 50, -70, 633, 943, this);

        //增加图书管理界面
        manageImg();
        this.getContentPane().repaint();
    }

    //1.1.1.2增加图书逻辑
    public void addBookList() throws SQLException {
        id = bookId.getText();
        name = bookName.getText();
        //首先判断编号的唯一性

        Book book = UtilJFrame.containsId(bookList, id);

        if (book != null) {
            System.out.println("生成添加失败的弹框");
            UtilJFrame.createJDialog("图书已经存在!");
        } else {

            if (id.isEmpty() || name.isEmpty()) {
                UtilJFrame.createJDialog("编号或书名为空!");
            } else {
                if (!id.matches("[^0]\\d{2,3}")) {
                    UtilJFrame.createJDialog("编号首位不为0,长度(3-4)!");
                } else {
                    if (!name.matches("[^0].{1,10}")) {
                        UtilJFrame.createJDialog("书名首位为0!");
                    } else {
                        theme = bookTheme.getText();
                        price = bookPrice.getText();
                        isDamage = BookIsDamage.getText();
                        isLend = bookLend.getText();
                        lTime = lendTime.getText();
                        bTime = backTime.getText();

                        newPrice = Integer.parseInt(price);

                        bookList.add(new Book(id, theme, name, newPrice, isDamage, isLend, lTime, bTime));
                        UtilJFrame.saveBook();
                        UtilJFrame.createJDialog("增加成功!");
                    }

                }
            }

        }
    }

    //1.1.2初始化查询图书界面
    public void initSearchBook() {
        this.getContentPane().removeAll();

        new_id = searchId.getText();//判断id如果找到加载信息页面,如果找不到加载无图书页面
        Book book = UtilJFrame.containsId(bookList, new_id);
        if (book != null) {

            String price = Integer.valueOf(book.getBook_price()).toString();

            UtilJFrame.createJLabelText("编号:  ", 250, 20, 600, 100, book.getBook_id(), 28, this);

            UtilJFrame.createJLabelText("主题:  ", 250, 90, 600, 100, book.getBook_theme(), 28, this);

            UtilJFrame.createJLabelText("名称:  ", 250, 160, 600, 100, book.getBook_name(), 28, this);

            UtilJFrame.createJLabelText("是否借阅:  ", 250, 230, 600, 100, book.getIs_lend(), 28, this);

            if (book.getLendTime().isEmpty()) {
                UtilJFrame.createJLabelText("借阅时间:", 250, 300, 600, 100, "0000-00-00", 28, this);
            } else {
                UtilJFrame.createJLabelText("借阅时间:", 250, 300, 600, 100, book.getLendTime(), 28, this);
            }

            if (book.getBackTime().isEmpty()) {
                UtilJFrame.createJLabelText("归还时间:", 250, 370, 600, 100, "0000-00-00", 28, this);
            } else {
                UtilJFrame.createJLabelText("归还时间:", 250, 370, 600, 100, book.getBackTime(), 28, this);
            }

            UtilJFrame.createJLabelText("价格:    ", 250, 435, 600, 100, price, 28, this);

            UtilJFrame.createJLabelText("是否损耗:", 250, 500, 600, 100, book.getIsDamage(), 28, this);

        } else {
            UtilJFrame.createJLabelText("无图书信息!", 250, 250, 600, 200, "", 38, this);
        }

        againSearchBook = UtilJFrame.createJButton("img\\继续查询划出.png", 55, 710, 208, 77, this, this);

        exitSearchBook = UtilJFrame.createJButton("img\\退出查询划出.png", 450, 710, 208, 77, this, this);
        //增加图书管理界面
        manageImg();

        this.getContentPane().repaint();
    }

    //1.1.2.1初始化查询时输入编号界面
    public void initSearchIdBook() {
        this.getContentPane().removeAll();

        rightSearchBookId = UtilJFrame.createJButton("img\\确认提交划出.png", 260, 250, 208, 77, this, this);

        searchId = UtilJFrame.createJTextField(270, 130, 200, 50, this);

        UtilJFrame.createJLabelImage("img\\请输入编号.png", 100, -5, 536, 800, this);

        //增加图书管理界面
        manageImg();

        this.getContentPane().repaint();
    }

    //1.1.3初始化修改图书界面
    public void initAlterBook() {
        this.getContentPane().removeAll();

        new_id = searchId.getText();
        Book book = UtilJFrame.containsId(bookList, new_id);
        System.out.println(book);
        if (book == null) {
            UtilJFrame.createJLabelText("无图书信息!", 250, 250, 600, 200, "", 38, this);
        } else {
            //添加编号框
            bookId = UtilJFrame.createJTextField(320, 35, 250, 40, this);

            //添加主题框
            bookTheme = UtilJFrame.createJTextField(320, 125, 250, 40, this);

            //添加名称框
            bookName = UtilJFrame.createJTextField(320, 215, 250, 40, this);

            //添加是否借阅框
            bookLend = UtilJFrame.createJTextField(320, 315, 250, 40, this);

            //添加借阅时间框
            lendTime = new JTextField(book.getLendTime());
            lendTime.setBounds(320, 415, 250, 40);
            this.getContentPane().add(lendTime);
            //添加归还时间框
            backTime = new JTextField(book.getBackTime());
            backTime.setBounds(320, 520, 250, 40);
            this.getContentPane().add(backTime);

            //添加价格框
            bookPrice = UtilJFrame.createJTextField(320, 630, 250, 40, this);

            //添加是否损耗框
            BookIsDamage = UtilJFrame.createJTextField(320, 735, 250, 40, this);

            rightAlterBook = UtilJFrame.createJButton("img\\确认修改划出.png", 278, 810, 208, 77, this, this);

            UtilJFrame.createJLabelImage("img\\增加图书界面.png", 50, -70, 633, 943, this);
        }

        againAlterBook = UtilJFrame.createJButton("img\\继续修改划出.png", 55, 810, 208, 77, this, this);

        exitAlterBook = UtilJFrame.createJButton("img\\退出修改划出.png", 500, 810, 208, 77, this, this);

        //增加图书管理界面
        manageImg();

        this.getContentPane().repaint();
    }

    //1.1.3初始化修改图书逻辑
    public void initAlterBookDialog() throws SQLException {
        Book book = UtilJFrame.containsId(bookList, new_id);
        id = bookId.getText();
        theme = bookTheme.getText();
        name = bookName.getText();
        price = bookPrice.getText();
        isDamage = BookIsDamage.getText();
        isLend = bookLend.getText();
        lTime = lendTime.getText();
        bTime = backTime.getText();


        if (book != null) {
            if (!id.isEmpty()) {
                book.setBook_id(id);
            }
            if (!theme.isEmpty()) {
                book.setBook_theme(theme);
            }
            if (!name.isEmpty()) {
                book.setBook_name(name);
            }
            if (!price.isEmpty()) {
                newPrice = Integer.parseInt(price);
                book.setBook_price(newPrice);
            }
            if (!isDamage.isEmpty()) {
                book.setIsDamage(isDamage);
            }
            if (!isLend.isEmpty()) {
                book.setIs_lend(isLend);
            }
            if (!lTime.isEmpty()) {
                book.setLendTime(lTime);
            }
            if (!bTime.isEmpty()) {
                book.setBackTime(bTime);
            }
            UtilJFrame.createJDialog("修改成功!");
            UtilJFrame.saveBook();
        } else {
            UtilJFrame.createJDialog("修改失败!");
        }

    }

    //1.1.3.1初始化修改时输入编号界面
    public void initAlterIdBook() {
        this.getContentPane().removeAll();

        rightAlterBookId = UtilJFrame.createJButton("img\\确认提交划出.png", 260, 250, 208, 77, this, this);

        searchId = UtilJFrame.createJTextField(270, 130, 200, 50, this);

        UtilJFrame.createJLabelImage("img\\请输入编号.png", 100, -5, 536, 800, this);

        //增加图书管理界面
        manageImg();
        this.getContentPane().repaint();
    }

    //1.1.4初始化损耗统计界面
    public void initDamageBook() {
        this.getContentPane().removeAll();

        for (Book book : bookList) {
            if (book.getIsDamage().equals("是")) {
                count++;
                sum += book.getBook_price();
            }
        }

        String str1 = Integer.toString(count);
        String str2 = Integer.toString(sum / 4);

        UtilJFrame.createJLabelText("破损图书为:  ", 200, 50, 600, 200, str1, 38, this);
        UtilJFrame.createJLabelText("破损耗费为:  ", 200, 300, 600, 200, str2, 38, this);

        exitDamageBook = UtilJFrame.createJButton("img\\退出界面划出.png", 260, 520, 214, 83, this, this);

        //增加图书管理界面
        manageImg();

        this.getContentPane().repaint();
    }

    //1.1.5初始化删除图书弹框
    public void initDeleteBook() throws SQLException {
        //点击提交编号按钮后进入此界面
        new_id = searchId.getText();
        Book book = UtilJFrame.containsId(bookList, new_id);

        if (book == null) {
            this.getContentPane().removeAll();
            System.out.println("没有此书的信息");
            exitDeleteBook = UtilJFrame.createJButton("img\\退出界面划出.png", 260, 600, 208, 77, this, this);

            UtilJFrame.createJLabelText("无图书信息!", 250, 250, 600, 200, "", 38, this);
        } else {
            //使用迭代器遍历集合删除元素
            Iterator<Book> it = bookList.iterator();
            while (it.hasNext()) {
                Book book1 = it.next();
                if (book1.getBook_id().equals(new_id)) {
                    it.remove();
                }
            }
            UtilJFrame.saveBook();
            UtilJFrame.createJDialog("删除成功!");
        }
        //增加图书管理界面
        manageImg();
        this.getContentPane().repaint();
    }

    //1.1.5.1初始化删除图书时输入编号界面
    public void initDeleteIdBook() {
        this.getContentPane().removeAll();

        rightDeleteBookId = UtilJFrame.createJButton("img\\确认提交划出.png", 260, 600, 208, 77, this, this);
        exitDeleteBook = UtilJFrame.createJButton("img\\退出界面划出.png", 260, 700, 208, 77, this, this);

        searchId = UtilJFrame.createJTextField(270, 130, 200, 50, this);

        UtilJFrame.createJLabelImage("img\\请输入编号.png", 100, -5, 536, 800, this);

        //增加图书管理界面
        manageImg();
        this.getContentPane().repaint();
    }


    //----------------------------------------用户管理界面-------------------------------------------

    //1.2初始化用户管理界面
    public void initManageUser() {
        //清空界面
        this.getContentPane().removeAll();
        //给页面添加按钮
        addUser = UtilJFrame.createJButton("img\\增加用户划出.png", 180, 100, 207, 75, this, this);

        searchUser = UtilJFrame.createJButton("img\\查询用户划出.png", 380, 270, 207, 75, this, this);

        alterUser = UtilJFrame.createJButton("img\\修改用户划出.png", 180, 440, 207, 75, this, this);

        deleteUser = UtilJFrame.createJButton("img\\删除用户划出.png", 380, 610, 207, 75, this, this);

        //增加退出按钮
        exitManageUser = UtilJFrame.createJButton("img\\退出界面划出.png", 180, 790, 207, 75, this, this);

        //初始化管理页面
        manageImg();
        //刷新界面
        this.getContentPane().repaint();
    }

    //1.2.1初始化增加用户界面
    public void initAddUser() {
        //用户只有两个属性:用户名和密码
        this.getContentPane().removeAll();

        againAddUser = UtilJFrame.createJButton("img\\继续添加划出.png", 55, 800, 208, 77, this, this);

        rightAddUser = UtilJFrame.createJButton("img\\确认提交划出.png", 278, 800, 208, 77, this, this);

        exitAddUser = UtilJFrame.createJButton("img\\退出添加划出.png", 500, 800, 208, 77, this, this);

        //添加用户名框
        userId = UtilJFrame.createJTextField(370, 140, 200, 50, this);

        //添加密码框
        userPassword = UtilJFrame.createJPasswordField(370, 270, 200, 50, this);

        UtilJFrame.createJLabelImage("img\\增加用户界面.png", 50, -70, 633, 943, this);

        //增加图书管理界面
        manageImg();
        this.getContentPane().repaint();
    }

    //1.2.1.1初始化增加用户的逻辑
    public void initAddUserDialog() throws SQLException {
        id = userId.getText();
        password = userPassword.getText();
        User user = UtilJFrame.containsName(userList, id);
        if (user == null) {

            if (id.isEmpty() || password.isEmpty()) {
                UtilJFrame.createJDialog("用户名或密码为空!");
            } else {
                if (id.matches("[^0].{3,9}")) {
                    if (password.matches(".{6,15}")) {
                        //添加成功
                        UtilJFrame.createJDialog("添加成功!");
                        userList.add(new User(id, password));
                        UtilJFrame.saveUser();
                    } else {
                        UtilJFrame.createJDialog("长度[6-15]不能有_!");
                    }

                } else {
                    UtilJFrame.createJDialog("长度[4,10],首不为0!");
                }

            }

        } else {
            //添加失败
            UtilJFrame.createJDialog("用户已存在!");
        }
    }

    //1.2.2初始化查询用户界面
    public void initSearchUser() {
        this.getContentPane().removeAll();

        new_id = searchId.getText();
        User user = UtilJFrame.containsName(userList, new_id);
        if (user == null) {
            UtilJFrame.createJLabelText("无用户信息!", 250, 250, 600, 200, "", 38, this);
        } else {
            UtilJFrame.createJLabelText("用户名:  ", 230, 70, 600, 150, user.getUser_Name(), 38, this);

            UtilJFrame.createJLabelText("密码:  ", 230, 140, 600, 150, user.getUser_Password(), 38, this);
        }

        againSearchUser = UtilJFrame.createJButton("img\\继续查询划出.png", 55, 710, 208, 77, this, this);

        exitSearchUser = UtilJFrame.createJButton("img\\退出查询划出.png", 450, 710, 208, 77, this, this);

        //增加图书管理界面
        manageImg();

        this.getContentPane().repaint();
    }

    //1.2.2.1初始化查询用户时查找编号的界面
    public void initSearchIdUser() {
        this.getContentPane().removeAll();

        rightSearchUserId = UtilJFrame.createJButton("img\\确认提交划出.png", 260, 250, 208, 77, this, this);

        searchId = UtilJFrame.createJTextField(270, 130, 200, 50, this);

        UtilJFrame.createJLabelImage("img\\请输入编号.png", 100, -5, 536, 800, this);

        //增加图书管理界面
        manageImg();

        this.getContentPane().repaint();
    }

    //1.3初始化修改用户界面
    public void initAlterUser() {
        this.getContentPane().removeAll();
        new_id = searchId.getText();
        User user = UtilJFrame.containsName(userList, new_id);
        System.out.println(new_id);
        System.out.println(user);
        if (user == null) {
            UtilJFrame.createJLabelText("无用户信息!", 250, 250, 600, 200, "", 38, this);
        } else {
            //加载修改界面
            //添加用户名框
            userId = UtilJFrame.createJTextField(370, 140, 200, 50, this);

            //添加密码框
            userPassword = UtilJFrame.createJPasswordField(370, 270, 200, 50, this);

            rightAlterUser = UtilJFrame.createJButton("img\\确认修改划出.png", 278, 810, 208, 77, this, this);

            UtilJFrame.createJLabelImage("img\\增加用户界面.png", 50, -70, 633, 943, this);
        }

        againAlterUser = UtilJFrame.createJButton("img\\继续修改划出.png", 55, 810, 208, 77, this, this);

        exitAlterUser = UtilJFrame.createJButton("img\\退出修改划出.png", 500, 810, 208, 77, this, this);

        manageImg();
        this.getContentPane().repaint();
    }

    //1初始化修改用户时查找编号界面
    public void initAlterIdUser() {
        this.getContentPane().removeAll();

        rightAlterUserId = UtilJFrame.createJButton("img\\确认提交划出.png", 260, 250, 208, 77, this, this);

        searchId = UtilJFrame.createJTextField(270, 130, 200, 50, this);

        UtilJFrame.createJLabelImage("img\\请输入编号.png", 100, -5, 536, 800, this);

        //增加图书管理界面
        manageImg();

        this.getContentPane().repaint();
    }

    //初始化修改用户弹框逻辑
    public void initAlterUserDialog() throws SQLException {
        User user = UtilJFrame.containsName(userList, new_id);

        name = userId.getText();
        password = userPassword.getText();

        if (user == null) {
            UtilJFrame.createJDialog("修改失败!");
        } else {
            if (!name.isEmpty()) {
                user.setUser_Name(name);
                for (lendBook lB : ArrayListAll.lendBookList) {
                    if (lB.getUsername().equals(new_id)) {
                        lB.setUsername(name);
                    }
                }
            }
            if (!password.isEmpty()) {
                user.setUser_Password(password);
            }
        }
        UtilJFrame.saveLendBook();
        UtilJFrame.saveUser();
        UtilJFrame.createJDialog("修改成功!");
    }

    //初始化删除用户界面
    public void initDeleteUser() throws SQLException {
        //点击提交编号按钮后进入此界面
        new_id = searchId.getText();
        User user = UtilJFrame.containsName(userList, new_id);

        if (user == null) {
            this.getContentPane().removeAll();
            System.out.println("没有此用户的信息");
            exitDeleteUser = UtilJFrame.createJButton("img\\退出界面划出.png", 260, 600, 208, 77, this, this);

            UtilJFrame.createJLabelText("无用户信息!", 250, 250, 600, 200, "", 38, this);
            manageImg();
        } else {
            //使用迭代器遍历集合删除元素
            Iterator<User> it = userList.iterator();
            while (it.hasNext()) {
                User user1 = it.next();
                if (user1.getUser_Name().equals(new_id)) {
                    it.remove();
                }
            }
            UtilJFrame.createJDialog("删除成功!");
            UtilJFrame.saveUser();
        }
        this.getContentPane().repaint();
    }

    //初始化删除用户时输入编号界面
    public void initDeleteIdUser() {
        this.getContentPane().removeAll();

        rightDeleteUserId = UtilJFrame.createJButton("img\\确认提交划出.png", 260, 600, 208, 77, this, this);
        exitDeleteUser = UtilJFrame.createJButton("img\\退出界面划出.png", 260, 700, 208, 77, this, this);

        searchId = UtilJFrame.createJTextField(270, 130, 200, 50, this);

        UtilJFrame.createJLabelImage("img\\请输入编号.png", 100, -5, 536, 800, this);

        //增加图书管理界面
        manageImg();
        this.getContentPane().repaint();
    }

    //------------------------------------借阅管理界面----------------------------------------------
    //1.3初始化借阅管理界面
    public void initManageLend() {
        this.getContentPane().removeAll();

        //添加按钮
        lendCount = UtilJFrame.createJButton("img\\借阅记录查询划出.png", 150, 200, 296, 107, this, this);

        bookState = UtilJFrame.createJButton("img\\图书状态查询划出.png", 150, 700, 296, 107, this, this);

        exitManageLend = UtilJFrame.createJButton("img\\退出界面划出.png", 400, 450, 296, 107, this, this);

        //初始化管理页面
        manageImg();

        this.getContentPane().repaint();
    }

    //1.3.1初始化借阅记录查询页面
    public void initLendBook() throws SQLException {
        this.getContentPane().removeAll();

        UtilJFrame.createJLabelText("---------------借阅与归还信息-----------------", 20, 20, 700, 50, "", 38, this);
        ArrayList<lendBack> tempList = new ArrayList<>();
        int i = 0;
        Iterator<lendBack> it = ArrayListAll.lendBackList.iterator();
        while (it.hasNext()) {
            lendBack lb = it.next();
            if (ArrayListAll.lendBackList.size() > 13) {
                it.remove();
                tempList.add(lb);
                i = 0;
            } else {
                i++;
                UtilJFrame.createJLabelText(lb.getNowTime() + "     " + lb.getUsername() + "   ", 30, 60 + 50 * i, 700, 40, lb.getBookName(), 28, this);
            }
        }

        Iterator<lendBack> tempIt = tempList.iterator();
        while (tempIt.hasNext()) {
            lendBack lb = tempIt.next();
            tempIt.remove();
            ArrayListAll.lendBackList.add(lb);
        }
        UtilJFrame.saveLendBack();
        exitLendBook = UtilJFrame.createJButton("img\\退出界面划出.png", 240, 780, 296, 107, this, this);

        manageImg();

        this.getContentPane().repaint();
    }

    //1.3.2初始化图书状态查询页面
    public void initSearchState() {
        this.getContentPane().removeAll();

        UtilJFrame.createJLabelText("---------------损耗与借阅信息-----------------", 30, 20, 750, 50, "", 38, this);

        UtilJFrame.createJLabelText("损耗图书                  ", 117, 70, 700, 60, "已借图书", 36, this);

        int i = 0;
        int j = 0;
        ArrayList<Book> tempList1 = new ArrayList<>();
        ArrayList<Book> tempList2 = new ArrayList<>();
        Iterator<Book> it = ArrayListAll.bookList.iterator();
        while (it.hasNext()) {
            Book lb = it.next();
            if (ArrayListAll.bookList.size() > 16 && lb.getIsDamage().equals("是")) {
                it.remove();
                tempList1.add(lb);
                i = 0;
            } else if (ArrayListAll.bookList.size() > 16 && lb.getIs_lend().equals("是")) {
                it.remove();
                tempList2.add(lb);
                j = 0;
            } else {
                if (lb.getIsDamage().equals("是")) {
                    i++;
                    UtilJFrame.createJLabelText(lb.getBook_name(), 133, 90 + 40 * i, 280, 40, "", 28, this);
                }
                if (lb.getIs_lend().equals("是")) {
                    j++;
                    UtilJFrame.createJLabelText(lb.getBook_name(), 459, 90 + 40 * j, 280, 40, "", 28, this);
                }
            }
        }

        Iterator<Book> tempIt1 = tempList1.iterator();
        while (tempIt1.hasNext()) {
            Book book = tempIt1.next();
            ArrayListAll.bookList.add(book);
            tempIt1.remove();
        }

        Iterator<Book> tempIt2 = tempList2.iterator();
        while (tempIt2.hasNext()) {
            Book book = tempIt2.next();
            ArrayListAll.bookList.add(book);
            tempIt2.remove();
        }

        exitLendBook = UtilJFrame.createJButton("img\\退出界面划出.png", 240, 780, 296, 107, this, this);

        manageImg();

        this.getContentPane().repaint();
    }

    //-----------------------------------反馈管理界面-----------------------------------------------
    //1.4初始化反馈管理界面
    public void initManageFeedback() {
        this.getContentPane().removeAll();

        //给反馈界面添加按钮
        searchFeedback = UtilJFrame.createJButton("img\\反馈记录查询划出.png", 240, 250, 296, 107, this, this);

        exitFeedback = UtilJFrame.createJButton("img\\退出界面划出.png", 240, 620, 296, 107, this, this);

        manageImg();

        this.getContentPane().repaint();
    }

    //1.4.1初始化查询反馈记录页面
    public void searchFeedback() throws SQLException {

        //输入的字符不多于25个字,如果集合的长度大于12,从第一个消息开始清理,计数器归1
        this.getContentPane().removeAll();

        UtilJFrame.createJLabelText("-----------------反馈信息查询------------------", 30, 20, 750, 50, "", 38, this);

        //临时集合用来存储删除的元素
        ArrayList<String> tempList = new ArrayList<>();
        int i = 1;
        Iterator<String> it = ArrayListAll.feedbackList.iterator();
        while (it.hasNext()) {
            String str = it.next();
            if (ArrayListAll.feedbackList.size() > 12) {
                i = 0;
                it.remove();
                tempList.add(str);
            } else {
                UtilJFrame.createJLabelText(Integer.toString(i), 50, 80 + 50 * i, 600, 30, ":   " + str, 20, this);
            }
            i++;
        }

        Iterator<String> tempIt = tempList.iterator();
        while (tempIt.hasNext()) {
            String str = tempIt.next();
            tempIt.remove();
            ArrayListAll.feedbackList.add(str);
        }
        UtilJFrame.saveFeedback();
        exitFeedback = UtilJFrame.createJButton("img\\退出界面划出.png", 240, 720, 296, 107, this, this);

        manageImg();

        this.getContentPane().repaint();
    }

    //预约信息查询的代码
    public void yuYueSearch() throws SQLException {
        this.getContentPane().removeAll();
        UtilJFrame.createJLabelText("---------------预约信息列表--------------", 50, 0, 800, 70, "", 40, this);

        Iterator<String> it = ArrayListAll.yuYueList.iterator();

        int i = 0;
        ArrayList<String> tempList = new ArrayList<>();
        while (it.hasNext()) {
            String str = it.next();
            if (ArrayListAll.yuYueList.size() > 15) {
                it.remove();
                tempList.add(str);
            } else {
                UtilJFrame.createJLabelText(str, 80, 60 + 50 * i, 800, 50, "", 26, this);
                i++;
            }
        }

        Iterator<String> tempIt = tempList.iterator();
        while (tempIt.hasNext()) {
            String str = tempIt.next();
            ArrayListAll.yuYueList.add(str);
            tempIt.remove();
        }
        UtilJFrame.saveYuYue();
        exitYuYue = UtilJFrame.createJButton("img\\退出界面划出.png", 280, 800, 200, 70, this, this);
        manageImg();
        this.getContentPane().repaint();
    }

    //-------------------------------------鼠标监听------------------------------------------------
    //鼠标点击事件
    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == saveExit) {
            try {
                System.out.println("数据库已断开!");
                UtilJFrame.connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.exit(1);
        } else if (obj == exitDeleteBook || obj == exitDamageBook || obj == exitAlterBook
                || obj == exitSearchBook || obj == exitAddBook || obj == managerBook_Button || obj == exitYuYue) {
            System.out.println("图书管理");
            //初始化图书管理界面
            initManageBook();
        } else if (obj == addBook || obj == againAdd) {
            System.out.println("添加图书");
            //加载添加图书的界面
            initAddBook();
        } else if (obj == rightAddBook) {
            System.out.println("确认提交图书");
            try {
                addBookList();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (obj == searchBook || obj == againSearchBook) {
            System.out.println("查找图书");
            //首先加载查找索引页面
            //再加载查找页面
            initSearchIdBook();
        } else if (obj == rightSearchBookId) {
            System.out.println("输入编号后进入查询界面");
            initSearchBook();
        } else if (obj == alterBook) {
            System.out.println("修改图书");
            //首先加载查找索引页面
            //再加载修改页面
            initAlterIdBook();
        } else if (obj == againAlterBook) {
            initAlterIdBook();
        } else if (obj == rightAlterBookId) {
            System.out.println("进入修改界面");
            initAlterBook();
        } else if (obj == rightAlterBook) {
            System.out.println("确定修改");
            try {
                initAlterBookDialog();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (obj == damageBook) {
            System.out.println("破损统计");
            initDamageBook();
        } else if (obj == deleteBook) {
            System.out.println("删除图书");
            initDeleteIdBook();
        } else if (obj == rightDeleteBookId) {
            System.out.println("进入删除图书界面");
            try {
                initDeleteBook();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (obj == managerUser_Button || obj == exitAddUser || obj == exitSearchUser
                || obj == exitAlterUser || obj == exitDeleteUser) {
            System.out.println("用户管理");
            //初始化用户管理界面
            initManageUser();
        } else if (obj == addUser || obj == againAddUser) {
            System.out.println("添加用户");
            initAddUser();
        } else if (obj == rightAddUser) {
            System.out.println("确定添加用户");
            try {
                initAddUserDialog();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (obj == searchUser || obj == againSearchUser) {
            System.out.println("查找用户");
            initSearchIdUser();
        } else if (obj == rightSearchUserId) {
            System.out.println("确定查询用户");
            initSearchUser();
        } else if (obj == alterUser || obj == againAlterUser) {
            System.out.println("修改用户");
            initAlterIdUser();
        } else if (obj == rightAlterUserId) {
            System.out.println("修改用户编号正确");
            initAlterUser();
        } else if (obj == rightAlterUser) {
            System.out.println("确定提交修改结果");
            try {
                initAlterUserDialog();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (obj == deleteUser) {
            System.out.println("删除用户");
            initDeleteIdUser();
        } else if (obj == rightDeleteUserId) {
            System.out.println("确定删除");
            try {
                initDeleteUser();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (obj == managerLend_Button) {
            System.out.println("借阅管理");
            //初始化借阅管理界面
            initManageLend();
        } else if (obj == exitLendBook) {
            initManageLend();
        } else if (obj == lendCount) {
            System.out.println("借阅记录查询");
            try {
                initLendBook();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (obj == bookState) {
            System.out.println("图书状态查询");
            initSearchState();
        } else if (obj == managerFeedback_Button) {
            System.out.println("反馈管理");
            //初始化反馈管理界面
            initManageFeedback();
        } else if (obj == searchFeedback) {
            System.out.println("反馈记录查询");
            try {
                searchFeedback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (obj == exitManageBook || obj == exitManageUser
                || obj == exitManageLend || obj == exitFeedback) {
            //退回主菜单且保存数据
            initJFrame();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    //鼠标划入事件
    @Override
    public void mouseEntered(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == managerBook_Button) {
            managerBook_Button.setIcon(new ImageIcon("img\\图书管理划入.png"));
        } else if (obj == managerUser_Button) {
            managerUser_Button.setIcon(new ImageIcon("img\\用户管理划入.png"));
        } else if (obj == managerLend_Button) {
            managerLend_Button.setIcon(new ImageIcon("img\\借阅管理划入.png"));
        } else if (obj == managerFeedback_Button) {
            managerFeedback_Button.setIcon(new ImageIcon("img\\反馈管理划入.png"));
        } else if (obj == exitManageBook) {
            exitManageBook.setIcon(new ImageIcon("img\\退出界面划入.png"));
        } else if (obj == addBook) {
            addBook.setIcon(new ImageIcon("img\\增加图书划入.png"));
        } else if (obj == searchBook) {
            searchBook.setIcon(new ImageIcon("img\\查询图书划入.png"));
        } else if (obj == alterBook) {
            alterBook.setIcon(new ImageIcon("img\\修改图书划入.png"));
        } else if (obj == damageBook) {
            damageBook.setIcon(new ImageIcon("img\\损耗统计划入.png"));
        } else if (obj == deleteBook) {
            deleteBook.setIcon(new ImageIcon("img\\删除图书划入.png"));
        } else if (obj == addUser) {
            addUser.setIcon(new ImageIcon("img\\增加用户划入.png"));
        } else if (obj == searchUser) {
            searchUser.setIcon(new ImageIcon("img\\查询用户划入.png"));
        } else if (obj == alterUser) {
            alterUser.setIcon(new ImageIcon("img\\修改用户划入.png"));
        } else if (obj == deleteUser) {
            deleteUser.setIcon(new ImageIcon("img\\删除用户划入.png"));
        } else if (obj == exitManageUser) {
            exitManageUser.setIcon(new ImageIcon("img\\退出界面划入.png"));
        } else if (obj == lendCount) {
            lendCount.setIcon(new ImageIcon("img\\借阅记录查询划入.png"));
        } else if (obj == bookState) {
            bookState.setIcon(new ImageIcon("img\\图书状态查询划入.png"));
        } else if (obj == exitManageLend) {
            exitManageLend.setIcon(new ImageIcon("img\\退出界面划入.png"));
        } else if (obj == searchFeedback) {
            searchFeedback.setIcon(new ImageIcon("img\\反馈记录查询划入.png"));
        } else if (obj == exitFeedback) {
            exitFeedback.setIcon(new ImageIcon("img\\退出界面划入.png"));
        } else if (obj == againAdd) {
            againAdd.setIcon(new ImageIcon("img\\继续添加划入.png"));
        } else if (obj == rightAddBook) {
            rightAddBook.setIcon(new ImageIcon("img\\确认提交划入.png"));
        } else if (obj == exitAddBook) {
            exitAddBook.setIcon(new ImageIcon("img\\退出添加划入.png"));
        } else if (obj == rightSearchBookId) {
            rightSearchBookId.setIcon(new ImageIcon("img\\确认提交划入.png"));
        } else if (obj == againSearchBook) {
            againSearchBook.setIcon(new ImageIcon("img\\继续查询划入.png"));
        } else if (obj == exitSearchBook) {
            exitSearchBook.setIcon(new ImageIcon("img\\退出查询划入.png"));
        } else if (obj == rightAlterBook) {
            rightAlterBook.setIcon(new ImageIcon("img\\确认修改划入.png"));
        } else if (obj == againAlterBook) {
            againAlterBook.setIcon(new ImageIcon("img\\继续修改划入.png"));
        } else if (obj == exitAlterBook) {
            exitAlterBook.setIcon(new ImageIcon("img\\退出修改划入.png"));
        } else if (obj == rightAlterBookId) {
            rightAlterBookId.setIcon(new ImageIcon("img\\确认提交划入.png"));
        } else if (obj == exitDamageBook) {
            exitDamageBook.setIcon(new ImageIcon("img\\退出界面划入.png"));
        } else if (obj == exitDeleteBook) {
            exitDeleteBook.setIcon(new ImageIcon("img\\退出界面划入.png"));
        } else if (obj == rightDeleteBookId) {
            rightDeleteBookId.setIcon(new ImageIcon("img\\确认提交划入.png"));
        } else if (obj == againAddUser) {
            againAddUser.setIcon(new ImageIcon("img\\继续添加划入.png"));
        } else if (obj == rightAddUser) {
            rightAddUser.setIcon(new ImageIcon("img\\确认提交划入.png"));
        } else if (obj == exitAddUser) {
            exitAddUser.setIcon(new ImageIcon("img\\退出添加划入.png"));
        } else if (obj == againSearchUser) {
            againSearchUser.setIcon(new ImageIcon("img\\继续查询划入.png"));
        } else if (obj == rightSearchUserId) {
            rightSearchUserId.setIcon(new ImageIcon("img\\确认提交划入.png"));
        } else if (obj == exitSearchUser) {
            exitSearchUser.setIcon(new ImageIcon("img\\退出查询划入.png"));
        } else if (obj == rightAlterUserId) {
            rightAlterUserId.setIcon(new ImageIcon("img\\确认提交划入.png"));
        } else if (obj == againAlterUser) {
            againAlterUser.setIcon(new ImageIcon("img\\继续修改划入.png"));
        } else if (obj == rightAlterUser) {
            rightAlterUser.setIcon(new ImageIcon("img\\确认修改划入.png"));
        } else if (obj == exitAlterUser) {
            exitAlterUser.setIcon(new ImageIcon("img\\退出修改划入.png"));
        } else if (obj == rightDeleteUserId) {
            rightDeleteUserId.setIcon(new ImageIcon("img\\确认提交划入.png"));
        } else if (obj == exitDeleteUser) {
            exitDeleteUser.setIcon(new ImageIcon("img\\退出界面划入.png"));
        } else if (obj == exitYuYue) {
            exitYuYue.setIcon(new ImageIcon("img\\退出界面划入.png"));
        } else if (obj == exitLendBook) {
            exitLendBook.setIcon(new ImageIcon("img\\退出界面划入.png"));
        }
    }

    //鼠标划出事件
    @Override
    public void mouseExited(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == managerBook_Button) {
            managerBook_Button.setIcon(new ImageIcon("img\\图书管理划出.png"));
        } else if (obj == managerUser_Button) {
            managerUser_Button.setIcon(new ImageIcon("img\\用户管理划出.png"));
        } else if (obj == managerLend_Button) {
            managerLend_Button.setIcon(new ImageIcon("img\\借阅管理划出.png"));
        } else if (obj == managerFeedback_Button) {
            managerFeedback_Button.setIcon(new ImageIcon("img\\反馈管理划出.png"));
        } else if (obj == exitManageBook) {
            exitManageBook.setIcon(new ImageIcon("img\\退出界面划出.png"));
        } else if (obj == addBook) {
            addBook.setIcon(new ImageIcon("img\\增加图书划出.png"));
        } else if (obj == searchBook) {
            searchBook.setIcon(new ImageIcon("img\\查询图书划出.png"));
        } else if (obj == alterBook) {
            alterBook.setIcon(new ImageIcon("img\\修改图书划出.png"));
        } else if (obj == damageBook) {
            damageBook.setIcon(new ImageIcon("img\\损耗统计划出.png"));
        } else if (obj == deleteBook) {
            deleteBook.setIcon(new ImageIcon("img\\删除图书划出.png"));
        } else if (obj == addUser) {
            addUser.setIcon(new ImageIcon("img\\增加用户划出.png"));
        } else if (obj == searchUser) {
            searchUser.setIcon(new ImageIcon("img\\查询用户划出.png"));
        } else if (obj == alterUser) {
            alterUser.setIcon(new ImageIcon("img\\修改用户划出.png"));
        } else if (obj == deleteUser) {
            deleteUser.setIcon(new ImageIcon("img\\删除用户划出.png"));
        } else if (obj == exitManageUser) {
            exitManageUser.setIcon(new ImageIcon("img\\退出界面划出.png"));
        } else if (obj == lendCount) {
            lendCount.setIcon(new ImageIcon("img\\借阅记录查询划出.png"));
        } else if (obj == bookState) {
            bookState.setIcon(new ImageIcon("img\\图书状态查询划出.png"));
        } else if (obj == exitManageLend) {
            exitManageLend.setIcon(new ImageIcon("img\\退出界面划出.png"));
        } else if (obj == searchFeedback) {
            searchFeedback.setIcon(new ImageIcon("img\\反馈记录查询划出.png"));
        } else if (obj == exitFeedback) {
            exitFeedback.setIcon(new ImageIcon("img\\退出界面划出.png"));
        } else if (obj == againAdd) {
            againAdd.setIcon(new ImageIcon("img\\继续添加划出.png"));
        } else if (obj == rightAddBook) {
            rightAddBook.setIcon(new ImageIcon("img\\确认提交划出.png"));
        } else if (obj == exitAddBook) {
            exitAddBook.setIcon(new ImageIcon("img\\退出添加划出.png"));
        } else if (obj == rightSearchBookId) {
            rightSearchBookId.setIcon(new ImageIcon("img\\确认提交划出.png"));
        } else if (obj == againSearchBook) {
            againSearchBook.setIcon(new ImageIcon("img\\继续查询划出.png"));
        } else if (obj == exitSearchBook) {
            exitSearchBook.setIcon(new ImageIcon("img\\退出查询划出.png"));
        } else if (obj == rightAlterBook) {
            rightAlterBook.setIcon(new ImageIcon("img\\确认修改划出.png"));
        } else if (obj == againAlterBook) {
            againAlterBook.setIcon(new ImageIcon("img\\继续修改划出.png"));
        } else if (obj == exitAlterBook) {
            exitAlterBook.setIcon(new ImageIcon("img\\退出修改划出.png"));
        } else if (obj == rightAlterBookId) {
            rightAlterBookId.setIcon(new ImageIcon("img\\确认提交划出.png"));
        } else if (obj == exitDamageBook) {
            exitDamageBook.setIcon(new ImageIcon("img\\退出界面划出.png"));
        } else if (obj == exitDeleteBook) {
            exitDeleteBook.setIcon(new ImageIcon("img\\退出界面划出.png"));
        } else if (obj == rightDeleteBookId) {
            rightDeleteBookId.setIcon(new ImageIcon("img\\确认提交划出.png"));
        } else if (obj == againAddUser) {
            againAddUser.setIcon(new ImageIcon("img\\继续添加划出.png"));
        } else if (obj == rightAddUser) {
            rightAddUser.setIcon(new ImageIcon("img\\确认提交划出.png"));
        } else if (obj == exitAddUser) {
            exitAddUser.setIcon(new ImageIcon("img\\退出添加划出.png"));
        } else if (obj == againSearchUser) {
            againSearchUser.setIcon(new ImageIcon("img\\继续查询划出.png"));
        } else if (obj == rightSearchUserId) {
            rightSearchUserId.setIcon(new ImageIcon("img\\确认提交划出.png"));
        } else if (obj == exitSearchUser) {
            exitSearchUser.setIcon(new ImageIcon("img\\退出查询划出.png"));
        } else if (obj == rightAlterUserId) {
            rightAlterUserId.setIcon(new ImageIcon("img\\确认提交划出.png"));
        } else if (obj == againAlterUser) {
            againAlterUser.setIcon(new ImageIcon("img\\继续修改划出.png"));
        } else if (obj == rightAlterUser) {
            rightAlterUser.setIcon(new ImageIcon("img\\确认修改划出.png"));
        } else if (obj == exitAlterUser) {
            exitAlterUser.setIcon(new ImageIcon("img\\退出修改划出.png"));
        } else if (obj == rightDeleteUserId) {
            rightDeleteUserId.setIcon(new ImageIcon("img\\确认提交划出.png"));
        } else if (obj == exitDeleteUser) {
            exitDeleteUser.setIcon(new ImageIcon("img\\退出界面划出.png"));
        } else if (obj == exitYuYue) {
            exitYuYue.setIcon(new ImageIcon("img\\退出界面划出.png"));
        } else if (obj == exitLendBook) {
            exitLendBook.setIcon(new ImageIcon("img\\退出界面划出.png"));
        }
    }

    //行为监听事件用于菜单行为捕捉
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == reLogin) {
            System.out.println("重新登陆");
            this.setVisible(false);
            new LoginJFrame();
        } else if (obj == register) {
            System.out.println("注册");
            this.setVisible(false);
            new RegisterJFrame();
        } else if (obj == yuYue) {
            System.out.println("预约信息");
            try {
                yuYueSearch();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
