package Controllers;

import javafx.scene.control.Alert;

public class AlertController {

    /**
     * Will Alert Error Message on the window
     * @param errorMessage String
     */

    public static void alertError(String errorMessage)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error: Please validate related field");
        alert.setHeaderText("ERROR!");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    /**
     * Will Alert Information Message on the window
     * @param infoMessage String
     */

    public static void alertInfo (String infoMessage){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information: One item has been sold.");
        alert.setHeaderText("Information");
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
}
