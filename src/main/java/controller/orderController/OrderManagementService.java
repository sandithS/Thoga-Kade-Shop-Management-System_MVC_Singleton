package controller.orderController;

import javafx.collections.ObservableList;
import model.Order;

public interface OrderManagementService {

    void placeOrders(Order order);

    ObservableList<Order> getAllOrders();

    void updateOrders(Order order);

    void deleteOrders(String orderID);

}
