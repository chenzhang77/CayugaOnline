/**
 * 
 */
package mun.model;

/**
 * @author cz5670
 *
 */
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
