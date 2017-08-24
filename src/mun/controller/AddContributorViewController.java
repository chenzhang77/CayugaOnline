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
import mun.model.User;
import mun.util.AnimationGenerator;
import mun.util.DBConnection;
import mun.util.ImageEditor;
import mun.util.NetworkConnection;

public class AddContributorViewController implements Initializable{

    @FXML
    public TextField txField;

    @FXML
    public PasswordField pwField, pwField1;
    
    @FXML
    private Label loginLabel, registerLabel;
	
    private final ImageEditor imageEditor;
    private final AnimationGenerator animationGenerator;
    private MainApp mainApp;  	
    private BorderPane rootLayout;
    public StackPane stackPane;
    public User previousUserObj;
    
    public AddContributorViewController() {
        imageEditor = new ImageEditor();
        animationGenerator = new AnimationGenerator();
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        this.rootLayout = mainApp.getBorderPane();
    }
    
    public void setStackPane(StackPane stackPane) {
    		this.stackPane = stackPane;
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

	    	if(pwField.getText().equals("") || pwField1.getText().equals("") || txField.getText().equals("")) {
	    		mainApp.showEmptyUsernamePassword();
	    	}else if(pwField.getText().equals(pwField1.getText())) {
	    		User userObj = new User();
	    		userObj.setUserName(txField.getText());
	    		userObj.setPassword(pwField.getText());
	    		if(!DBConnection.insertUserRole(userObj)) {
	    			mainApp.showUserExist();
	    		} else {
	    			mainApp.showSuccess();
	    		}	    		
	    	} else {
	    		mainApp.showConfirmPassword();
	    	}		
    }
    
    @FXML
    private void update() {

	    	if(pwField.getText().equals("") || pwField1.getText().equals("") || txField.getText().equals("")) {
	    		mainApp.showEmptyUsernamePassword();
	    	}else if(pwField.getText().equals(pwField1.getText())) {
	    		User userObj = new User();
	    		userObj.setUserName(txField.getText());
	    		userObj.setPassword(pwField.getText());
	    		if(!DBConnection.updateUserRole(previousUserObj,userObj)) {
	    			mainApp.showUserExist();
	    		} else {
	    			mainApp.showSuccess();
	    		}	    		
	    	} else {
	    		mainApp.showConfirmPassword();
	    	}		
    }
    
    
    @FXML
    private void reset() { 	
    		txField.setText(null);
    		pwField.setText(null);
    		pwField1.setText(null);
    }
    
    @FXML
    private void edit() {  	
	    	try {
	    		FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/manageContributor.fxml"));
	        AnchorPane contributor = (AnchorPane) loader.load();	        
	        ManageViewController controller = loader.getController();
	        controller.setMainApp(this.mainApp);	      
	        controller.setStackPane(stackPane);
	        stackPane.getChildren().remove(0, stackPane.getChildren().size());
	        stackPane.getChildren().add(contributor);
	        animationGenerator.applyFadeAnimationOn(stackPane, 500, 0f, 1.0f, null);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    	
    }
    
    private void animateWhenBadLogin() {
    	//mainApp.showNewContributorFeedback();	 
    }
    
}