package GLuong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class XLLuong {
	private String url = "jdbc:sqlserver://TEDDY\\QUANGANH:1433;databaseName=DLLuong;encrypt=true;trustServerCertificate=true";
	private String userName = "sa";
	private String password = "123456";
	
	public  Connection getCon()
	{
		Connection conn = null;
		try {
		
		conn = DriverManager.getConnection(url,userName, password);
		JOptionPane.showMessageDialog(null, "Ket noi thanh cong");
		}catch(SQLException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ket noi that bại");

		}
		return conn;
	}
	
	public List<Nhanvien> getNVbyMa(String ID) throws SQLException
	{
		List<Nhanvien> nv = new ArrayList<>();
		Connection conn = getCon();
		String query;
		if(ID != null) {
		query = "Select * from tbNhanvien where MaNV = ?";
		}else {
			query = "Select * from tbNhanvien";
		}
		
		
		PreparedStatement ps = conn.prepareStatement(query);
		if(ID != null) {
		 ps.setString(1, ID);
		}
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Nhanvien nhanvien = new Nhanvien(
					rs.getString("MaNV"),rs.getString("Hoten"),rs.getString("Diachi"),rs.getFloat("Luong")
					);
			nv.add(nhanvien);
			
		}
		return nv;
		
		
	}
	
	public boolean update(Nhanvien nv) throws SQLException 
	{
		Connection conn = getCon();
		String query = "Update tbNhanvien set Hoten = ? , Diachi = ?, Luong = ? WHERE MaNV = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, nv.getName());
		ps.setString(2, nv.getDiaChi());
		ps.setFloat(3, nv.getLuong());
		ps.setString(4, nv.getId());
		return	ps.executeUpdate() > 0; 
	}
	
	public boolean checkID(String ID)
	
	{
		try {
			Connection conn = getCon();
			String query = "select MaNV from tbNhanvien where MaNV = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, ID);
			ResultSet rs = ps.executeQuery();

			return rs.next();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}
	
	public Nhanvien search(String ID)
	{
		try {
		Connection conn = getCon();
		String query = "select * from tbNhanvien where MaNV = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, ID);
		ResultSet rs = ps.executeQuery();
		Nhanvien nv = new Nhanvien();
		  if (rs.next())
		{
			String maNV = rs.getString("MaNV");
            String hoTen = rs.getString("Hoten");
            String diaChi = rs.getString("Diachi");
            float luong = rs.getFloat("Luong");
            return new  Nhanvien(maNV, hoTen, diaChi, luong);
		}
	
		
	}catch (Exception ex) {
		System.out.println(ex.getMessage());
	}

		  return null;
	}
}
	
