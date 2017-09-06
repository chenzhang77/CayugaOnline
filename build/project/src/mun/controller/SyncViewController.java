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
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import mun.util.*;

public class SyncViewController implements Initializable{

	@FXML
	ProgressBar progressBar = new ProgressBar(0);
	@FXML
	ProgressIndicator progressIndicator = new ProgressIndicator(0);
	@FXML
	Label statusLabel = new Label();
	private UploadTask uploadTask;
	
	private ImageView menuButton;
	
	
	public void setMenuButton(ImageView menuButton) {
		this.menuButton = menuButton;
		System.out.println("Coming");
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// TODO Auto-generated method stub
		  if(menuButton == null) System.out.println("Empty");
          progressBar.setProgress(0);
          progressIndicator.setProgress(0);
          uploadTask = new UploadTask();
          progressBar.progressProperty().unbind();
          progressBar.progressProperty().bind(uploadTask.progressProperty());
          progressIndicator.progressProperty().unbind();
          progressIndicator.progressProperty().bind(uploadTask.progressProperty());
          statusLabel.textProperty().unbind();
          statusLabel.textProperty().bind(uploadTask.messageProperty());
          uploadTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, //
                  new EventHandler<WorkerStateEvent>() {

                      @Override
                      public void handle(WorkerStateEvent t) {
                          //List<File> copied = copyTask.getValue();
                          statusLabel.textProperty().unbind();
                          statusLabel.setText("Upload Successed");
                          menuButton.setDisable(false);
  
                      }
                  });
          // Start the Task.
          new Thread(uploadTask).start();
      }
}
