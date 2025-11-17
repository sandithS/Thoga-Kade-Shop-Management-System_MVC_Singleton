package service;

import javafx.collections.ObservableList;
import model.dto.Order;

public interface OrderManagementService {

    boolean placeOrders(Order order);

    ObservableList<Order> getAllOrders();

    void updateOrders(Order order);

    void deleteOrders(String orderID);

}
