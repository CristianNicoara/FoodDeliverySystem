package businessLayer;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IDeliveryServiceProcessing {
    ArrayList<MenuItem> importProducts(String fileName) throws IOException;
    void addProduct(MenuItem product);
    void modifyProduct(String productTitle, MenuItem item);
    void deleteProduct(String productTitle);
    String generateReport(String value1, String value2, int category);
    void createOrder(Order order, ArrayList<MenuItem> products);
    List<MenuItem> searchProducts(String value, int category);
}
