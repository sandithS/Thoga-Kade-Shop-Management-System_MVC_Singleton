package controller.orderDetailsController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailsManagementController implements OrderDetailsManagementService {
    @Override
    public ObservableList<OrderDetails> getAllOrderDetails() {

        ObservableList<OrderDetails> orderDetailsList = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM OrderDetail");
            ResultSet resultSet = preparedStatement.executeQuery();

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
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE OrderDetail SET OrderQTY=?, Discount=? WHERE OrderID=? AND ItemCode=?");

            preparedStatement.setObject(1, orderDetail.getOrderQTY());
            preparedStatement.setObject(2, orderDetail.getDiscount());
            preparedStatement.setObject(3, orderDetail.getOrderID());
            preparedStatement.setObject(4, orderDetail.getItemCode());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrderDetails(String orderId, String itemCode) {
        try {

            DBConnection.getInstance().getConnection().createStatement().executeUpdate("DELETE FROM OrderDetail WHERE OrderID='" + orderId + "' AND ItemCode='" + itemCode + "'");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
