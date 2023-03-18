package presentationLayer.view;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class EmployeeView extends JFrame  implements Observer {

    public EmployeeView(){
        this.setBounds(100, 100, 640, 480);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel employeeViewLbl = new JLabel("Employee View");
        employeeViewLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
        employeeViewLbl.setHorizontalAlignment(SwingConstants.CENTER);
        employeeViewLbl.setBounds(176, 11, 243, 25);
        this.getContentPane().add(employeeViewLbl);
    }

    @Override
    public void update(Observable o, Object arg) {
        showMessage((String) arg);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
