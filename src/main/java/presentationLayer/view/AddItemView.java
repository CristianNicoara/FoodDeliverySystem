package presentationLayer.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddItemView extends JFrame {
    private JTextField titleField;
    private JTextField ratingField;
    private JTextField caloriesField;
    private JTextField proteinsField;
    private JTextField fatsField;
    private JTextField sodiumField;
    private JTextField priceField;
    private JButton okButton;

    public AddItemView(){
        this.setBounds(100, 100, 640, 480);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Add Menu Item");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(201, 25, 221, 30);
        this.getContentPane().add(lblNewLabel);

        JLabel lblTitle = new JLabel("Title");
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(48, 88, 99, 30);
        this.getContentPane().add(lblTitle);

        JLabel lblRating = new JLabel("Rating");
        lblRating.setHorizontalAlignment(SwingConstants.CENTER);
        lblRating.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblRating.setBounds(48, 129, 99, 30);
        this.getContentPane().add(lblRating);

        JLabel lblCalories = new JLabel("Calories");
        lblCalories.setHorizontalAlignment(SwingConstants.CENTER);
        lblCalories.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCalories.setBounds(48, 170, 99, 30);
        this.getContentPane().add(lblCalories);

        JLabel lblProteins = new JLabel("Proteins");
        lblProteins.setHorizontalAlignment(SwingConstants.CENTER);
        lblProteins.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblProteins.setBounds(48, 211, 99, 30);
        this.getContentPane().add(lblProteins);

        JLabel lblFats = new JLabel("Fats");
        lblFats.setHorizontalAlignment(SwingConstants.CENTER);
        lblFats.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblFats.setBounds(48, 252, 99, 30);
        this.getContentPane().add(lblFats);

        JLabel lblSodium = new JLabel("Sodium");
        lblSodium.setHorizontalAlignment(SwingConstants.CENTER);
        lblSodium.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSodium.setBounds(48, 293, 99, 30);
        this.getContentPane().add(lblSodium);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPrice.setBounds(48, 334, 99, 30);
        this.getContentPane().add(lblPrice);

        titleField = new JTextField();
        titleField.setBounds(157, 88, 410, 30);
        this.getContentPane().add(titleField);
        titleField.setColumns(10);

        ratingField = new JTextField();
        ratingField.setColumns(10);
        ratingField.setBounds(157, 129, 410, 30);
        this.getContentPane().add(ratingField);

        caloriesField = new JTextField();
        caloriesField.setColumns(10);
        caloriesField.setBounds(157, 170, 410, 30);
        this.getContentPane().add(caloriesField);

        proteinsField = new JTextField();
        proteinsField.setColumns(10);
        proteinsField.setBounds(157, 211, 410, 30);
        this.getContentPane().add(proteinsField);

        fatsField = new JTextField();
        fatsField.setColumns(10);
        fatsField.setBounds(157, 252, 410, 30);
        this.getContentPane().add(fatsField);

        sodiumField = new JTextField();
        sodiumField.setColumns(10);
        sodiumField.setBounds(157, 293, 410, 30);
        this.getContentPane().add(sodiumField);

        priceField = new JTextField();
        priceField.setColumns(10);
        priceField.setBounds(157, 334, 410, 30);
        this.getContentPane().add(priceField);

        okButton = new JButton("OK");
        okButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        okButton.setBounds(237, 375, 150, 30);
        this.getContentPane().add(okButton);
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

    public void okBtnListener(ActionListener action){
        this.okButton.addActionListener(action);
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
