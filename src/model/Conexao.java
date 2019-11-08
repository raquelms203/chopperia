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
		url="jdbc:postgresql://ec2-174-129-231-116.compute-1.amazonaws.com:5432/d64q4qcqv826sg";
		usr = "hvcvtjzeqcgzxe";
	    pass = "123";
	}
	
	public Connection getConnection() {
		try {			
			Class.forName("org.postgresql.Driver");		
			Connection con = DriverManager.getConnection(url, usr, pass);
			System.out.println(con);
			return con;
			
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Problemas com a conexão\n" + e);
			return null;
		}
	}
	
	public static boolean executarQuery(String query) {
		Conexao con = new Conexao();		
		Connection conn = con.getConnection();
		
		try {
			PreparedStatement prep = conn.prepareStatement(query);
			boolean res = prep.execute();
			prep.close();
			conn.close();
			
			return res;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
