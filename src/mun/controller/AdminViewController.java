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
package mun.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import mun.MainApp;
import mun.util.*;

public class AdminViewController implements Initializable{

    @FXML
    public StackPane stackPane;
    @FXML
    private BorderPane borderPane;
    @FXML
    protected ImageView dictionaryImage, menuButton, syn, contributor, file, about, logOut ;
    @FXML
    private Label title;
    @FXML
    private VBox menuRoot;
      
    @FXML
    private Separator sep_1, sep_2, sep_3, sep_4, sep_5 ;
    
    private Separator[] separators = new Separator[5];
    private static final int DEFAULT_STARTING_X_POSITION = 0;
    private static final int DEFAULT_ENDING_X_POSITION = -200;
    private MainApp mainApp;  	
    private BorderPane rootLayout;
    
    public void initSeparators() {
        separators[0] = sep_1;
        separators[1] = sep_2;
        separators[2] = sep_3;
        separators[3] = sep_4;
        separators[4] = sep_5;
    }

    private boolean isOpened = false;
    private final AnimationGenerator animationGenerator;
    private final ImageEditor imageEditor;
    
    public AdminViewController() {
        animationGenerator = new AnimationGenerator();
        imageEditor = new ImageEditor();
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        this.rootLayout = mainApp.getBorderPane();
        // Add observable list data to the table
       // dictionaryTable.setItems(mainApp.getData());
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub	
	     initSeparators();
	     setImages();
	     closeAnimation();
	}
	
    private void openAnimation() {
	    animationGenerator.applyTranslateAnimationOn(menuRoot, 500, DEFAULT_ENDING_X_POSITION, DEFAULT_STARTING_X_POSITION);
	    animationGenerator.applyTranslateAnimationOn(stackPane, 500, DEFAULT_ENDING_X_POSITION, DEFAULT_STARTING_X_POSITION);
	    animationGenerator.applyFadeAnimationOn(dictionaryImage, 500, 0.1f, 1.0f, null);
	    animateSeparators(0.1f, 1.0f);
	    rotateMenuIcon();
	    isOpened = true;
	}
	
    private void setImages() {
        imageEditor.setImageOn(menuButton, "/buttons/Menu-50.png", 35, 18);
        imageEditor.setImageOn(syn, "/buttons/Globe.png", 35, 18);
        imageEditor.setImageOn(dictionaryImage, "/buttons/book.jpg", 150, 150);
        imageEditor.setRectangularClipOf(dictionaryImage, 15, 15);
        imageEditor.setImageOn(contributor, "/buttons/Settings-50.png", 35, 18);
        imageEditor.setImageOn(file, "/buttons/Share-50.png", 35, 18);
        imageEditor.setImageOn(about, "/buttons/Info-50.png", 35, 18);
        imageEditor.setImageOn(logOut, "/buttons/Shutdown-52.png", 35, 18);
    }

    public void closeAnimation() {
        animationGenerator.applyTranslateAnimationOn(menuRoot, 500, DEFAULT_STARTING_X_POSITION, DEFAULT_ENDING_X_POSITION);
        animationGenerator.applyTranslateAnimationOn(stackPane, 500, DEFAULT_STARTING_X_POSITION , DEFAULT_ENDING_X_POSITION);
        animationGenerator.applyFadeAnimationOn(dictionaryImage, 500, 1.0f, 0.1f, null);
        animateSeparators(1.0, 0.1f);
        rotateMenuIcon();
        isOpened = false;
    }
    
    private void rotateMenuIcon() {
        animationGenerator.applyRotationOn(menuButton, 500, 180f, 1);
    }

    private void animateSeparators(double from, double to) {
        for(Separator separator : separators)
            animationGenerator.applyFadeAnimationOn(separator, 500, from, to, null);
    }
    
    @FXML
    private void setOnMenuClick() { 	
        if(isOpened)
            closeAnimation();
        else
            openAnimation();
    }
    
    @FXML
    private void logoutSelected() {
        try {
        	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/login.fxml"));
            GridPane login = (GridPane) loader.load();
            LoginViewController controller = loader.getController();
            controller.setMainApp(mainApp);
            animationGenerator.applyFadeAnimationOn(borderPane, 2000, 1.0f, 0f, event -> {
            	login.setOpacity(0f);              	
                rootLayout.setCenter(login);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void dictionarySelected() {
        try {
        	closeAnimation();
        	title.setText("Cayuga Dictionary");
        	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AdminDictionary.fxml"));
            AnchorPane dictionary = (AnchorPane) loader.load();
 
            DictionaryController controller = loader.getController();
            controller.setMainApp(mainApp);
            
            
            stackPane.getChildren().remove(0, stackPane.getChildren().size());
            stackPane.getChildren().add(dictionary);
            
            
            animationGenerator.applyFadeAnimationOn(stackPane, 500, 0f, 1.0f, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void contributorSelected() {
        try {
        	closeAnimation();
        	title.setText("Manage Contributor");
        	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/contributor.fxml"));
            AnchorPane about = (AnchorPane) loader.load();
              
        	 
            stackPane.getChildren().remove(0, stackPane.getChildren().size());
            stackPane.getChildren().add(about);
            animationGenerator.applyFadeAnimationOn(stackPane, 500, 0f, 1.0f, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    @FXML
    private void fileSelected() {
        try {
        	closeAnimation();
        	title.setText("File Update");
        	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/file.fxml"));
            AnchorPane about = (AnchorPane) loader.load();
              
        	 
            stackPane.getChildren().remove(0, stackPane.getChildren().size());
            stackPane.getChildren().add(about);
            animationGenerator.applyFadeAnimationOn(stackPane, 500, 0f, 1.0f, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    @FXML
    private void aboutSelected() {
        try {
        	closeAnimation();
        	title.setText("About");
        	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/about.fxml"));
            AnchorPane about = (AnchorPane) loader.load();
              
//        	loader.setLocation(MainApp.class.getResource("view/login.fxml"));
//            GridPane about = (GridPane) loader.load();
        	 
            stackPane.getChildren().remove(0, stackPane.getChildren().size());
            stackPane.getChildren().add(about);
            animationGenerator.applyFadeAnimationOn(stackPane, 500, 0f, 1.0f, null);
//            LoginViewController controller = loader.getController();
//            controller.setMainApp(mainApp);
//            animationGenerator.applyFadeAnimationOn(borderPane, 2000, 1.0f, 0f, event -> {
//            	about.setOpacity(0f);              	
//                rootLayout.setCenter(about);
//            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    @FXML
//    private void profileSelected() {
//        try {
//            closeAnimation();
//            title.setText("Profile");
//            Parent profileView = FXMLLoader.load(getClass().getResource("profile.fxml"));
//            stackPane.getChildren().remove(0, stackPane.getChildren().size());
//            stackPane.getChildren().add(profileView);
//            animationGenerator.applyFadeAnimationOn(stackPane, 500, 0f, 1.0f, null);
//        }catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//    }

}
