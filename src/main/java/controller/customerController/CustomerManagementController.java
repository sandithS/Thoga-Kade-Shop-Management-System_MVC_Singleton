package controller.customerController;

import db.DBConnection;
import model.Customer;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

            boolean response = preparedStatement.executeUpdate()>0;

            if (response){
                JOptionPane.showMessageDialog(null,"Added Success...");
            }else {
                JOptionPane.showMessageDialog(null,"Added Failed...");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
