package service;

import model.Customer;
import model.Drone;
import model.Store;

public class UtilsService {

    public static double distanceToStore(Drone drone, Store store) {
        int x = Math.abs(drone.getX() - store.getX());
        int y = Math.abs(drone.getY() - store.getY());
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public static double distanceToCustomer(Drone drone, Customer customer) {
        int x = Math.abs(drone.getX() - customer.getX());
        int y = Math.abs(drone.getY() - customer.getY());
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}
