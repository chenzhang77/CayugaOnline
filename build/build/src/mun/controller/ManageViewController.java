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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import mun.MainApp;
import mun.model.Dictionary;
import mun.model.User;
import mun.util.AnimationGenerator;
import mun.util.DBConnection;
import mun.util.RemoveLine;;

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
	    public StackPane stackPane;
	    private final AnimationGenerator animationGenerator;
		private ObservableList<User> adminnObserver = FXCollections.observableArrayList();
		private ObservableList<User> contributorObserver = FXCollections.observableArrayList(); 
		
		
		public ManageViewController() {
			animationGenerator = new AnimationGenerator();
		}	
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
	    
	    public void setStackPane(StackPane stackPane) {
    			this.stackPane = stackPane;
	    }
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			adminTable.setItems(adminnObserver);
			contributorTable.setItems(contributorObserver);
			adminFirsteColumn.setCellValueFactory(cellData -> cellData.getValue().userNameColProperty());
			contributorFirsteColumn.setCellValueFactory(cellData -> cellData.getValue().userNameColProperty());
			
			
			List<User> userRoleList = DBConnection.userRoleList();
			Iterator iterator = userRoleList.iterator();
			while(iterator.hasNext()) {
				
				User userObj = (User) iterator.next();
				if(userObj.getRole() == 1) adminnObserver.add(userObj);
				else if (userObj.getRole() == 2) contributorObserver.add(userObj);
				else {
					System.out.println("error");
				}
			}
			
			adminTable.setRowFactory( tv -> {
	          TableRow<User> row = new TableRow<>();
	          row.setOnMouseClicked(event -> {
	        	  if(event.getClickCount() == 1 && (! row.isEmpty()) ) {    		  	  
	        	  }else if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
	        		  User userObj = adminTable.getSelectionModel().getSelectedItem(); 
	        		  update (userObj);	  
	              }       
	          });
	          return row ;
	          });
			
			contributorTable.setRowFactory( tv -> {
		          TableRow<User> row = new TableRow<>();
		          row.setOnMouseClicked(event -> {
		        	  if(event.getClickCount() == 1 && (! row.isEmpty()) ) {    		  	  
		        	  }else if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		        		  User userObj = contributorTable.getSelectionModel().getSelectedItem();
		        		   update (userObj);  
		              }       
		          });
		          return row ;
		          });

		}

		@FXML
		private void delete () {
			
			
		   int selectedIndex = contributorTable.getSelectionModel().getSelectedIndex();
	    	   User userObj = contributorTable.getSelectionModel().getSelectedItem(); 

	    	    if (selectedIndex >= 0) {
		    	    	Alert alert = new Alert(AlertType.CONFIRMATION);
		    	    	alert.setTitle("Confirmation Dialog");
		    	    	alert.setHeaderText("Delete");
		    	    	alert.setContentText("Are you sure to delete this row from the database?");
		    	    	Optional<ButtonType> result = alert.showAndWait();
		    	    	if (result.get() == ButtonType.OK){	
	    			    if (userObj !=null) {
	    			    		System.out.println("not null");
	    			    		if(DBConnection.deleteUserRole(userObj)) contributorTable.getItems().remove(selectedIndex); 
	    			    }
		    	    	}  	
	    	    	} else {
		    	    	Alert alert = new Alert(AlertType.INFORMATION);
		    	    	alert.setTitle("Information Dialog");
		    	    	alert.setHeaderText(null);
		    	    	alert.setContentText("Please select one row !");
		    	    	alert.showAndWait();
	    	    }
		}
			
		
		@FXML
		private void update (User userObj) {
	    	try {
	    		FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/UpdateUserAccount.fxml"));
	        AnchorPane contributor = (AnchorPane) loader.load();	        
	        AddContributorViewController controller = loader.getController();
	        controller.setMainApp(this.mainApp);	    
	        controller.txField.setText(userObj.getUserName()); 
	        controller.pwField.setText(userObj.getPassword());
	        controller.pwField1.setText(userObj.getPassword());
	        controller.previousUserObj = userObj;
	        stackPane.getChildren().remove(0, stackPane.getChildren().size());
	        stackPane.getChildren().add(contributor);
	        animationGenerator.applyFadeAnimationOn(stackPane, 500, 0f, 1.0f, null);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		}
}
