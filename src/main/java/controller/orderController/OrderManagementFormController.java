package controller.orderController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Order;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderManagementFormController implements Initializable {

    OrderManagementService orderManagementService = new OrderManagementController();

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colOrderID;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colCustID;

    @FXML
    private TableView<Order> tblOrderDetails;

    @FXML
    private JFXTextField txtCustId;

    @FXML
    private JFXTextField txtOrderDate;

    @FXML
    private JFXTextField txtOrderId;

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtOrderId.setText(null);
        txtOrderDate.setText(null);
        txtCustId.setText(null);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        orderManagementService.deleteOrders(txtOrderId.getText());

        loadTable();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

        Order order = new Order(
                txtOrderId.getText(),
                txtOrderDate.getText(),
                txtCustId.getText()
        );

        orderManagementService.placeOrders(order);

        loadTable();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Order order = new Order(
                txtOrderId.getText(),
                txtOrderDate.getText(),
                txtCustId.getText()
        );

        orderManagementService.updateOrders(order);

        loadTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        colOrderID.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
        colCustID.setCellValueFactory(new PropertyValueFactory<>("custID"));
        
        loadTable();

        //---------------------------------------------------------------------------------------

        tblOrderDetails.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setSelectedValue(newValue);
            }
        });
    }

    private void setSelectedValue(Order selectedValue) {
        txtOrderId.setText(selectedValue.getOrderID());
        txtOrderDate.setText(selectedValue.getOrderDate());
        txtCustId.setText(selectedValue.getCustID());
    }

    private void loadTable() {
        tblOrderDetails.setItems(orderManagementService.getAllOrders());
    }
}
