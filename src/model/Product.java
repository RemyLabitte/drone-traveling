package model;

import java.util.List;

public class Product {

    private String productId;
    private String name;
    private List<StoreStock> storesStocks;

    public Product(String productId, String name, List<StoreStock> storesStocks) {
        this.productId = productId;
        this.name = name;
        this.storesStocks = storesStocks;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StoreStock> getStoresStocks() {
        return storesStocks;
    }

    public void setStoresStocks(List<StoreStock> storesStocks) {
        this.storesStocks = storesStocks;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", storesStocks=" + storesStocks +
                '}';
    }
}
