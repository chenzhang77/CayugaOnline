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

import java.io.File;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import mun.MainApp;
import mun.util.*;

public class FileController implements Initializable{

    private MainApp mainApp;  	
    private BorderPane rootLayout;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        this.rootLayout = mainApp.getBorderPane();
    }
	@FXML
	public void importWordInitial() {
		
	}
	@FXML
	public void importWordMedial() {
		
	}
	@FXML
	public void importWordMedial2() {
		
	}
	@FXML
	public void importSuffix() {
		
	}
	@FXML
	public void importWithout() {
		
	}
	@FXML
	public void importDictionary() {
    	System.out.println("import");
    	FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "TXT files (cayuga-english.txt)", "cayuga-english.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());       
//        if (file != null) {
//            new ImportDictionary(file,dictionaryEnglishList,dictionaryCayugaList);
//            initialArrayList();
//            mainApp.getData().clear();
//        	inputText.clear();
//        }
	}
	
	@FXML
	public void exportWordInitial() {
		
	}
	@FXML
	public void exportWordMedial() {
		
	}
	@FXML
	public void importWordMedia2l() {
		
	}
	@FXML
	public void exportSuffix() {
		
	}
	@FXML
	public void exportWithout() {
		
	}
	@FXML
	public void exportDictionary() {
    	System.out.println("export");
        FileChooser fileChooser = new FileChooser();  
        
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*cayuga-english.txt)", "*cayuga-english.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("cayuga-english");
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());      
        if(file != null){
        	new ExportDictionary(file);    
        }  
	}
	
	@FXML
	public void overwriteWordInitial() {
		
	}
	@FXML
	public void overwriteWordMedial() {
		
	}
	@FXML
	public void overwriteWordMedial2() {
		
	}
	@FXML
	public void overwriteSuffix() {
		
	}
	@FXML
	public void overwriteWithout() {
		
	}
	@FXML
	public void overwriteDictionary() {
		
	}
	
}
