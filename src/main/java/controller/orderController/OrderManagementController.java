package controller.orderController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderManagementController implements OrderManagementService{
    @Override
    public void placeOrders(Order order) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Orders VALUES(?,?,?)");

            preparedStatement.setObject(1,order.getOrderID());
            preparedStatement.setObject(2,order.getOrderDate());
            preparedStatement.setObject(3,order.getCustID());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Order> getAllOrders() {

        ObservableList<Order> orderList = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Orders");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
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
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Orders SET OrderDate=?, CustID=? WHERE OrderID=?");

            preparedStatement.setObject(1,order.getOrderDate());
            preparedStatement.setObject(2,order.getCustID());
            preparedStatement.setObject(3,order.getOrderID());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrders(String orderID) {
        try {

            DBConnection.getInstance().getConnection().createStatement().executeUpdate("DELETE FROM Orders WHERE OrderID='"+orderID+"'");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
