package Controllers;

import Models.Inventory;
import Models.Product;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.net.URL;
import java.util.*;


import static Models.Inventory.getAllProducts;

public class InventoryViewController implements Initializable {

    @FXML private ListView<Product> listView;
    @FXML private ImageView imageView;
    @FXML private Label totalNumberStock;
    @FXML private Label price;
    @FXML private Label calculatedInventoryValue;
    @FXML private ComboBox<String> comboBox;
    @FXML private RadioButton radioButtonPhlow;
    @FXML private RadioButton radioButtonPlhigh;
    @FXML private RadioButton radioButtonAscending;
    @FXML private RadioButton radioButtonDescending;
    public ToggleGroup buttonGroup = new ToggleGroup();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // ComboBox<String> comboBox = new ComboBox<String>();
        comboBox.setPromptText("Select Category");


        //First we need to populate the Treemap
        Inventory.InitializeCategories();
        getAllProducts();
      ObservableList<String> content = Inventory.getCategoryNames();

      for(String category : content)
        comboBox.getItems().add(category);

     listView.getItems().addAll(Inventory.getAllProducts());


    comboBox.getSelectionModel().selectedItemProperty().addListener(

            (observable, oldValue, newValue) -> {
             //   Remove all items on the table before load new items regarding category
             listView.getItems().removeAll(Inventory.getAllProducts());
                listView.getItems().addAll(Inventory.getProductsWCategory(newValue));

            }
    );


        // It makes each radiobutton to select seperately which means when we select one radio button it will deselect others
        buttonGroup.getToggles().addAll(radioButtonPhlow,radioButtonPlhigh,radioButtonAscending,radioButtonDescending);

        // As a default radio button that sorts items according to price high to low is selected
        radioButtonPhlow.setSelected(true);
        calculateInventoryValue();
        setImageView();



    }

    // Create an Method for showing Image in ImageView
    public void setImageView(){

        /*System.out.println(listView.getSelectionModel().getSelectedItem().getImageFile().getName());*/
     //  Image image = new Image(listView.getSelectionModel().getSelectedItem().getImageFile().toString());
     //   imageView.setImage(image);

      //  price.setText(Double.toString(listView.getSelectionModel().getSelectedItem().getPrice()));
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



