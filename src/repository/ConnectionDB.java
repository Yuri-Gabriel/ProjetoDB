package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			return this.getConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
}
