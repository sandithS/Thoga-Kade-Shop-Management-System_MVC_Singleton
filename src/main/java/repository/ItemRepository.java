package repository;

import model.dto.Item;

import java.sql.ResultSet;

public interface ItemRepository {

    ResultSet getAll();

    ResultSet searchItem(String itemCode, String description);

    void addItem(Item item);

    void updateItem(Item item);

    void deleteItem(String itemCode);

    boolean updateItemQuantity(String itemCode, int quantity);
}
