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

import mun.model.Dictionary;
import mun.MainApp;
import mun.util.Constant;
import mun.util.ExportDictionary;
import mun.util.ImportDictionary;
import mun.util.RemoveLine;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Optional;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.AutoCompletionBinding.AutoCompletionEvent;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Callback;

public class DictionaryController {

	
	  @FXML
	  private AnchorPane mainScene;	
	  @FXML
	  private ImageView imagebackgroup;
	  @FXML
	  private TableView<Dictionary> dictionaryTable;
	  @FXML
	  private TableColumn<Dictionary, String> firsteColumn;
	  @FXML
	  private TableColumn<Dictionary, String> lastColumn;  
	  @FXML
	  private RadioButton englishBut;
	  @FXML
	  private RadioButton cayugaBut; 
//	  @FXML
//	  private RadioButton related_1;
//	  @FXML
//	  private RadioButton related_2; 
//	  @FXML
//	  private RadioButton related_3;   
//	  @FXML
//	  private Button relatedWordBut;
	  @FXML
	  private Button searchBut;
	  @FXML
	  private Pane topPane;	  
	  @FXML
	  private Label firstLabel;
	  @FXML
	  private Label lastLabel; 
	  @FXML
	  private Button textBut_1;
	  @FXML
	  private Button textBut_2;
	  @FXML
	  private Button textBut_3;
	  @FXML
	  private Button textBut_4;
	  @FXML
	  private Label similarityButton;
	  private boolean similaritySelected;
	  
	  @FXML
	  private Button relatedImg;  
	  @FXML
	  private HBox relatedPane;
	  @FXML
	  private Label relatedLabel;
	  @FXML
	  private CheckBox Prefix_checkBox_1;
	  @FXML
	  private CheckBox Prefix_checkBox_2;
	  @FXML
	  private CheckBox Prefix_checkBox_3;
	  @FXML
	  private CheckBox Suffix_checkBox;
	  @FXML
	  private Label Prefix_label;
	  @FXML
	  private Label Suffix_label;
	  
	 
	  
//	  @FXML
//	  private Button showButton;  
//	  @FXML
//	  private Button deleteButton;  	  
	  @FXML
	  private TextField inputText;
	  
	  private MainApp mainApp;  
	  private ArrayList<String> dictionaryEnglishList = new ArrayList<>();
	  private ArrayList<String> dictionaryCayugaList = new ArrayList<>();	 	  
	  private Hashtable<String, String> word_initial = new Hashtable<String, String>();
	  private Hashtable<String, String> word_medial_1 = new Hashtable<String, String>();
	  private Hashtable<String, String> word_medial_2 = new Hashtable<String, String>();
	  private Hashtable<String, String> word_suffix = new Hashtable<String, String>();
	  private Hashtable<String, String> word_without_affixes = new Hashtable<String, String>(); 
	  private String firstItem = "";
	  private String inputTextString ="";
	  
	  private ArrayList<String> aa = new ArrayList<>();  
	  public DictionaryController() {
		  
		  
	  }

	  /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	  */
    @SuppressWarnings("unchecked")
	@FXML
    private void initialize() {
    	
//    	dictionaryTable.setVisible(true);
//    	showButton.setVisible(false);
//    	deleteButton.setVisible(false);   	
    	initialArrayList();
    	initialButtons();
    	initialAffixesFiles();
    	//testFiles();
    	
    	firsteColumn.setCellValueFactory(cellData -> cellData.getValue().englishColProperty());
    	firsteColumn.setCellFactory(new Callback<TableColumn<Dictionary, String>, TableCell<Dictionary, String>>() {
			@Override
		     public TableCell<Dictionary, String> call(
		                TableColumn<Dictionary, String> param) {
		            TableCell<Dictionary, String> cell = new TableCell<>();
		            Text text = new Text("term of addess for a maternal grandmotherterm of addess for a maternal grandmother");
		            cell.setGraphic(text);
		            text.wrappingWidthProperty().bind(cell.widthProperty());
		            text.textProperty().bind(cell.itemProperty());
		            return cell ;
		        }
    	});

    	lastColumn.setCellValueFactory(cellData -> cellData.getValue().CayugaColProperty());
    	lastColumn.setCellFactory(new Callback<TableColumn<Dictionary, String>, TableCell<Dictionary, String>>() {
			@Override
		     public TableCell<Dictionary, String> call(
		                TableColumn<Dictionary, String> param) {
		            TableCell<Dictionary, String> cell = new TableCell<>();
		            Text text = new Text("term of addess for a maternal grandmotherterm of addess for a maternal grandmother");
		            cell.setGraphic(text);
		            text.wrappingWidthProperty().bind(cell.widthProperty());
		            text.textProperty().bind(cell.itemProperty());
		            return cell ;
		        }
    	 });
     		
    	AutoCompletionTextFieldBinding<String> autoTF = new AutoCompletionTextFieldBinding(inputText, new Callback<AutoCompletionBinding.ISuggestionRequest, ArrayList<String>>() {
    	    @Override
    	    public ArrayList<String> call(AutoCompletionBinding.ISuggestionRequest param) {
    	       	relatedImg.setVisible(false);
    	   		relatedLabel.setVisible(false);
    	       	relatedPane.setVisible(false);
    	    	
    	    	aa = new ArrayList<>();
    	    	String inputString = param.getUserText().toLowerCase();  	    	
    	    	inputTextString = inputString;
    	    	inputString = inputString.replace("*", ".*");

    	    	if(inputString.length() != 0 && inputString != null) {    	
    	    		if(englishBut.selectedProperty().getValue())
	        	    	for(String item :dictionaryEnglishList) {
	        	    		if(item.toLowerCase().matches("^"+inputString+".*$")){
	        	    			aa.add(item);
	        	    		}	        			
	        	    	}
    	    		else
            	    	for(String item :dictionaryCayugaList) {
            	    		if(item.toLowerCase().matches("^"+inputString+".*$")){
            	    			aa.add(item);
            	    		}          	    			
            	    	}
    	    		Collections.sort(aa);
    	    		return aa;	
    	    	} else {
    	    		return aa;
    	    	}   	
    	    }
    	});

    	autoTF.setMinWidth(457);
    	
//    	mainScene.setOnKeyPressed(event -> {
//            if(event.getCode().equals(KeyCode.ENTER))
//            	System.out.println("dddd");
//            });
//    	mainScene.setOnMouseClicked(event ->{
//    		System.out.println("setOnMouseClicked");
//    	});
//    	
//    	
//    	autoTF.addEventHandler(Event.ANY, new EventHandler<Event>(){
//
//			@Override
//			public void handle(Event event) {
//				// TODO Auto-generated method stub
//				if(event.getEventType() == MouseEvent.MOUSE_CLICKED){
//					System.out.println("work well!!!");
//				}
//				System.out.println(event.getEventType());
//				System.out.println("work outside!");
//				//if(event.getClass() == Event.ANY)
//				
//				
//			}
//    		
//    	});
//    	autoTF.setOnAutoCompleted(new EventHandler(){
//			@Override
//			public void handle(Event event) {
//				// TODO Auto-generated method stub
////				AutoCompletionEvent event2 = (AutoCompletionEvent)event;
//			
////				if(event2.getCode().equals(KeyCode.ENTER)){
////					System.out.println("work !!!");
////				}
////				
//				firstItem = aa.get(0);
//				if(inputText.getText().equalsIgnoreCase(firstItem)){
//					inputText.setText(inputTextString);
//					firstItem = "";
//					inputTextString = "";
//					onEnter(null);
//				}
//			}		
//    	});

      dictionaryTable.setRowFactory( tv -> {
      TableRow<Dictionary> row = new TableRow<>();
      row.setOnMouseClicked(event -> {
    	  if(event.getClickCount() == 1 && (! row.isEmpty()) ) {    		  	  
    	  }else if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
    		  showAction(null);	  
          }       
      });
      return row ;
      });
    	
    }
     
