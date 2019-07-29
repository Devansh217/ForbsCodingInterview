package models;

public class Discount {
    private Double amount;
    private String discountTag;

    public Discount(Double amount, String discountTag) {
        this.amount = amount;
        this.discountTag = discountTag;
    }
}
