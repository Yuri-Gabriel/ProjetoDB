package repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectionDB {
	private Connection conn;
	
	private String url = "jdbc:postgresql://localhost/projetodb";
	private String user = "postgres";
	private String password = "12345678";
	
	public ConnectionDB() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection startConnection() {
		try {
			this.setConn(DriverManager.getConnection(this.url, this.user, this.password));
			this.createTables();
			return this.getConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void createTables() {
		String path = System.getProperty("user.dir") + "/dump/dump.sql";
		String lineFile;
		String query = "";
		ArrayList<String> queryList = new ArrayList<>();
		
		try {
			BufferedReader textFile = new BufferedReader(new FileReader(path));
			while((lineFile = textFile.readLine()) != null) {
				
				query += lineFile.trim() + " ";
				
				if(lineFile.trim().length() == 0) {
					queryList.add(query);
					query = "";
					
				}
				
			}
			queryList.forEach((q) -> {
				
				try {
					Statement stm = conn.createStatement();
					stm.execute(q);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
			
		} catch (IOException err) {
			err.printStackTrace();
		}
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
}
