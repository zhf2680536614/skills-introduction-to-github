package com.progame.jFrame;

import com.progame.ArrayListAll;
import com.progame.Manager.Manager;
import com.progame.User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Iterator;

public class LoginJFrame extends JFrame implements ActionListener, MouseListener {

    //给界面添加菜单
    JMenuBar jb = new JMenuBar();
    JMenu jm = new JMenu("菜单");
    JMenuItem ji = new JMenuItem("退出登录");

    //增加登录按钮
    JButton login_Button, menage_Button, register_Button, codeImage;

    JTextField username, code;

    JPasswordField password;

    String randomCode;

    String name, new_password, new_code;

    //创建登录界面
    public LoginJFrame() {
        //设置界面的宽高
        this.setSize(805, 458);
        //设置界面的标题
        this.setTitle("登录1.0");
        //设置界面的图标
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\图标.png"));
        //设置界面至于屏幕最上方
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置界面的关闭方式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //设置界面的样式为0
        this.setLayout(null);

        ji.addActionListener(this);
        jm.add(ji);
        jb.add(jm);
        this.setJMenuBar(jb);

        //初始化登录界面
        initLoginJFrame();
        this.setVisible(true);
    }

    //生成随机验证码
    public void initCode() {
        randomCode = UtilJFrame.createCode();
        UtilJFrame.createJLabelText("", 603, 177, 200, 50, randomCode, 34, this);
    }

    //初始化登录界面
    public void initLoginJFrame() {
        this.getContentPane().removeAll();

        initCode();

        //给界面添加文本框
        //给界面添加文本输入框
        username = UtilJFrame.createJTextField(245, 42, 200, 40, this);

        //给界面添加密码输入框
        password = UtilJFrame.createJPasswordField(245, 125, 200, 40, this);

        //给界面添加验证码输入框
        code = UtilJFrame.createJTextField(245, 205, 200, 40, this);

        //给登录界面添加按钮
        login_Button = UtilJFrame.createJButton("img\\登录划出.png", 90, 310, 150, 52, this, this);

        menage_Button = UtilJFrame.createJButton("img\\管理员划出.png", 318, 310, 150, 52, this, this);

        register_Button = UtilJFrame.createJButton("img\\注册划出.png", 540, 310, 150, 52, this, this);

        UtilJFrame.createJLabelImage("img\\name.png", 50, 35, 180, 50, this);

        UtilJFrame.createJLabelImage("img\\password.png", 50, 120, 180, 50, this);

        codeImage = UtilJFrame.createJButton("img\\code.png", 50, 200, 180, 50, this, this);

        //初始化登录界面
        JLabel jlabel = new JLabel(new ImageIcon("img\\登录界面.png"));
        jlabel.setBounds(0, 0, 805, 458);
        this.getContentPane().add(jlabel);

        this.getContentPane().repaint();
    }

    //实现管理员登录的逻辑
    public void loginManager() {
        name = username.getText();
        new_password = password.getText();
        new_code = code.getText();


        for (Manager manager : ArrayListAll.managerList) {
            if (!new_code.equalsIgnoreCase(randomCode)) {
                UtilJFrame.createJDialog("验证码错误!");
                break;
            } else if (!name.equalsIgnoreCase(manager.getManager_Name()) || !new_password.equals(manager.getManager_Password())) {
                UtilJFrame.createJDialog("用户名或密码错误!");
                break;
            } else {
                this.setVisible(false);
                new ManagerJFrame();
                break;
            }
        }

    }

    //实现用户登录的逻辑
    public void loginUser() {
        name = username.getText();
        new_password = password.getText();
        new_code = code.getText();
        boolean flag = false;

        if (new_code.equalsIgnoreCase(randomCode)) {
            for (User user : ArrayListAll.userList) {
                if (name.equalsIgnoreCase(user.getUser_Name()) && new_password.equals(user.getUser_Password())) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                this.setVisible(false);
                Iterator<String> it = ArrayListAll.tempUserNameList.iterator();
                while(it.hasNext()){
                    it.next();
                    it.remove();
                }
                ArrayListAll.tempUserNameList.add(name);
                new UserJFrame();
            } else {
                UtilJFrame.createJDialog("用户名或密码错误!");
            }

        } else {
            UtilJFrame.createJDialog("验证码错误!");
        }


    }

    //行为监听事件
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == ji) {
            try {
                System.out.println("数据库已断开!");
                UtilJFrame.connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.exit(1);
        }
    }

    //鼠标单机事件
    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == login_Button) {
            //实现用户登录逻辑
            loginUser();
        } else if (obj == register_Button) {
            this.setVisible(false);
            new RegisterJFrame();
        } else if (obj == menage_Button) {
            //实现管理员登录的逻辑
            loginManager();
        } else if (obj == codeImage) {
            initLoginJFrame();
            System.out.println("刷新验证码");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    //鼠标划入
    @Override
    public void mouseEntered(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == login_Button) {
            login_Button.setIcon(new ImageIcon("img\\登录划入.png"));
        } else if (obj == register_Button) {
            register_Button.setIcon(new ImageIcon("img\\注册划入.png"));
        } else if (obj == menage_Button) {
            menage_Button.setIcon(new ImageIcon("img\\管理员划入.png"));
        } else if (obj == codeImage) {
            codeImage.setIcon(new ImageIcon("img\\codeIn.png"));
        }
    }

    //鼠标划出
    @Override
    public void mouseExited(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == login_Button) {
            login_Button.setIcon(new ImageIcon("img\\登录划出.png"));
        } else if (obj == register_Button) {
            register_Button.setIcon(new ImageIcon("img\\注册划出.png"));
        } else if (obj == menage_Button) {
            menage_Button.setIcon(new ImageIcon("img\\管理员划出.png"));
        } else if (obj == codeImage) {
            codeImage.setIcon(new ImageIcon("img\\code.png"));
        }
    }

}
