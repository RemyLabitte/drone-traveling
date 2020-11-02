package model;

import java.util.List;

public class Order {

    private String orderId;
    private String customerId;
    private List<OrderedProduct> products;

    public Order(String orderId, String customerId, List<OrderedProduct> products) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.products = products;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<OrderedProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderedProduct> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", products=" + products +
                "} \n";
    }
}
