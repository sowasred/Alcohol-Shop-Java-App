package Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.util.LinkedList;
import java.util.TreeMap;

import static Models.Inventory.addProduct;
import static Models.Inventory.getCategoryNames;
import static Models.Inventory.getProductsWCategory;

public class Main extends Application {

    public static void main (String[] args){


        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("InventoryView.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Products");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
