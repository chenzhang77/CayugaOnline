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
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class WriteToFile {
	
	String file = Constant.dictionaryPath;
	FileInputStream fstream;
	FileOutputStream fos;
	OutputStreamWriter bw;
	
	public WriteToFile() {
		
		File fout = new File(file);	
		try {
			fos = new FileOutputStream(fout,true);
			bw = new OutputStreamWriter(fos, "UTF-16");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addNewItem(String english,String cayuga) {
		
		if(bw != null) {
			
			//String outsingleStirng = english+"     "+cayuga;
			String outsingleStirng = String.format("%s     %s%s",english,cayuga,System.getProperty("line.separator"));
			//System.out.println(outsingleStirng);
			try {
				//bw.write("\n");
				bw.append(outsingleStirng);
				//bw.write(outsingleStirng);
				//bw.write("\n");
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}	
	}
	

	public void addNewItemAndComments(String english,String cayuga,String comments) {
		
		if(bw != null) {
			
			//String outsingleStirng = english+"     "+cayuga;
			String outsingleStirng = String.format("%s     %s     %s%s",english,cayuga,comments,System.getProperty("line.separator"));
			//System.out.println(outsingleStirng);
			try {
				//bw.write("\n");
				bw.append(outsingleStirng);
				//bw.write(outsingleStirng);
				//bw.write("\n");
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}	
	}
	
	public String findComments(String english,String cayuga) {
		System.out.println("findComments");
		try {         
	      	String file = Constant.dictionaryPath;
	  		FileInputStream fstream;
	  		try {
	  			fstream = new FileInputStream(file);
	  			Reader chars = new InputStreamReader(fstream, StandardCharsets.UTF_16);
	  			BufferedReader br = new BufferedReader(chars);
	  			String strLine;
	  			while ((strLine = br.readLine()) != null && !strLine.trim().isEmpty())   {  
	  				//System.out.println(strLine);
	  				String[] outstring = strLine.split("     ");
	  				if(outstring.length ==3) {
		  				if(outstring[0].replaceAll("\\p{C}", "").trim().compareTo(cayuga.replaceAll("\\p{C}", "").trim()) == 0 && outstring[1].replaceAll("\\p{C}", "").trim().compareTo(english.replaceAll("\\p{C}", "").trim()) == 0) {
		  					return outstring[2];      	
		  				}
	  				}
	  			}
	  			br.close();
	  		} catch (FileNotFoundException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		} catch (IOException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		}
	      }catch (Exception ex) {
	          ex.printStackTrace();
	      }
			return null;
	}	
}
