package presentationLayer.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ModifyItemView extends JFrame {
    private JTextField titleField;
    private JTextField ratingField;
    private JTextField caloriesField;
    private JTextField proteinsField;
    private JTextField fatsField;
    private JTextField sodiumField;
    private JTextField priceField;
    private JButton okButton;

    public ModifyItemView(){
        this.setBounds(100, 100, 640, 480);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Modify Menu Item");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setBounds(201, 45, 221, 30);
        this.getContentPane().add(lblNewLabel);

        JLabel lblTitle = new JLabel("Title");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTitle.setBounds(36, 108, 99, 30);
        this.getContentPane().add(lblTitle);

        titleField = new JTextField();
        titleField.setColumns(10);
        titleField.setBounds(145, 108, 410, 30);
        this.getContentPane().add(titleField);

        JLabel lblRating = new JLabel("Rating");
        lblRating.setHorizontalAlignment(SwingConstants.CENTER);
        lblRating.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblRating.setBounds(36, 149, 99, 30);
        this.getContentPane().add(lblRating);

        ratingField = new JTextField();
        ratingField.setColumns(10);
        ratingField.setBounds(145, 149, 410, 30);
        this.getContentPane().add(ratingField);

        JLabel lblCalories = new JLabel("Calories");
        lblCalories.setHorizontalAlignment(SwingConstants.CENTER);
        lblCalories.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCalories.setBounds(36, 190, 99, 30);
        this.getContentPane().add(lblCalories);

        caloriesField = new JTextField();
        caloriesField.setColumns(10);
        caloriesField.setBounds(145, 190, 410, 30);
        this.getContentPane().add(caloriesField);

        JLabel lblProteins = new JLabel("Proteins");
        lblProteins.setHorizontalAlignment(SwingConstants.CENTER);
        lblProteins.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblProteins.setBounds(36, 231, 99, 30);
        this.getContentPane().add(lblProteins);

        proteinsField = new JTextField();
        proteinsField.setColumns(10);
        proteinsField.setBounds(145, 231, 410, 30);
        this.getContentPane().add(proteinsField);

        JLabel lblFats = new JLabel("Fats");
        lblFats.setHorizontalAlignment(SwingConstants.CENTER);
        lblFats.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblFats.setBounds(36, 272, 99, 30);
        this.getContentPane().add(lblFats);

        fatsField = new JTextField();
        fatsField.setColumns(10);
        fatsField.setBounds(145, 272, 410, 30);
        this.getContentPane().add(fatsField);

        JLabel lblSodium = new JLabel("Sodium");
        lblSodium.setHorizontalAlignment(SwingConstants.CENTER);
        lblSodium.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSodium.setBounds(36, 313, 99, 30);
        this.getContentPane().add(lblSodium);

        sodiumField = new JTextField();
        sodiumField.setColumns(10);
        sodiumField.setBounds(145, 313, 410, 30);
        this.getContentPane().add(sodiumField);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPrice.setBounds(36, 354, 99, 30);
        this.getContentPane().add(lblPrice);

        priceField = new JTextField();
        priceField.setColumns(10);
        priceField.setBounds(145, 354, 410, 30);
        this.getContentPane().add(priceField);

        okButton = new JButton("OK");
        okButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        okButton.setBounds(237, 395, 150, 30);
        this.getContentPane().add(okButton);
    }

    public void okBtnListener(ActionListener action){
        this.okButton.addActionListener(action);
    }

    public String getTitleField() {
        return titleField.getText();
    }

    public String getRatingField() {
        return ratingField.getText();
    }

    public String getCaloriesField() {
        return caloriesField.getText();
    }

    public String getProteinsField() {
        return proteinsField.getText();
    }

    public String getFatsField() {
        return fatsField.getText();
    }

    public String getSodiumField() {
        return sodiumField.getText();
    }

    public String getPriceField() {
        return priceField.getText();
    }

    public void refresh(){
        titleField.setText(null);
        ratingField.setText(null);
        caloriesField.setText(null);
        proteinsField.setText(null);
        fatsField.setText(null);
        sodiumField.setText(null);
        proteinsField.setText(null);
        priceField.setText(null);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
