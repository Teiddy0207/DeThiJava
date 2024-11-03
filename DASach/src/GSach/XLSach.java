package GSach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class XLSach {
	private String url = "jdbc:sqlserver://TEDDY\\QUANGANH:1433;databaseName=DLSach;encrypt=true;trustServerCertificate=true";
	private String userName = "sa";
	private String password = "123456";
	public  Connection getCon()
	{
		Connection conn = null;
		try {
		
		conn = DriverManager.getConnection(url,userName, password);

		}catch(SQLException e)
		{
			e.printStackTrace();
	

		}
		return conn;
	}
	
	public List<Sach> getSA() throws SQLException
	{
		List<Sach> sach = new ArrayList<>();
		Connection conn = getCon();
		String query = "Select * from tbSach ";
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			Sach sa = new Sach(
					rs.getString("MaS"),
					rs.getString("TenS"),
					rs.getString("NhaXB"),
					rs.getInt("NamXB"),
					rs.getInt("GiaB")
					);
			sach.add(sa);
		}
		return sach;
		
		
	}
	public List<Sach> getSAbyNXBGB(String NhaXB, Double GiaB) throws SQLException
	{
		
	
	
		List<Sach> sach = new ArrayList<>();
		Connection conn = getCon();
		
		String query = "SELECT * FROM tbSach WHERE NhaXB = ? AND GiaB <= ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, NhaXB);
		ps.setDouble(2, GiaB);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			Sach sa = new Sach(
					rs.getString("MaS"),
					rs.getString("TenS"),
					rs.getString("NhaXB"),
					rs.getInt("NamXB"),
					rs.getInt("GiaB")
					);
			sach.add(sa);
		}
		return sach;
	}
	
}
