package businessLayer;

import java.util.Date;

public class Order {
    private int id;
    private int clientId;
    private Date date;
    private double value;

    public Order(int id, int clientId, double value) {
        this.id = id;
        this.clientId = clientId;
        this.value = value;
        this.date = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public int hashCode(){
        return id * clientId * date.getDay();
    }
}
