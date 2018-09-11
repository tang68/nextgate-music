package ng.music.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ng.music.data.MusicData;;
public class MusicDAO {

	private String searchKey;
	private Connection conn;
	
	public MusicDAO(String searchKey) {
		this.searchKey = searchKey;
		conn = DataLoader.conn;
	}
	
	 
	public List<MusicData> getSearchResults() {
		List<MusicData> result = new ArrayList<>();
		Statement stmt;
		String query;

		try {
			stmt = conn.createStatement();			
			query = ""
					+ "select 	ng_albums.album_name, "
					+ "		ng_albums.release_year, "
					+ "		ng_albums.record_company, "
					+ "		ng_singers.name, "
					+ "		ng_singers.dob, "
					+ "		ng_singers.sex "
					+ "from ng_singers, ng_albums "
					+ "where (ng_albums.ng_singers_id = ng_singers.ng_singers_id) and "
					+ "		(ng_singers.name LIKE ('%" + searchKey + "%') or "
					+ "		 ng_albums.album_name LIKE ('%" + searchKey + "%'));";
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				MusicData md = new MusicData();
				md.setSinger(rs.getString("name"));
				md.setDob(rs.getString("dob"));
				md.setSex(rs.getString("sex"));
				md.setAlbum(rs.getString("album_name"));
				md.setYear(rs.getString("release_year"));
				md.setCompany(rs.getString("record_company"));
				result.add(md);
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
			stmt = null;
		}
		
		return result;
	}
	
	
}
