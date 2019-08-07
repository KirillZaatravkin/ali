import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDetails {
    private ArrayList<ProductModel> gpsProductDetails = new ArrayList<ProductModel>();

    public ArrayList<ProductModel> getGpsProductDetails() {
        return gpsProductDetails;
    }

    public void setGpsProductDetails(ArrayList<ProductModel> gpsProductDetails) {
        this.gpsProductDetails = gpsProductDetails;
    }
}
