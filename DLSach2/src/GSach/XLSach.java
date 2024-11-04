package GSach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class XLSach {
		private String url = "jdbc:sqlserver://TEDDY\\QUANGANH:1433;databaseName=DLSach2;encrypt=true;trustServerCertificate=true";
		private String userName = "sa";
		private String password = "123456";
		
		public Connection getCon() 
		{
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(url, userName, password);
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			return conn;
		}
		
		public  List<Sach> getSA()
		{
		List<Sach> ListSA = new ArrayList<>();
		Connection conn = getCon();
		String query = "Select * from tbSach";
		
		
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				
			
			while(rs.next())
			{
				Sach sa = new Sach(
						rs.getString("MaS"),
						rs.getString("TenS"),
						rs.getInt("NamXB"),
						rs.getDouble("GiaB")
						);
				ListSA.add(sa);
			}
			
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		return ListSA;
		
			
		}

		public void delete(int NamXB)
		{
			Connection conn = getCon();
			String query = "delete from tbSach where NamXB = ?";
			
			
				try {
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setInt(1, NamXB);
					ps.executeUpdate();	
					conn.close();
		}catch(SQLException e)
				{
			e.printStackTrace();
			}
				}
		
		
		public void add(Sach sach) throws SQLException
		{
			Connection conn = getCon();
			String query = "Insert into tbSach(MaS, TenS,NamXB ,GiaB) values(?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, sach.getMas());
			ps.setString(2, sach.getTenS());
			ps.setInt(3, sach.getNamXB());
			ps.setDouble(4, sach.getGiaB());
			ps.executeUpdate();
			conn.close(); 
		}
		
		public Boolean update(Sach sach) throws SQLException
		{
			Connection conn = getCon();
			String query = "Update tbSach set TenS = ? ,NamXB = ? ,GiaB = ? where Mas = ? ";
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, sach.getTenS());
			ps.setInt(2, sach.getNamXB());
			ps.setDouble(3, sach.getGiaB());
			ps.setString(4, sach.getMas());
		return 	ps.executeUpdate() > 0;
			
		}
		
		public Boolean CheckID(String MaS)
		{
			try {
				Connection conn = getCon();
				String query = "select Mas from tbSach where Mas = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, MaS);
				ResultSet rs = ps.executeQuery();
				return rs.next();
				
				
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			return false;
		}
				
}
