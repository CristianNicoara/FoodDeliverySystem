package presentationLayer.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientView extends JFrame {

    private JTable table;
    private JTextField keywordField;
    private JTextField ratingField;
    private JTextField caloriesField;
    private JTextField proteinsField;
    private JTextField fatsField;
    private JTextField sodiumField;
    private JTextField priceField;
    private JButton viewProductsBtn;
    private JButton searchBtn;
    private JButton btnCreateOrder;
    private JScrollPane scrollPane;

    public ClientView(){
        this.setBounds(100, 100, 640, 480);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(260, 57, 354, 373);
        this.getContentPane().add(panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0};
        gbl_panel.rowHeights = new int[]{0, 0};
        gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);

        scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 0;
        panel.add(scrollPane, gbc_scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        JLabel lblClientView = new JLabel("Client View");
        lblClientView.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblClientView.setHorizontalAlignment(SwingConstants.CENTER);
        lblClientView.setBounds(213, 11, 190, 24);
        this.getContentPane().add(lblClientView);

        viewProductsBtn = new JButton("View Products");
        viewProductsBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        viewProductsBtn.setBounds(58, 57, 150, 31);
        this.getContentPane().add(viewProductsBtn);

        JLabel lblSearchCriteria = new JLabel("Search Criteria:");
        lblSearchCriteria.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSearchCriteria.setHorizontalAlignment(SwingConstants.CENTER);
        lblSearchCriteria.setBounds(69, 99, 133, 24);
        this.getContentPane().add(lblSearchCriteria);

        JLabel lblKeyword = new JLabel("Keyword");
        lblKeyword.setHorizontalAlignment(SwingConstants.CENTER);
        lblKeyword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblKeyword.setBounds(26, 134, 71, 20);
        this.getContentPane().add(lblKeyword);

        JLabel lblRating = new JLabel("Rating");
        lblRating.setHorizontalAlignment(SwingConstants.CENTER);
        lblRating.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblRating.setBounds(26, 165, 71, 20);
        this.getContentPane().add(lblRating);

        JLabel lblCalories = new JLabel("Calories");
        lblCalories.setHorizontalAlignment(SwingConstants.CENTER);
        lblCalories.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCalories.setBounds(26, 196, 71, 20);
        this.getContentPane().add(lblCalories);

        JLabel lblProteins = new JLabel("Proteins");
        lblProteins.setHorizontalAlignment(SwingConstants.CENTER);
        lblProteins.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProteins.setBounds(26, 227, 71, 20);
        this.getContentPane().add(lblProteins);

        JLabel lblFats = new JLabel("Fats");
        lblFats.setHorizontalAlignment(SwingConstants.CENTER);
        lblFats.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblFats.setBounds(26, 258, 71, 20);
        this.getContentPane().add(lblFats);

        JLabel lblSodium = new JLabel("Sodium");
        lblSodium.setHorizontalAlignment(SwingConstants.CENTER);
        lblSodium.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSodium.setBounds(26, 289, 71, 20);
        this.getContentPane().add(lblSodium);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPrice.setBounds(26, 320, 71, 20);
        this.getContentPane().add(lblPrice);

        keywordField = new JTextField();
        keywordField.setBounds(107, 134, 107, 20);
        this.getContentPane().add(keywordField);
        keywordField.setColumns(10);

        ratingField = new JTextField();
        ratingField.setColumns(10);
        ratingField.setBounds(107, 167, 107, 20);
        this.getContentPane().add(ratingField);

        caloriesField = new JTextField();
        caloriesField.setColumns(10);
        caloriesField.setBounds(107, 198, 107, 20);
        this.getContentPane().add(caloriesField);

        proteinsField = new JTextField();
        proteinsField.setColumns(10);
        proteinsField.setBounds(107, 229, 107, 20);
        this.getContentPane().add(proteinsField);

        fatsField = new JTextField();
        fatsField.setColumns(10);
        fatsField.setBounds(107, 260, 107, 20);
        this.getContentPane().add(fatsField);

        sodiumField = new JTextField();
        sodiumField.setColumns(10);
        sodiumField.setBounds(107, 291, 107, 20);
        this.getContentPane().add(sodiumField);

        priceField = new JTextField();
        priceField.setColumns(10);
        priceField.setBounds(107, 322, 107, 20);
        this.getContentPane().add(priceField);

        searchBtn = new JButton("Search");
        searchBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        searchBtn.setBounds(58, 351, 150, 31);
        this.getContentPane().add(searchBtn);

        btnCreateOrder = new JButton("Create Order");
        btnCreateOrder.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnCreateOrder.setBounds(58, 393, 150, 31);
        this.getContentPane().add(btnCreateOrder);
    }

    public String getKeywordField() {
        return keywordField.getText();
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

    public void viewProductsBtnListener(ActionListener action){
        this.viewProductsBtn.addActionListener(action);
    }

    public void searchBtnListener(ActionListener action){
        this.searchBtn.addActionListener(action);
    }

    public void createOrderBtnListener(ActionListener action){
        this.btnCreateOrder.addActionListener(action);
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public void showTable(JTable table){
        scrollPane.setViewportView(table);
    }

    public boolean selected(){
        if (table.getSelectedRows().length >= 1)
            return true;
        return false;
    }

    public void refresh(){
        keywordField.setText(null);
        ratingField.setText(null);
        caloriesField.setText(null);
        proteinsField.setText(null);
        fatsField.setText(null);
        sodiumField.setText(null);
        priceField.setText(null);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
