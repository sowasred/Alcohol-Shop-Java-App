/**
 * @author Ozan Muldur
 * */
package Models;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sun.reflect.generics.tree.Tree;

import java.io.File;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Inventory{


    private static TreeMap<String, LinkedList<Product>> alcoholshop;

    /**
     * Get products categories with passing category name in the method
     * @param category String
     * @return LinkedList<Product>
     */
    //
    public static LinkedList<Product> getProductsWCategory(String category){

        return alcoholshop.get(category);
    }

    /**
     * Initiliaze the Treemap with category names as keys and LinkedList<Product> as values
     * Basically Load our inventory
     */
    // Initializing Products and Categories
    public static void InitializeCategories()
    {
        alcoholshop = new TreeMap<>();
        addProduct("Bud", "Light golden colour; light malt & hops.", 123, 2.6, new File("/Images/bud.jpg"),"Beers");
        addProduct("Gosser", "The colour with a fine mousse, perfection.", 100, 2.15,new File("/Images/gosser.jpeg"),"Beers" );
        addProduct("Ruffino Chianti", "Purple&red colour; aromas and flavours of red fruits.", 125, 14.95,new File("/Images/chianti.jpeg"),"Wines");
        addProduct("Fontana Di Papa Red", "Pale garnet almost rose colour; light strawberry.", 110, 19.15, new File("/Images/fontana.jpeg"),"Wines");
        addProduct("Jack Daniel's Rye", "This whiskey, made with a high percentage of alcohol.", 87, 36.95,new File("/Images/jack.jpeg"),"Whiskeys");
        addProduct("Crown Royal Whisky", "Aging in both new and seasoned white oak barrels.", 56, 29.95,new File("/Images/crown.jpeg"),"Whiskeys" );
        addProduct("Efes", "Turkish taste of Beer.", 2, 3,new File("/Images/efes.jpg"),"Beers" );

    }

    /**
     * Getting Category Names for putting in ComboBox
     * @return ObservableList<String>
     *
     */

    public static ObservableList<String> getCategoryNames(){

        Set<String> keys = alcoholshop.keySet();
            ObservableList<String> categoryList =  keys.stream().sorted().collect(Collector.of(
                     FXCollections::observableArrayList,
                     ObservableList::add,
                     (l1, l2) -> { l1.addAll(l2); return l1; }));
             return  categoryList;
        }

    /**
     * Getting All products for displaying in Listview
     * @return LinkedList<Product>
     *
     */

    public static LinkedList<Product> getAllProducts() {

        LinkedList<Product> list = new LinkedList<>();
        for (String key: alcoholshop.keySet())
        {
            list.addAll(alcoholshop.get(key));
        }
        return list;

        }

    /**
     * Creating a TreeMap and putting keys as a category and putting product lists as a value
     * @param productName String
     * @param description String
     * @param numberOfStock Int
     * @param price         Double
     * @param imageFile     File
     * @param category      String
     */

    public static void addProduct(String productName, String description, int numberOfStock, double price, File imageFile, String category)
    {
        Product newProduct = new Product(productName, description, numberOfStock,price,imageFile,category);
        String categoryName = category;

        if (!alcoholshop.containsKey(categoryName)){
            LinkedList<Product> listt = new LinkedList<>();
            listt.add(newProduct);
            alcoholshop.put(categoryName, listt);
        }
        else
        {
            alcoholshop.get(categoryName).add(newProduct);
        }
    }
}