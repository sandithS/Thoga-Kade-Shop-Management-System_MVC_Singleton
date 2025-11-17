package service.impl;

import db.DBConnection;
import javafx.collections.ObservableList;
import model.dto.CartItem;
import model.dto.Customer;
import model.dto.Item;
import model.dto.Order;
import service.*;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderServiceImpl implements PlaceOrderService {

    ItemManagementService itemManagementService = new ItemManagementServiceImpl();
    CustomerManagementService customerManagementService = new CustomerManagementServiceImpl();
    OrderManagementService orderManagementService = new OrderManagementServiceImpl();
    OrderDetailsManagementService orderDetailsManagementService = new OrderDetailsManagementServiceImpl();

    @Override
    public Customer getCustomer(String custId) {
        return customerManagementService.searchCustomer(custId);
    }

    @Override
    public Item getItem(String itemCode) {
        return itemManagementService.searchItem(itemCode, null);
    }

    @Override
    public void placeOrder(Order order, ObservableList<CartItem> cartItemObservableList) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            boolean isAddOrder = orderManagementService.placeOrders(order);

            if (isAddOrder){
                boolean isAddOrderDetails = orderDetailsManagementService.addOrderDetails(order, cartItemObservableList);
                if (isAddOrderDetails){
                    if(itemManagementService.updateItemQuantity(cartItemObservableList)){
                       // JOptionPane.showMessageDialog(null,"Order Placed..");
                        connection.commit();
                    }
                }
            }
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }finally {
            connection.setAutoCommit(true);
        }

    }
}
