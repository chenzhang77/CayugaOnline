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
package mun.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public class ImportDictionary {

	public ImportDictionary(File inputfile,ArrayList<String> dictionaryEnglishList,ArrayList<String> dictionaryCayugaList,String fileName) {		
		try {
				String file = fileName;
				FileOutputStream fos = new FileOutputStream(file,false);
				OutputStreamWriter bw = new OutputStreamWriter(fos, "UTF-16");	          	          
				FileInputStream fstream;
				try {
	  				fstream = new FileInputStream(inputfile);
	  				Reader chars = new InputStreamReader(fstream, StandardCharsets.UTF_16);
	  				BufferedReader br = new BufferedReader(chars);
	  				String strLine;	  					  				
	  	  			while ((strLine = br.readLine()) != null && !strLine.trim().isEmpty())   {	  	  				
	  	  				String[] outstring = strLine.split("     ");
	  	  					if(dictionaryCayugaList.contains(outstring[0].replaceAll("\\p{C}", "").trim()) &&
	  	  							dictionaryEnglishList.contains(outstring[1].replaceAll("\\p{C}", "").trim())) {	  	  					
	  	  						dictionaryCayugaList.remove(outstring[0].replaceAll("\\p{C}", "").trim());
	  	  						dictionaryEnglishList.remove(outstring[1].replaceAll("\\p{C}", "").trim());	  	  					
	  	  					}	  	  					
	  	  				String outsingleStirng = String.format("%s     %s%s",outstring[0].replaceAll("\\p{C}", "").trim(),outstring[1].replaceAll("\\p{C}", "").trim(),System.getProperty("line.separator"));
	  					bw.append(outsingleStirng);	  	  					  	  
	  	  			}	 				
	  				int length = dictionaryCayugaList.size();	  				
	  				for (int i=0; i< length; i++) {	  						  					
	  					String outsingleStirng = String.format("%s     %s%s",dictionaryCayugaList.get(i).trim().toString(),dictionaryEnglishList.get(i).trim().toString(),System.getProperty("line.separator"));
	  					bw.append(outsingleStirng);	  					
	  				}	  					  					  				
	  				bw.close();
	  				br.close();
				} catch (FileNotFoundException e) {
	  			// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
	  			// TODO Auto-generated catch block
					e.printStackTrace();
				}
	      }catch (IOException ex) {
	          ex.printStackTrace();
	      }						
	}

	public ImportDictionary(File inputfile,Hashtable<String, String> hashTable,String fileName) {		
		try {
				String file = fileName;
				FileOutputStream fos = new FileOutputStream(file,false);
				OutputStreamWriter bw = new OutputStreamWriter(fos, "UTF-16");	          	          
				FileInputStream fstream;
				try {
	  				fstream = new FileInputStream(inputfile);
	  				Reader chars = new InputStreamReader(fstream, StandardCharsets.UTF_16);
	  				BufferedReader br = new BufferedReader(chars);
	  				String strLine;	  					  				
	  	  			while ((strLine = br.readLine()) != null && !strLine.trim().isEmpty())   {	  	  				
	  	  				String[] outstring = strLine.split("     ");
	  	  				System.out.println(outstring[0].replaceAll("\\p{C}", "").trim());
  	  					if(hashTable.containsKey(outstring[0].replaceAll("\\p{C}", "").trim())) {
  	  						hashTable.remove(outstring[0].replaceAll("\\p{C}", "").trim());	  	  					
  	  					}	  	  					
	  	  				String outsingleStirng = String.format("%s     %s",outstring[0].replaceAll("\\p{C}", "").trim(),System.getProperty("line.separator"));
	  					bw.append(outsingleStirng);	  	  					  	  
	  	  			}
	  	  			Iterator<String> keySet = hashTable.keySet().iterator();
	  	  			while(keySet.hasNext()) {
	  	  				String key = (String) keySet.next();
	  					String outsingleStirng = String.format("%s     %s",key,System.getProperty("line.separator"));
	  					bw.append(outsingleStirng);	
	  	  			}	  					  					  				
	  				bw.close();
	  				br.close();
				} catch (FileNotFoundException e) {
	  			// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
	  			// TODO Auto-generated catch block
					e.printStackTrace();
				}
	      }catch (IOException ex) {
	          ex.printStackTrace();
	      }						
	}
	
	
	public ImportDictionary(File inputfile,Hashtable<String, String> hashTable,String fileName, String Suffix) {		
		try {
				String file = fileName;
				FileOutputStream fos = new FileOutputStream(file,false);
				OutputStreamWriter bw = new OutputStreamWriter(fos, "UTF-16");	          	          
				FileInputStream fstream;
				try {
	  				fstream = new FileInputStream(inputfile);
	  				Reader chars = new InputStreamReader(fstream, StandardCharsets.UTF_16);
	  				BufferedReader br = new BufferedReader(chars);
	  				String strLine;	  					  				
	  	  			while ((strLine = br.readLine()) != null && !strLine.trim().isEmpty())   {	  	  				
	  	  				String[] outstring = strLine.split("     ");
	  	  					if(hashTable.containsKey(outstring[0].replaceAll("\\p{C}", "").trim())) {
	  	  						hashTable.remove(outstring[0].replaceAll("\\p{C}", "").trim());	  	  					
	  	  					}	  	  					
	  	  				String outsingleStirng = String.format("%s     %s%s",outstring[0].replaceAll("\\p{C}", "").trim(),outstring[1].replaceAll("\\p{C}", "").trim(),System.getProperty("line.separator"));
	  					bw.append(outsingleStirng);	  	  					  	  
	  	  			}
	  	  			Iterator<String> keySet = hashTable.keySet().iterator();
	  	  			while(keySet.hasNext()) {
	  	  				String key = (String) keySet.next();
	  	  				String value = (String)hashTable.get(key);
	  					String outsingleStirng = String.format("%s     %s%s",key,value,System.getProperty("line.separator"));
	  					bw.append(outsingleStirng);	
	  	  			}	  					  					  				
	  				bw.close();
	  				br.close();
				} catch (FileNotFoundException e) {
	  			// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
	  			// TODO Auto-generated catch block
					e.printStackTrace();
				}
	      }catch (IOException ex) {
	          ex.printStackTrace();
	      }						
	}
	
	public ImportDictionary(File inputfile, String fileName) {
		try {
			String file = fileName;
			FileOutputStream fos = new FileOutputStream(file,false);
			OutputStreamWriter bw = new OutputStreamWriter(fos, "UTF-16");	          	          
			FileInputStream fstream;
			try {
  				fstream = new FileInputStream(inputfile);
  				Reader chars = new InputStreamReader(fstream, StandardCharsets.UTF_16);
  				BufferedReader br = new BufferedReader(chars);
  				String strLine;	  					  				
  	  			while ((strLine = br.readLine()) != null && !strLine.trim().isEmpty())   {	  	  				
  	  				String[] outstring = strLine.split("     ");
  	  				String outsingleStirng = String.format("%s     %s",outstring[0].replaceAll("\\p{C}", "").trim(),System.getProperty("line.separator"));
  	  				bw.append(outsingleStirng);	
  	  			}	 					  					  					  				
  				bw.close();
  				br.close();
			} catch (FileNotFoundException e) {
  			// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
  			// TODO Auto-generated catch block
				e.printStackTrace();
			}
      }catch (IOException ex) {
          ex.printStackTrace();
      }	
	}
	
	public ImportDictionary(File inputfile, String fileName,String suffix) {
		try {
			String file = fileName;
			FileOutputStream fos = new FileOutputStream(file,false);
			OutputStreamWriter bw = new OutputStreamWriter(fos, "UTF-16");	          	          
			FileInputStream fstream;
			try {
  				fstream = new FileInputStream(inputfile);
  				Reader chars = new InputStreamReader(fstream, StandardCharsets.UTF_16);
  				BufferedReader br = new BufferedReader(chars);
  				String strLine;	  					  				
  	  			while ((strLine = br.readLine()) != null && !strLine.trim().isEmpty())   {	  	  				
  	  				String[] outstring = strLine.split("     ");
  	  				String outsingleStirng = String.format("%s     %s%s",outstring[0].replaceAll("\\p{C}", "").trim(),outstring[1].replaceAll("\\p{C}", "").trim(),System.getProperty("line.separator"));
  	  				bw.append(outsingleStirng);	
  	  			}	 					  					  					  				
  				bw.close();
  				br.close();
			} catch (FileNotFoundException e) {
  			// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
  			// TODO Auto-generated catch block
				e.printStackTrace();
			}
      }catch (IOException ex) {
          ex.printStackTrace();
      }	
	}
	
}
