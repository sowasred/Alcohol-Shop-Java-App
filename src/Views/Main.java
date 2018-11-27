package Views;

import Controllers.InventoryViewController;
import Models.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.TreeMap;

import static Controllers.InventoryViewController.getAllProducts;
import static Controllers.InventoryViewController.getCategoryNames;


public class Main  extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("InventoryView.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Products");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main (String[] args){
        Product pro1 = new Product("Bud Light", "Light golden colour; light malt & hops.", 123, 2.6, "File:./src/Images/bud.jpeg");
        Product pro2 = new Product("Gosser", "Pale yellow/amber colour with a fine mousse, perfection.", 100, 2.15, "File:./src/Images/gosser.jpeg");
        Product pro3 = new Product("Ruffino Chianti", "Purple&red colour; aromas and flavours of red fruits.", 125, 14.95, "File:./src/Images/chianti.jpeg");
        Product pro4 = new Product("Fontana Di Papa Red", "Pale garnet almost rose colour; light strawberry.", 110, 19.15, "File:./src/Images/fontana.jpeg");
        Product pro5 = new Product("Jack Daniel's Rye", "This whiskey, made with a high percentage of alcohol.", 87, 36.95, "File:./src/Images/jack.jpeg");
        Product pro6 = new Product("Crown Royal Whisky", "Aging in both new and seasoned white oak barrels.", 56, 29.95, "File:./src/Images/crown.jpeg");

        TreeMap<Integer, Product> treeMap = new TreeMap<Integer, Product>();

        treeMap.put(1, pro1);
        treeMap.put(2, pro2);
        treeMap.put(11,pro3);
        treeMap.put(12,pro4);
        treeMap.put(101,pro5);
        treeMap.put(102,pro6);

        System.out.println(treeMap);






    launch(args);
    }
}
