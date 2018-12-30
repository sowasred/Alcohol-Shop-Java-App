package Controllers;

import Models.Inventory;
import Models.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateProductController implements Initializable,Preloader {
    @FXML TextField productName;
    @FXML TextField productDescription;
    @FXML TextField numberOfStock;
    @FXML Label state;
    @FXML TextField productPrice;
    @FXML TextField category;
    @FXML Button saveButton;
    @FXML Button cancelButton;
    private Inventory inventory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inventory = new Inventory();
    }

    /**
     * Saving the product that entered by user
     *
     */
    public void saveButtonPushed(ActionEvent event) throws IOException {
        inventory.addProduct(productName.getText(),
                productDescription.getText(),
                Integer.valueOf(numberOfStock.getText()),
                Double.valueOf(productPrice.getText()),
                new File("/Images.default.jpg"),
                category.getText());
        SceneChanger.changeScene(event,"../Views/InventoryView.fxml","dsa",inventory,null);
    }
    /**
     * Going back to Inventory View
     */
    public void cancelBtnPushed(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event,"../Views/InventoryView.fxml","Yarrak",inventory,null);
    }

    @Override
    public void preLoadData(Inventory inventory) {
        this.inventory = inventory;
    }

}
