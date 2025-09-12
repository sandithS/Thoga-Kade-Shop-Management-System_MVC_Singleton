package controller.orderDetailsController;

import javafx.collections.ObservableList;
import model.OrderDetails;

public interface OrderDetailsManagementService {

    ObservableList<OrderDetails> getAllOrderDetails();

    void updateOrderDetails(OrderDetails orderDetail);

    void deleteOrderDetails(String orderId, String itemCode);
}
