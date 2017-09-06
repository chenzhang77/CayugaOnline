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
package mun.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Dictionary {
		
	private final StringProperty englishCol;
	private final StringProperty cayugaCol;

	public void setEnglishCol(String english) {
		this.englishCol.set(english);
	}
	
	public String getEnglishCol() {
		return englishCol.get();
	}
	
	public StringProperty englishColProperty() {
		return englishCol;
	}
	
	public void setCayugaCol(String cayuga) {
		this.cayugaCol.set(cayuga);
	}
	
	public String getCayugaCol() {
		return cayugaCol.get();
	}
	
	public StringProperty CayugaColProperty() {
		return cayugaCol;
	}

	public Dictionary() {
		this(null,null);
	}
	
	public Dictionary(String english, String cayuga) {
		
		this.englishCol = new SimpleStringProperty(english);
		this.cayugaCol = new SimpleStringProperty(cayuga);
	}
	
}
