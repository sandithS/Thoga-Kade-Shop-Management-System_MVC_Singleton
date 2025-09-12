package controller.itemController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemManagementController implements ItemManagementService {
    @Override
    public void addItemDetails(Item item) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Item VALUES(?,?,?,?,?)");

            preparedStatement.setObject(1, item.getItemCode());
            preparedStatement.setObject(2, item.getDescription());
            preparedStatement.setObject(3, item.getPackSize());
            preparedStatement.setObject(4, item.getUnitPrice());
            preparedStatement.setObject(5, item.getQtyOnHand());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Item> getAllItemDetails() {

        ObservableList<Item> itemList = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Item");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Item item = new Item(
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getInt("QtyOnHand")
                );
                itemList.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemList;
    }

    @Override
    public void updateItemDetails(Item item) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Item SET Description=?, PackSize=?, UnitPrice=?, QtyOnHand=? WHERE ItemCode=?");

            preparedStatement.setObject(1, item.getDescription());
            preparedStatement.setObject(2, item.getPackSize());
            preparedStatement.setObject(3, item.getUnitPrice());
            preparedStatement.setObject(4, item.getQtyOnHand());
            preparedStatement.setObject(5, item.getItemCode());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteItemDetails(String itemCode) {

        try {

            DBConnection.getInstance().getConnection().createStatement().executeUpdate("DELETE FROM Item WHERE ItemCode='" + itemCode + "'");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
