package com.progame.jFrame;

import com.progame.ArrayListAll;
import com.progame.book.Book;
import com.progame.book.lendBack;
import com.progame.book.lendBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class UserJFrame extends JFrame implements ActionListener, MouseListener {

    //给界面添加菜单
    JMenuBar jb = new JMenuBar();
    JMenu jm = new JMenu("菜单");
    JMenuItem ji = new JMenuItem("重新登陆");

    //主界面按钮
    JButton booking, lend, back, feedback;

    //预约图书按钮
    JButton rightBook, exitBook;

    //借阅图书按钮
    JButton rightLend, exitLend;

    JTextField bookName;

    String tempName;

    //归还图书按钮
    JButton rightBack, exitBack;

    //反馈按钮
    JButton rightFeed, exitFeed;

    //创建图书管理系统的主界面
    public UserJFrame() {
        //设置界面的宽高
        this.setSize(722, 950);
        //设置界面的标题
        this.setTitle("用户系统1.0");
        //设置界面的图标
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\图标.png"));
        //设置界面至于屏幕最上方
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置界面的关闭方式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setLayout(null);
        ji.addActionListener(this);
        jm.add(ji);
        jb.add(jm);
        this.setJMenuBar(jb);

        initUserJFrame();

        for (String str : ArrayListAll.tempUserNameList) {
            tempName = str;
        }

        //设置界面可视化
        this.setVisible(true);
    }

    //初始化界面
    public void initUserJFrame() {
        this.getContentPane().removeAll();
        //给用户界面增加按钮

        booking = UtilJFrame.createJButton("img\\预约图书划出.png", 130, 100, 206, 74, this, this);

        lend = UtilJFrame.createJButton("img\\借阅图书划出.png", 330, 300, 206, 74, this, this);

        back = UtilJFrame.createJButton("img\\归还图书划出.png", 130, 500, 206, 74, this, this);

        feedback = UtilJFrame.createJButton("img\\反馈划出.png", 330, 700, 206, 74, this, this);

        UtilJFrame.createJLabelImage("img\\用户界面.png", 0, 0, 722, 1090, this);

        this.getContentPane().repaint();
    }

    //-----------------------------------------------预约图书-------------------------------------------------------
    //初始化预约图书界面
    public void initBook() throws SQLException {
        this.getContentPane().removeAll();

        //确认借阅按钮
        rightBook = UtilJFrame.createJButton("img\\确认用户划出.png", 370, 230, 150, 50, this, this);
        //退出按钮
        exitBook = UtilJFrame.createJButton("img\\退出用户划出.png", 545, 230, 150, 50, this, this);

        //文本框
        bookName = UtilJFrame.createJTextField(385, 170, 280, 40, this);
        //已被借阅的图书
        UtilJFrame.createJLabelText("------------可预约图书列表-----------", 25, 15, 800, 70, "", 44, this);
        int i = 0;
        ArrayList<Book> tempList = new ArrayList<>();

        Iterator<Book> it = ArrayListAll.bookList.iterator();
        while (it.hasNext()) {
            Book book = it.next();
            if (ArrayListAll.bookList.size() > 12) {
                it.remove();
                tempList.add(book);
                i = 0;
            } else {
                i++;
                UtilJFrame.createJLabelText(book.getBook_theme() + ":    ", 10, 95 + 40 * i, 500, 30, book.getBook_name(), 26, this);
            }
        }

        Iterator<Book> tempIt = tempList.iterator();
        while (tempIt.hasNext()) {
            Book book = tempIt.next();
            ArrayListAll.bookList.add(book);
            tempIt.remove();
        }
        UtilJFrame.saveBook();
        if (i == 0) {
            UtilJFrame.createJLabelText("无可预约的图书", 20, 150, 500, 60, "", 40, this);
        }

        UtilJFrame.createJLabelImage("img\\预约图书界面.png", -50, -30, 767, 1090, this);

        this.getContentPane().repaint();
    }

    //初始化预约图书的逻辑
    public void initBookName() throws SQLException {

        String book_name = bookName.getText();

        if (book_name.isEmpty()) {
            UtilJFrame.createJDialog("书名为空!");
        } else {
            boolean flag = false;
            for (Book book : ArrayListAll.bookList) {
                if (book.getBook_name().equals(book_name)) {

                    flag = true;

                    LocalDateTime nowTime = LocalDateTime.now();
                    ArrayListAll.yuYueList.add(nowTime.getYear() + "." + nowTime.getMonthValue() + "." + nowTime.getDayOfMonth() + "  " + nowTime.getHour() + ":" + nowTime.getMinute() + ":" + nowTime.getSecond() + "   " + tempName + "    " + book_name);
                    UtilJFrame.saveYuYue();
                    initBook();
                    UtilJFrame.createJDialog("预约成功!");
                    break;
                }
            }
            if (!flag) {
                UtilJFrame.createJDialog("预约失败!");
            }
        }

    }

    //----------------------------------------------借阅图书---------------------------------------------------------
    //初始化借阅图书界面
    public void initLend() throws SQLException {
        this.getContentPane().removeAll();

        //确认借阅按钮
        rightLend = UtilJFrame.createJButton("img\\确认用户划出.png", 360, 250, 150, 50, this, this);
        //退出按钮
        exitLend = UtilJFrame.createJButton("img\\退出用户划出.png", 535, 250, 150, 50, this, this);

        //文本框
        bookName = UtilJFrame.createJTextField(365, 190, 300, 40, this);
        //未被借阅的图书
        UtilJFrame.createJLabelText("------------可借阅图书列表-----------", 25, 20, 800, 70, "", 44, this);
        int i = 0;
        ArrayList<Book> tempList = new ArrayList<>();

        Iterator<Book> it = ArrayListAll.bookList.iterator();
        while (it.hasNext()) {
            Book book = it.next();
            if (ArrayListAll.bookList.size() > 12 && book.getIs_lend().equals("否")) {
                it.remove();
                tempList.add(book);
                i = 0;
            } else {
                if (book.getIs_lend().equals("否")) {
                    i++;
                    UtilJFrame.createJLabelText(book.getBook_theme() + ":    ", 10, 115 + 40 * i, 500, 30, book.getBook_name(), 26, this);
                }
            }
        }

        Iterator<Book> tempIt = tempList.iterator();
        while (tempIt.hasNext()) {
            Book book = tempIt.next();
            ArrayListAll.bookList.add(book);
            tempIt.remove();
        }

        UtilJFrame.saveBook();

        if (i == 0) {
            UtilJFrame.createJLabelText("无可借阅的图书", 20, 180, 500, 60, "", 36, this);
        }

        UtilJFrame.createJLabelImage("img\\借阅图书界面.png", -70, -100, 767, 1090, this);

        this.getContentPane().repaint();
    }

    //初始化借阅图书的逻辑
    public void initBookLend() throws SQLException {


        String book_name = bookName.getText();

        if (book_name.isEmpty()) {
            UtilJFrame.createJDialog("书名为空!");
        } else {
            boolean flag = false;
            for (Book book : ArrayListAll.bookList) {
                if (book.getBook_name().equals(book_name) && book.getIs_lend().equals("否")) {
                    flag = true;

                    book.setIs_lend("是");
                    LocalDateTime nowTime = LocalDateTime.now();
                    book.setLendTime(nowTime.getYear() + "-" + nowTime.getMonthValue() + "-" + nowTime.getDayOfMonth() + "  " + nowTime.getHour() + ":" + nowTime.getMinute() + ":" + nowTime.getSecond());
                    book.setBackTime("0000-00-00");
                    ArrayListAll.lendBackList.add(new lendBack(tempName, "[lend]" + book_name, nowTime.getYear() + "." + nowTime.getMonthValue() + "." + nowTime.getDayOfMonth() + "  " + nowTime.getHour() + ":" + nowTime.getMinute() + ":" + nowTime.getSecond()));
                    ArrayListAll.lendBookList.add(new lendBook(tempName, book_name));
                    UtilJFrame.saveLendBook();
                    UtilJFrame.saveLendBack();
                    UtilJFrame.saveBook();
                    initLend();
                    UtilJFrame.createJDialog("借阅成功!");
                    break;
                }
            }
            if (!flag) {
                UtilJFrame.createJDialog("借阅失败!");
            }
        }

    }

    //----------------------------------------------归还图书----------------------------------------------------------
    //初始化归还图书界面
    public void initBack() throws SQLException {
        this.getContentPane().removeAll();

        //确认归还按钮
        rightBack = UtilJFrame.createJButton("img\\确认用户划出.png", 360, 250, 150, 50, this, this);
        //退出按钮
        exitBack = UtilJFrame.createJButton("img\\退出用户划出.png", 535, 250, 150, 50, this, this);

        //文本框
        bookName = UtilJFrame.createJTextField(365, 190, 300, 40, this);
        //未被归还的图书
        UtilJFrame.createJLabelText("------------未归还图书列表-----------", 25, 20, 800, 70, "", 44, this);
        int i = 0;

        ArrayList<lendBook> tempList = new ArrayList<>();
        Iterator<lendBook> it = ArrayListAll.lendBookList.iterator();
        while (it.hasNext()) {
            lendBook lb = it.next();
            if (ArrayListAll.lendBookList.size() > 10) {
                it.remove();
                tempList.add(lb);
                i = 0;
            } else {
                if (lb.getUsername().equals(tempName)) {
                    i++;
                    UtilJFrame.createJLabelText(lb.getBookName(), 50, 95 + 50 * i, 500, 30, " ", 26, this);
                }
            }
        }

        Iterator<lendBook> tempIt = tempList.iterator();
        while (tempIt.hasNext()) {
            lendBook lb = tempIt.next();
            ArrayListAll.lendBookList.add(lb);
            tempIt.remove();
        }
        UtilJFrame.saveLendBook();
        if (i == 0) {
            UtilJFrame.createJLabelText("无可归还的图书", 20, 180, 500, 60, "", 36, this);
        }

        UtilJFrame.createJLabelImage("img\\归还图书界面.png", -70, -70, 767, 1090, this);

        this.getContentPane().repaint();
    }

    //归还图书的代码
    public void initBackBook() throws SQLException {

        String book_name = bookName.getText();

        if (book_name.isEmpty()) {
            UtilJFrame.createJDialog("书名为空!");
        } else {
            boolean flag = false;
            for (Book book : ArrayListAll.bookList) {
                if (book.getBook_name().equals(book_name) && book.getIs_lend().equals("是")) {
                    flag = true;
                    book.setIs_lend("否");
                    LocalDateTime nowTime = LocalDateTime.now();
                    book.setLendTime("0000-00-00");
                    book.setBackTime(nowTime.getYear() + "-" + nowTime.getMonthValue() + "-" + nowTime.getDayOfMonth() + "  " + nowTime.getHour() + ":" + nowTime.getMinute() + ":" + nowTime.getSecond());
                    ArrayListAll.lendBackList.add(new lendBack(tempName, "[back]  " + book_name, nowTime.getYear() + "." + nowTime.getMonthValue() + "." + nowTime.getDayOfMonth() + "  " + nowTime.getHour() + ":" + nowTime.getMinute() + ":" + nowTime.getSecond()));
                    Iterator<lendBook> it = ArrayListAll.lendBookList.iterator();
                    while (it.hasNext()) {
                        lendBook lb = it.next();
                        if (lb.getBookName().equals(book_name)) {
                            it.remove();
                        }
                    }
                    UtilJFrame.saveLendBook();
                    UtilJFrame.saveLendBack();
                    UtilJFrame.saveBook();
                    initBack();
                    UtilJFrame.createJDialog("归还成功!");
                    break;
                }
            }
            if (!flag) {
                UtilJFrame.createJDialog("归还失败!");
            }
        }


    }

    //----------------------------------------------反馈界面----------------------------------------------------------
    //初始化反馈界面
    public void initFeedBack() {
        this.getContentPane().removeAll();

        //确认归还按钮
        rightFeed = UtilJFrame.createJButton("img\\确认用户划出.png", 400, 250, 150, 50, this, this);
        //退出按钮
        exitFeed = UtilJFrame.createJButton("img\\退出用户划出.png", 400, 400, 150, 50, this, this);

        //文本框
        bookName = UtilJFrame.createJTextField(40, 190, 300, 100, this);
        //未被归还的图书
        UtilJFrame.createJLabelText("-------------------反馈--------------------", 25, 20, 800, 70, "", 44, this);

        UtilJFrame.createJLabelText("注意:不多于20个字!", 20, 300, 500, 60, "", 36, this);


        UtilJFrame.createJLabelImage("img\\反馈界面.png", -10, -70, 767, 1090, this);

        this.getContentPane().repaint();
    }

    //反馈代码实现
    public void initFeed() throws SQLException {
        String text = bookName.getText();
        if (text.isEmpty()) {
            UtilJFrame.createJDialog("反馈为空!");
        } else {
            UtilJFrame.createJDialog("反馈成功!");
            ArrayListAll.feedbackList.add("[" + tempName + "]:  " + text);
            UtilJFrame.saveFeedback();

            initFeedBack();
        }
    }

    //----------------------------------------------鼠标监听----------------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == ji) {
            this.setVisible(false);
            new LoginJFrame();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == booking) {
            System.out.println("点击申请");
            try {
                initBook();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (obj == rightBook) {
            System.out.println("确定预约");
            try {
                initBookName();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (obj == lend) {
            System.out.println("点击借阅");
            try {
                initLend();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (obj == rightLend) {
            System.out.println("确定借阅");
            try {
                initBookLend();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (obj == back) {
            System.out.println("点击归还");
            try {
                initBack();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (obj == rightBack) {
            System.out.println("确认归还");
            try {
                initBackBook();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (obj == feedback) {
            System.out.println("点击反馈");
            initFeedBack();
        } else if (obj == rightFeed) {
            try {
                initFeed();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (obj == exitBook || obj == exitLend || obj == exitBack || obj == exitFeed) {
            System.out.println("退出界面");
            initUserJFrame();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == booking) {
            booking.setIcon(new ImageIcon("img\\预约图书划入.png"));
        } else if (obj == lend) {
            lend.setIcon(new ImageIcon("img\\借阅图书划入.png"));
        } else if (obj == back) {
            back.setIcon(new ImageIcon("img\\归还图书划入.png"));
        } else if (obj == feedback) {
            feedback.setIcon(new ImageIcon("img\\反馈划入.png"));
        } else if (obj == rightBook) {
            rightBook.setIcon(new ImageIcon("img\\确认用户划入.png"));
        } else if (obj == exitBook) {
            exitBook.setIcon(new ImageIcon("img\\退出用户划入.png"));
        } else if (obj == rightLend) {
            rightLend.setIcon(new ImageIcon("img\\确认用户划入.png"));
        } else if (obj == exitLend) {
            exitLend.setIcon(new ImageIcon("img\\退出用户划入.png"));
        } else if (obj == rightBack) {
            rightBack.setIcon(new ImageIcon("img\\确认用户划入.png"));
        } else if (obj == exitBack) {
            exitBack.setIcon(new ImageIcon("img\\退出用户划入.png"));
        } else if (obj == rightFeed) {
            rightFeed.setIcon(new ImageIcon("img\\确认用户划入.png"));
        } else if (obj == exitFeed) {
            exitFeed.setIcon(new ImageIcon("img\\退出用户划入.png"));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == booking) {
            booking.setIcon(new ImageIcon("img\\预约图书划出.png"));
        } else if (obj == lend) {
            lend.setIcon(new ImageIcon("img\\借阅图书划出.png"));
        } else if (obj == back) {
            back.setIcon(new ImageIcon("img\\归还图书划出.png"));
        } else if (obj == feedback) {
            feedback.setIcon(new ImageIcon("img\\反馈划出.png"));
        } else if (obj == rightBook) {
            rightBook.setIcon(new ImageIcon("img\\确认用户划出.png"));
        } else if (obj == exitBook) {
            exitBook.setIcon(new ImageIcon("img\\退出用户划出.png"));
        } else if (obj == rightLend) {
            rightLend.setIcon(new ImageIcon("img\\确认用户划出.png"));
        } else if (obj == exitLend) {
            exitLend.setIcon(new ImageIcon("img\\退出用户划出.png"));
        } else if (obj == rightBack) {
            rightBack.setIcon(new ImageIcon("img\\确认用户划出.png"));
        } else if (obj == exitBack) {
            exitBack.setIcon(new ImageIcon("img\\退出用户划出.png"));
        } else if (obj == rightFeed) {
            rightFeed.setIcon(new ImageIcon("img\\确认用户划出.png"));
        } else if (obj == exitFeed) {
            exitFeed.setIcon(new ImageIcon("img\\退出用户划出.png"));
        }
    }
}
