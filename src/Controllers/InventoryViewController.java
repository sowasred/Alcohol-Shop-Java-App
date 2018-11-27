package Controllers;

import Models.Product;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;

import javax.swing.text.html.ImageView;
import javax.swing.text.html.ListView;
import java.util.*;
import java.awt.*;

public class InventoryViewController {

    @FXML
    private ListView listView;
    @FXML
    private ImageView imageView;
    @FXML
    private Button sellButton;
    @FXML
    private Label totalNumberStock;
    @FXML
    private Label price;
    @FXML
    private Label totalInventoryValue;
    @FXML
    private ComboBox<Product> comboBox;
    @FXML
    private RadioButton radioButtonPhlow;
    @FXML
    private RadioButton radioButtonPlhigh;
    @FXML
    private RadioButton radioButtonAscending;
    @FXML
    private RadioButton radioButtonDescending;



public static LinkedList<String> getCategoryNames(){

    LinkedList llist = new LinkedList();
    llist.add("Beers");
    llist.add("Wines");
    llist.add("Whiskeys");

    return llist;
}

public static LinkedList<String> getAllProducts(TreeMap<Integer, Product> treeMap){

    LinkedList<String> allist = new LinkedList(treeMap.values());

    return allist;

}




}
