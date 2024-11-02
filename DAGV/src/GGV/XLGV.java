package GGV;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class XLGV {
	private String url = "jdbc:sqlserver://TEDDY\\QUANGANH:1433;databaseName=DLGV;encrypt=true;trustServerCertificate=true";
	private String userName = "sa";
	private String password = "123456";
	
	public Connection getCon()
	{
		Connection conn = null;
		
		try {
			
			conn = DriverManager.getConnection(url,userName, password);
			
		}catch(SQLException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ket noi that bại");

		}
		return conn;
	}
	
	public List<Giangvien> getGV (String Donvi, Integer  SoCt) throws SQLException
	{
		
		List<Giangvien> gv = new ArrayList<>();
		Connection conn = getCon();
		String query;
		
	
	    if (Donvi != null && SoCt != null) {
	        query = "SELECT * FROM tbGiangvien WHERE Donvi = ? AND Soct = ?";
	    } else if (Donvi != null) {
	        query = "SELECT * FROM tbGiangvien WHERE Donvi = ?";
	    } else if (SoCt != null) {
	        query = "SELECT * FROM tbGiangvien WHERE Soct = ?";
	    } else {
	        query = "SELECT * FROM tbGiangvien";
	    }
		
		PreparedStatement ps = conn.prepareStatement(query);
		if (Donvi != null && SoCt != null) {
	        ps.setString(1, Donvi);
	        ps.setInt(2, SoCt);
	    } else if (Donvi != null) {
	        ps.setString(1, Donvi);
	    } else if (SoCt != null) {
	        ps.setInt(1, SoCt);
	    }
		
		ResultSet  rs = ps.executeQuery();
		
		while(rs.next())
		{
			Giangvien giangvien = new Giangvien
					(rs.getString("MaDD"),rs.getString("Hoten"),rs.getString("GioiTinh"),
						rs.getString("Donvi"), rs.getInt("SoCt")	
							);
			gv.add(giangvien);
		}
		
		return gv;
		
	}

	public void insertGV (Giangvien gv) throws SQLException 
	{
		Connection conn = getCon();
		String query = "Insert into tbGiangvien (MaDD,Hoten,GioiTinh, Donvi ,SoCt) values (?, ?, ?, ?, ?) ";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, gv.getMaDD());
		ps.setString(2, gv.getHoTen());
		ps.setString(3, gv.getGioiTinh());
	    ps.setString(4, gv.getDonVi());
	    ps.setInt(5, gv.getSoCt());
	    ps.executeUpdate();
	    conn.close(); 
	}

	public void deleteGV (String MaDD) throws SQLException
	{
		
			Connection conn = getCon();
		    String query = "DELETE FROM tbGiangvien WHERE MaDD = ?";
		    PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, MaDD);
			ps.executeUpdate(); // Thực thi lệnh xóa

			
			 conn.close();
		
		
	}
	
	public void update(String MaDD, String Hoten, String GioiTinh,String Donvi, int SoCt) throws SQLException
	{
		Connection conn = getCon();
		String query = "Update tbGiangvien set Hoten = ? , GioiTinh = ?, Donvi = ?, SoCt = ? WHERE MaDD = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, Hoten);
		 ps.setString(2, GioiTinh);
		 ps.setString(3, Donvi);
		 ps.setInt(4, SoCt);
		ps.setString(5, MaDD);
		ps.executeUpdate();
}
	
}