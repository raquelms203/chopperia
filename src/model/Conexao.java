package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Conexao {
	private String url;
	private String usr;
	private String pass;
	private String address;
	
	public Conexao() {
		url="jdbc:postgresql://127.0.0.1:59730/d64q4qcqv82sg"; 
		usr = "postgres";
	    pass = "36de7d63f79543eb1480eaa06286cde9eb4039f74150bed205cd2f72aad47775";
	}
	
	public Connection getConnection() {
		try {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection con = DriverManager.getConnection(url, usr, pass);
			return con;
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Problemas com a conexão\n" + e);
			return null;
		}
	}
	
	public boolean executeUpdate(String query) {
		try {
			Connection con = getConnection();
			PreparedStatement prep = con.prepareStatement(query);
			boolean res = prep.execute();
			con.close();
			return res;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			return false;
		}
	}
	
	public ResultSet executeQuery(String query) {
		try {
			Connection con = getConnection();
			PreparedStatement prep = con.prepareStatement(query);
			ResultSet rs = prep.executeQuery();
			// con.close;
			return rs;
		} catch (SQLException  e) {
			JOptionPane.showMessageDialog(null, e);
			return null;

		}
	}

}
