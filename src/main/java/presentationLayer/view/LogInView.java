package presentationLayer.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LogInView extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton logInBtn;
    private JButton registerBtn;

    public LogInView(){
        this.setBounds(100, 100, 640, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel logInLabel = new JLabel("Log-In View");
        logInLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        logInLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logInLabel.setBounds(212, 24, 200, 32);
        this.getContentPane().add(logInLabel);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        usernameLabel.setBounds(90, 136, 139, 24);
        this.getContentPane().add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setHorizontalAlignment(SwingConstants.CENTER);
        usernameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        usernameField.setBounds(258, 137, 259, 26);
        this.getContentPane().add(usernameField);
        usernameField.setColumns(10);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        passwordLabel.setBounds(90, 186, 139, 24);
        this.getContentPane().add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        passwordField.setBounds(258, 180, 259, 24);
        this.getContentPane().add(passwordField);

        logInBtn = new JButton("Log In");
        logInBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        logInBtn.setBounds(136, 272, 139, 32);
        this.getContentPane().add(logInBtn);

        registerBtn = new JButton("Register");
        registerBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        registerBtn.setBounds(351, 272, 139, 32);
        this.getContentPane().add(registerBtn);

        this.setVisible(true);
    }

    public String getUsernameField(){
        return usernameField.getText();
    }

    public String getPasswordField(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < passwordField.getPassword().length; i++){
            sb.append(passwordField.getPassword()[i]);
        }
        return sb.toString();
    }

    public void logInBtnListener(ActionListener action){
        this.logInBtn.addActionListener(action);
    }

    public void registerBtnListener(ActionListener action){
        this.registerBtn.addActionListener(action);
    }

    public void refresh(){
        passwordField.setText(null);
        usernameField.setText(null);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
