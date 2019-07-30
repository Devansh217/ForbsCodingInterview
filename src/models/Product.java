package models;

import java.io.IOException;

import static utils.JsonUtility.fetchExchangeRates;

public class Product {

    private String product;
    private String origin;
    private String currency;
    private double price;
    private double rating;
    private long inventory;
    private String category;
    private String arrival;

    public void setProductName(String name) {
        this.product = name;
    }

    public String getProductName() {
        return this.product;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setCurrency(String currency) throws  IOException{
        checkAndConvertCurrency(currency);
        this.currency = currency;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return this.rating;
    }
    public void setInventory(long inventory) {
        this.inventory = inventory;
    }

    public long getInventory() {
        return this.inventory;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }
    public void setArrival(String arrivalStatus) {
        this.arrival = arrivalStatus;
    }

    public String getArrival() {
        if(this.arrival == null)
            return "";
        return this.arrival;
    }

    public static double convertCurrency(String currency , Double price) throws IOException{
        return fetchExchangeRates(currency,price);
    }

    public void checkAndConvertCurrency(String currency) throws IOException{
        if(!currency.equals("INR")){
            this.setPrice(convertCurrency(currency,this.price));
            this.setCurrency("INR");
        }
    }


}