//    private void setOnKeyPressed() {
//
//    }
    
    private void initialButtons() {
    	
    	final ToggleGroup group = new ToggleGroup();
    	englishBut.setToggleGroup(group);
    	englishBut.setSelected(true);
    	cayugaBut.setToggleGroup(group);
    	textBut_1.setVisible(false);
    	textBut_2.setVisible(false);
    	textBut_3.setVisible(false);
    	textBut_4.setVisible(false); 	
    	relatedImg.setVisible(false);
    	relatedPane.setVisible(false);
    	relatedLabel.setVisible(false);
    		
    	similarityButton.setVisible(false);
    	similaritySelected = false;
    	
    	group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){    		
    	    public void changed(ObservableValue<? extends Toggle> ov,
    	        Toggle old_toggle, Toggle new_toggle) {    	    	
    	            if (group.getSelectedToggle() != null) {
    	            	if(englishBut.selectedProperty().getValue()) {
    	                	textBut_1.setVisible(false);
    	                	textBut_2.setVisible(false);
    	                	textBut_3.setVisible(false);
    	                	textBut_4.setVisible(false);   	                	
    	                	relatedImg.setVisible(false);
    	                	relatedPane.setVisible(false);
    	                	relatedLabel.setVisible(false);
    	                	mainApp.getData().clear();
    	                	inputText.clear();   	
    	                	
    	                	
    	                	similarityButton.setVisible(false);
    	                	similaritySelected = false;
    	            	} else {
    	            		inputText.clear();	  
    	                	textBut_1.setVisible(true);
    	                	textBut_2.setVisible(true);
    	                	textBut_3.setVisible(true);
    	                	textBut_4.setVisible(true);
    	                	mainApp.getData().clear();
    	                	
    	                	
    	                	similarityButton.setVisible(false);
    	                	similaritySelected = false;
    	            	}
    	            }                
    	        }
    	});	
    }
    
    private void initialArrayList() {
    	
    	String file = Constant.dictionaryPath;
		FileInputStream fstream;
		dictionaryCayugaList.clear();
		dictionaryEnglishList.clear();
		try {
			fstream = new FileInputStream(file);
			Reader chars = new InputStreamReader(fstream, StandardCharsets.UTF_16);
			BufferedReader br = new BufferedReader(chars);
			String strLine;			
			//Read File Line By Line
			while ((strLine = br.readLine()) != null && !strLine.trim().isEmpty())   {
				
				String[] outstring = strLine.split("     ");
				dictionaryCayugaList.add(outstring[0].replaceAll("\\p{C}", "").trim());
				dictionaryEnglishList.add(outstring[1]);
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
    
    private void initialAffixesFiles() {
    	initialWord_initialFiles();
    	initialWord_medial_1Files();
    	initialWord_medial_2Files();
    	initialWord_suffixFiles();
    	initialWord_without_affixesFiles();
    }
    
    private void initialWord_initialFiles() {
    	
    	String file = Constant.word_initial_Path;
		FileInputStream fstream;
		word_initial.clear();
		try {
			fstream = new FileInputStream(file);
			Reader chars = new InputStreamReader(fstream, StandardCharsets.UTF_16);
			BufferedReader br = new BufferedReader(chars);
			String strLine;			
			//Read File Line By Line
			while ((strLine = br.readLine()) != null && !strLine.trim().isEmpty())   {
				word_initial.put(strLine.replaceAll("\\p{C}", "").trim(),"");
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void initialWord_medial_1Files() {
    	
    	String file = Constant.word_medial_1_Path;
		FileInputStream fstream;
		word_medial_1.clear();
		try {
			fstream = new FileInputStream(file);
			Reader chars = new InputStreamReader(fstream, StandardCharsets.UTF_16);
			BufferedReader br = new BufferedReader(chars);
			String strLine;			
			//Read File Line By Line
			while ((strLine = br.readLine()) != null && !strLine.trim().isEmpty())   {
				word_medial_1.put(strLine.replaceAll("\\p{C}", "").trim(),"");
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void initialWord_medial_2Files() {
    	
    	String file = Constant.word_medial_2_Path;
		FileInputStream fstream;
		word_medial_2.clear();
		try {
			fstream = new FileInputStream(file);
			Reader chars = new InputStreamReader(fstream, StandardCharsets.UTF_16);
			BufferedReader br = new BufferedReader(chars);
			String strLine;			
			//Read File Line By Line
			while ((strLine = br.readLine()) != null && !strLine.trim().isEmpty())   {
				word_medial_2.put(strLine.replaceAll("\\p{C}", "").trim(),"");
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void initialWord_suffixFiles() {
    	
    	String file = Constant.word_suffix_Path;
		FileInputStream fstream;
		word_suffix.clear();
		try {
			fstream = new FileInputStream(file);
			Reader chars = new InputStreamReader(fstream, StandardCharsets.UTF_16);
			BufferedReader br = new BufferedReader(chars);
			String strLine;			
			//Read File Line By Line
			while ((strLine = br.readLine()) != null && !strLine.trim().isEmpty())   {
				word_suffix.put(strLine.replaceAll("\\p{C}", "").trim(),"");
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void initialWord_without_affixesFiles() {
    	
    	String file = Constant.word_without_affixes_Path;
		FileInputStream fstream;
		word_without_affixes.clear();
		try {
			fstream = new FileInputStream(file);
			Reader chars = new InputStreamReader(fstream, StandardCharsets.UTF_16);
			BufferedReader br = new BufferedReader(chars);
			String strLine;			
			//Read File Line By Line
			while ((strLine = br.readLine()) != null && !strLine.trim().isEmpty())   {
				String[] outstring = strLine.split("     ");
				
				String matches = outstring[0].replaceAll("\\p{C}", "").trim();				
				StringBuilder sb = new StringBuilder(matches);
				for(int a=0; a<sb.length();a++) {					
					if(sb.charAt(a)=='ˀ'||sb.charAt(a)==':') sb.deleteCharAt(a);					
				}
				matches = sb.toString();				
				word_without_affixes.put(matches,"");
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private boolean checkAffixes(String word_residue) {
    	
		String matches = word_residue;				
		StringBuilder sb = new StringBuilder(matches);
		for(int a=0; a<sb.length();a++) {					
			if(sb.charAt(a)=='ˀ'||sb.charAt(a)==':') sb.deleteCharAt(a);					
		}
		matches = sb.toString();
    	
    	if(word_without_affixes.containsKey(matches)) {		
    		return false;
    	}
    	return true;
    }
    
    private String searchLongestPrefixString(String inputString, Hashtable<String, String> hashTable) {
    	
    	System.out.println("inputTextString = "+inputString);
    	String longestString = "";
    	int longestIndex = 0;
    	
    	for (int i=0; i< inputString.length(); i++) {   		
    		char current = inputString.charAt(i);    		
    		if(current == 'ˀ'|| current == ':') {
    			if(longestIndex == i) longestIndex = i+1;
    			continue;
    		}
    		longestString = longestString + inputString.charAt(i);
    		if(hashTable.containsKey(longestString.toLowerCase())) longestIndex = i+1;   			
    	}	
    	return inputString.substring(0,longestIndex);
    }
    
    private String searchLongestSuffixString(String inputString, Hashtable<String, String> hashTable) {
    	
    	System.out.println("suffix inputTextString = "+inputString);
    	String longestString = ""; 
    	int longestIndex = inputString.length();
    	for (int i=inputString.length()-1; i >= 0; i--) {  		
    		char current = inputString.charAt(i);    		
    		if(current == 'ˀ'|| current == ':') {
    			if(longestIndex == i+1)  longestIndex = i;
    			continue;
    		}
    		longestString = inputString.charAt(i) + longestString;
    		if(hashTable.containsKey(longestString.toLowerCase())) {
    			longestIndex = i;
    		}
    					
    	}
    	String returnString = inputString.substring(longestIndex);
    	int flag = 0;
    	for(int i=0; i<returnString.length(); i++) {
    		char current = returnString.charAt(i);    		
    		if(current == 'ˀ'|| current == ':') {
    			flag++;
    			continue;
    		}else break;
    	}
    	
    	return returnString.substring(flag);
    }
    
    @FXML
    public void relatedFunction() {
    	
    	
    	Prefix_label.setText("Prefix:");
    	similaritySelected = false;
    	Suffix_checkBox.setVisible(true);
    	similarityButton.setVisible(true);
    	mainApp.getData().clear();	
    	
    	relatedPane.setVisible(true);
    	Prefix_label.setVisible(true);
    	Suffix_label.setVisible(true);
    	Prefix_checkBox_1.setVisible(true);
    	Prefix_checkBox_1.setSelected(true);   	
    	String word_initial_string = searchLongestPrefixString(inputText.getText(),word_initial);
    	if(!word_initial_string.equals("")){

    		Prefix_checkBox_1.setDisable(false);
    		Prefix_checkBox_1.setSelected(false);
    		Prefix_checkBox_1.setText(word_initial_string);
    	}
    	else {
    		Prefix_checkBox_1.setDisable(true);
    		Prefix_checkBox_1.setSelected(false);
    		Prefix_checkBox_1.setText("No Prefix");
    	}
    			
    	Prefix_checkBox_2.setVisible(false);
    	Prefix_checkBox_3.setVisible(false);
    	
    	String word_suffix_string = searchLongestSuffixString(inputText.getText(),word_suffix);
    	if(!word_suffix_string.equals("")){
    		Suffix_checkBox.setSelected(false);
    		Suffix_checkBox.setDisable(false);
    		Suffix_checkBox.setText(word_suffix_string);
    	}
    	else {
    		Suffix_checkBox.setText("No Suffix");
    		Suffix_checkBox.setDisable(true);
    	}
    		
//  	  private Separator sep_1, sep_2, sep_3, sep_4;
//  	  @FXML
//  	  private Label relatedLabel;
//  	  @FXML
//  	  private CheckBox Prefix_checkBox_1;
//  	  @FXML
//  	  private CheckBox Prefix_checkBox_2;
//  	  @FXML
//  	  private CheckBox Prefix_checkBox_3;
//  	  @FXML
//  	  private CheckBox Suffix_checkBox;
//  	  @FXML
//  	  private Label Prefix_label;
//  	  @FXML
//  	  private Label Suffix_label;  	
    }
    
    @FXML
    public void similarLetterFunction() {
    	
    	System.out.println("similarLetterFunction");
    	similaritySelected = true;
    	mainApp.getData().clear();	
    	
    	relatedPane.setVisible(true);
    	Prefix_label.setVisible(true);
    	Prefix_label.setText("Similarity:");
    	Suffix_label.setVisible(false);
    	Suffix_checkBox.setVisible(false);
    	
    	Prefix_checkBox_1.setSelected(false);
    	Prefix_checkBox_2.setSelected(false);
    	Prefix_checkBox_3.setSelected(false);
    	similarityButton.setVisible(false);
    	
    	
    	String currentInput = inputText.getText();
       	if(currentInput.length() < 3) onEnter(null);
       	int total = 0;
    	int length = currentInput.length();
       	int subStringLength = length-1;
       	ArrayList<String> newList = new ArrayList<>(dictionaryCayugaList);
       	     	
       	for(int i =subStringLength; i>=2; i--) {
       		if(total >=3) break;      		
       		for(int j=0; j+i<=length; j++) {    			
       			if(total >=3) break;    			
       			String sub  = currentInput.substring(length-j-i,length-j);		
       			for(int d=0; d<newList.size();d++) {
       				
	    			if(newList.get(d).toString().matches(".*"+sub+".*") && !newList.get(d).toString().matches(".*"+currentInput+".*")) {    				
	    				if(total == 0) {
	    					newList.remove(d);
	    					Prefix_checkBox_1.setText(sub);
	    					Prefix_checkBox_1.setVisible(true);
	    					
	    					
	    					Prefix_checkBox_2.setVisible(false);
	    					Prefix_checkBox_3.setVisible(false);
	    					
	    					total++;
	    					break;
	    				} else if(total == 1) {
	    					newList.remove(d);
	    					Prefix_checkBox_2.setText(sub);
	    					Prefix_checkBox_2.setVisible(true);
	    					
	    					
	    					Prefix_checkBox_3.setVisible(false);
	    					
	    					total++;
	    					break;
	    				} else if(total == 2) {
	    					newList.remove(d);
	    					Prefix_checkBox_3.setText(sub);
	    					Prefix_checkBox_3.setVisible(true);
	    					total++;
	    					break;
	    				} else {break;}		
	    			}
       			}		
       		}       		
       	}
    	
    	
    	
    }
    
    @FXML
    public void prefixCheckBox_1() {
    	
    	if(similaritySelected) {
    		similaritySearch();
    	}else{   	
	       	String currentInput = inputText.getText();	
	       	if(currentInput.length() == 0) return;
	       	int suffix_length = Suffix_checkBox.isSelected() && !Suffix_checkBox.isDisable() ? Suffix_checkBox.getText().length():0;
	    	if(!Prefix_checkBox_1.isSelected()) {
	    		Prefix_checkBox_2.setVisible(false);
	        	Prefix_checkBox_3.setVisible(false);       	
	        	if(suffix_length == 0)SearchByString(currentInput.substring(0, currentInput.length()).trim());
	        	else SearchByString(currentInput.substring(0, currentInput.length()-suffix_length).trim()+"*");
	    	} else {
	    		Prefix_checkBox_2.setVisible(true);
	    		Prefix_checkBox_3.setVisible(false);    		
           	int length_initial = Prefix_checkBox_1.getText().length();   
           	if(suffix_length == 0) SearchByString("*"+currentInput.substring(length_initial, currentInput.length()).trim());  
           	else SearchByString("*"+currentInput.substring(length_initial, currentInput.length() - suffix_length).trim()+"*");           	
	        	String word_medial_string = searchLongestPrefixString(currentInput.substring(length_initial),word_medial_1);      	
	        	if(!word_medial_string.equals("")) {
	        		Prefix_checkBox_2.setSelected(false);
	        		Prefix_checkBox_2.setText(word_medial_string);       		
	        	}        		
	        	else
	        		Prefix_checkBox_2.setVisible(false);   		
	    	}
    	}
    }
    
    @FXML
    public void prefixCheckBox_2() {
    	if(similaritySelected) {
    		similaritySearch();
    	}else{ 
	       	String currentInput = inputText.getText();	
	       	if(currentInput.length() == 0) return;
	       	int length_initial = Prefix_checkBox_1.getText().length();
	       	int suffix_length = Suffix_checkBox.isSelected() && !Suffix_checkBox.isDisable() ? Suffix_checkBox.getText().length():0;
	    	if(!Prefix_checkBox_2.isSelected()) {
	        	Prefix_checkBox_3.setVisible(false);       	
	        	if(suffix_length == 0) SearchByString("*"+currentInput.substring(length_initial).trim()); 
	        	else SearchByString("*"+currentInput.substring(length_initial,currentInput.length() - suffix_length).trim()+"*"); 
	    	} else {    		
	           	int length_medial = Prefix_checkBox_2.getText().length();   		        	
	           	if(suffix_length == 0) SearchByString("*"+currentInput.substring(length_initial+length_medial).trim());
	           	else SearchByString("*"+currentInput.substring(length_initial+length_medial,currentInput.length() - suffix_length).trim()+"*");        	        	
	        	String word_medial2_string = searchLongestPrefixString(currentInput.substring(length_initial+length_medial),word_medial_2);
	        	if(!word_medial2_string.equals("")) {
	        		Prefix_checkBox_3.setSelected(false);
	        		Prefix_checkBox_3.setVisible(true);
	        		Prefix_checkBox_3.setText(word_medial2_string);       		
	        	}  		
	        	else
	        		Prefix_checkBox_3.setVisible(false);		
	    	}
    	}
    }
    
    @FXML
    public void prefixCheckBox_3() {
    	if(similaritySelected) {
    		similaritySearch();
    	}else{ 
    		String currentInput = inputText.getText();	
           	if(currentInput.length() == 0) return;
           	int length_initial = Prefix_checkBox_1.getText().length();
           	int length_medial1 = Prefix_checkBox_2.getText().length();
           	int suffix_length = Suffix_checkBox.isSelected() && !Suffix_checkBox.isDisable() ? Suffix_checkBox.getText().length() : 0;
	    	if(Prefix_checkBox_3.isSelected()) {     
	    		if(suffix_length == 0) SearchByString("*"+currentInput.substring(length_initial+length_medial1).trim());
	    		else	SearchByString("*"+currentInput.substring(length_initial+length_medial1,currentInput.length() - suffix_length).trim() +"*");        	
	    	} else {   		
	           	int length_medial2 = Prefix_checkBox_3.getText().length();
	           	if(suffix_length == 0) SearchByString("*"+currentInput.substring(length_initial+length_medial1+length_medial2).trim());
	           	else SearchByString("*"+currentInput.substring(length_initial+length_medial1+length_medial2,currentInput.length() - suffix_length).trim() +"*");
	    	}
    	}		
    }
    
    private void similaritySearch() {
    	mainApp.getData().clear();
    	ArrayList<String> dictionaryCayugaListRemaining_1 = new ArrayList<>();
    	ArrayList<String> dictionaryEnglishListRemaining_1 = new ArrayList<>();	
    	ArrayList<String> dictionaryCayugaListRemaining_2 = new ArrayList<>();
    	ArrayList<String> dictionaryEnglishListRemaining_2 = new ArrayList<>();	
    	System.out.println("similaritySearch");
    	if(Prefix_checkBox_1.isSelected()) {
    		String checkBox_1 = Prefix_checkBox_1.getText();
			for(int i=0; i<dictionaryCayugaList.size();i++) {				
				String words = dictionaryCayugaList.get(i).toString().toLowerCase();
				StringBuilder words_sb = new StringBuilder(words);
				for(int a=0; a<words_sb.length();a++) {					
					if(words_sb.charAt(a)=='ˀ'||words_sb.charAt(a)==':') words_sb.deleteCharAt(a);					
				}
				words = words_sb.toString();
				words = " "+words+" ";				
				String matches = checkBox_1;				
				StringBuilder sb = new StringBuilder(matches);
				for(int a=0; a<sb.length();a++) {					
					if(sb.charAt(a)=='ˀ'||sb.charAt(a)==':') sb.deleteCharAt(a);					
				}
				matches = sb.toString();
				//matches = createRegularExpression(matches);			
				if(words.toString().matches(".*"+matches+".*")) {
					Dictionary tempData = new Dictionary();
					tempData.setEnglishCol(dictionaryEnglishList.get(i));
					tempData.setCayugaCol(dictionaryCayugaList.get(i));
					mainApp.getData().add(tempData);
				} else {
					dictionaryCayugaListRemaining_1.add(dictionaryCayugaList.get(i));
					dictionaryEnglishListRemaining_1.add(dictionaryEnglishList.get(i));
				}
			}
    	} else {
    		dictionaryCayugaListRemaining_1 = dictionaryCayugaList;
    		dictionaryEnglishListRemaining_1 = dictionaryEnglishList;
    	}
    	
    	if(Prefix_checkBox_2.isSelected()) {
    		String checkBox_2 = Prefix_checkBox_2.getText();
			for(int i=0; i<dictionaryCayugaListRemaining_1.size();i++) {				
				String words = dictionaryCayugaListRemaining_1.get(i).toString().toLowerCase();
				StringBuilder words_sb = new StringBuilder(words);
				for(int a=0; a<words_sb.length();a++) {					
					if(words_sb.charAt(a)=='ˀ'||words_sb.charAt(a)==':') words_sb.deleteCharAt(a);					
				}
				words = words_sb.toString();
				words = " "+words+" ";				
				String matches = checkBox_2;				
				StringBuilder sb = new StringBuilder(matches);
				for(int a=0; a<sb.length();a++) {					
					if(sb.charAt(a)=='ˀ'||sb.charAt(a)==':') sb.deleteCharAt(a);					
				}
				matches = sb.toString();
				//matches = createRegularExpression(matches);			
				if(words.toString().matches(".*"+matches+".*")) {
					Dictionary tempData = new Dictionary();
					tempData.setEnglishCol(dictionaryEnglishListRemaining_1.get(i));
					tempData.setCayugaCol(dictionaryCayugaListRemaining_1.get(i));
					mainApp.getData().add(tempData);
				}else {
					dictionaryCayugaListRemaining_2.add(dictionaryCayugaListRemaining_1.get(i));
					dictionaryEnglishListRemaining_2.add(dictionaryEnglishListRemaining_1.get(i));
				}
			}
    	} else {
    		dictionaryCayugaListRemaining_2 = dictionaryCayugaListRemaining_1;
    		dictionaryEnglishListRemaining_2 = dictionaryEnglishListRemaining_1;
    	}
    	
    	if(Prefix_checkBox_3.isSelected()) {
    		String checkBox_3 = Prefix_checkBox_3.getText();
			for(int i=0; i<dictionaryCayugaListRemaining_2.size();i++) {				
				String words = dictionaryCayugaListRemaining_2.get(i).toString().toLowerCase();
				StringBuilder words_sb = new StringBuilder(words);
				for(int a=0; a<words_sb.length();a++) {					
					if(words_sb.charAt(a)=='ˀ'||words_sb.charAt(a)==':') words_sb.deleteCharAt(a);					
				}
				words = words_sb.toString();
				words = " "+words+" ";				
				String matches = checkBox_3;				
				StringBuilder sb = new StringBuilder(matches);
				for(int a=0; a<sb.length();a++) {					
					if(sb.charAt(a)=='ˀ'||sb.charAt(a)==':') sb.deleteCharAt(a);					
				}
				matches = sb.toString();
				//matches = createRegularExpression(matches);			
				if(words.toString().matches(".*"+matches+".*")) {
					Dictionary tempData = new Dictionary();
					tempData.setEnglishCol(dictionaryEnglishListRemaining_2.get(i));
					tempData.setCayugaCol(dictionaryCayugaListRemaining_2.get(i));
					mainApp.getData().add(tempData);
				}
			}
    	}
    	

    }
    
    
    @FXML
    public void suffixCheckBox() {
    	String currentInput = inputText.getText();
    	if(currentInput.length() == 0) return;	
	 	int length_initial = Prefix_checkBox_1.isSelected()?0:Prefix_checkBox_1.getText().length();
       	int length_medial1 = Prefix_checkBox_2.isSelected() || !Prefix_checkBox_2.isVisible()?0:Prefix_checkBox_2.getText().length();
       	int length_medial2 = Prefix_checkBox_3.isSelected() || !Prefix_checkBox_3.isVisible()?0:Prefix_checkBox_3.getText().length();
       	String word_residue = currentInput.substring(length_initial+length_medial1+length_medial2).trim();
       	System.out.println("word_residue = " + word_residue);
    	if(Suffix_checkBox.isSelected()) {   		
           	SearchByString("*"+word_residue);
//    		if(checkAffixes(word_residue)){
//    			if(Prefix_checkBox_3.isSelected() && !Prefix_checkBox_2.isSelected() && !Prefix_checkBox_1.isSelected()) Prefix_checkBox_3.setVisible(true);
//    			else if (Prefix_checkBox_2.isSelected() && !Prefix_checkBox_1.isSelected()) Prefix_checkBox_2.setVisible(true);
//    			else if (Prefix_checkBox_1.isSelected()) {
//    				String word_initial_string = searchLongestPrefixString(inputText.getText(),word_initial);
//    				Prefix_checkBox_1.setText(word_initial_string);
//    			}
//    		}
    	} else {
    		word_residue = word_residue.substring(0, word_residue.length()-Suffix_checkBox.getText().length()).trim();
    		SearchByString("*"+word_residue+"*"); 
//    		if(!checkAffixes(word_residue)){
//    			if(Prefix_checkBox_3.isSelected()) Prefix_checkBox_3.setVisible(false);
//    			else if (Prefix_checkBox_2.isSelected()) Prefix_checkBox_2.setVisible(false);
//    			else if (Prefix_checkBox_1.isSelected()) Prefix_checkBox_1.setText("No Prefix");
//    		}
//    		
    	}
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        dictionaryTable.setItems(mainApp.getData());
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new dictionary.
     */
    @FXML
    private void handleNewData() {
    	Dictionary tempData = new Dictionary();
        boolean okClicked = mainApp.showAddItemDialog(tempData);
        if (okClicked) {
        	dictionaryEnglishList.add(tempData.getEnglishCol());
        	dictionaryCayugaList.add(tempData.getCayugaCol());
            mainApp.getData().add(tempData);
        }     
    }
    
    @FXML
    public void onEnter(ActionEvent ae){
    	
    	searchBut.requestFocus();
    	searchButtonAction();
    	return;
    }
    
    public void showAction(ActionEvent ae) {
    	    	
    	Dictionary tempData = dictionaryTable.getSelectionModel().getSelectedItem();
	    if (tempData !=null) {	    		   
	    	String english = tempData.getEnglishCol();
	    	String cayuga = tempData.getCayugaCol();
	    	mainApp.showUpdateItemDialog(tempData);	    		   
           for(int j=0; j<dictionaryEnglishList.size();j++) {
        	   if(dictionaryEnglishList.get(j).toString().equals(english) 
        			   &&dictionaryCayugaList.get(j).toString().equals(cayuga))  {
        		   dictionaryEnglishList.set(j, tempData.getEnglishCol());
        		   dictionaryCayugaList.set(j, tempData.getCayugaCol());           		   
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
    public void deleteAction() {
    	
    	   int selectedIndex = dictionaryTable.getSelectionModel().getSelectedIndex();
    	   Dictionary tempData = dictionaryTable.getSelectionModel().getSelectedItem();  	   
    	    if (selectedIndex >= 0) {
    	    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	    	alert.setTitle("Confirmation Dialog");
    	    	alert.setHeaderText("Delete");
    	    	alert.setContentText("Are you sure to delete this row from the database?");
    	    	Optional<ButtonType> result = alert.showAndWait();
    	    	if (result.get() == ButtonType.OK){
    	    		RemoveLine rl = new RemoveLine();
    	        	rl.removeLine(tempData.getCayugaCol(),tempData.getEnglishCol());   	  		
    	    		dictionaryTable.getItems().remove(selectedIndex);     	    		
	               for(int j=0; j<dictionaryEnglishList.size();j++) {
	            	   
	            	   if(dictionaryEnglishList.get(j).toString().equals(tempData.getEnglishCol()) 
	            			   &&dictionaryCayugaList.get(j).toString().equals(tempData.getCayugaCol()))  {
	            		 dictionaryEnglishList.remove(j);
	            		 dictionaryCayugaList.remove(j);    	            		   
	            	   }   
	               }  	    		
    	    	} else {	
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
    public void MouseMoveText() {
    	mainScene.requestFocus();
    }    
    @FXML
    public void textButAction_1(ActionEvent ae){
    	inputText.requestFocus();
    	String currentString = inputText.getText();
    	inputText.setText(currentString+"ˀ");
    	inputText.positionCaret(currentString.length()+1);
    }
    @FXML
    public void textButAction_2(ActionEvent ae){
    	inputText.requestFocus();
    	String currentString = inputText.getText();
    	inputText.setText(currentString+"ǫ");
    	inputText.positionCaret(currentString.length()+1);
    }
    @FXML
    public void textButAction_3(ActionEvent ae){
    	inputText.requestFocus();
    	String currentString = inputText.getText();
    	inputText.setText(currentString+":");
    	inputText.positionCaret(currentString.length()+1);
    }
    @FXML
    public void textButAction_4(ActionEvent ae){
    	inputText.requestFocus();
    	String currentString = inputText.getText();
    	inputText.setText(currentString+"ę");
    	inputText.positionCaret(currentString.length()+1);
    }  
    @FXML
    public void searchButtonAction() {	 	
       	String currentInput = inputText.getText().toLowerCase();	
       	if(currentInput.length() == 0) return;
       	relatedImg.setVisible(false);
   		relatedLabel.setVisible(false);
       	relatedPane.setVisible(false);
       	
       	SearchByString(currentInput);
    }   
    private void SearchByString(String currentInput) {
    	mainApp.getData().clear();	
    	System.out.println("currentInput = " + currentInput);
    	currentInput = currentInput.toLowerCase();
    	if(englishBut.selectedProperty().getValue()) {
			for(int i=0; i<dictionaryEnglishList.size();i++) {			
				String words = dictionaryEnglishList.get(i).toString().toLowerCase();		
				StringBuilder words_sb = new StringBuilder(words);
				for(int a=0; a<words_sb.length();a++) {					
					if(words_sb.charAt(a)==')'||words_sb.charAt(a)=='(') words_sb.deleteCharAt(a);				
				}
				words = words_sb.toString();
				words = " "+words+" ";
				StringBuilder sb = new StringBuilder(currentInput);
				for(int a=0; a<sb.length();a++) {					
					if(sb.charAt(a)==')'||sb.charAt(a)=='(') sb.deleteCharAt(a);					
				}
				String matches = sb.toString();
				matches = createRegularExpression(matches);				
				if(words.matches(matches)) {
					Dictionary tempData = new Dictionary();
					tempData.setEnglishCol(dictionaryEnglishList.get(i));
					tempData.setCayugaCol(dictionaryCayugaList.get(i));
					mainApp.getData().add(tempData);
				}
			}	
		} else {
			
			similarityButton.setVisible(true);
			
	       	if(checkAffixes(currentInput)) {
	       		relatedImg.setVisible(true);
	       		relatedLabel.setVisible(true);
	       	} else {
	       		relatedImg.setVisible(false);
	       		relatedLabel.setVisible(false);
	       	}				
			for(int i=0; i<dictionaryCayugaList.size();i++) {				
				String words = dictionaryCayugaList.get(i).toString().toLowerCase();
				StringBuilder words_sb = new StringBuilder(words);
				for(int a=0; a<words_sb.length();a++) {					
					if(words_sb.charAt(a)=='ˀ'||words_sb.charAt(a)==':') words_sb.deleteCharAt(a);					
				}
				words = words_sb.toString();
				words = " "+words+" ";				
				String matches = currentInput;				
				StringBuilder sb = new StringBuilder(matches);
				for(int a=0; a<sb.length();a++) {					
					if(sb.charAt(a)=='ˀ'||sb.charAt(a)==':') sb.deleteCharAt(a);					
				}
				matches = sb.toString();
				matches = createRegularExpression(matches);			
				if(words.toString().matches(matches)) {
					Dictionary tempData = new Dictionary();
					tempData.setEnglishCol(dictionaryEnglishList.get(i));
					tempData.setCayugaCol(dictionaryCayugaList.get(i));
					mainApp.getData().add(tempData);
				}
			}			
		}
    }
    
    public String createRegularExpression(String input) {
    	
		String matches = input;		
		StringBuilder sb = new StringBuilder(matches);
		int begin = 0;
		int mid = 0;
		int end = 0;
		for(int a=0; a<sb.length();a++) {			
			if(sb.charAt(a)=='*'&& a == 0)	{
				begin = 1;
				sb.deleteCharAt(a);
			} else if(sb.charAt(a)=='*'&& a == sb.length() -1 ){
				end = 1;
				sb.deleteCharAt(a);
			} else if(sb.charAt(a)=='*'&& a!= 0 && a!= sb.length() -1)	{				
				sb.deleteCharAt(a);
				sb.insert(a, ".");
				mid = 1;
			}
		}
		matches = sb.toString();
		matches = matches.replace(".", ".*");
		if(begin == 0 && mid == 0 && end == 0) {
			matches = ".* "+matches+" .*";
		} else if (begin == 1 && mid == 0 && end == 0) {
			matches = " .*"+matches+" .*";
		} else if (begin == 0 && mid == 1 && end == 0) {
			matches = ".* "+matches+" .*";
		} else if (begin == 0 && mid == 0 && end == 1) {
			matches = ".* "+matches+".* ";
		} else if (begin == 1 && mid == 1 && end == 0) {
			matches = " .*"+matches+" .*";
		} else if (begin == 0 && mid == 1 && end == 1) {
			matches = ".* "+matches+".* ";
		} else if (begin == 1 && mid == 0 && end == 1) {
			matches = " .*"+matches+".* ";
		} else if (begin == 1 && mid == 1 && end == 1) {
			matches = " .*"+matches+".* ";
		} 	
    	return matches;
    }
    
    @FXML
    public void partialSearch(ActionEvent ae) {
    	
    	mainApp.getData().clear();
//    	related_1.setVisible(false);
//    	related_2.setVisible(false);
//    	related_3.setVisible(false);
       	String currentInput = inputText.getText();
       	if(currentInput.length() == 0) return;
		if(englishBut.selectedProperty().getValue()) {
			for(int i=0; i<dictionaryEnglishList.size();i++) {		
				String words = dictionaryEnglishList.get(i).toString();		
				String matches = currentInput;				
				StringBuilder words_sb = new StringBuilder(words);
				for(int a=0; a<words_sb.length();a++) {					
					if(words_sb.charAt(a)==')'||words_sb.charAt(a)=='(') words_sb.deleteCharAt(a);					
				}			
				words = words_sb.toString();
				words = " "+words+" ";				
				StringBuilder sb = new StringBuilder(matches);
				for(int a=0; a<sb.length();a++) {				
					if(sb.charAt(a)==')'||sb.charAt(a)=='(') sb.deleteCharAt(a);				
				}				
				matches = sb.toString();
				matches = " "+matches +" ";	
				matches = ".*"+matches+".*";
				System.out.println(matches);
				if(words.matches(matches)) {
					Dictionary tempData = new Dictionary();
					tempData.setEnglishCol(dictionaryEnglishList.get(i));
					tempData.setCayugaCol(dictionaryCayugaList.get(i));
					mainApp.getData().add(tempData);
				}
			}	
		} else {
			
			for(int i=0; i<dictionaryCayugaList.size();i++) {
				String words = dictionaryCayugaList.get(i).toString();
				words = " "+words+" ";
				currentInput = " "+currentInput +" ";
				if(words.matches(".*"+currentInput+".*")) {
					Dictionary tempData = new Dictionary();
					tempData.setEnglishCol(dictionaryEnglishList.get(i));
					tempData.setCayugaCol(dictionaryCayugaList.get(i));
					mainApp.getData().add(tempData);
				}
			}		
		}	
    }
      
    public void relatedWord(ActionEvent ae) {
    	
    	String currentInput = inputText.getText();
       	if(currentInput.length() < 3) partialSearch(null);
       	int total = 0;
    	int length = currentInput.length();
       	int subStringLength = length-1;
       	ArrayList<String> newList = new ArrayList<>(dictionaryCayugaList);
       	
       	
       	for(int i =subStringLength; i>=2; i--) {
       		if(total >=3) break;      		
       		for(int j=0; j+i<=length; j++) {    			
       			if(total >=3) break;    			
       			String sub  = currentInput.substring(length-j-i,length-j);		
       			for(int d=0; d<newList.size();d++) {
       				
	    			if(newList.get(d).toString().matches(".*"+sub+".*") && !newList.get(d).toString().matches(".*"+currentInput+".*")) {    				
	    				if(total == 0) {
	    					newList.remove(d);
//	    					related_1.setText(sub);
//	    					related_1.setVisible(true);
	    					total++;
	    					break;
	    				} else if(total == 1) {
	    					newList.remove(d);
//	    					related_2.setText(sub);
//	    					related_2.setVisible(true);
	    					total++;
	    					break;
	    				} else if(total == 2) {
	    					newList.remove(d);
//	    					related_3.setText(sub);
//	    					related_3.setVisible(true);
	    					total++;
	    					break;
	    				} else {break;}		
	    			}
       			}		
       		}       		
       	}
    }
    
//    public void exportDictionary() {
//    	System.out.println("export");
//        FileChooser fileChooser = new FileChooser();  
//        
//        //Set extension filter
//        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*cayuga-english.txt)", "*cayuga-english.txt");
//        fileChooser.getExtensionFilters().add(extFilter);
//        fileChooser.setInitialFileName("cayuga-english");
//        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());      
//        if(file != null){
//        	new ExportDictionary(file);    
//        }     
//    }
//    
//    public void importDictionary() {
//    	System.out.println("import");
//    	FileChooser fileChooser = new FileChooser();
//        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
//                "TXT files (cayuga-english.txt)", "cayuga-english.txt");
//        fileChooser.getExtensionFilters().add(extFilter);
//
//        // Show save file dialog
//        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());       
//        if (file != null) {
//            new ImportDictionary(file,dictionaryEnglishList,dictionaryCayugaList);
//            initialArrayList();
//            mainApp.getData().clear();
//        	inputText.clear();
//        }
//    }
}
