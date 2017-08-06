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

import mun.MainApp;
import mun.util.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {

    @FXML
    private ImageView imgView;
    @FXML
    private ImageView imgView1;
    @FXML
    private ImageView imgView11;
    @FXML
    private ImageView imgView2;
    @FXML
    private ImageView imgView33;
    @FXML
    private ImageView imgView13;

    @FXML
    private GridPane root;

    @FXML
    private TextField txField;

    @FXML
    private PasswordField pwField;

    @FXML
    private Label loginLabel, registerLabel;

    private final ImageEditor imageEditor;
    private final AnimationGenerator animationGenerator;
    private MainApp mainApp;  	
    private BorderPane rootLayout;
    
    public LoginViewController() {
        imageEditor = new ImageEditor();
        animationGenerator = new AnimationGenerator();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        this.rootLayout = mainApp.getBorderPane();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        animationGenerator.applyFadeAnimationOn(root, 3000, 0f, 1.0f, null);
        imageEditor.setImageOn(imgView, "/buttons/cayuga.png", 91, 83);
        imageEditor.setImageOn(imgView2, "/buttons/mid.jpg", 250, 10);
        imageEditor.setImageOn(imgView11, "/buttons/sun.jpg", 44, 48);
        imageEditor.setImageOn(imgView1, "/buttons/mun.jpg", 84, 50);
        imageEditor.setImageOn(imgView13, "/buttons/sixNation.jpg", 60, 74);
        imageEditor.setImageOn(imgView33, "/buttons/SNP.jpg", 96, 48);
        setOnKeyPressed();
    }

    @FXML
    private void mouseMovedLoginLabel() {
        animationGenerator.applyFadeAnimationOn(loginLabel, 500, 1.0f, 0.7f, null);
    }

    @FXML
    private void mouseMovedRegisterLabel() {
        animationGenerator.applyFadeAnimationOn(registerLabel, 500, 1.0f, 0.7f, null);
    }

    @FXML
    private void mouseExitedLoginLabel() {
        animationGenerator.applyFadeAnimationOn(loginLabel, 500, 0.7f, 1.0f, null);
    }

    @FXML
    private void mouseExitedRegisterLabel() {
        animationGenerator.applyFadeAnimationOn(registerLabel, 500, 0.7f, 1.0f, null);
    }

    @FXML
    private void login() {
        if(loginSuccessful())
            animateWhenLoginSuccess();
        else
            animateWhenBadLogin();
    }

    private boolean loginSuccessful() {
    	//return true;
        return txField.getText().equals("admin") || txField.getText().equals("contributor");
    }

    private void setOnKeyPressed() {
        root.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER) && loginSuccessful())
                animateWhenLoginSuccess();
            else
                animateWhenBadLogin();
        });
    }

    @FXML
    private void guest() {
    	animateWhenGuestLogin();
    }
    
    private void animateWhenLoginSuccess() {
    	
    	if(txField.getText().equals("admin")) {
    		try {
    			FXMLLoader loader = new FXMLLoader();
    			loader.setLocation(MainApp.class.getResource("view/AdminLayout.fxml"));
    			BorderPane mainView = (BorderPane) loader.load();
    			AdminViewController controller = loader.getController();
    			controller.setMainApp(mainApp);              
    	      	FXMLLoader loader2 = new FXMLLoader();
    	        loader2.setLocation(MainApp.class.getResource("view/AdminDictionary.fxml"));
    	        AnchorPane dictionary = (AnchorPane) loader2.load();
    	        DictionaryController controller2 = loader2.getController();
    	        controller2.setMainApp(mainApp);       
    	        controller.stackPane.getChildren().remove(0, controller.stackPane.getChildren().size());
    	        controller.stackPane.getChildren().add(dictionary);          
    			animationGenerator.applyFadeAnimationOn(root, 1000, 1.0f, 0f, event -> {
    				mainView.setOpacity(0f);              	
    			    rootLayout.setCenter(mainView);
    			    animationGenerator.applyFadeAnimationOn(mainView, 1000, 0f, 1.0f, null);
    			});
    	        }catch(IOException ex) {
    	            ex.printStackTrace();
    	        }
    	}else {
    		try {
    			FXMLLoader loader = new FXMLLoader();
    			loader.setLocation(MainApp.class.getResource("view/ContributorLayout.fxml"));
    			BorderPane mainView = (BorderPane) loader.load();
    			ContributorViewController controller = loader.getController();
    			controller.setMainApp(mainApp);              
    	      	FXMLLoader loader2 = new FXMLLoader();
    	        loader2.setLocation(MainApp.class.getResource("view/AdminDictionary.fxml"));
    	        AnchorPane dictionary = (AnchorPane) loader2.load();
    	        DictionaryController controller2 = loader2.getController();
    	        controller2.setMainApp(mainApp);       
    	        controller.stackPane.getChildren().remove(0, controller.stackPane.getChildren().size());
    	        controller.stackPane.getChildren().add(dictionary);          
    			animationGenerator.applyFadeAnimationOn(root, 1000, 1.0f, 0f, event -> {
    				mainView.setOpacity(0f);              	
    			    rootLayout.setCenter(mainView);
    			    animationGenerator.applyFadeAnimationOn(mainView, 1000, 0f, 1.0f, null);
    			});
    	        }catch(IOException ex) {
    	            ex.printStackTrace();
    	        }
    	}
    	
    	
        
    }

    private void animateWhenGuestLogin() {
        try {        	
	    	FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(MainApp.class.getResource("view/GuestLayout.fxml"));
	    	BorderPane mainView = (BorderPane) loader.load();
	    	GuestViewController controller = loader.getController();
	    	controller.setMainApp(mainApp);             
			FXMLLoader loader2 = new FXMLLoader();
			loader2.setLocation(MainApp.class.getResource("view/GuestDictionary.fxml"));
			AnchorPane dictionary = (AnchorPane) loader2.load();		
			DictionaryController controller2 = loader2.getController();
			controller2.setMainApp(mainApp);				
			controller.stackPane.getChildren().remove(0, controller.stackPane.getChildren().size());
			controller.stackPane.getChildren().add(dictionary);		  		  
			animationGenerator.applyFadeAnimationOn(root, 1000, 1.0f, 0f, event -> {
				mainView.setOpacity(0f);              	
			    rootLayout.setCenter(mainView);
			    animationGenerator.applyFadeAnimationOn(mainView, 1000, 0f, 1.0f, null);
			});
			}catch(IOException ex) {
			    ex.printStackTrace();
			}
    }
    
    private void animateWhenBadLogin() {
        try {        	
        	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/login.fxml"));
            GridPane login = (GridPane) loader.load();
            LoginViewController controller = loader.getController();
            controller.setMainApp(mainApp);
            
            StackPane temp = new StackPane();
            temp.setMaxSize(100, 100);
            temp.getChildren().add(new ImageView(new Image("buttons/memeber.jpg")));
         
            animationGenerator.applyFadeAnimationOn(login, 1000, 1.0f, 0f, event -> {
                temp.setOpacity(0f);
                rootLayout.setCenter(temp);
                animationGenerator.applyFadeAnimationOn(temp, 1000, 0f, 1.0f, event1 -> {                	
                	login.setOpacity(0f);              	
                    rootLayout.setCenter(login);
                    animationGenerator.applyFadeAnimationOn(login, 1000, 0f, 1.0f, null);
            });
                    
        });
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
