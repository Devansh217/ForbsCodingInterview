import Promotions.*;
import models.Product;
import models.ProductDiscount;
import org.json.*;

import java.io.IOException;
import java.util.ArrayList;

import static utils.JsonUtility.*;


public class Main {
    public static void main(String[] args) throws IOException, JSONException {
        JSONArray json = readJsonAPI("https://api.jsonbin.io/b/5d31a1c4536bb970455172ca/latest");
        ArrayList<Product> products = deserelizeJsonInput(json);
        if (args.length == 0) {
            System.out.println("Incorrect Argument");
        } else {
            for (Product p : products) {
                p.checkAndConvertCurrency(p.getCurrency());
                Promotions promo = new PromotionFactory(p).getPromotion(args[0]);
                ProductDiscount pd = promo.applyPromotion();
                String jpd = convertToJson(pd);
                System.out.println(jpd);
            }
        }
    }
}
