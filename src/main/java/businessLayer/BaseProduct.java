package businessLayer;

public class BaseProduct extends MenuItem{
    /*private String title;
    private double rating;
    private double calories;
    private double proteins;
    private double fats;
    private double sodium;
    private double price;*/

    public BaseProduct(String title, double rating, double calories, double proteins, double fats, double sodium, double price) {
        super(title, rating, calories, proteins, fats, sodium, price);
        /*this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.sodium = sodium;
        this.price = price;*/
    }

    public String getTitle() {
        return super.getTitle();
    }

    public void setTitle(String title) {
        //this.title = title;
        super.setTitle(title);
    }

    public double getRating() {
        return super.getRating();
    }

    public void setRating(double rating) {
        //this.rating = rating;
        super.setRating(rating);
    }

    public double getCalories() {
        return super.getCalories();
    }

    public void setCalories(double calories) {
        //this.calories = calories;
        super.setCalories(calories);
    }

    public double getProteins() {
        return super.getProteins();
    }

    public void setProteins(double proteins) {
        //this.proteins = proteins;
        super.setProteins(proteins);
    }

    public double getFats() {
        return super.getFats();
    }

    public void setFats(double fats) {
        //this.fats = fats;
        super.setFats(fats);
    }

    public double getSodium() {
        return super.getSodium();
    }

    public void setSodium(double sodium) {
        //this.sodium = sodium;
        super.setSodium(sodium);
    }

    public double computePrice() {
        return super.computePrice();
    }

    public void setPrice(double price) {
        //this.price = price;
        super.setPrice(price);
    }
}
