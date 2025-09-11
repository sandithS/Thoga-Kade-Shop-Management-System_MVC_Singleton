package controller.itemController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemManagementFormController implements Initializable {

    ItemManagementService itemManagementService = new ItemManagementController();

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
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colPackSize;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<Item> tblItemDetails;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtPackSize;

    @FXML
    private JFXTextField txtQtyOnHand;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    void btnAddOnAction(ActionEvent event) {

        Item item = new Item(
                txtItemCode.getText(),
                txtDescription.getText(),
                txtPackSize.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText())
        );

        itemManagementService.addItemDetails(item);

        loadTable();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        itemManagementService.deleteItemDetails(txtItemCode.getText());

        loadTable();

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtItemCode.setText(null);
        txtDescription.setText(null);
        txtPackSize.setText(null);
        txtUnitPrice.setText(null);
        txtQtyOnHand.setText(null);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        Item item = new Item(
                txtItemCode.getText(),
                txtDescription.getText(),
                txtPackSize.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText())
        );

        itemManagementService.updateItemDetails(item);

        loadTable();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("PackSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("QtyOnHand"));

        loadTable();

        //---------------------------------------------------------------------------------------

        tblItemDetails.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setSelectedValue(newValue);
            }
        });

    }

    private void setSelectedValue(Item selectedValue) {
        txtItemCode.setText(selectedValue.getItemCode());
        txtDescription.setText(selectedValue.getDescription());
        txtPackSize.setText(selectedValue.getPackSize());
        txtUnitPrice.setText(String.valueOf(selectedValue.getUnitPrice()));
        txtQtyOnHand.setText(String.valueOf(selectedValue.getQtyOnHand()));
    }

    private void loadTable() {
        tblItemDetails.setItems(itemManagementService.getAllItemDetails());
    }
}
