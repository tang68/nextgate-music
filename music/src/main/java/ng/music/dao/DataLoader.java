package ng.music.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataLoader {
	
	protected static final String url = "jdbc:mysql://localhost:3306/ng?autoReconnect=true&useSSL=false";
	protected static final String username = "ng_user";
	protected static final String password = "ngng";
	protected static final String driver = "com.mysql.jdbc.Driver";
	protected static Connection conn;
	
	public Connection connect() {
		
		
		try {
			Class.forName(driver).newInstance();

			conn = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			e.printStackTrace();
			conn = null;
		}

		return conn;
	}

	public Boolean dataExist(Connection conn, String tableName) {
		Statement stmt;
		String query;
		Boolean result = false;
		try {
			stmt = conn.createStatement();
			query = "SELECT COUNT(*) FROM " + tableName + ";";
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			result = rs.getInt(1) > 0;
			System.out.println("Query Executed. " + tableName + " has rows: " + rs.getInt(1));
		} catch (Exception e) {
			e.printStackTrace();
			stmt = null;
		}
		
		return result;
	}
	
	public void importAlbumsData(Connection conn, String filename) {
		Statement stmt;
		String query;

		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			query = "LOAD DATA INFILE '" + filename + 
					"' INTO TABLE ng_albums " +
					"FIELDS TERMINATED BY '|' " + 
					"LINES TERMINATED BY '\n' " +
					"IGNORE 1 LINES " + 
					"(@var_ng_singers_id, album_name, release_year, record_company) " + 
					"SET ng_singers_id = (SELECT ng_singers_id FROM ng_singers WHERE @var_ng_singers_id = name );";
			stmt.executeUpdate(query);

			
		} catch (Exception e) {
			e.printStackTrace();
			stmt = null;
		}
	}
	
	public void importSingersData(Connection conn, String filename) {
		Statement stmt;
		String query;

		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			query = "LOAD DATA INFILE '" + filename + 
					"' INTO TABLE ng_singers " +
					"FIELDS TERMINATED BY '|' " + 
					"LINES TERMINATED BY '\n' " +
					"IGNORE 1 LINES " + 
					"(name, @var_date, sex) " +
					"set dob = STR_TO_DATE(@var_date,'%Y%m%d');";
					
			
			stmt.executeUpdate(query);

		} catch (Exception e) {
			e.printStackTrace();
			stmt = null;
		}
	}
	
	public void importUserData(Connection conn, String filename) {

		Statement stmt;
		String query;

		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			query = "LOAD DATA INFILE '" + filename + 
					"' INTO TABLE ng_users " +
					"FIELDS TERMINATED BY '|' " + 
					"LINES TERMINATED BY '\n' " +
					"IGNORE 1 LINES " + 
					"(username, password);";
			stmt.executeUpdate(query);

		} catch (Exception e) {
			e.printStackTrace();
			stmt = null;
		}
	}
	
}







