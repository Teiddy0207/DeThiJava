package PReview;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


public class XLSach  {
	private String url = "jdbc:sqlserver://TEDDY\\QUANGANH:1433;databaseName=OnTap1;encrypt=true;trustServerCertificate=true";
	private String userName = "sa";
	private String password = "123456";
	
	public Connection getCon()
	{
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,userName,password);
			
		}catch(SQLException e)
		{
			e.printStackTrace();
			
		}
		return conn;
	}
	
	public List<Sach> getAll()
	{
		List<Sach> listSA = new ArrayList<>();
		Connection conn = getCon();
		String query ="Select * from tbSach";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Sach sa = new Sach(
						rs.getString("MaS"),
						rs.getString("TenS"),
						rs.getInt("NamXB"),
						rs.getDouble("GiaB"),
						rs.getString("TacGia"),
						rs.getString("GioiTinh")
						);
						listSA.add(sa);
				
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return listSA;
	}
	
	public void insert(Sach sach)
	{
		Connection conn = getCon();
		String query = "insert into tbSach (MaS,TenS,NamXB,GiaB,TacGia,GioiTinh) values(?, ?, ?, ?,?,?) ";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, sach.getMaSach());
			ps.setString(2, sach.getTenSach());
			ps.setInt(3, sach.getNamXB());
			ps.setDouble(4, sach.getGiaB());
			ps.setString(5, sach.getTacGia());
			ps.setString(6, sach.getGioiTinh());
			
			ps.executeUpdate();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public boolean update(Sach sach) throws SQLException
	{
		Connection conn = getCon();
		String query = "update tbSach set  TenS = ?, NamXB = ?, GiaB = ?, TacGia = ? , GioiTinh = ? where MaS = ?";
	
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, sach.getTenSach());
			ps.setInt(2, sach.getNamXB());
			ps.setDouble(3, sach.getGiaB());
			ps.setString(4, sach.getTacGia());
			ps.setString(5, sach.getGioiTinh());
			ps.setString(6, sach.getMaSach());

			return ps.executeUpdate() > 0;
		}
	
	public boolean checkID(String MaS)
	{
		try {
			Connection conn =  getCon();
			String query = "select MaS from tbSach where MaS = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, MaS);
			ResultSet rs = ps.executeQuery();
			
			return rs.next();
			
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public void delete(String MaS) {
		Connection conn = getCon();
		String query = "delete from tbSach where MaS = ?  ";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, MaS);
			ps.executeUpdate();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	public Sach Search(String MaS)
	{
		Connection conn = getCon();
		String query = "select *  from tbSach where MaS = ? ";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, MaS);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				String MaSach = rs.getString("MaS");
				String TenS =rs.getString("TenS");
				int NamXB=  rs.getInt("NamXB");
				Double GiaB= rs.getDouble("GiaB");
				String TacGia= rs.getString("TacGia");
				String GioiTinh = rs.getString("GioiTinh");
				return new Sach(MaSach,TenS,NamXB,GiaB,TacGia,GioiTinh);
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}



	public List<Sach> getSachByNXBGB(int NamXB,double GiaB )
	{
		List<Sach> listSA = new ArrayList<>();
		Connection conn = getCon();
		String query ="Select * from tbSach WHERE NamXB = ? AND GiaB <= ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, NamXB);
			ps.setDouble(2, GiaB);
			ResultSet rs = ps.executeQuery();
		
			while(rs.next())
			{
				Sach sa = new Sach(
						rs.getString("MaS"),
						rs.getString("TenS"),
						rs.getInt("NamXB"),
						rs.getDouble("GiaB"),
						rs.getString("TacGia"),
						rs.getString("GioiTinh")
						);
						listSA.add(sa);
				
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return listSA;
	}

	public List<Sach> getSachByGT ( int NamXB,String Gioitinh)
	{
		List<Sach> ListSA = new ArrayList<>();
		Connection conn = getCon();
		String query ="Select * from tbSach WHERE NamXB = ? AND GioiTinh <= ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, NamXB);
			ps.setString(2, Gioitinh);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Sach sa = new Sach(
						rs.getString("MaS"),
						rs.getString("TenS"),
						rs.getInt("NamXB"),
						rs.getDouble("GiaB"),
						rs.getString("TacGia"),
						rs.getString("GioiTinh")
						);
				ListSA.add(sa);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return ListSA;
		}
	}


