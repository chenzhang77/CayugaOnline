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
import java.util.Iterator;
import java.util.Optional;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
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
	  private Button relatedImg;  
	  @FXML
	  private HBox relatedPane;
	  @FXML
	  private Separator sep_1, sep_2, sep_3, sep_4;
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
	  
	  //private ArrayList<String> word_initial = new ArrayList<>();
//	  private ArrayList<String> word_medial_1 = new ArrayList<>();
//	  private ArrayList<String> word_medial_2 = new ArrayList<>();
//	  private ArrayList<String> word_suffix = new ArrayList<>();
//	  private ArrayList<String> word_without_affixes = new ArrayList<>();
	  
	  
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
    	    	
    	    	aa = new ArrayList<>();
    	    	String inputString = param.getUserText();  	    	
    	    	inputTextString = inputString;
    	    	inputString = inputString.replace("*", ".*");

    	    	if(inputString.length() != 0 && inputString != null) {    	
    	    		if(englishBut.selectedProperty().getValue())
	        	    	for(String item :dictionaryEnglishList) {
	        	    		if(item.matches("^"+inputString+".*$")){
	        	    			aa.add(item);
	        	    		}	        			
	        	    	}
    	    		else
            	    	for(String item :dictionaryCayugaList) {
            	    		if(item.matches("^"+inputString+".*$")){
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
	  	autoTF.setOnAutoCompleted(new EventHandler(){
				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					firstItem = aa.get(0);
					if(inputText.getText().equalsIgnoreCase(firstItem)){
						inputText.setText(inputTextString);
						firstItem = "";
						inputTextString = "";
						onEnter(null);
					}
				}		
	    	});

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
        
    private void initialButtons() {
    	
    	final ToggleGroup group = new ToggleGroup();
      	englishBut.setToggleGroup(group);
    	englishBut.setSelected(true);
    	cayugaBut.setToggleGroup(group);
//    	relatedWordBut.setVisible(false);
//    	final ToggleGroup group2 = new ToggleGroup();
//    	related_1.setToggleGroup(group2);
//    	related_2.setToggleGroup(group2);
//    	related_3.setToggleGroup(group2);
//    	related_1.setVisible(false);
//    	related_2.setVisible(false);
//    	related_3.setVisible(false);
    	textBut_1.setVisible(false);
    	textBut_2.setVisible(false);
    	textBut_3.setVisible(false);
    	textBut_4.setVisible(false);
    	
    	relatedImg.setVisible(false);
    	relatedPane.setVisible(false);
    	sep_1.setVisible(false);
    	sep_2.setVisible(false);
    	sep_3.setVisible(false);
    	sep_4.setVisible(false);
    	relatedLabel.setVisible(false);
    	

    	
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
    	                	sep_1.setVisible(false);
    	                	sep_2.setVisible(false);
    	                	sep_3.setVisible(false);
    	                	sep_4.setVisible(false);
    	                	relatedLabel.setVisible(false);
    	                	
//    	                	relatedWordBut.setVisible(false);
//    	                	related_1.setVisible(false);
//    	                	related_2.setVisible(false);
//    	                	related_3.setVisible(false);
    	                	
    	                	
    	                	mainApp.getData().clear();
    	                	inputText.clear();
    	                	

//    	            		  showButton.setVisible(false);
//    	            		  deleteButton.setVisible(false);
    	                	
    	            	} else {
    	            		inputText.clear();
//		            		showButton.setVisible(false);
//		            		deleteButton.setVisible(false);
		            		  
//	            		  	relatedWordBut.setVisible(true);
//	            			related_1.setVisible(false);
//	            	    	related_2.setVisible(false);
//	            	    	related_3.setVisible(false);
		            		  
		            		  
    	                	textBut_1.setVisible(true);
    	                	textBut_2.setVisible(true);
    	                	textBut_3.setVisible(true);
    	                	textBut_4.setVisible(true);
    	                	
//    	                	relatedImg.setVisible(true);
//    	                	relatedLabel.setVisible(true);

    	                	
    	                	
    	                	mainApp.getData().clear();
    	            	}
    	            }                
    	        }
    	});
    	
    	
