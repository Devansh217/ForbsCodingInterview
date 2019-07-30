package utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import models.Product;
import models.ProductDiscount;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class JsonUtility {

    private static String read(Reader rd) throws IOException {
        StringBuilder str = new StringBuilder();
        int ch;
        while ((ch = rd.read()) != -1) {
            str.append((char) ch);
        }
        return str.toString();
    }

    public static JSONArray readJsonAPI(String url) throws IOException, JSONException {
        System.setProperty("http.agent", "Chrome");
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = read(rd);
            System.out.println(jsonText);
            return new JSONArray(jsonText);
        } finally {
            is.close();
        }
    }

    public static double fetchExchangeRates(String currency , Double price) throws IOException {
        //TODO:Fix this Code
        URL urlForGetRequest = new URL("https://api.exchangeratesapi.io/latest");
        String readLine = null;
        Gson gson = new Gson();
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        int responseCode = conection.getResponseCode();
        double INRprice = 0;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
            // print result

            JSONObject forexRates = new JSONObject(response.toString());
            double exRate = forexRates.getJSONObject("rates").getDouble(currency);
            double InrRate =  forexRates.getJSONObject("rates").getDouble("INR");

            double euroPrice = price/exRate;
            INRprice = InrRate * euroPrice;
        } else {
            System.out.println("GET NOT WORKED");
        }

        return INRprice;

    }


    public static String convertToJson(ProductDiscount pd){
        Gson gson = new Gson();
        return gson.toJson(pd,ProductDiscount.class);
    }

    public static ArrayList<Product> deserelizeJsonInput(JSONArray jsonArr){
        Gson gson = new Gson();
        ArrayList<Product> products = new ArrayList<Product>();
        for (int i =0 ; i < jsonArr.length(); i++) {
            products.add(gson.fromJson(jsonArr.getJSONObject(i).toString(),Product.class));
        }
        return products;
    }


}
