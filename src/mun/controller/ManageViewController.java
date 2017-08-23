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
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import mun.MainApp;
import mun.model.Dictionary;
import mun.model.User;;

public class ManageViewController implements Initializable{

		@FXML
		private TableView<User> adminTable;
		@FXML
		private TableColumn<User, String> adminFirsteColumn;
		@FXML
		private TableView<User> contributorTable;
		@FXML
		private TableColumn<User, String> contributorFirsteColumn;
	    private MainApp mainApp;  	
	    private BorderPane rootLayout;
	    
		private ObservableList<User> adminnObserver = FXCollections.observableArrayList();
		private ObservableList<User> contributorObserver = FXCollections.observableArrayList(); 
		
		public ObservableList<User> getAdminnObserver() {
			return adminnObserver;
		}
		public ObservableList<User> getContributorObserver() {
			return contributorObserver;
		}
		
	    public void setMainApp(MainApp mainApp) {
	        this.mainApp = mainApp;
	        this.rootLayout = mainApp.getBorderPane();
	    }
	    
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			adminTable.setItems(adminnObserver);
			contributorTable.setItems(contributorObserver);
			adminFirsteColumn.setCellValueFactory(cellData -> cellData.getValue().userNameColProperty());
			contributorFirsteColumn.setCellValueFactory(cellData -> cellData.getValue().userNameColProperty());
			User userObj = new User();
    			userObj.setUserNameCol("admin");
    			adminnObserver.add(userObj);
    			contributorObserver.add(userObj);
    			contributorObserver.add(userObj);
    			contributorObserver.add(userObj);
    			contributorObserver.add(userObj);
    			contributorObserver.add(userObj);
		}

		@FXML
		private void delete () {
			
		}
		
		@FXML
		private void update () {
			
		}
}
