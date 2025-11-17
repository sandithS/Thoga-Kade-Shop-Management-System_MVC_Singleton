package repository;

import model.dto.OrderDetails;

import java.sql.ResultSet;

public interface OrderDetailsRepository {

    ResultSet getAllOrderDetails();

    void updateOrderDetail(OrderDetails orderDetail);

    void deleteOrderDetail(String orderId, String itemCode);

    boolean addOrderDetails(OrderDetails orderDetails);
}
