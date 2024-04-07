package com.progame.jFrame;

import com.progame.ArrayListAll;
import com.progame.User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class RegisterJFrame extends JFrame implements ActionListener, MouseListener {

    //验证码图片
    JButton codeImage;
    JTextField username, code;
    JPasswordField password, againPassword;
    //增加登录按钮
    JButton login_Button, register_Button;

    //给界面添加菜单
    JMenuBar jb = new JMenuBar();
    JMenu jm = new JMenu("菜单");
    JMenuItem ji = new JMenuItem("退出注册");

    String new_name, new_password, new_againPassword, new_code;

    String random;

    //创建随机验证码界面
    public void initCode() {
        random = UtilJFrame.createCode();
        UtilJFrame.createJLabelText("", 610, 253, 150, 50, random, 36, this);
    }

    public RegisterJFrame() {
        //设置界面的宽高
        this.setSize(812, 530);
        //设置界面的标题
        this.setTitle("注册1.0");
        //设置界面至于屏幕最上方
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置界面的图标
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\图标.png"));
        this.setLayout(null);
        //设置界面的关闭方式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ji.addActionListener(this);
        jm.add(ji);
        jb.add(jm);
        this.setJMenuBar(jb);

        initRegister();

        this.setVisible(true);
    }

    //初始化界面
    public void initRegister() {
        this.getContentPane().removeAll();

        login_Button = UtilJFrame.createJButton("img\\登录划出.png", 525, 360, 150, 52, this, this);

        register_Button = UtilJFrame.createJButton("img\\注册划出.png", 120, 360, 150, 52, this, this);

        //给界面添加文本输入框
        username = UtilJFrame.createJTextField(400, 20, 200, 40, this);

        //给界面添加密码输入框
        password = UtilJFrame.createJPasswordField(400, 99, 200, 40, this);

        //给界面添加密码输入框
        againPassword = UtilJFrame.createJPasswordField(400, 180, 200, 40, this);

        //给界面添加验证码输入框
        code = UtilJFrame.createJTextField(400, 260, 200, 40, this);

        UtilJFrame.createJLabelImage("img\\name.png", 190, 15, 180, 50, this);

        UtilJFrame.createJLabelImage("img\\password.png", 190, 93, 180, 50, this);

        UtilJFrame.createJLabelImage("img\\again.png", 190, 173, 180, 50, this);

        codeImage = UtilJFrame.createJButton("img\\code.png", 190, 253, 180, 50, this, this);

        initCode();

        //初始化注册界面
        UtilJFrame.createJLabelImage("img\\注册界面.png", 0, 0, 812, 530, this);

        this.getContentPane().repaint();
    }

    //注册的代码逻辑
    public void successRegister() throws SQLException {
        new_name = username.getText();
        new_password = password.getText();
        new_againPassword = againPassword.getText();
        new_code = code.getText();

        boolean flag = false;

        if (new_code.equalsIgnoreCase(random)) {
            for (User user : ArrayListAll.userList) {
                if (user.getUser_Name().equals(new_name)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                UtilJFrame.createJDialog("用户名重复!");
            } else {
                if (new_password.equals(new_againPassword) && !new_password.isEmpty()) {
                    UtilJFrame.createJDialog("注册成功!");
                    ArrayListAll.userList.add(new User(new_name, new_password));
                    UtilJFrame.saveUser();
                } else {
                    UtilJFrame.createJDialog("密码不一致!");
                }
            }
        } else {
            UtilJFrame.createJDialog("验证码错误!");
        }


    }

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
        if (obj == codeImage) {
            System.out.println("刷新验证码");
            initRegister();
        } else if (obj == login_Button) {
            this.setVisible(false);
            new LoginJFrame();
        } else if (obj == register_Button) {
            try {
                successRegister();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
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
        if (obj == codeImage) {
            codeImage.setIcon(new ImageIcon("img\\codeIn.png"));
        }
        if (obj == login_Button) {
            login_Button.setIcon(new ImageIcon("img\\登录划入.png"));
        } else if (obj == register_Button) {
            register_Button.setIcon(new ImageIcon("img\\注册划入.png"));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == codeImage) {
            codeImage.setIcon(new ImageIcon("img\\code.png"));
        }
        if (obj == login_Button) {
            login_Button.setIcon(new ImageIcon("img\\登录划出.png"));
        } else if (obj == register_Button) {
            register_Button.setIcon(new ImageIcon("img\\注册划出.png"));
        }
    }

}
