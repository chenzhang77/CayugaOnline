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

import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import mun.model.User;

public class DBConnection {

	//static Sql2o sql2o = new Sql2o("jdbc:mysql://localhost:3306/cayugaOnline?autoReconnect=false&useSSL=false", "root", "123");
	static Sql2o sql2o = new Sql2o("jdbc:mysql://mysql3000.mochahost.com:3306/cjdyck_cayugaOnline?autoReconnect=false&useSSL=false&autocommit=false", "cjdyck_chen", "chen123");
	public static User getUserRole(User userObj){

        String sql = "SELECT role FROM User where userName='"+userObj.getUserName()+"' and password='"+userObj.getPassword()+"'";
        System.out.println(sql);
        try (Connection con = sql2o.open()) {
        	if(con.createQuery(sql).executeScalar(Integer.class) !=null) {
        		userObj.setRole(con.createQuery(sql).executeScalar(Integer.class));
        	}
        	return userObj;
        }
    }
	
	public static boolean updateUserRole(User previousUserObj, User userObj){

        String updateSql = "Update User set userName='"+userObj.getUserName()+"', password='"+userObj.getPassword()+"' where userName='"+previousUserObj.getUserName()+"' and password='"+previousUserObj.getPassword()+"'";
        System.out.println(updateSql);
        try (Connection con = sql2o.open()) {
            con.createQuery(updateSql).executeUpdate();
            return true;
        }catch(Exception e) {
        	return false;
        }
    }
	
	public static boolean insertUserRole(User userObj){

		User userObjInDB = getUserRole(userObj);
		if(userObjInDB.getRole() != 0) return false;		
        String insertSql = "Insert into User (userName,password,role) values ('"+userObj.getUserName()+"', '"+userObj.getPassword()+"',2)";
        System.out.println(insertSql);
        try (Connection con = sql2o.open()) {
            con.createQuery(insertSql)
        	    .executeUpdate();
            return true;
        }catch(Exception e) {
        	return false;
        }
    }
	
	public static List<User> userRoleList(){

        String sql = "SELECT userName,password,role FROM User";
        System.out.println(sql);
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(User.class);
        }catch(Exception e) {
        	return null;
        }
    }
	
	public static boolean deleteUserRole(User userObj){

        String insertSql = "Delete From User where userName='"+userObj.getUserName()+"'";
        System.out.println(insertSql);
        try (Connection con = sql2o.open()) {
            con.createQuery(insertSql)
        	    .executeUpdate();
            return true;
        }catch(Exception e) {
        	return false;
        }
    }
	
}
