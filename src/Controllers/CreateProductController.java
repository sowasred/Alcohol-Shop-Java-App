package Controllers;

import Models.Inventory;
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

public class CreateProductController implements Initializable {
    @FXML TextField productName;
    @FXML
    TextField productDescription;
    @FXML TextField numberOfStock;
    @FXML
    Label state;
    @FXML TextField productPrice;
    @FXML TextField category;
    @FXML
    Button saveButton;
    @FXML Button cancelButton;
    Inventory inventory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    /**
     * Saving the product that entered by user
     *
     */
    public void saveButtonPushed(){

        inventory.addProduct(productName.getText(), productDescription.getText(),Integer.valueOf(numberOfStock.getText()), Double.valueOf(productPrice.getText()),new File("/Images.default.jpg"), category.getText());
        state.setText("Success!");

    }
    /**
     * Going back to Inventory View
     */
    public void cancelBtnPushed(ActionEvent event) throws IOException {
        Parent createProductView = FXMLLoader.load(getClass().getResource("/Views/InventoryView.fxml"));

        Scene createProductScene = new Scene(createProductView);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(createProductScene);
        window.show();
    }

}
