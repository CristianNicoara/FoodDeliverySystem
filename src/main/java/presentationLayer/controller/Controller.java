package presentationLayer.controller;

import businessLayer.*;
import presentationLayer.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class Controller {
    private AdministratorView administratorView;
    private ClientView clientView;
    private EmployeeView employeeView;
    private LogInView logInView;
    private AddItemView addItemView;
    private ModifyItemView modifyItemView;
    private DeliveryService deliveryService;
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    int nrMeniu = 1;
    int nrOrder = 1;

    public Controller(AdministratorView administratorView, ClientView clientView, EmployeeView employeeView, LogInView logInView, AddItemView addItemView, ModifyItemView modifyItemView) {
        this.administratorView = administratorView;
        this.clientView = clientView;
        this.employeeView = employeeView;
        this.logInView = logInView;
        this.addItemView = addItemView;
        this.modifyItemView = modifyItemView;
        this.deliveryService = new DeliveryService();
        deserialize();
        if (deliveryService.getUsers() == null){
            deliveryService.setUsers(new ArrayList<>());
            deliveryService.getUsers().add(new User("cristian","1234", "administrator"));
            deliveryService.getUsers().add(new User("employee1", "1", "employee"));
            deliveryService.getUsers().add(new User("employee2", "12", "employee"));
            deliveryService.getUsers().add(new User("employee3", "13", "employee"));
            deliveryService.getUsers().add(new User("employee4", "14", "employee"));
        }

        logInView.logInBtnListener(new LogIn());
        logInView.registerBtnListener(e->{
            if (!logInView.getPasswordField().equals("") && !logInView.getUsernameField().equals("")) {
                boolean isRegistered = false;
                for (User user : deliveryService.getUsers()) {
                    if (user.getPassword().equals(logInView.getPasswordField()) && user.getUsername().equals(logInView.getUsernameField())) {
                        isRegistered = true;
                    }
                }
                if (!isRegistered) {
                    deliveryService.getUsers().add(new User(logInView.getUsernameField(), logInView.getPasswordField(), "client"));
                    serialize();
                } else
                    logInView.showMessage("User is already registered!");
            }else if (logInView.getUsernameField().equals(""))
                logInView.showMessage("Introduce the new username");
            else
                logInView.showMessage("Introduce the new password");

            });
        administratorView.importBtnListener(new ImportProducts());
        administratorView.addBtnListener(new AddProduct());
        administratorView.modifyBtnListener(new ModifyProduct());
        administratorView.deleteBtnListener(new DeleteProduct());
        administratorView.addCompositeBtnListener(new AddCompositeProduct());

        clientView.viewProductsBtnListener(new ViewProducts());
        clientView.createOrderBtnListener(new CreateOrder());
        administratorView.generateBtnListener(new GenerateReport());
        clientView.searchBtnListener(new SearchProducts());

    }

    public void deserialize(){
        try {
            fileInputStream = new FileInputStream("delivery.ser");
            objectInputStream = new ObjectInputStream(fileInputStream);
            deliveryService = (DeliveryService) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception exception){

        }
    }

    public void serialize(){
        try {
            fileOutputStream = new FileOutputStream("delivery.ser");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(deliveryService);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception exception) {

        }
    }

    public String[] generateColumns(){
        try (BufferedReader br = new BufferedReader(new FileReader("products.csv"))){
            String line = br.readLine();
            String columns[] = line.split(",");
            return columns;
        } catch (IOException exception){
            exception.printStackTrace();
        }
        return null;
    }

    public JTable createTable(ArrayList<MenuItem> menuItems){
        String columns[] = generateColumns();
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < menuItems.size(); i++){
            data.add(new ArrayList<>());
            for (Field field : menuItems.get(i).getClass().getSuperclass().getDeclaredFields()){
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(menuItems.get(i));
                    data.get(i).add(value.toString());
                } catch (IllegalArgumentException exception){
                    exception.printStackTrace();
                } catch (IllegalAccessException exception){
                    exception.printStackTrace();
                }
            }
        }
        String data1[][] = new String[data.size()][data.get(0).size()];
        for (int i = 0; i < data.size(); i++){
            for (int j = 0; j < data.get(i).size(); j++){
                data1[i][j] = data.get(i).get(j);
            }
        }
        JTable table = new JTable(data1,columns);

        return table;
    }

    class LogIn implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean isRegistered = false;
            for (User user : deliveryService.getUsers()) {
                if (user.getPassword().equals(logInView.getPasswordField()) && user.getUsername().equals(logInView.getUsernameField())) {
                    isRegistered = true;
                    if (user.getRole().equals("administrator")) {
                        deserialize();
                        if (deliveryService.getProducts() != null){
                            ArrayList<MenuItem> items = deliveryService.getProducts();
                            JTable table = createTable(items);
                            administratorView.setTable(table);
                            administratorView.showTable(table);
                        }
                        administratorView.setVisible(true);
                    }
                    else if (user.getRole().equals("employee")) {
                        deliveryService.addObserver(employeeView);
                        employeeView.setVisible(true);
                    }
                    else
                        clientView.setVisible(true);
                }
            }
            if (!isRegistered){
                logInView.showMessage("User is not registered!\nPlease register before logging into the account!");
            }
        }
    }

    class ImportProducts implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                final JFileChooser fileChooser = new JFileChooser();
                int response = fileChooser.showOpenDialog(null);
                if (response == JFileChooser.APPROVE_OPTION){
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    ArrayList<MenuItem> menuItems = deliveryService.importProducts(file.getName());
                    JTable table = createTable(menuItems);
                    administratorView.setTable(table);
                    administratorView.showTable(table);
                    serialize();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    class AddProduct implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            addItemView.setVisible(true);
            addItemView.okBtnListener(ok->{
                String title = addItemView.getTitleField();
                String ratingS = addItemView.getRatingField();
                String caloriesS = addItemView.getCaloriesField();
                String proteinsS = addItemView.getProteinsField();
                String fatsS = addItemView.getFatsField();
                String sodiumS = addItemView.getSodiumField();
                String priceS = addItemView.getPriceField();
                if (!title.equals("") && !ratingS.equals("") && !caloriesS.equals("") && !proteinsS.equals("") && !fatsS.equals("") && !sodiumS.equals("") && !priceS.equals("")){
                    double rating = Double.parseDouble(ratingS);
                    double calories = Double.parseDouble(caloriesS);
                    double proteins = Double.parseDouble(proteinsS);
                    double fats = Double.parseDouble(fatsS);
                    double sodium = Double.parseDouble(sodiumS);
                    double price = Double.parseDouble(priceS);
                    deliveryService.addProduct(new BaseProduct(title,rating,calories,proteins,fats,sodium,price));
                    addItemView.refresh();
                    addItemView.setVisible(false);
                    JTable table = createTable(deliveryService.getProducts());
                    administratorView.setTable(table);
                    administratorView.showTable(table);
                    serialize();
                } else {
                    addItemView.showMessage("Complete all the fields!");
                }
            });
        }
    }

    class ModifyProduct implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (administratorView.isSelected()) {
                modifyItemView.setVisible(true);
                modifyItemView.okBtnListener(ok -> {
                    String title = modifyItemView.getTitleField();
                    String ratingS = modifyItemView.getRatingField();
                    String caloriesS = modifyItemView.getCaloriesField();
                    String proteinsS = modifyItemView.getProteinsField();
                    String fatsS = modifyItemView.getFatsField();
                    String sodiumS = modifyItemView.getSodiumField();
                    String priceS = modifyItemView.getPriceField();
                    if (!title.equals("") && !ratingS.equals("") && !caloriesS.equals("") && !proteinsS.equals("") && !fatsS.equals("") && !sodiumS.equals("") && !priceS.equals("")) {
                        double rating = Double.parseDouble(ratingS);
                        double calories = Double.parseDouble(caloriesS);
                        double proteins = Double.parseDouble(proteinsS);
                        double fats = Double.parseDouble(fatsS);
                        double sodium = Double.parseDouble(sodiumS);
                        double price = Double.parseDouble(priceS);
                        String product = (String) administratorView.getTable().getValueAt(administratorView.getTable().getSelectedRow(),0);
                        deliveryService.modifyProduct(product,new BaseProduct(title,rating,calories,proteins,fats,sodium,price));
                        modifyItemView.refresh();
                        modifyItemView.setVisible(false);
                        JTable table = createTable(deliveryService.getProducts());
                        administratorView.setTable(table);
                        administratorView.showTable(table);
                        serialize();
                    }
                });
            } else
                administratorView.showMessage("Select a product to be modified!");
        }
    }

    class DeleteProduct implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (administratorView.isSelected()) {
                String product = (String) administratorView.getTable().getValueAt(administratorView.getTable().getSelectedRow(),0);
                //ArrayList<MenuItem> products = deliveryService.getProducts();
                deliveryService.deleteProduct(product);
                //deliveryService.setProducts(products);
                JTable table = createTable(deliveryService.getProducts());
                administratorView.setTable(table);
                administratorView.showTable(table);
                serialize();
            } else
                administratorView.showMessage("Select a product to be deleted!");
        }
    }

    class AddCompositeProduct implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (administratorView.areSelected()){
                ArrayList<MenuItem> menuItems = deliveryService.getProducts();
                ArrayList<MenuItem> compositeProducts = new ArrayList<>();
                for (int i = 0; i < administratorView.getTable().getSelectedRows().length; i++){
                    String name = (String) administratorView.getTable().getValueAt(administratorView.getTable().getSelectedRows()[i],0);
                    for (MenuItem menuItem : menuItems){
                        if(menuItem.getTitle().equals(name)){
                            compositeProducts.add(menuItem);
                            break;
                        }
                    }
                }
                String name = "Daily Menu " +  nrMeniu++;
                double rating = 0, calories = 0, proteins = 0, fats = 0, sodium = 0, price = 0;
                for (MenuItem menuItem: compositeProducts){
                    rating += menuItem.getRating() ;
                    calories += menuItem.getCalories();
                    proteins += menuItem.getProteins();
                    fats += menuItem.getFats();
                    sodium += menuItem.getSodium();
                    price += menuItem.computePrice();
                }
                deliveryService.addProduct(new CompositeProduct(name,rating/compositeProducts.size(),calories,proteins,fats,sodium,price*0.9,compositeProducts));
                JTable table = createTable(deliveryService.getProducts());
                administratorView.setTable(table);
                administratorView.showTable(table);
                serialize();
            } else
                administratorView.showMessage("Select two or more rows");
        }
    }

    class ViewProducts implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            deserialize();
            JTable table = createTable(deliveryService.getProducts());
            clientView.setTable(table);
            clientView.showTable(table);
        }
    }

    class CreateOrder implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (clientView.selected()){
                ArrayList<MenuItem> products = new ArrayList<>();
                int id = 0;
                for (int i = 0; i < clientView.getTable().getSelectedRows().length; i++){
                    String name = (String) clientView.getTable().getValueAt(clientView.getTable().getSelectedRows()[i],0);
                    for (MenuItem menuItem : deliveryService.getProducts()){
                        if (menuItem.getTitle().equals(name)){
                            products.add(menuItem);
                        }
                    }
                    for (User client : deliveryService.getUsers()){
                        if (logInView.getUsernameField().equals(client.getUsername()) && logInView.getPasswordField().equals(client.getPassword())){
                            id = client.getId();
                        }
                    }
                }
                deliveryService.createOrder(new Order(nrOrder++, id,0), products);
                serialize();
            }else
                clientView.showMessage("Select at least 1 product!");
        }
    }

    class GenerateReport implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String startHour = administratorView.getStartHourField();
            String endHour = administratorView.getEndHourField();
            String nrOfOrders = administratorView.getNrOfOrdersField();
            String nrOfOrdersClient = administratorView.getNrOrdersPerClientField();
            String orderValue = administratorView.getOrderValueField();
            String orderDay = administratorView.getDayField();
            if (!startHour.equals("") && !endHour.equals("")){
                String message = deliveryService.generateReport(startHour,endHour,0);
                administratorView.showMessage(message);
            } else if (!nrOfOrdersClient.equals("") && !orderValue.equals("")){
                String message = deliveryService.generateReport(nrOfOrdersClient,orderValue,2);
                administratorView.showMessage(message);
            }else {
                if (!administratorView.getNrOfOrdersField().equals("")) {
                    String message = deliveryService.generateReport(nrOfOrders,null,1);
                    administratorView.showMessage(message);
                }else if (!administratorView.getDayField().equals("")){
                    String message = deliveryService.generateReport(orderDay,null,3);
                    administratorView.showMessage(message);
                }
            }
            serialize();
        }
    }

    class SearchProducts implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String keyword = clientView.getKeywordField();
            String rating = clientView.getRatingField();
            String calories = clientView.getCaloriesField();
            String proteins = clientView.getProteinsField();
            String fats = clientView.getFatsField();
            String sodium = clientView.getSodiumField();
            String price = clientView.getPriceField();

            if (!keyword.equals("")){
                List<MenuItem> products = deliveryService.searchProducts(keyword,0);
                deliveryService.setProducts((ArrayList<MenuItem>) products);
                JTable table = createTable((ArrayList<MenuItem>) products);
                clientView.setTable(table);
                clientView.showTable(table);
            } else if (!rating.equals("")){
                List<MenuItem> products = deliveryService.searchProducts(rating,1);
                deliveryService.setProducts((ArrayList<MenuItem>) products);
                JTable table = createTable((ArrayList<MenuItem>) products);
                clientView.setTable(table);
                clientView.showTable(table);
            } else if (!calories.equals("")){
                List<MenuItem> products = deliveryService.searchProducts(calories,2);
                deliveryService.setProducts((ArrayList<MenuItem>) products);
                JTable table = createTable((ArrayList<MenuItem>) products);
                clientView.setTable(table);
                clientView.showTable(table);
            } else if (!proteins.equals("")){
                List<MenuItem> products = deliveryService.searchProducts(proteins,3);
                deliveryService.setProducts((ArrayList<MenuItem>) products);
                JTable table = createTable((ArrayList<MenuItem>) products);
                clientView.setTable(table);
                clientView.showTable(table);
            } else if (!fats.equals("")){
                List<MenuItem> products = deliveryService.searchProducts(fats,4);
                deliveryService.setProducts((ArrayList<MenuItem>) products);
                JTable table = createTable((ArrayList<MenuItem>) products);
                clientView.setTable(table);
                clientView.showTable(table);
            } else if (!sodium.equals("")){
                List<MenuItem> products = deliveryService.searchProducts(sodium,5);
                deliveryService.setProducts((ArrayList<MenuItem>) products);
                JTable table = createTable((ArrayList<MenuItem>) products);
                clientView.setTable(table);
                clientView.showTable(table);
            } else if (!price.equals("")){
                List<MenuItem> products = deliveryService.searchProducts(price,6);
                deliveryService.setProducts((ArrayList<MenuItem>) products);
                JTable table = createTable((ArrayList<MenuItem>) products);
                clientView.setTable(table);
                clientView.showTable(table);
            }
        }
    }
}
