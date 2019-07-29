package models;

public class ProductDiscount {
    private Product product;
    private Discount discount;

    public void setDiscount(Discount discount){
        this.discount = discount;
    }

    public Discount getDiscount(){
        return this.discount;
    }

    public void setProduct(Product product){
        this.product = product;
    }

    public Product getProduct(){
        return this.product;
    }
}
