package service.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.CartItem;
import model.dto.Order;
import model.dto.OrderDetails;
import repository.impl.OrderDetailsRepositoryImpl;
import service.OrderDetailsManagementService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailsManagementServiceImpl implements OrderDetailsManagementService {

    OrderDetailsRepositoryImpl orderDetailsRepository = new OrderDetailsRepositoryImpl();

    @Override
    public boolean addOrderDetails(Order order, ObservableList<CartItem> cartItems){

        boolean isAdd = false;

        for (CartItem cartItem: cartItems){
             isAdd = orderDetailsRepository.addOrderDetails(
                    new OrderDetails(
                            order.getOrderID(),
                            cartItem.getItemCode(),
                            cartItem.getQuantity(),
                            cartItem.getDiscount()
                    )
            );
             if (isAdd == false){
                 break;
             }
        }
        return isAdd;
    }

    @Override
    public ObservableList<OrderDetails> getAllOrderDetails() {

        ObservableList<OrderDetails> orderDetailsList = FXCollections.observableArrayList();

        ResultSet resultSet = orderDetailsRepository.getAllOrderDetails();

        try {
            while (resultSet.next()) {
                OrderDetails orderDetail = new OrderDetails(
                        resultSet.getString("OrderID"),
                        resultSet.getString("ItemCode"),
                        resultSet.getInt("OrderQTY"),
                        resultSet.getInt("Discount")
                );
                orderDetailsList.add(orderDetail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderDetailsList;
    }

    @Override
    public void updateOrderDetails(OrderDetails orderDetail) {

        orderDetailsRepository.updateOrderDetail(orderDetail);

    }

    @Override
    public void deleteOrderDetails(String orderId, String itemCode) {

        orderDetailsRepository.deleteOrderDetail(orderId,itemCode);

    }

}
