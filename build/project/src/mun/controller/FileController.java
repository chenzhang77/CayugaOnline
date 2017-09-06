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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import mun.MainApp;
import mun.util.*;

public class FileController implements Initializable{

    private MainApp mainApp;  	
	private ArrayList<String> dictionaryEnglishList = new ArrayList<>();
	private ArrayList<String> dictionaryCayugaList = new ArrayList<>();
	private Hashtable<String, String> word_initial = new Hashtable<String, String>();
	private Hashtable<String, String> word_medial_1 = new Hashtable<String, String>();
	private Hashtable<String, String> word_medial_2 = new Hashtable<String, String>();
	private Hashtable<String, String> word_suffix = new Hashtable<String, String>();
	private Hashtable<String, String> word_without_affixes = new Hashtable<String, String>(); 
	  
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initialArrayList();
		initialWord_initialFiles();
		initialWord_medial_1Files();
		initialWord_medial_2Files();
		initialWord_suffixFiles();
		initialWord_without_affixesFiles();
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
				matches = sb.toString();				
				word_without_affixes.put(matches,outstring[1].replaceAll("\\p{C}", "").trim());
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
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
	
    @FXML
	public void importWordInitial() {
	    FileChooser fileChooser = new FileChooser();
	    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (word_initial.txt)", "word_initial.txt");
	    fileChooser.getExtensionFilters().add(extFilter);	
	    // Show save file dialog
	    File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());       
	    if (file != null) {
	        new ImportDictionary(file,word_initial,Constant.word_initial_Path);
	        mainApp.getData().clear();
	    }
	}
    	
	@FXML
	public void importWordMedial() {
	    FileChooser fileChooser = new FileChooser();
	    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (word_medial-1.txt)", "word_medial-1.txt");
	    fileChooser.getExtensionFilters().add(extFilter);	
	    // Show save file dialog
	    File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());       
	    if (file != null) {
	        new ImportDictionary(file,word_medial_1,Constant.word_medial_1_Path);
	        mainApp.getData().clear();
	    }
	}
	@FXML
	public void importWordMedial2() {
	    FileChooser fileChooser = new FileChooser();
	    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (word_medial-2.txt)", "word_medial-2.txt");
	    fileChooser.getExtensionFilters().add(extFilter);	
	    // Show save file dialog
	    File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());       
	    if (file != null) {
	        new ImportDictionary(file,word_medial_2,Constant.word_medial_2_Path);
	        mainApp.getData().clear();
	    }
	}
	@FXML
	public void importSuffix() {
	    FileChooser fileChooser = new FileChooser();
	    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (suffix_list.txt)", "suffix_list.txt");
	    fileChooser.getExtensionFilters().add(extFilter);	
	    // Show save file dialog
	    File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());       
	    if (file != null) {
	        new ImportDictionary(file,word_suffix,Constant.word_suffix_Path);
	        mainApp.getData().clear();
	    }
	}
	@FXML
	public void importWithout() {
	    FileChooser fileChooser = new FileChooser();
	    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (words_without_affixes.txt)", "words_without_affixes.txt");
	    fileChooser.getExtensionFilters().add(extFilter);	
	    // Show save file dialog
	    File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());       
	    if (file != null) {
	        new ImportDictionary(file,word_without_affixes,Constant.word_without_affixes_Path,"");
	        mainApp.getData().clear();
	    }
	}
	@FXML
	public void importDictionary() {
	    System.out.println("import");
	    FileChooser fileChooser = new FileChooser();
	    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (cayuga-english.txt)", "cayuga-english.txt");
	    fileChooser.getExtensionFilters().add(extFilter);	
	    // Show save file dialog
	    File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());       
	    if (file != null) {
	        new ImportDictionary(file,dictionaryEnglishList,dictionaryCayugaList,Constant.dictionaryPath);
	        mainApp.getData().clear();
	    }
	}
	
		
	@FXML
	public void exportWordInitial() {
    	System.out.println("exportWordInitial");
        FileChooser fileChooser = new FileChooser();         
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*word_initial.txt)", "*word_initial.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("word_initial");
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());      
        if(file != null){
        	new ExportDictionary(file,Constant.word_initial_Path);    
        } 
	}
	@FXML
	public void exportWordMedial() {
    	System.out.println("exportWordMedial");
        FileChooser fileChooser = new FileChooser();         
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*word_medial-1.txt)", "*word_medial-1.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("word_medial-1");
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());      
        if(file != null){
        	new ExportDictionary(file,Constant.word_medial_1_Path);    
        } 
	}
	@FXML
	public void exportWordMedial2() {
    	System.out.println("exportWordMedial2");
        FileChooser fileChooser = new FileChooser();          
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*word_medial-2.txt)", "*word_medial-2.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("word_medial-2");
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());      
        if(file != null){
        	new ExportDictionary(file,Constant.word_medial_2_Path);    
        } 
	}
	@FXML
	public void exportSuffix() {
    	System.out.println("exportSuffix");
        FileChooser fileChooser = new FileChooser();          
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*suffix_list.txt)", "*suffix_list.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("suffix_list");
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());      
        if(file != null){
        	new ExportDictionary(file,Constant.word_suffix_Path);    
        } 
	}
	@FXML
	public void exportWithout() {
    	System.out.println("exportWithout");
        FileChooser fileChooser = new FileChooser();          
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*words_without_affixes.txt)", "*words_without_affixes.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("words_without_affixes");
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());      
        if(file != null){
        	new ExportDictionary(file,Constant.word_without_affixes_Path);    
        } 
	}
	@FXML
	public void exportDictionary() {
    	System.out.println("exportDictionary");
        FileChooser fileChooser = new FileChooser();          
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*cayuga-english.txt)", "*cayuga-english.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("cayuga-english");
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());      
        if(file != null){
        	new ExportDictionary(file,Constant.dictionaryPath);    
        }  
	}
		
	
	@FXML
	public void overwriteWordInitial() {
	    System.out.println("overwriteWordInitial");
	    FileChooser fileChooser = new FileChooser();
	    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (word_initial.txt)", "word_initial.txt");
	    fileChooser.getExtensionFilters().add(extFilter);	
	    // Show save file dialog
	    File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());       
	    if (file != null) {
	        new ImportDictionary(file,Constant.word_initial_Path);
	        mainApp.getData().clear();
	    }
	}
	@FXML
	public void overwriteWordMedial() {
	    System.out.println("overwriteWordMedial");
	    FileChooser fileChooser = new FileChooser();
	    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*word_medial-1.txt)", "*word_medial-1.txt");
	    fileChooser.getExtensionFilters().add(extFilter);	
	    // Show save file dialog
	    File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());       
	    if (file != null) {
	        new ImportDictionary(file,Constant.word_medial_1_Path);
	        mainApp.getData().clear();
	    }
	}
	@FXML
	public void overwriteWordMedial2() {
	    System.out.println("overwriteWordMedial2");
	    FileChooser fileChooser = new FileChooser();
	    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*word_medial-2.txt)", "*word_medial-2.txt");
	    fileChooser.getExtensionFilters().add(extFilter);	
	    // Show save file dialog
	    File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());       
	    if (file != null) {
	        new ImportDictionary(file,Constant.word_medial_2_Path);
	        mainApp.getData().clear();
	    }
	}
	@FXML
	public void overwriteSuffix() {
	    System.out.println("overwriteSuffix");
	    FileChooser fileChooser = new FileChooser();
	    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (suffix_list.txt)", "suffix_list.txt");
	    fileChooser.getExtensionFilters().add(extFilter);	
	    // Show save file dialog
	    File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());       
	    if (file != null) {
	        new ImportDictionary(file,Constant.word_suffix_Path);
	        mainApp.getData().clear();
	    }
	}
	@FXML
	public void overwriteWithout() {
	    System.out.println("overwriteWithout");
	    FileChooser fileChooser = new FileChooser();
	    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (words_without_affixes.txt)", "words_without_affixes.txt");
	    fileChooser.getExtensionFilters().add(extFilter);	
	    // Show save file dialog
	    File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());       
	    if (file != null) {
	        new ImportDictionary(file,Constant.word_without_affixes_Path,"");
	        mainApp.getData().clear();
	    }
	}
	@FXML
	public void overwriteDictionary() {
	    System.out.println("overwriteDictionary");
	    FileChooser fileChooser = new FileChooser();
	    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (cayuga-english.txt)", "cayuga-english.txt");
	    fileChooser.getExtensionFilters().add(extFilter);	
	    // Show save file dialog
	    File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());       
	    if (file != null) {
	        new ImportDictionary(file,Constant.dictionaryPath);
	        mainApp.getData().clear();
	    }
	}
	
}
