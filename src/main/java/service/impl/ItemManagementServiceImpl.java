package service.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.CartItem;
import model.dto.Item;
import org.hibernate.Session;
import repository.impl.ItemRepositoryImpl;
import service.ItemManagementService;
import util.HibernateUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItemManagementServiceImpl implements ItemManagementService {

    ItemRepositoryImpl itemRepository = new ItemRepositoryImpl();

    @Override
    public void addItemDetails(Item item) {

        itemRepository.addItem(item);

    }

    @Override
    public ObservableList<Item> getAllItemDetails() {

        ObservableList<Item> itemList = FXCollections.observableArrayList();

        ResultSet resultSet = itemRepository.getAll();

        try {
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


//        Session session = HibernateUtil.getSession();
//        List fromItem = session.createQuery("From Item").list();
//        for (Item item : fromItem){
//            itemList.add(item);
//        }
//        return itemList;
    }

    @Override
    public void updateItemDetails(Item item) {

        itemRepository.updateItem(item);

    }

    @Override
    public void deleteItemDetails(String itemCode) {

        itemRepository.deleteItem(itemCode);

    }

    @Override
    public Item searchItem(String itemCode, String description) {
        ResultSet resultSet = itemRepository.searchItem(itemCode,description);

        try {
            resultSet.next();
                return new Item(
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getInt("QtyOnHand")
                );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateItemQuantity(ObservableList<CartItem> cartItemObservableList) {

        boolean isItemQtyUpdate = false;

        for (CartItem cartItem:cartItemObservableList){
            isItemQtyUpdate = itemRepository.updateItemQuantity(cartItem.getItemCode(), cartItem.getQuantity());
        }
        if (isItemQtyUpdate == false){
            return false;
        }
        return isItemQtyUpdate;
    }
}
