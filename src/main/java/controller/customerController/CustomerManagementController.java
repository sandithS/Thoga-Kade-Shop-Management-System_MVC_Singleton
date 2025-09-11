package controller.customerController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerManagementController implements CustomerManagementService{

    @Override
    public void addCustomerDetails(Customer customer) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Customer VALUES(?,?,?,?,?,?,?,?,?)");

            preparedStatement.setObject(1,customer.getCustID());
            preparedStatement.setObject(2,customer.getCustTitle());
            preparedStatement.setObject(3,customer.getCustName());
            preparedStatement.setObject(4,customer.getDob());
            preparedStatement.setObject(5,customer.getSalary());
            preparedStatement.setObject(6,customer.getAddress());
            preparedStatement.setObject(7,customer.getCity());
            preparedStatement.setObject(8,customer.getProvince());
            preparedStatement.setObject(9,customer.getPostalCode());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Customer> getAllCustomerDetails() {

        ObservableList<Customer> customerList = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Customer");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Customer customer = new Customer(
                        resultSet.getString("CustID"),
                        resultSet.getString("CustTitle"),
                        resultSet.getString("CustName"),
                        resultSet.getString("DOB"),
                        resultSet.getDouble("Salary"),
                        resultSet.getString("CustAddress"),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("PostalCode")
                );
                customerList.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }

    @Override
    public void updateCustomerDetails(Customer customer) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Customer SET CustTitle=?, CustName=?, dob=?, salary=?, CustAddress=?, city=?, province=?, postalCode=? WHERE custID=?");

            preparedStatement.setObject(1,customer.getCustTitle());
            preparedStatement.setObject(2,customer.getCustName());
            preparedStatement.setObject(3,customer.getDob());
            preparedStatement.setObject(4,customer.getSalary());
            preparedStatement.setObject(5,customer.getAddress());
            preparedStatement.setObject(6,customer.getCity());
            preparedStatement.setObject(7,customer.getProvince());
            preparedStatement.setObject(8,customer.getPostalCode());
            preparedStatement.setObject(9,customer.getCustID());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteCustomerDetails(String custId) {

        try {

            DBConnection.getInstance().getConnection().createStatement().executeUpdate("DELETE FROM Customer WHERE custID='"+custId+"'");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
