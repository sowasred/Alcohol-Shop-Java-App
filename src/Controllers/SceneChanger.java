package Controllers;


import Models.Inventory;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class SceneChanger {

    public static void changeScene(ActionEvent event, String viewName, String title, Inventory inventory, Preloader preloader) throws IOException {

        //load the fxml file
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new Object(){}.getClass().getResource(viewName));
        Parent parent = loader.load();

        //access controller class and pass the contact object

            preloader = loader.getController();
            preloader.preLoadData(inventory);

        //create the scene
        Scene scene = new Scene(parent);

        // get the window from the event passed in
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }


}