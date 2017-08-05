/**
* The Cayuga Dictionary project is to keep the language vibrant and alive 
* through immersion courses for adults and language daycare for children.
* 
* The project is Led by Dr. Carrie Dyck
* Faculty of Humanities and Social Sciences
* Memorial University of Newfoundland
*
* @author  Chen Zhang
* @version online 2.0
* @since   2016-12-01 
*/
package mun;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mun.controller.LoginViewController;
import mun.controller.DictionaryController;
import mun.controller.NewDataController;
import mun.controller.UpdateDataController;
import mun.model.Dictionary;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Dictionary> dictionary = FXCollections.observableArrayList();
    
    public static void main(String[] args) {
        launch(args);
    }
    
	public ObservableList<Dictionary> getData() {
		return dictionary;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
	    this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Cayuga Dictionary");
        initRootLayout();
        showLoginView();
	}

    public void initRootLayout() {
        try { 
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showLoginView() {
    	
      try {
      // Load login overview.
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(MainApp.class.getResource("view/login.fxml"));
      GridPane loginOverview = (GridPane) loader.load();
      // Set login overview into the center of root layout.
      rootLayout.setCenter(loginOverview);
      // Give the controller access to the main app.
      LoginViewController controller = loader.getController();
      controller.setMainApp(this);

  } catch (IOException e) {
      e.printStackTrace();
  }
    	
    }
    
    public BorderPane getBorderPane() {
        return rootLayout;
    }
	
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
//	
//    private BorderPane rootLayout;

	

//    
//    public void showMainOverview() {
//        try {
//            // Load person overview.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(MainApp.class.getResource("view/LayoutView.fxml"));
//            AnchorPane personOverview = (AnchorPane) loader.load();
//
//            // Set person overview into the center of root layout.
//            rootLayout.setCenter(personOverview);
//
//            // Give the controller access to the main app.
//            DictionaryController controller = loader.getController();
//            controller.setMainApp(this);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    
    public boolean showAddItemDialog(Dictionary dictionary) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AddData.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Dictionary");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the Dictionary into the controller.
            NewDataController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setDictionary(dictionary);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
//    
    public Dictionary showUpdateItemDialog(Dictionary dictionary) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/UpdateData.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Dictionary");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the Dictionary into the controller.
            UpdateDataController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setDictionary(dictionary);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.oldDictionary();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
