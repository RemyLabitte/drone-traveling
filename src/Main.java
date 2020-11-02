import model.Customer;
import model.Drone;
import model.Order;
import model.OrderedProduct;
import model.Product;
import model.Store;
import model.StoreStock;
import model.TravelPlan;
import service.UtilsService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static final String COMMA_DELIMITER = ";";
    private static String nearestStoreId;
    private static String droneId;

    private static List<Store> stores;
    private static List<Customer> customers;
    private static List<Drone> drones;
    private static List<Product> products;

    public static void main(String[] args) throws IOException {
        stores = readStoresFiles();
        List<Order> orders = readOrdersFile();
        customers = readCustomersFile();
        drones = readDronesFiles();
        products = readStoreStockFiles();

        List<TravelPlan> travelPlans = new ArrayList<>();

        orders.forEach(order -> {
            order.getProducts().forEach(product -> {
                while (product.getOrderedQty() != 0) {
                    //Récupération du produit à livrer
                    Product productToDeliver = products.stream()
                            .filter(p -> p.getProductId().equals(product.getProductId())).findFirst().get();

                    //Récupérer la liste des magasins avec la qty suffisante pour le produit
                    List<StoreStock> storesWithStockForProduct = productToDeliver.getStoresStocks()
                            .stream()
                            .filter(storeStock -> storeStock.getQuantity() >= 1).collect(Collectors.toList());

                    //On récupère la postion du client que l'on doit livrer pour la commande
                    Customer customer = customers.stream().filter(c -> c.getCustomerId().equals(order.getCustomerId())).collect(Collectors.toList()).get(0);

                    //Calculer la distance la plus courte drone / magasins / client pour définir quel drone livre depuis quel magasin
                    // droneId = id du drone qui va effectuer la livraison
                    // nearestStoreId = id du magasin dans lequel on va chercher le produit
                    resetDistanceToTravelOfDrones();
                    droneId = null;
                    drones.forEach(drone -> {
                        drone.setDistanceToTravel((double) 0);
                        drone.setDistanceToCustomer(UtilsService.distanceToCustomer(drone, customer));
                        nearestStoreId = setDistanceToTravelByDrone(storesWithStockForProduct, drone, drone.getDistanceToCustomer());
                        if ((droneId == null || isTheFasterDrone(drone)) && (drone.getAutonomie() > drone.getDistanceToTravel())) {
                            droneId = drone.getDroneId();
                        }
                    });

                    //Aller chercher le produit dans le magasin et actualiser la position du drone
                    Drone droneLivreur = drones.stream().filter(d -> d.getDroneId().equals(droneId)).findFirst().get();
                    Store storeLivreur = stores.stream().filter(s -> s.getStoreId().equals(nearestStoreId)).findFirst().get();
                    StoreStock storeStockLivreur = storesWithStockForProduct.stream().filter(storeStock -> storeStock.getStoreId().equals(nearestStoreId)).findFirst().get();
                    droneLivreur.setX(storeLivreur.getX());
                    droneLivreur.setY(storeLivreur.getY());
                    droneLivreur.setAutonomie(droneLivreur.getAutonomie() - droneLivreur.getDistanceToStore());
                    storeStockLivreur.setQuantity(storeStockLivreur.getQuantity() - 1);

                    //Livrer le colis au client et actuliser la positon du drone
                    droneLivreur.setX(customer.getX());
                    droneLivreur.setY(customer.getY());
                    droneLivreur.setAutonomie(droneLivreur.getAutonomie() - (droneLivreur.getDistanceToStore() + droneLivreur.getDistanceToCustomer()));
                    product.setOrderedQty(product.getOrderedQty() - 1);

                    TravelPlan travelPlan = new TravelPlan();
                    travelPlan.setCustomerId(customer.getCustomerId());
                    travelPlan.setDroneId(droneLivreur.getDroneId());
                    travelPlan.setProductId(product.getProductId());
                    travelPlan.setStoreId(storeLivreur.getStoreId());

                    travelPlans.add(travelPlan);
                }
            });
        });

        FileWriter csvWriter = new FileWriter("resources/TravelPlan.csv");
        csvWriter.append("DroneId");
        csvWriter.append(COMMA_DELIMITER);
        csvWriter.append("StoreId");
        csvWriter.append(COMMA_DELIMITER);
        csvWriter.append("ProductId");
        csvWriter.append(COMMA_DELIMITER);
        csvWriter.append("CustomerId");
        csvWriter.append(COMMA_DELIMITER);
        csvWriter.append("\n");

        for (TravelPlan travelPlan : travelPlans) {
            csvWriter.append(travelPlan.getDroneId()).append(COMMA_DELIMITER);
            csvWriter.append(travelPlan.getStoreId()).append(COMMA_DELIMITER);
            csvWriter.append(travelPlan.getProductId()).append(COMMA_DELIMITER);
            csvWriter.append(travelPlan.getCustomerId()).append(COMMA_DELIMITER);
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }

    private static boolean isTheFasterDrone(Drone drone) {
        return drones.stream().filter(d -> drone.getDistanceToTravel() < d.getDistanceToTravel()).collect(Collectors.toList()).size() == 0;
    }

    private static void resetDistanceToTravelOfDrones() {
        drones.forEach(drone -> {
            drone.setDistanceToTravel((double) 0);
            drone.setDistanceToStore((double) 0);
            drone.setDistanceToCustomer((double) 0);
        });
    }

    private static String setDistanceToTravelByDrone(List<StoreStock> storeStockDispo, Drone drone, Double distanceToCustomer) {
        final String[] nearestStoreId = {null};
        storeStockDispo.forEach(storeStock -> {
            Store s = stores.stream().filter(store -> store.getStoreId().equals(storeStock.getStoreId())).collect(Collectors.toList()).get(0);
            Double distanceToStore = UtilsService.distanceToStore(drone, s);

            if (drone.getDistanceToTravel() != 0) {
                if (drone.getDistanceToTravel() > (distanceToStore + (distanceToCustomer + distanceToStore))) {
                    drone.setDistanceToTravel(distanceToStore + (distanceToCustomer + distanceToStore));
                    drone.setDistanceToStore(distanceToStore);
                    nearestStoreId[0] = storeStock.getStoreId();
                }
            } else {
                drone.setDistanceToTravel(distanceToStore + (distanceToCustomer + distanceToStore));
                drone.setDistanceToStore(distanceToStore);
                nearestStoreId[0] = storeStock.getStoreId();
            }
        });
        return nearestStoreId[0];
    }

    private static List<Product> readStoreStockFiles() throws IOException {
        List<Product> products = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("./resources/ProductsStock.csv"))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                List<StoreStock> storeStocks = new ArrayList<>();
                for (int counter = 2; counter < values.length; counter += 2) {
                    StoreStock storeStock = new StoreStock(values[counter], Integer.parseInt(values[counter + 1]));
                    storeStocks.add(storeStock);
                }
                products.add(new Product(values[0], values[1], storeStocks));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return products;
    }

    private static List<Drone> readDronesFiles() throws IOException {
        List<Drone> drones = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("./resources/Drones.csv"))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                drones.add(new Drone(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]), (double) 100));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return drones;
    }

    private static List<Customer> readCustomersFile() throws IOException {
        List<Customer> customers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("./resources/Customers.csv"))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                customers.add(new Customer(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2])));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return customers;
    }

    private static List<Order> readOrdersFile() throws IOException {
        List<Order> orders = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("./resources/Orders.csv"))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                List<OrderedProduct> orderedProducts = new ArrayList<>();
                for (int counter = 2; counter < values.length; counter += 2) {
                    OrderedProduct orderedProduct = new OrderedProduct(values[counter], Integer.parseInt(values[counter + 1]));
                    orderedProducts.add(orderedProduct);
                }
                orders.add(new Order(values[0], values[1], orderedProducts));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }

    private static List<Store> readStoresFiles() throws IOException {
        List<Store> stores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("./resources/Stores.csv"))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                stores.add(new Store(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2])));

            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return stores;
    }
}


