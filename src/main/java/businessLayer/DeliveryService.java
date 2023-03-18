package businessLayer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {

    private ArrayList<MenuItem> products;
    private ArrayList<User> users;
    private Map<Order,ArrayList<MenuItem>> orders = new HashMap<>();

    public ArrayList<MenuItem> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<MenuItem> products) {
        this.products = products;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public boolean checkIfExists(ArrayList<MenuItem> menu, MenuItem item) throws NoSuchFieldException, IllegalAccessException {
        if (menu.size() == 0)
            return false;
        boolean exists = false;
        for (MenuItem menuItem : menu) {
            if (menuItem.getTitle().equals(item.getTitle())) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    /**
     * Imports a file into the application
     * @param fileName
     * @return ArrayList<MenuItem>
     * @throws IOException if a file was not found
     */
    @Override
    public ArrayList<MenuItem> importProducts(String fileName) throws IOException {
        assert fileName != null;
        ArrayList<MenuItem> menuItem = new ArrayList<>();
        Stream<String> lines = Files.lines(Paths.get(fileName));
        List<List<String>> items = lines.map(line -> Arrays.asList(line.split(","))).collect(Collectors.toList());
        items.remove(0);

        items.forEach(value -> {
            MenuItem item = new BaseProduct(value.get(0), Float.parseFloat(value.get(1)), Integer.parseInt(value.get(2)),
                    Integer.parseInt(value.get(3)), Integer.parseInt(value.get(4)), Integer.parseInt(value.get(5)), Double.parseDouble(value.get(6)));
            try {
                if (!checkIfExists(menuItem, item))
                    menuItem.add(item);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

        });
        products = menuItem;
        assert menuItem != null;
        return menuItem;
    }

    /**
     * Adds a product to the list of products
     * @param product
     */
    @Override
    public void addProduct(MenuItem product) {
        assert product != null;
        products.add(product);
    }

    /**
     * Modifies the selected product
     * @param productTitle
     * @param item
     */
    @Override
    public void modifyProduct(String productTitle, MenuItem item) {
        assert productTitle != null && item != null;
        for (MenuItem product:products){
            if (product.getTitle().equals(productTitle)){
                product.setTitle(item.getTitle());
                product.setRating(item.getRating());
                product.setCalories(item.getCalories());
                product.setProteins(item.getProteins());
                product.setFats(item.getFats());
                product.setSodium(item.getSodium());
                product.setPrice(item.computePrice());
                break;
            }
        }
    }

    /**
     * Deletes a selected product
     * @param productTitle
     */
    @Override
    public void deleteProduct(String productTitle) {
        for (int i = 0; i < products.size(); i++){
            if (products.get(i).getTitle().equals(productTitle)){
                products.remove(i);
                break;
            }
        }
    }

    /**
     * Generate a report based on the chosen category
     * @param value1
     * @param value2
     * @param category
     * @return The message to be displayed
     */
    @Override
    public String generateReport(String value1, String value2, int category) {
        assert value1 != null && value2 != null;
        if (value1 != null && value2 != null) {
            if (category == 0) {
                String[] message = {""};
                final int val1 = Integer.parseInt(value1);
                final int val2 = Integer.parseInt(value2);
                List<Order> stream = orders.entrySet().stream().filter(filter -> filter.getKey().getDate().getHours() >= val1 && filter.getKey().getDate().getHours() <= val2)
                        .map(Map.Entry::getKey).collect(Collectors.toList());
                message[0] = "Between start hour " + val1 + " and end hour " + val2 + " the orders are : \n";
                stream.forEach(e -> message[0] = message[0] + "Order Id " + e.getId() + ", Client Id: " + e.getClientId() + ", Date: " + e.getDate() + "\n");
                return message[0];
            }
            if (category == 2){
                String[] message = {""};
                final int nrClientOrders = Integer.parseInt(value1);
                final int orderValue = Integer.parseInt(value2);
                List<Order> stream = orders.entrySet().stream().filter(filter -> filter.getKey().getValue() > orderValue).map(Map.Entry :: getKey).collect(Collectors.toList());

                message[0] = "The clients who ordered more than " + nrClientOrders + " times and with a order value more than " + orderValue + " are: \n";
                ArrayList<Integer> clientsId = new ArrayList();
                stream.forEach(e-> clientsId.add(e.getClientId()));
                int[] nrOfApearences = new int[clientsId.size()];
                for (int i = 0; i < clientsId.size() - 1; i++){
                    int id = clientsId.get(i);
                    int var = 1;
                    for (int j = i+1; j < clientsId.size(); j++){
                        if (id == clientsId.get(j)){
                            var++;
                        }
                    }
                    nrOfApearences[i] = var;
                }
                for (int i = 0; i < nrOfApearences.length; i++){
                    if (nrOfApearences[i] >= nrClientOrders)
                        message[0] += "Client with id = " + clientsId.get(i) + "\n";
                }
                return message[0];
            }
        } else {
            if (category == 1){
                String[] message = {""};
                final int nrOrders = Integer.parseInt(value1);
                List<ArrayList<MenuItem>> stream = orders.entrySet().stream().map(Map.Entry :: getValue).collect(Collectors.toList());

                message[0] = "The products ordered more than " + nrOrders + " times are : \n";
                ArrayList<String> titles = new ArrayList<>();
                stream.forEach(e->{
                    for (int i = 0; i < e.size(); i++){
                        titles.add(e.get(i).getTitle());
                    }
                });
                int[] nrOfApearences = new int[titles.size()];
                for (int i = 0; i < titles.size() - 1; i++){
                    String title = titles.get(i);
                    int var = 1;
                    for (int j = i+1; j < titles.size(); j++){
                        if (title.equals(titles.get(j))){
                            var++;
                        }
                    }
                    nrOfApearences[i] = var;
                }
                for (int i = 0; i < nrOfApearences.length; i++){
                    if (nrOfApearences[i] >= nrOrders)
                        message[0] += titles.get(i) + "\n";
                }
                return message[0];
            }
            if (category == 3){
                String[] message = {""};
                assert value1 != null;
                String day = value1.toLowerCase();
                final int dayNr;
                switch (day){
                    case "monday" : dayNr = 1;
                        break;
                    case "tuesday" : dayNr = 2;
                        break;
                    case "wednesday" : dayNr = 3;
                        break;
                    case "thursday" : dayNr = 4;
                        break;
                    case "friday" : dayNr = 5;
                        break;
                    case "saturday" : dayNr = 6;
                        break;
                    case "sunday" : dayNr = 0;
                        break;
                    default: dayNr = -1;
                }
                List<ArrayList<MenuItem>> stream = orders.entrySet().stream().filter(filter -> filter.getKey().getDate().getDay() == dayNr).map(Map.Entry :: getValue).collect(Collectors.toList());
                message[0] = "The products ordered on " + value1 + " are:\n";
                ArrayList<String> names = new ArrayList<>();
                stream.forEach(e -> {
                    for (int i = 0; i < e.size(); i++){
                        names.add(e.get(i).getTitle());
                    }
                });
                for (int i = 0; i < names.size() - 1; i++){
                    String name = names.get(i);
                    for (int j = i + 1; j < names.size(); j++){
                        if (name.equals(names.get(j))){
                            names.remove(j);
                            i--;
                            j--;
                        }
                    }
                }
                for (int i = 0; i < names.size(); i++){
                    message[0] += names.get(i) + "\n";
                }
                return message[0];
            }
        }
        return null;
    }

    /**
     * Filters the products table based on the category chosen by the clien
     * @param value
     * @param category
     * @return the filtered list of products
     */
    @Override
    public List<MenuItem> searchProducts(String value, int category) {
        assert value != null;
        if (category == 0){
            List<MenuItem> items = products.stream().filter(filter -> filter.getTitle().toLowerCase().contains(value)).collect(Collectors.toList());
            return  items;
        }else {
            double doubleValue = Double.parseDouble(value);
            if (category == 1) {
                List<MenuItem> items = products.stream().filter(filter -> filter.getRating() == doubleValue).collect(Collectors.toList());
                return items;
            } else if (category == 2){
                List<MenuItem> items = products.stream().filter(filter -> filter.getCalories() == doubleValue).collect(Collectors.toList());
                return items;
            } else if (category == 3){
                List<MenuItem> items = products.stream().filter(filter -> filter.getProteins() == doubleValue).collect(Collectors.toList());
                return items;
            } else if (category == 4){
                List<MenuItem> items = products.stream().filter(filter -> filter.getFats() == doubleValue).collect(Collectors.toList());
                return items;
            } else if (category == 5){
                List<MenuItem> items = products.stream().filter(filter -> filter.getSodium() == doubleValue).collect(Collectors.toList());
                return items;
            } else if (category == 6){
                List<MenuItem> items = products.stream().filter(filter -> filter.computePrice() == doubleValue).collect(Collectors.toList());
                return items;
            }
        }
        return null;
    }

    /**
     * Creates an order of the products chosen by the clients from the table in the interface
     * @param order
     * @param products
     */
    @Override
    public void createOrder(Order order, ArrayList<MenuItem> products) {
        assert order != null && products != null;
        orders.put(order,products);
        String orderedProducts = "Order " + order.getId() + "\nProducts: ";
        String filename = "order_" + order.getId() + ".txt";
        double price = 0;
        for (int i = 0; i < products.size(); i++){
            orderedProducts += products.get(i).getTitle() + " ";
            price += products.get(i).computePrice();
        }
        order.setValue(price);
        String orderTxt = "Client " + order.getClientId() + "\n" + orderedProducts + "\nPrice: " + price;
        try {
            FileWriter fileWriter = new FileWriter(filename, false);
            fileWriter.write(orderTxt);
            fileWriter.close();
        } catch (IOException exception){
            exception.printStackTrace();
        }
        String arg = "An order has just been created by client " + order.getClientId() + "!";
        setChanged();
        notifyObservers(arg);
    }
}
