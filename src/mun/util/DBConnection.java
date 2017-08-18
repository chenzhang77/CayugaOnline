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

import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DBConnection {

	//static Sql2o sql2o = new Sql2o("jdbc:mysql://localhost:3306/cayugaOnline?autoReconnect=false&useSSL=false", "root", "123");
	static Sql2o sql2o = new Sql2o("jdbc:mysql://mysql3000.mochahost.com:3306/cjdyck_cayugaOnline?autoReconnect=false&useSSL=false", "cjdyck_chen", "chen123");
	public static User getUserRole(User userObj){

        String sql = "SELECT role FROM User where name='"+userObj.getUserName()+"' and password='"+userObj.getPassword()+"'";
        System.out.println(sql);
        try (Connection con = sql2o.open()) {
        	if(con.createQuery(sql).executeScalar(Integer.class) !=null) {
        		userObj.setRole(con.createQuery(sql).executeScalar(Integer.class));
        	}
        	return userObj;
        }
    }
	
	
}
