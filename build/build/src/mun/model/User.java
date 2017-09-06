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

public class User {
	
	private StringProperty userNameCol;
	private String userName;
	private String password;
	private int role = 0;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
		 setUserNameCol(userName);
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
	public void setUserNameCol(String username) {
		this.userNameCol = new SimpleStringProperty(username);
//		this.userNameCol.set(username);
	}
	
	public String getUserNameCol() {
		return userNameCol.get();
	}
	
	public StringProperty userNameColProperty() {
		return userNameCol;
	}


}
