package controller.customerController;

import javafx.collections.ObservableList;
import model.Customer;

public interface CustomerManagementService {

    void addCustomerDetails(Customer customer);

    ObservableList<Customer> getAllCustomerDetails();

    void updateCustomerDetails(Customer customer);

    void deleteCustomerDetails(String custId);

}
