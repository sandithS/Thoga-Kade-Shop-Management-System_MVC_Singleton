package controller.orderDetailsController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.OrderDetails;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderDetailsManagementFormController implements Initializable {

    OrderDetailsManagementService orderDetailsManagementService = new OrderDetailsManagementController();

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colOrderID;

    @FXML
    private TableColumn<?, ?> colOrderQty;

    @FXML
    private TableView<OrderDetails> tblOrderDetails;

    @FXML
    private JFXTextField txtDiscount;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtOrderId;

    @FXML
    private JFXTextField txtOrderQty;

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtOrderId.setText(null);
        txtItemCode.setText(null);
        txtOrderQty.setText(null);
        txtDiscount.setText(null);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        orderDetailsManagementService.deleteOrderDetails(txtOrderId.getText(),txtItemCode.getText());

        loadTable();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        OrderDetails orderDetails = new OrderDetails(
                txtOrderId.getText(),
                txtItemCode.getText(),
                Integer.parseInt(txtOrderQty.getText()),
                Integer.parseInt(txtDiscount.getText())
        );

        orderDetailsManagementService.updateOrderDetails(orderDetails);

        loadTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colOrderID.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colOrderQty.setCellValueFactory(new PropertyValueFactory<>("OrderQTY"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("Discount"));

        loadTable();

        //---------------------------------------------------------------------------------------

        tblOrderDetails.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setSelectedValue(newValue);
            }
        });

    }

    private void setSelectedValue(OrderDetails selectedValue) {
        txtOrderId.setText(selectedValue.getOrderID());
        txtItemCode.setText(selectedValue.getItemCode());
        txtOrderQty.setText(String.valueOf(selectedValue.getOrderQTY()));
        txtDiscount.setText(String.valueOf(selectedValue.getDiscount()));
    }

    private void loadTable() {
        tblOrderDetails.setItems(orderDetailsManagementService.getAllOrderDetails());
    }

}
