package controller.customerController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerManagementFormController implements Initializable {

    CustomerManagementService customerManagementService = new CustomerManagementController();

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colCustId;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableView<Customer> tblCustomerDetails;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtCustId;

    @FXML
    private JFXTextField txtDob;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPostalCode;

    @FXML
    private JFXTextField txtProvince;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    private JFXTextField txtTitle;

    @FXML
    void btnAddOnAction(ActionEvent event) {

        Customer customer = new Customer(
                txtCustId.getText(),
                txtTitle.getText(),
                txtName.getText(),
                txtDob.getText(),
                Double.parseDouble(txtSalary.getText()),
                txtAddress.getText(),
                txtCity.getText(),
                txtProvince.getText(),
                txtPostalCode.getText()
        );

        customerManagementService.addCustomerDetails(customer);

        loadTable();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        customerManagementService.deleteCustomerDetails(txtCustId.getText());

        loadTable();

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

        txtCustId.setText(null);
        txtTitle.setText(null);
        txtName.setText(null);
        txtDob.setText(null);
        txtSalary.setText(null);
        txtAddress.setText(null);
        txtCity.setText(null);
        txtProvince.setText(null);
        txtPostalCode.setText(null);

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        Customer customer = new Customer(
                txtCustId.getText(),
                txtTitle.getText(),
                txtName.getText(),
                txtDob.getText(),
                Double.parseDouble(txtSalary.getText()),
                txtAddress.getText(),
                txtCity.getText(),
                txtProvince.getText(),
                txtPostalCode.getText()
        );

        customerManagementService.updateCustomerDetails(customer);

        loadTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colCustId.setCellValueFactory(new PropertyValueFactory<>("custID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("custTitle"));
        colName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        loadTable();

        //---------------------------------------------------------------------------------------

        tblCustomerDetails.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setSelectedValue(newValue);
            }
        });

    }

    private void setSelectedValue(Customer selectedValue) {

        txtCustId.setText(selectedValue.getCustID());
        txtTitle.setText(selectedValue.getCustTitle());
        txtName.setText(selectedValue.getCustName());
        txtDob.setText(selectedValue.getDob());
        txtSalary.setText(String.valueOf(selectedValue.getSalary()));
        txtAddress.setText(selectedValue.getAddress());
        txtCity.setText(selectedValue.getCity());
        txtProvince.setText(selectedValue.getProvince());
        txtPostalCode.setText(selectedValue.getPostalCode());

    }

    public void loadTable() {
        tblCustomerDetails.setItems(customerManagementService.getAllCustomerDetails());
    }
}
