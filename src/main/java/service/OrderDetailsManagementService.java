package service;

import javafx.collections.ObservableList;
import model.dto.CartItem;
import model.dto.Order;
import model.dto.OrderDetails;

public interface OrderDetailsManagementService {

    boolean addOrderDetails(Order order, ObservableList<CartItem> cartItems);

    ObservableList<OrderDetails> getAllOrderDetails();

    void updateOrderDetails(OrderDetails orderDetail);

    void deleteOrderDetails(String orderId, String itemCode);

}
