package model;

public class TravelPlan {

    private String droneId;
    private String storeId;
    private String productId;
    private String customerId;

    public TravelPlan(String droneId, String storeId, String productId, String customerId) {
        this.droneId = droneId;
        this.storeId = storeId;
        this.productId = productId;
        this.customerId = customerId;
    }

    public String getDroneId() {
        return droneId;
    }

    public void setDroneId(String droneId) {
        this.droneId = droneId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "TravelPlan{" +
                "droneId='" + droneId + '\'' +
                ", storeId='" + storeId + '\'' +
                ", productId='" + productId + '\'' +
                ", CustomerId='" + customerId + '\'' +
                '}';
    }
}
