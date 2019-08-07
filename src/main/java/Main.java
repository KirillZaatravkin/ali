import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws IOException {

        ArrayList<ProductModel> arrayListProduct = new ArrayList<ProductModel>();
        CloseableHttpClient client = HttpClients.createDefault();
        Processing processing = new Processing();
        ProductDetails productDetails;

        //csvResult - result of work  method main
        CSV csvResult = new CSV();
        String[] information = new String[5];
        int j = 0;
        for (int offset = 0; offset < 100; offset = offset + 20) {
            String URL = "https://gpsfront.aliexpress.com/queryGpsProductAjax.do?callback=jQuery1830210686789858054_1564941263000&widget_id=5547572&platform=pc&limit=20&offset=" + offset + "&phase=1&productIds2Top=&postback=72e7624e-338b-4e57-9e00-4dce88e8a303&_=1564941392951";
            information[j] = processing.getInformationFromURL(URL, client);
            j++;
            try {
                //slep, that site not to  block
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        client.close();

        //parse the result of script from Alli
        for (int i = 0; i < information.length; i++) {

            String[] temp = information[i].split("\"gpsProductDetails\":");
            String jsonString = "{\"gpsProductDetails\":" + temp[1].substring(0, temp[1].length() - 1);
            ObjectMapper mapper = new ObjectMapper();
            productDetails = mapper.readValue(jsonString, ProductDetails.class);
            arrayListProduct.addAll(productDetails.getGpsProductDetails());

        }
        csvResult.createCSVFile(arrayListProduct);

    }
}
