package model;

public class Customer {

    private String customerId;
    private Integer x;
    private Integer y;

    public Customer() {
    }

    public Customer(String customerId, Integer x, Integer y) {
        this.customerId = customerId;
        this.x = x;
        this.y = y;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
