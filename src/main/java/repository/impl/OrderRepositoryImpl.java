package repository.impl;

import db.DBConnection;
import model.dto.Order;
import repository.OrderRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public boolean placeOrder(Order order) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Orders VALUES(?,?,?)");

            preparedStatement.setObject(1, order.getOrderID());
            preparedStatement.setObject(2, order.getOrderDate());
            preparedStatement.setObject(3, order.getCustID());

            return preparedStatement.executeUpdate()>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet getAllOrders() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Orders");
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateOrder(Order order) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Orders SET OrderDate=?, CustID=? WHERE OrderID=?");

            preparedStatement.setObject(1, order.getOrderDate());
            preparedStatement.setObject(2, order.getCustID());
            preparedStatement.setObject(3, order.getOrderID());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrder(String orderID) {
        try {

            DBConnection.getInstance().getConnection().createStatement().executeUpdate("DELETE FROM Orders WHERE OrderID='" + orderID + "'");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
