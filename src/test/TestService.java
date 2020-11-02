package test;

import model.Customer;
import model.Drone;
import model.Store;
import service.UtilsService;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;

public class TestService {

    @Test
    public void testDistanceToStore() {
        Drone droneTest = new Drone("Testeur",2,2,100d);
        Store storeTest = new Store("StoreTest", 3, 3);

        Double result = UtilsService.distanceToStore(droneTest, storeTest);

        assertNotNull(result);
    }

    @Test
    public void testDistanceToCustomer() {
        Drone droneTest = new Drone("Testeur",2,2,100d);
        Customer customerTest = new Customer("CustomerTest", 3, 3);

        Double result = UtilsService.distanceToCustomer(droneTest, customerTest);

        assertNotNull(result);
    }
}