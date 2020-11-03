package model;

public class OrderedProduct {

    private String productId;
    private Integer orderedQty;

    public OrderedProduct(String productId, Integer orderedQty) {
        this.productId = productId;
        this.orderedQty = orderedQty;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getOrderedQty() {
        return orderedQty;
    }

    public void setOrderedQty(Integer orderedQty) {
        this.orderedQty = orderedQty;
    }

    @Override
    public String toString() {
        return "OrderedProduct{" +
                "productId='" + productId + '\'' +
                ", orderedQty=" + orderedQty +
                '}';
    }
}
