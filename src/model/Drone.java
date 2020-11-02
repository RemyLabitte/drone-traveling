package model;

public class Drone {

    private String droneId;
    private Integer x;
    private Integer y;
    private Double autonomie;
    private Boolean isHoldingProduct;
    private Double distanceToCustomer;
    private Double distanceToStore;
    private Double distanceToTravel;

    public Drone(String droneId, Integer x, Integer y, Double autonomie) {
        this.droneId = droneId;
        this.x = x;
        this.y = y;
        this.autonomie = autonomie;
    }

    public String getDroneId() {
        return droneId;
    }

    public void setDroneId(String droneId) {
        this.droneId = droneId;
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

    public Double getAutonomie() {
        return autonomie;
    }

    public void setAutonomie(Double autonomie) {
        this.autonomie = autonomie;
    }

    public Double getDistanceToTravel() {
        return distanceToTravel;
    }

    public void setDistanceToTravel(Double distanceToTravel) {
        this.distanceToTravel = distanceToTravel;
    }

    public Double getDistanceToCustomer() {
        return distanceToCustomer;
    }

    public void setDistanceToCustomer(Double distanceToCustomer) {
        this.distanceToCustomer = distanceToCustomer;
    }

    public Double getDistanceToStore() {
        return distanceToStore;
    }

    public void setDistanceToStore(Double distanceToStore) {
        this.distanceToStore = distanceToStore;
    }

    public Boolean getIsHoldingProduct() {
        return isHoldingProduct;
    }

    public void setIsHoldingProduct(Boolean isHoldingProduct) {
        this.isHoldingProduct = isHoldingProduct;
    }

    @Override
    public String toString() {
        return "Drone{" +
                "droneId='" + droneId + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", autonomie=" + autonomie +
                ", isHoldingProduct=" + isHoldingProduct +
                ", distanceToCustomer=" + distanceToCustomer +
                ", distanceToStore=" + distanceToStore +
                ", distanceToTravel=" + distanceToTravel +
                '}';
    }
}
