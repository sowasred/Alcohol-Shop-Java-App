/**
 * @author Ozan Muldur
 * */
package Controllers;

import Models.Inventory;
import Models.Product;
import com.sun.javafx.scene.control.skin.LabeledText;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;


import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;


import static Models.Inventory.getAllProducts;
import static java.lang.System.*;

public class InventoryViewController implements Initializable{

    @FXML private ListView<Product> listView;
    @FXML private ImageView imageView;
    @FXML private Label price;
    @FXML private Label calculatedInventoryValue;
    @FXML private Label description;
    @FXML private ComboBox<String> comboBox;
    @FXML private RadioButton radioButtonPhlow;
    @FXML private RadioButton radioButtonPlhigh;
    @FXML private RadioButton radioButtonAscending;
    @FXML private RadioButton radioButtonDescending;
    public ToggleGroup buttonGroup = new ToggleGroup();

    /**
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // ComboBox<String> comboBox = new ComboBox<String>();
        comboBox.setPromptText("Select Category");

        //First we need to populate the Treemap
        Inventory.InitializeCategories();
        getAllProducts();

        // For Loop is created to put categories from created ObservableList<String>
        ObservableList<String> content = Inventory.getCategoryNames();

        // For Loop is created to put categories from created ObservableList<String>
          for(String category : content)
            comboBox.getItems().add(category);

        // To put all products in inventory to list view, getAllProducts() method is called
        listView.getItems().addAll(Inventory.getAllProducts());

        // To set our image according to selection in the list view, event listener is added to listview
        listView.getSelectionModel().selectedItemProperty().addListener(

                (observable, oldValue, newValue) ->{
                    if(newValue != null){
                   setImageView(newValue.getImageFile().toPath());
                   setDescriptionLabel(newValue);
                    }
                }
        );
        // To get our products according to their category, event listener is added to combobox
        comboBox.getSelectionModel().selectedItemProperty().addListener(

            (observable, oldValue, newValue) -> {
             //   Remove all items on the table before load new items regarding category
             listView.getItems().removeAll(Inventory.getAllProducts());
                listView.getItems().addAll(Inventory.getProductsWCategory(newValue));
                calculateCategoryValue(listView.getItems());


            }
    );

        // It makes each radiobutton to select seperately which means when we select one radio button it will deselect others
        buttonGroup.getToggles().addAll(radioButtonPhlow,radioButtonPlhigh,radioButtonAscending,radioButtonDescending);

        // We call the method to calculate whole inventory value
        calculateInventoryValue();

        // To sort our products in list view, event listener is added to toggle group
        buttonGroup.selectedToggleProperty().addListener((p,o,n) -> {
            if(p.getValue() == radioButtonPlhigh || p.getValue() == radioButtonPhlow){
                sortCategoryPriceLowToHigh(listView.getItems());
            }
            if(p.getValue() == radioButtonAscending || p.getValue() == radioButtonDescending){
                sortCategoryToName(listView.getItems());
            }
        });

        // As a default radio button that sorts items according to price high to low is selected
        radioButtonPhlow.setSelected(true);


    }

    // Initializer finishes here

    /**
     * Sorting Products in the list according to their names (either ascending or descending) regards to selected option
     * @param olist ObservableList<Product>
     */
    public void sortCategoryToName(ObservableList<Product> olist) {
        List<Product> list = olist.stream().collect(Collectors.toList());
        if (radioButtonAscending.isSelected()) {
            list.sort(Comparator.comparing(a -> a.getProductName()));
            ObservableList<Product> oolist = FXCollections.observableArrayList(list);
            listView.setItems(oolist);
        }
        if (radioButtonDescending.isSelected()) {
            ObservableList<Product> oolist2 = FXCollections.observableArrayList(list.stream().sorted(Comparator.comparing(Product::getProductName).reversed()).collect(Collectors.toList()));
            listView.setItems(oolist2);
        }
        listView.refresh();
    }


    /**
     * Sorting Products in the list according to price (either ascending or descending) regards to selected option
     * @param olist ObservableList<Product>
     */
    public void sortCategoryPriceLowToHigh(ObservableList<Product> olist){
        List<Product> list = olist.stream().collect(Collectors.toList());
        if(radioButtonPlhigh.isSelected()){
            list.sort(Comparator.comparing(a -> a.getPrice()));
            ObservableList<Product> oolist = FXCollections.observableArrayList(list);
            listView.setItems(oolist);
            }
        if(radioButtonPhlow.isSelected()){
            ObservableList<Product> oolist2 = FXCollections.observableArrayList(list.stream().sorted(Comparator.comparing(Product::getPrice).reversed()).collect(Collectors.toList()));
            listView.setItems(oolist2);
        }
        listView.refresh();
    }

    /**
     * Calculate total category value according to selected category
     * @param products ObservableList<Product>
     */
    public void calculateCategoryValue(ObservableList<Product> products){
        double categoryValue = 0;
            for (Product product: products){

                double price =product.getPrice();
                int units = product.getNumberOfStock();
                categoryValue += (units *price);
            }
            String str = String.format("$%.2f", categoryValue);
            price.setText(str);

    }
    /**
     * Create an Method for showing Image in ImageView
     * @param path
     */
    public void setImageView(Path path){
        Image img = new Image(path.toString());
        imageView.setImage(img);
    }

    public void setDescriptionLabel(Product product){
        String str = product.getDescription();
        description.setText(str);

    }



    /**
     *
     * Decrease the number of stock by 1 for each selling
     *  And update the listview's stock for product that been sold
     */
    public void sellUnit() {
        if(listView.getSelectionModel().getSelectedItem().getNumberOfStock()  > 0 ){

            int numberStrock = listView.getSelectionModel().getSelectedItem().getNumberOfStock();
            --numberStrock;
            listView.getSelectionModel().getSelectedItem().setNumberOfStock(numberStrock);
            listView.refresh();
            calculateInventoryValue();
            calculateCategoryValue(listView.getItems());
        } else
            AlertController.alertError("There is no available item to sell.");
        throw new IllegalArgumentException("There is no available item to sell.");
    }
    /**
     *
     * Calculate total Inventory for whole alcoholshop
     * And set the label as a calculation result
     */
    public void calculateInventoryValue(){
        double total = 0;
        for (Product product : Inventory.getAllProducts()){
            double productPrice = product.getPrice();
            int stockNumber = product.getNumberOfStock();
            total += productPrice * stockNumber;
        }
       calculatedInventoryValue.setText("$" +(Double.toString(total)));

    }



}



