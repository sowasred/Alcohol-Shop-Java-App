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
    public static void main(String[] args)
    {
        InitializeCategories();






    }

    public static LinkedList<Product> getProductsWCategory(String category){

        return alcoholshop.get(category);
    }


    public static void InitializeCategories()
    {
        alcoholshop = new TreeMap<>();
        addProduct("Bud Light", "Light golden colour; light malt & hops.", 123, 2.6, new File("bud.jpeg"),"Beers");
        addProduct("Gosser", "Pale yelisttow mber colour with a fine mousse, perfection.", 100, 2.15,new File("gosser.jpeg"),"Beers" );
        addProduct("Ruffino Chianti", "Purple&red colour; aromas and flavours of red fruits.", 125, 14.95,new File("chianti.jpeg"),"Wines");
        addProduct("Fontana Di Papa Red", "Pale garnet almost rose colour; light strawberry.", 110, 19.15, new File("fontana.jpeg"),"Wines");
        addProduct("Jack Daniel's Rye", "This whiskey, made with a high percentage of alcohol.", 87, 36.95,new File("jack.jpeg"),"Whiskeys");
        addProduct("Crown Royal Whisky", "Aging in both new and seasoned white oak barrels.", 56, 29.95,new File("crown.jpeg"),"Whiskeys" );

    }

    public static ObservableList<String> getCategoryNames(){


        Set<String> keys = alcoholshop.keySet();
            ObservableList<String> categoryList =  keys.stream().sorted().collect(Collector.of(
                     FXCollections::observableArrayList,
                     ObservableList::add,
                     (l1, l2) -> { l1.addAll(l2); return l1; }));
             return  categoryList;

        }


    public static LinkedList<Product> getAllProducts() {

      LinkedList<Product> newList = new LinkedList<>();
      for(Product product: newList) {
          newList.remove(product);
      }
            newList =  alcoholshop.get("Beers");

            System.out.println(newList);
      LinkedList<Product> newList2 = new LinkedList<>();
         newList2 =     alcoholshop.get("Wines");
        System.out.println(newList2);

        for (Product product: newList2) {
             newList.push(product);

         }
      LinkedList<Product> newList3 = new LinkedList<>();
        newList3 =   alcoholshop.get("Whiskeys");
        System.out.println(newList3);

        for(Product product: newList3) {
            newList.push(product);
        }

        System.out.println(newList);

      return newList;

    }

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