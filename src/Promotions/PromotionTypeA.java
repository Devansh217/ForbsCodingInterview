package Promotions;

/*
1. There will be a 7% discount on the price if the origin of the products is Africa
2. There will be a discount of 4% if the product rating is 2 and 8 % if the product
rating is below 2.
3. There will be a flat discount of Rs 100 on the products in these categories:
“electronics”,”furnishing” for items costing Rs 500 and above.
* */
import models.Discount;
import models.Product;
import models.ProductDiscount;


public class PromotionTypeA implements  Promotions{

    private Product product;
    public PromotionTypeA(Product product){
        this.product = product;
    }
    @Override
    public ProductDiscount applyPromotion() {
        ProductDiscount pd =  new ProductDiscount();

        pd.setProduct(this.product);

        String discountTag = "No Discount";
        double amount = 0 ;

        if(product.getRating() < 2 ){
            amount = product.getPrice()* 0.08;
            discountTag = "get 8% off";
        }
        else if(product.getOrigin().equals("Africa")){
            amount = product.getPrice()* 0.07;
            discountTag = "get 7% off";
        }
        else if(product.getRating() == 2){
            amount = product.getPrice()*0.04;
            discountTag = "get 4% off";
        }
        else if(product.getPrice() > 1000) {
            amount = product.getPrice()*0.02;
            discountTag = "get 2% off";
        }
        if((product.getCategory().equals("electronics") || product.getCategory().equals("furnishing") ) && amount < 100.0) {
            amount =  100;
            discountTag = "get Rs 100 off";
        }


        Discount dis = new Discount(amount,discountTag);
        pd.setDiscount(dis);
        return pd;
    }
}
