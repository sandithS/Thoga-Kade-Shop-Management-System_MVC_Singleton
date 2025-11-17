package repository.impl;

import db.DBConnection;
import model.dto.OrderDetails;
import repository.OrderDetailsRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailsRepositoryImpl implements OrderDetailsRepository {
    @Override
    public ResultSet getAllOrderDetails() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM OrderDetail");
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateOrderDetail(OrderDetails orderDetail) {
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
    public void deleteOrderDetail(String orderId, String itemCode) {
        try {

            DBConnection.getInstance().getConnection().createStatement().executeUpdate("DELETE FROM OrderDetail WHERE OrderID='" + orderId + "' AND ItemCode='" + itemCode + "'");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addOrderDetails(OrderDetails orderDetails) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO OrderDetail VALUES(?,?,?,?)");

            preparedStatement.setObject(1, orderDetails.getOrderID());
            preparedStatement.setObject(2, orderDetails.getItemCode());
            preparedStatement.setObject(3, orderDetails.getOrderQTY());
            preparedStatement.setObject(4, orderDetails.getDiscount());

            return preparedStatement.executeUpdate()>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
