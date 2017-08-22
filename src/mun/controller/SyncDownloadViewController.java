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

public class SyncDownloadViewController implements Initializable{

	@FXML
	ProgressBar progressBar = new ProgressBar(0);
	@FXML
	ProgressIndicator progressIndicator = new ProgressIndicator(0);
	@FXML
	Label statusLabel = new Label();
	private DownloadTask downloadTask;
	
	private ImageView menuButton;
	
	
	public void setMenuButton(ImageView menuButton) {
		this.menuButton = menuButton;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// TODO Auto-generated method stub
		  if(menuButton == null) System.out.println("Empty");
          progressBar.setProgress(0);
          progressIndicator.setProgress(0);
          downloadTask = new DownloadTask();
          progressBar.progressProperty().unbind();
          progressBar.progressProperty().bind(downloadTask.progressProperty());
          progressIndicator.progressProperty().unbind();
          progressIndicator.progressProperty().bind(downloadTask.progressProperty());
          statusLabel.textProperty().unbind();
          statusLabel.textProperty().bind(downloadTask.messageProperty());
          downloadTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, //
                  new EventHandler<WorkerStateEvent>() {

                      @Override
                      public void handle(WorkerStateEvent t) {
                          //List<File> copied = copyTask.getValue();
                          statusLabel.textProperty().unbind();
                          statusLabel.setText("Download Successed");
                          menuButton.setDisable(false);
  
                      }
                  });
          // Start the Task.
          new Thread(downloadTask).start();
      }
}
