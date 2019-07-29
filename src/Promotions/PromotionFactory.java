package Promotions;

import models.Product;

public class PromotionFactory {

    private Product product ;
    public PromotionFactory(Product product){
        this.product = product;
    }
    public  Promotions getPromotion(String promotionType){
        if(promotionType == "promotionSetA")
            return new PromotionTypeA(this.product);
        else if (promotionType == "promotionSetB")
            return new PromotionTypeB(this.product);
        else
            return null;
    }
}
