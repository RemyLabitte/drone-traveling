package model;

public class StoreStock {

    private String StoreId;
    private Integer quantity;

    public StoreStock(String storeId, Integer quantity) {
        StoreId = storeId;
        this.quantity = quantity;
    }

    public String getStoreId() {
        return StoreId;
    }

    public void setStoreId(String storeId) {
        StoreId = storeId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "StoreStock{" +
                "StoreId='" + StoreId + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
