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

public class ExportDictionary {

	public ExportDictionary(File outputfile,String fileName) {
				
		try {
	          FileOutputStream fos = new FileOutputStream(outputfile,false);
	          OutputStreamWriter bw = new OutputStreamWriter(fos, "UTF-16");	          
	          String file = fileName;
	          FileInputStream fstream;
	          try {
	  			fstream = new FileInputStream(file);
	  			Reader chars = new InputStreamReader(fstream, StandardCharsets.UTF_16);
	  			BufferedReader br = new BufferedReader(chars);
	  			String strLine;
	  			//Read File Line By Line
	  			while ((strLine = br.readLine()) != null && !strLine.trim().isEmpty())   {
	  				String outsingleStirng = String.format("%s%s",strLine,System.getProperty("line.separator"));
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
	
