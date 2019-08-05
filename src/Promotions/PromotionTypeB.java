package Promotions;

/*
1. There will be a 12% discount on the prices if the inventory is more than 20.
2. There will be a discount of 7% if the product arrival status is new.
 */

import models.Discount;
import models.Product;
import models.ProductDiscount;

public class PromotionTypeB implements Promotions {

    private Product product;
    public PromotionTypeB(Product product){
        this.product = product;
    }
    @Override
    public ProductDiscount applyPromotion() {
        ProductDiscount pd =  new ProductDiscount();
        pd.setProduct(this.product);

        String discountTag = "No Discount";
        double amount = 0 ;

        if(product.getInventory() > 20){
            //12%
            amount = product.getPrice()* 0.12;
            discountTag = "get 12% off";
        }
        else if(product.getArrival().equals("NEW")) {
            //7%
            amount = product.getPrice()*0.07;
            discountTag = "get 7% off";
        }
        else if(product.getPrice() > 1000) {
            amount = product.getPrice()*0.02;
            discountTag = "get 2% off";
        }


        Discount dis = new Discount(amount,discountTag);
        pd.setDiscount(dis);
        return pd;
    }
}
