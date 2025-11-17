package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.CartItem;
import model.dto.Customer;
import model.dto.Item;
import model.dto.Order;
import service.PlaceOrderService;
import service.impl.PlaceOrderServiceImpl;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {

    @FXML
    private JFXButton btnCart;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView<CartItem> tblCartItem;

    @FXML
    private JFXTextField txtCustId;

    @FXML
    private JFXTextField txtCustName;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtDiscount;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtOrderId;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtUnitPrice;

    PlaceOrderService placeOrderService = new PlaceOrderServiceImpl();

    ObservableList<CartItem> cartItems = FXCollections.observableArrayList();

    @FXML
    void btnCartOnAction(ActionEvent event) {

        cartItems.add(new CartItem(
                txtItemCode.getText(),
                txtDescription.getText(),
                Integer.parseInt(txtQty.getText()),
                Double.parseDouble(txtUnitPrice.getText()),
                Double.parseDouble(txtDiscount.getText()),
                calculateTotal(txtUnitPrice.getText(), txtQty.getText())
        ));

        tblCartItem.setItems(cartItems);

        clearFields();
        calculateNetTotal();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        placeOrderService.placeOrder(new Order(
                txtOrderId.getText(),
                String.valueOf(LocalDate.now()),
                txtCustId.getText()
        ),cartItems);
    }

    @FXML
    void txtCustIdOnAction(ActionEvent event) {
        Customer customer = placeOrderService.getCustomer(txtCustId.getText());
        txtCustName.setText(customer.getCustName());
    }

    @FXML
    void txtItemCodeOnAction(ActionEvent event) {
        Item item = placeOrderService.getItem(txtItemCode.getText());

        txtDescription.setText(item.getDescription());
        txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        txtDiscount.setText("0.0");
    }

    private double calculateTotal(String unitPrice, String quantity){
        return Double.parseDouble(unitPrice) * Integer.parseInt(quantity);
    }

    public void clearFields(){
        txtItemCode.setText(null);
        txtDescription.setText(null);
        txtUnitPrice.setText(null);
        txtQty.setText(null);
        txtDiscount.setText("0.0");
    }

    public void calculateNetTotal(){
        double netTotal =0.0;
        for (CartItem cartItem: cartItems){
            netTotal += cartItem.getTotalPrice();
            lblTotal.setText(String.valueOf(netTotal));
        }
    }
}
