package repository;

import model.dto.Order;

import java.sql.ResultSet;

public interface OrderRepository {

    boolean placeOrder(Order order);

    ResultSet getAllOrders();

    void updateOrder(Order order);

    void deleteOrder(String orderID);

}
