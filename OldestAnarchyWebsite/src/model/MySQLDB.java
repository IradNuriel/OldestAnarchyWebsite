package model;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public class MySQLDB {
	Connection con;
	
	public MySQLDB(){
		String connectionURL = "jdbc:mysql://localhost:3306/mysqldb?serverTimezone=Asia/Jerusalem";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con= DriverManager.getConnection(connectionURL, "root", "Irad9731!");
        } catch (Exception e) {
			System.out.println("error in connecting to the DB");
			con = (Connection)null;	
        }
	}
	
	public boolean IsAlive()
	{
		return con != null;
	}
	
	public boolean UserExists(String nickname){
		
		try{
		    Statement statement = con.createStatement();
            String queryString = "SELECT * FROM users WHERE nickname='" + nickname + "'";
            ResultSet rs = statement.executeQuery(queryString);
            return((rs != null) && (rs.next()));
		} catch (Exception e) {
								System.out.println("error in querying the DB");
								return true;			
				   			  }
	}
	
	

	
	public void AddNewUser(User user){
       String sqlString = "INSERT INTO  users" + " (nickname, password, lastdateentered)" 
							+ "VALUES ('"
							+ user.getNickName() + "', SHA1('" 
        					+ user.getPassword()  + "'), '"+
							user.getDate()+"')";	
        try {
        		Statement statement = con.createStatement();
	            statement.executeUpdate(sqlString);
	            
	            statement.close();
            } catch(SQLException ex) {System.out.println("SQLException: " + ex.getMessage());}			
	}

	public boolean ModifyUser(User user){
	    try {
		        String updString = "UPDATE users SET password =SHA1(?)"
		        		+ "   WHERE nickname='" + user.getNickName() + "'" ;
				PreparedStatement statement = con.prepareStatement(updString);
				statement.setString(3, user.getPassword());
	            statement.executeUpdate();
			    statement.close();
			    return true;
	    	} catch(SQLException ex) {System.out.println("SQLException: " + ex.getMessage());
	    								return false;
	    								}			
	}
	
	
	public boolean UserAuthenticate(String name, String pass){
		try{
		    Statement statement = con.createStatement();
            String queryString = "SELECT * FROM users WHERE nickname='" + name + "' and password=SHA1('" + pass + "')" ;
            ResultSet rs = statement.executeQuery(queryString);
            	return rs.next();
 		} catch (Exception e) {
								System.out.println("error in querying the DB");
								return false;			
				   			  }
	}
	
	

	public List<User> QueryUsers(Map<String,String> qParams, String strOrderField)
	{
	    Statement statement;
        ResultSet rs;
		try {
				statement = con.createStatement();
		        String queryString;
		        queryString = "SELECT * FROM users";
		        if ((qParams == null) || qParams.isEmpty());	// no filter required -> do nothing
		        else
		        {
			        Iterator<String> key   = qParams.keySet().iterator();
		        	queryString += " WHERE ";
		            while(key.hasNext()){
		                String aKey   = key.next();
		                String  aValue = qParams.get(aKey);
		                queryString +=  aKey + "='" + aValue + "'";
		                queryString += key.hasNext()? " and ": "";
		              }
		        }
		        if (strOrderField != "")
		        	queryString += " order by " + strOrderField + " DESC";
				rs = statement.executeQuery(queryString);
				List<User> result = new ArrayList<User>();
				while(rs.next()) {
					User u = new User();
					u.setNickName(rs.getString(rs.findColumn("nickname")));
					u.setPassword(rs.getString(rs.findColumn("password")));
					u.setLastDateEntered();
					u.setmID(rs.getInt(rs.findColumn("id")));
					result.add(u);
					
				}
				return result;
			} catch (SQLException e) 
			{
				System.out.println("error in querying the DB");
				e.printStackTrace();
				return null;
			}
	}
	
	public List<String> getAllUsersNickNames()
	{
	    Statement statement;
        ResultSet rs;
		try {
				statement = con.createStatement();
		        String queryString;
		        queryString = "SELECT nickname FROM users";
		        queryString += " order by nickname DESC";
				rs = statement.executeQuery(queryString);
				List<String> users = new ArrayList<String>();
				while (rs.next())
					users.add(rs.getString(rs.findColumn("nickname")));
				return users;
			} catch (SQLException e) 
			{
				System.out.println("error in querying the DB");
				e.printStackTrace();
				return null;
			}
	}
	
	public boolean DeleteUser(String nickName)
	{
		try{
	        String delString = "DELETE FROM users  WHERE nickname=?";
			PreparedStatement statement = con.prepareStatement(delString);
			statement.setString(1, nickName);
            statement.executeUpdate();
            return true;
		} catch (Exception e) {
				System.out.println("error deleting the user");	
				return false;
			  }
	}
	
	public boolean updateDate(String nickName) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String date=dtf.format(now);
		String updateString="UPDATE users SET lastdateentered=? WHERE nickname=?";
		PreparedStatement statement;
		try {
			statement = con.prepareStatement(updateString);
			statement.setString(1, date);
			statement.setString(2, nickName);
	        statement.executeUpdate();
	        return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error updating date");
			return false;
		}
		
	}
	
	public void Close() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