//    	group2.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
//    		
//    	    public void changed(ObservableValue<? extends Toggle> ov,
//    	        Toggle old_toggle, Toggle new_toggle) {
//    	    	
//    	    	
//	    	    	relatedWordBut.requestFocus();
//		            if (group2.getSelectedToggle() != null) {
//		            	mainApp.getData().clear();
//		            	String currentInput;
//		            	if(related_1.selectedProperty().getValue()) {
//		            		currentInput = related_1.getText();
//		                	
//		            	} else if (related_2.selectedProperty().getValue()) {
//		            		currentInput = related_2.getText();
//		            	} else {
//		            		currentInput = related_3.getText();
//		            	}		            	
//		        		for(int i=0; i<dictionaryCayugaList.size();i++) {		    			
//			    			if(dictionaryCayugaList.get(i).toString().matches(".*"+currentInput+".*")) {
//			    				//System.out.println("coming in update dictionaryCayugaList");
//			    				Dictionary tempData = new Dictionary();
//			    				tempData.setEnglishCol(dictionaryEnglishList.get(i));
//			    				tempData.setCayugaCol(dictionaryCayugaList.get(i));
//			    				mainApp.getData().add(tempData);
//			    			}
//		        		}
//		            }                
//	    	   }
//    	}); 	
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
				word_without_affixes.put(outstring[0].replaceAll("\\p{C}", "").trim(),"");
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
    	
    	if(word_without_affixes.containsKey(word_residue)) {		
    		return false;
    	}
    	return true;
    }
    
    private String searchLongestPrefixString(String inputString, Hashtable<String, String> hashTable) {
    	
    	System.out.println("inputTextString = "+inputString);
    	String longestString = "";
    	
    	for (int i=0; i< inputString.length(); i++) {
    		
    		char current = inputString.charAt(i);
    		
    		if(current == 'ˀ'|| current == ':') {
    			continue;
    		}
    		longestString = longestString + inputString.charAt(i);
    		if(!hashTable.containsKey(longestString)) {
    			return inputString.substring(0, i);
    		} else {
    			System.out.println("loop = "+longestString);
    		}
    			
    	}
    	
    	return "";
    }
    
    @FXML
    public void relatedFunction() {
    	relatedPane.setVisible(true);
    	sep_1.setVisible(true);
    	sep_2.setVisible(true);
    	Prefix_label.setVisible(true);
    	Suffix_label.setVisible(true);
    	Prefix_checkBox_1.setVisible(true);
    	Prefix_checkBox_1.setSelected(true);
    	
    	String word_initial_string = searchLongestPrefixString(inputText.getText(),word_initial);
    	System.out.println("word_initial_string = "+word_initial_string);
    	if(!word_initial_string.equals(""))
    		Prefix_checkBox_1.setText(word_initial_string);
    	else
    		Prefix_checkBox_1.setText("No Prefix");
    	
    	
    	
    	Prefix_checkBox_2.setVisible(false);
    	Prefix_checkBox_3.setVisible(false);

    	
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
    public void prefixCheckBox_1() {
    	if(Prefix_checkBox_1.isSelected()) {
    		Prefix_checkBox_2.setVisible(false);
        	Prefix_checkBox_3.setVisible(false);
        	
           	String currentInput = inputText.getText();	
           	if(currentInput.length() == 0) return;
        	SearchByString(currentInput);
    	} else {
    		Prefix_checkBox_2.setVisible(true);
    		Prefix_checkBox_3.setVisible(false);
    		
    		String currentInput = inputText.getText();	
           	if(currentInput.length() == 0) return;
           	int length_initial = Prefix_checkBox_1.getText().length();
    		
        	String word_medial_string = searchLongestPrefixString(currentInput.substring(length_initial),word_medial_1);
        	System.out.println("word_initial_string = "+word_medial_string);
        	if(!word_medial_string.equals("")) {
        		Prefix_checkBox_2.setSelected(true);
        		Prefix_checkBox_2.setText(word_medial_string);
        		SearchByString("*"+currentInput.substring(length_initial));
        	}
        		
        	else
        		Prefix_checkBox_2.setVisible(false);
    		
    		
    	}
    }
    
    @FXML
    public void prefixCheckBox_2() {
    	if(Prefix_checkBox_2.isSelected()) {
        	Prefix_checkBox_3.setVisible(false);
        	
           	String currentInput = inputText.getText();	
           	if(currentInput.length() == 0) return;	
           	int length_initial = Prefix_checkBox_1.getText().length();
        	SearchByString("*"+currentInput.substring(length_initial));
        	
    	} else {
    		
    		String currentInput = inputText.getText();	
           	if(currentInput.length() == 0) return;
           	int length_initial = Prefix_checkBox_1.getText().length();
           	int length_medial = Prefix_checkBox_2.getText().length();
    		
        	String word_medial2_string = searchLongestPrefixString(currentInput.substring(length_initial+length_medial),word_medial_2);
        	System.out.println("word_initial_string = "+word_medial2_string);
        	if(!word_medial2_string.equals("")) {
        		Prefix_checkBox_3.setSelected(true);
        		Prefix_checkBox_3.setVisible(true);
        		Prefix_checkBox_3.setText(word_medial2_string);
        		SearchByString("*"+currentInput.substring(length_initial+length_medial));
        	}
        		
        	else
        		Prefix_checkBox_3.setVisible(false);
    		
    		
    	}
    }
    
    @FXML
    public void prefixCheckBox_3() {
    	
    }
    
//    private void testFiles() {
//    	
//    	System.out.println("---------");
//    	for (String key : word_initial.keySet()) {
//    		   System.out.println("key: " + key);
//    		}
//    	System.out.println("---------");
//    }
    
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
     * details for a new person.
     */
    @FXML
    private void handleNewData() {
//    	Dictionary tempData = new Dictionary();
//        boolean okClicked = mainApp.showAddItemDialog(tempData);
//        if (okClicked) {
//        	dictionaryEnglishList.add(tempData.getEnglishCol());
//        	dictionaryCayugaList.add(tempData.getCayugaCol());
//            mainApp.getData().add(tempData);
//        }     
    }
    
    @FXML
    public void onEnter(ActionEvent ae){
    	
    	searchBut.requestFocus();
    	searchButtonAction();
    	return;
    }
    
    public void showAction(ActionEvent ae) {
    	    	
//    	Dictionary tempData = dictionaryTable.getSelectionModel().getSelectedItem();
//	    if (tempData !=null) {	    		   
//	    	String english = tempData.getEnglishCol();
//	    	String cayuga = tempData.getCayugaCol();
//	    	mainApp.showUpdateItemDialog(tempData);	    		   
//           for(int j=0; j<dictionaryEnglishList.size();j++) {
//        	   if(dictionaryEnglishList.get(j).toString().equals(english) 
//        			   &&dictionaryCayugaList.get(j).toString().equals(cayuga))  {
//        		   dictionaryEnglishList.set(j, tempData.getEnglishCol());
//        		   dictionaryCayugaList.set(j, tempData.getCayugaCol());           		   
//        	   }      	   
//           }
//	    } else {
//	    	Alert alert = new Alert(AlertType.INFORMATION);
//	    	alert.setTitle("Information Dialog");
//	    	alert.setHeaderText(null);
//	    	alert.setContentText("Please select one row !");
//	    	alert.showAndWait();
//	    }
    }
    
    public void deleteAction(ActionEvent ae) {
    	
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

    	
       	String currentInput = inputText.getText();	
       	if(currentInput.length() == 0) return;
       	SearchByString(currentInput);
    }
    
    private void SearchByString(String currentInput) {
    	mainApp.getData().clear();	
    	if(englishBut.selectedProperty().getValue()) {

			for(int i=0; i<dictionaryEnglishList.size();i++) {			
				String words = dictionaryEnglishList.get(i).toString();		
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
			
	       	if(checkAffixes(currentInput)) {
	       		relatedImg.setVisible(true);
	       		relatedLabel.setVisible(true);
	       	} else {
	       		relatedImg.setVisible(false);
	       		relatedLabel.setVisible(false);
	       	}
				
			for(int i=0; i<dictionaryCayugaList.size();i++) {
				
				String words = dictionaryCayugaList.get(i).toString();
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
					//System.out.println("coming in update dictionaryCayugaList");
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
	    				}
	    				else if(total == 1) {
	    					newList.remove(d);
//	    					related_2.setText(sub);
//	    					related_2.setVisible(true);
	    					total++;
	    					break;
	    				}
	    				else if(total == 2) {
	    					newList.remove(d);
//	    					related_3.setText(sub);
//	    					related_3.setVisible(true);
	    					total++;
	    					break;
	    				} else {	
	    					break;
	    				}		
	    			}
       			}		
       		}       		
       	}
    }
    
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
    
    public void importDictionary() {
    	System.out.println("import");
    	FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "TXT files (cayuga-english.txt)", "cayuga-english.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());       
        if (file != null) {
            new ImportDictionary(file,dictionaryEnglishList,dictionaryCayugaList);
            initialArrayList();
            mainApp.getData().clear();
        	inputText.clear();
        }
    }
}
