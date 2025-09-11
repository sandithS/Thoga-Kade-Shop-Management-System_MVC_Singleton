package controller.itemController;

import javafx.collections.ObservableList;
import model.Item;

public interface ItemManagementService {

    void addItemDetails(Item item);

    ObservableList<Item> getAllItemDetails();

    void updateItemDetails(Item item);

    void deleteItemDetails(String itemCode);

}
