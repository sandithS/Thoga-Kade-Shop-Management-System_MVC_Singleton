package service.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.Customer;
import repository.impl.CustomerRepositoryImpl;
import service.CustomerManagementService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerManagementServiceImpl implements CustomerManagementService {

    CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();

    @Override
    public void addCustomerDetails(Customer customer) {

        customerRepository.addCustomer(customer);

    }

    @Override
    public ObservableList<Customer> getAllCustomerDetails() {

        ObservableList<Customer> customerList = FXCollections.observableArrayList();

        ResultSet resultSet = customerRepository.getAllCustomers();

        try {
            while (resultSet.next()) {
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

        customerRepository.updateCustomer(customer);

    }

    @Override
    public void deleteCustomerDetails(String custId) {

        customerRepository.deleteCustomer(custId);

    }

    @Override
    public Customer searchCustomer(String custId) {
        ResultSet resultSet = customerRepository.searchCustomer(custId);

        try {
            resultSet.next();
                return new Customer(
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
