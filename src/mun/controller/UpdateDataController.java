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

import javafx.event.ActionEvent;
/**
 * @author cz5670
 *
 */
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mun.model.Dictionary;
import mun.util.RemoveLine;
import mun.util.WriteToFile;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class UpdateDataController {

    @FXML
    private TextField firstField;
    @FXML
    private TextField lastField;  
    @FXML
    private TextArea commentsText;   
    @FXML
    private Button textButAdd_1;
    @FXML
    private Button textButAdd_2;
    @FXML
    private Button textButAdd_3;
    @FXML
    private Button textButAdd_4;
    
    private Stage dialogStage;
    private Dictionary dictionary;
    private boolean okClicked = false;
 
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {   	
    	firstField.setText(" ");   	
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
        lastField.setText(dictionary.getEnglishCol());
        firstField.setText(dictionary.getCayugaCol());
    }
    
    public void setDictionaryAndComments(Dictionary dictionary,String comments) {
        this.dictionary = dictionary;
        lastField.setText(dictionary.getEnglishCol());
        firstField.setText(dictionary.getCayugaCol());
        String prefix = comments.split(":")[0];    
        commentsText.setText(comments.substring(prefix.length()+1));
    }

    public boolean isOkClicked() {
        return okClicked;
    }
    
    public Dictionary oldDictionary() {
    	Dictionary dic = new Dictionary();
    	dic.setEnglishCol(dictionary.getEnglishCol());
    	dic.setCayugaCol(dictionary.getCayugaCol());
    	return dic;
    }
    
    @FXML
    private void handleOk() {   	
        if (isInputValid()) {      	
        	RemoveLine rl = new RemoveLine();
        	rl.removeLine(dictionary.getCayugaCol(),dictionary.getEnglishCol());
        	dictionary.setEnglishCol(lastField.getText());
        	dictionary.setCayugaCol(firstField.getText());     
        	WriteToFile wf = new WriteToFile();
            wf.addNewItem(firstField.getText(),lastField.getText());   
        	dialogStage.close();
        }
    }
    
    
    @FXML
    private void handleOkContributor() {   	
        if (isInputValid()) {   
        	System.out.println("handleOkContributor");
        	RemoveLine rl = new RemoveLine();
        	rl.removeLine(dictionary.getCayugaCol(),dictionary.getEnglishCol());
        	dictionary.setEnglishCol(lastField.getText());
        	dictionary.setCayugaCol(firstField.getText());     
        	WriteToFile wf = new WriteToFile();
            wf.addNewItemAndComments(firstField.getText(),lastField.getText(),"UPDATE: "+commentsText.getText());   
        	dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    private boolean isInputValid() {
        String errorMessage = "";
        if (firstField.getText() == null || firstField.getText().length() == 0) {
            errorMessage += "No valid english input!"; 
        }
        if (lastField.getText() == null || lastField.getText().length() == 0) {
            errorMessage += "No valid cayuga input!"; 
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
        	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Information Dialog");
        	alert.setHeaderText("Look, an Information Dialog");
        	alert.setContentText(errorMessage);
        	alert.showAndWait();
            return false;
        }
    }
    
    @FXML
    public void textButAction_1(ActionEvent ae){
    	firstField.requestFocus();
    	String currentString = firstField.getText();
    	if(currentString == null)currentString = "";
    	firstField.setText(currentString+"ˀ");
    	firstField.positionCaret(currentString.length()+1);
    }
    @FXML
    public void textButAction_2(ActionEvent ae){
    	firstField.requestFocus();
    	String currentString = firstField.getText();
    	if(currentString == null)currentString = "";
    	firstField.setText(currentString+"ǫ");
    	firstField.positionCaret(currentString.length()+1);
    }
    @FXML
    public void textButAction_3(ActionEvent ae){
    	firstField.requestFocus();
    	String currentString = firstField.getText();
    	if(currentString == null)currentString = "";
    	firstField.setText(currentString+":");
    	firstField.positionCaret(currentString.length()+1);
    }
    @FXML
    public void textButAction_4(ActionEvent ae){
    	firstField.requestFocus();
    	String currentString = firstField.getText();
    	if(currentString == null)currentString = "";
    	firstField.setText(currentString+"ę");
    	firstField.positionCaret(currentString.length()+1);
    }
    
    @FXML
    public void MouseMoveText() {
    	//mainScene.requestFocus();
    }
    
}
