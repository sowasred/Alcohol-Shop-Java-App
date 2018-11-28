package Controllers;

import Models.Inventory;
import Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.util.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

import static Models.Inventory.getAllProducts;
import static Models.Inventory.getCategoryNames;
import static Models.Inventory.getProductsWCategory;

public class InventoryViewController implements Initializable {

    @FXML private ListView<Product> listView;
    @FXML private ImageView imageView;
    @FXML private Button sellButton;
    @FXML private Label totalNumberStock;
    @FXML private Label price;
    @FXML private Label totalInventoryValue;
    @FXML private ComboBox<String> comboBox;
    @FXML private RadioButton radioButtonPhlow;
    @FXML private RadioButton radioButtonPlhigh;
    @FXML private RadioButton radioButtonAscending;
    @FXML private RadioButton radioButtonDescending;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // ComboBox<String> comboBox = new ComboBox<String>();
        comboBox.setPromptText("Select Category");

        //First we need to populate the Treemap
        Inventory.InitializeCategories();

      ObservableList<String> content = Inventory.getCategoryNames();

      for(String category : content)
        comboBox.getItems().add(category);

      listView.getItems().addAll(Inventory.getAllProducts());



    comboBox.getSelectionModel().selectedItemProperty().addListener(

            (observable, oldValue, newValue) -> {
                listView.getItems().removeAll(Inventory.getAllProducts());
                listView.getItems().addAll(Inventory.getProductsWCategory(newValue));

            }

    );

    }

}



