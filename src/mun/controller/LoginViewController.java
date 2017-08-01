package mun.controller;

import mun.MainApp;
import mun.util.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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
        return txField.getText().equals("steve") && pwField.getText().equals("steve");
    }

    private void setOnKeyPressed() {
        root.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER) && loginSuccessful())
                animateWhenLoginSuccess();
            else
                animateWhenBadLogin();
        });
    }

    private void animateWhenLoginSuccess() {
        try {
            Parent main = FXMLLoader.load(getClass().getResource("sample.fxml"));
            StackPane temp = new StackPane();
            temp.getChildren().add(new ImageView(new Image("buttons/Checkmark-50.png")));
            animationGenerator.applyFadeAnimationOn(root, 1000, 1.0f, 0f, event -> {
                temp.setOpacity(0);
                mainApp.getPrimaryStage().setScene(new Scene(temp, 800, 700));
                animationGenerator.applyFadeAnimationOn(temp, 1000, 0f, 1.0f, event1 -> {
                    animationGenerator.applyFadeAnimationOn(temp, 1000, 1.0f, 0f, event2 -> {
                    	mainApp.getPrimaryStage().setScene(new Scene(main, 800, 700));
                        animationGenerator.applyFadeAnimationOn(main, 1000, 0f, 1.0f, null);
                    });
                });
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
        	
            StackPane temp = new StackPane();
            temp.getChildren().add(new ImageView(new Image("buttons/Delete-50.png")));
            
            animationGenerator.applyFadeAnimationOn(login, 1000, 1.0f, 0f, event -> {
                temp.setOpacity(0f);
                
                //mainApp.getPrimaryStage().setScene(new Scene(temp, 600, 400));
               // rootLayout.setCenter(temp);
                animationGenerator.applyFadeAnimationOn(temp, 1000, 0f, 1.0f, event1 -> {                	
                	login.setOpacity(0f);
                    rootLayout.setCenter(login);
                    //mainApp.getPrimaryStage().setScene(new Scene(root, 800, 700));
                    animationGenerator.applyFadeAnimationOn(login, 1000, 0f, 1.0f, null);
            });
                    
        });
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
