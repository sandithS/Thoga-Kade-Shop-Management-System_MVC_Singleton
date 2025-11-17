package service;

import javafx.collections.ObservableList;
import model.dto.CartItem;
import model.dto.Item;

public interface ItemManagementService {

    void addItemDetails(Item item);

    ObservableList<Item> getAllItemDetails();

    void updateItemDetails(Item item);

    void deleteItemDetails(String itemCode);

    Item searchItem(String itemCode, String description);

    boolean updateItemQuantity(ObservableList<CartItem> cartItemObservableList);
}
