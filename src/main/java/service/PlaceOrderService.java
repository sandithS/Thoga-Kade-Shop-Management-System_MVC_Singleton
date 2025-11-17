package service;

import javafx.collections.ObservableList;
import model.dto.CartItem;
import model.dto.Customer;
import model.dto.Item;
import model.dto.Order;

import java.sql.SQLException;

public interface PlaceOrderService {

    Customer getCustomer(String custId);

    Item getItem(String itemCode);

    void placeOrder(Order order, ObservableList<CartItem> cartItems) throws SQLException;
}
