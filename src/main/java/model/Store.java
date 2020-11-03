package model;

public class Store {

    private String storeId;
    private Integer x;
    private Integer y;

    public Store(String storeId, Integer x, Integer y) {
        this.storeId = storeId;
        this.x = x;
        this.y = y;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
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
        return "Store{" +
                "storeId='" + storeId + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
