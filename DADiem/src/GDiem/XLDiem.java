package GDiem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public  class XLDiem implements IHocvien {

	private String url = "jdbc:sqlserver://TEDDY\\QUANGANH:1433;databaseName=DLDiem;encrypt=true;trustServerCertificate=true";
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
	
	public List<Hocvien> getHVbyLop(String Lop) throws SQLException
	{
		Connection conn = getCon();
		List<Hocvien> hocvien = new ArrayList<>();
		String query = "Select * from tbHocvien where Lop = ? ";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1,Lop );
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			Hocvien hv = new Hocvien(
					rs.getString("MaHV"),
					rs.getString("HoTen"),
					rs.getString("GioiTinh"),
					rs.getString("Lop"),
					rs.getDouble("DiemTongKet")
					);
			hocvien.add(hv);
		}
		return hocvien;
		
	}
	
	public List<Hocvien> getHVbyLopGT(String Lop, String gioiTinh) throws SQLException
	{
		Connection conn = getCon();
		List<Hocvien> hocvien = new ArrayList<>();
		String query = "Select * from tbHocvien where Lop = ? AND GioiTinh = ? ";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1,Lop );
		ps.setString(2,gioiTinh );
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			Hocvien hv = new Hocvien(
					rs.getString("MaHV"),
					rs.getString("HoTen"),
					rs.getString("GioiTinh"),
					rs.getString("Lop"),
					rs.getDouble("DiemTongKet")
					);
			hocvien.add(hv);
		}
		return hocvien;
		
	}
	
}
