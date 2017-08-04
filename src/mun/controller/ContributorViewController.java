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

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mun.util.AnimationGenerator;
import mun.util.ImageEditor;

public class ContributorViewController implements Initializable{

    @FXML
    private TextField txField;

    @FXML
    private PasswordField pwField, pwField1;
    
    @FXML
    private Label loginLabel, registerLabel;
	
    private final ImageEditor imageEditor;
    private final AnimationGenerator animationGenerator;
    
    public ContributorViewController() {
        imageEditor = new ImageEditor();
        animationGenerator = new AnimationGenerator();
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

    }
}
