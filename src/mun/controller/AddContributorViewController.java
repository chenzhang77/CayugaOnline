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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import mun.MainApp;
import mun.util.AnimationGenerator;
import mun.util.ImageEditor;
import mun.util.NetworkConnection;

public class AddContributorViewController implements Initializable{

    @FXML
    private TextField txField;

    @FXML
    private PasswordField pwField, pwField1;
    
    @FXML
    private Label loginLabel, registerLabel;
	
    private final ImageEditor imageEditor;
    private final AnimationGenerator animationGenerator;
    private MainApp mainApp;  	
    private BorderPane rootLayout;
    
    public AddContributorViewController() {
        imageEditor = new ImageEditor();
        animationGenerator = new AnimationGenerator();
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        this.rootLayout = mainApp.getBorderPane();
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
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

//		NetworkConnection networkConnectionObj = new NetworkConnection();   	
//		if(!networkConnectionObj.networkConnection) {
//			System.out.println("animateWhenBadLogin");
//			animateWhenBadLogin();
//			return;
//		} 
		animateWhenBadLogin();
    }
    
    @FXML
    private void reset() { 	
    	txField.setText(null);
    	pwField.setText(null);
    	pwField1.setText(null);
    }
    
    private void animateWhenBadLogin() {
    	mainApp.showNewContributorFeedback();	 
    }
    
}