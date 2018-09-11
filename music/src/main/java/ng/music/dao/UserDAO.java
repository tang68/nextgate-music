package ng.music.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import ng.music.dao.DataLoader;

public class UserDAO {

	private String username;
	private String pw;
	private Connection conn;
	
	public UserDAO(String name, String pw) {
		this.username = name;
		this.pw = pw;
		conn = DataLoader.conn;
	}
	
	public Boolean isAuthorizedUser() {
		Statement stmt;
		String query, rsUsername, rsPassword;
		Boolean result = false;
		try {
			stmt = conn.createStatement();			
			query = "SELECT username, password FROM ng_users WHERE username = '" + username + "';";
			ResultSet rs = stmt.executeQuery(query);
			rs.last();
			if (rs.getRow() == 0)
				return false;
			
			rs.first();
			rsUsername = rs.getString("username").trim();
			rsPassword = rs.getString("password").trim();

			if (!rsUsername.equals(username) || !rsPassword.equals(pw ))
				result = false;
			else 
				result = true;
			

		} catch (Exception e) {
			e.printStackTrace();
			stmt = null;
		}
		
		return result;
	}
}
