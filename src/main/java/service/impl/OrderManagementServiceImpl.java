package service.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.Order;
import repository.impl.OrderRepositoryImpl;
import service.OrderManagementService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderManagementServiceImpl implements OrderManagementService {

    OrderRepositoryImpl orderRepository = new OrderRepositoryImpl();

    @Override
    public boolean placeOrders(Order order) {

        return orderRepository.placeOrder(order);

    }

    @Override
    public ObservableList<Order> getAllOrders() {

        ObservableList<Order> orderList = FXCollections.observableArrayList();

        ResultSet resultSet = orderRepository.getAllOrders();

        try {
            while (resultSet.next()) {
                Order order = new Order(
                        resultSet.getString("OrderID"),
                        resultSet.getString("OrderDate"),
                        resultSet.getString("CustID")
                );
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderList;
    }

    @Override
    public void updateOrders(Order order) {

        orderRepository.updateOrder(order);

    }

    @Override
    public void deleteOrders(String orderID) {

        orderRepository.deleteOrder(orderID);

    }
}
