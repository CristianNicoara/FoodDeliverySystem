package presentationLayer.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdministratorView extends JFrame {

    private JTextField startHourField;
    private JTextField endHourField;
    private JTextField nrOfOrdersField;
    private JTextField nrOrdersPerClientField;
    private JTextField orderValueField;
    private JTextField orderDayField;
    private JTable table;
    private JButton importBtn;
    private JButton addItemBtn;
    private JButton modifyItemBtn;
    private JButton deleteBtn;
    private JButton btnAddCompositeItem;
    private JButton generateBtn;
    private JScrollPane scrollPane;

    public AdministratorView(){
        this.setBounds(100, 100, 640, 480);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel adminLabel = new JLabel("Administrator View");
        adminLabel.setHorizontalAlignment(SwingConstants.CENTER);
        adminLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        adminLabel.setBounds(198, 11, 228, 30);
        this.getContentPane().add(adminLabel);

        importBtn = new JButton("Import Products");
        importBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        importBtn.setBounds(30, 67, 194, 30);
        this.getContentPane().add(importBtn);

        addItemBtn = new JButton("Add Menu Item");
        addItemBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        addItemBtn.setBounds(30, 108, 194, 30);
        this.getContentPane().add(addItemBtn);

        modifyItemBtn = new JButton("Modify Menu Item");
        modifyItemBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        modifyItemBtn.setBounds(30, 149, 194, 30);
        this.getContentPane().add(modifyItemBtn);

        deleteBtn = new JButton("Delete Menu Item");
        deleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        deleteBtn.setBounds(30, 190, 194, 30);
        this.getContentPane().add(deleteBtn);

        JPanel panel = new JPanel();
        panel.setBounds(268, 67, 346, 363);
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

        generateBtn = new JButton("Generate Report");
        generateBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        generateBtn.setBounds(30, 400, 194, 30);
        this.getContentPane().add(generateBtn);

        JLabel startEndHourLabel = new JLabel("Start/End Hour:");
        startEndHourLabel.setBounds(45, 267, 84, 20);
        this.getContentPane().add(startEndHourLabel);

        startHourField = new JTextField();
        startHourField.setBounds(126, 267, 44, 20);
        this.getContentPane().add(startHourField);
        startHourField.setColumns(10);

        endHourField = new JTextField();
        endHourField.setColumns(10);
        endHourField.setBounds(180, 267, 44, 20);
        this.getContentPane().add(endHourField);

        JLabel lblNrOfOrders = new JLabel("Nr. Of Orders: ");
        lblNrOfOrders.setBounds(45, 298, 84, 20);
        this.getContentPane().add(lblNrOfOrders);

        nrOfOrdersField = new JTextField();
        nrOfOrdersField.setColumns(10);
        nrOfOrdersField.setBounds(126, 298, 98, 20);
        this.getContentPane().add(nrOfOrdersField);

        JLabel lblNrOfOrdersPerClient = new JLabel("Nr. Of Orders/Client: ");
        lblNrOfOrdersPerClient.setBounds(45, 323, 104, 20);
        this.getContentPane().add(lblNrOfOrdersPerClient);

        nrOrdersPerClientField = new JTextField();
        nrOrdersPerClientField.setColumns(10);
        nrOrdersPerClientField.setBounds(159, 323, 65, 20);
        this.getContentPane().add(nrOrdersPerClientField);

        JLabel lblOrderValue = new JLabel("Order Value: ");
        lblOrderValue.setBounds(45, 348, 84, 20);
        this.getContentPane().add(lblOrderValue);

        orderValueField = new JTextField();
        orderValueField.setColumns(10);
        orderValueField.setBounds(126, 348, 98, 20);
        this.getContentPane().add(orderValueField);

        btnAddCompositeItem = new JButton("Add Composite Item");
        btnAddCompositeItem.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnAddCompositeItem.setBounds(30, 226, 194, 30);
        this.getContentPane().add(btnAddCompositeItem);

        JLabel lblOrderDay = new JLabel("Order Day: ");
        lblOrderDay.setBounds(45, 379, 84, 20);
        this.getContentPane().add(lblOrderDay);

        orderDayField = new JTextField();
        orderDayField.setToolTipText("");
        orderDayField.setHorizontalAlignment(SwingConstants.CENTER);
        orderDayField.setColumns(10);
        orderDayField.setBounds(126, 379, 98, 20);
        this.getContentPane().add(orderDayField);
    }

    public String getStartHourField() {
        return startHourField.getText();
    }

    public String getEndHourField() {
        return endHourField.getText();
    }

    public String getNrOfOrdersField() {
        return nrOfOrdersField.getText();
    }

    public String getNrOrdersPerClientField() {
        return nrOrdersPerClientField.getText();
    }

    public String getOrderValueField() {
        return orderValueField.getText();
    }

    public String getDayField() {
        return orderDayField.getText();
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

    public void importBtnListener(ActionListener action){
        this.importBtn.addActionListener(action);
    }

    public void addBtnListener(ActionListener action){
        this.addItemBtn.addActionListener(action);
    }

    public void modifyBtnListener(ActionListener action){
        this.modifyItemBtn.addActionListener(action);
    }

    public void deleteBtnListener(ActionListener action){
        this.deleteBtn.addActionListener(action);
    }

    public void addCompositeBtnListener(ActionListener action){
        this.btnAddCompositeItem.addActionListener(action);
    }

    public void generateBtnListener(ActionListener action){
        this.generateBtn.addActionListener(action);
    }

    public boolean isSelected(){
        if (table.getSelectedRow() >= 0)
            return true;
        return false;
    }

    public boolean areSelected(){
        if (table.getSelectedRows().length >= 2)
            return true;
        return false;
    }

    public void refresh(){
        startHourField.setText(null);
        endHourField.setText(null);
        nrOfOrdersField.setText(null);
        nrOrdersPerClientField.setText(null);
        orderValueField.setText(null);
        orderDayField.setText(null);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
