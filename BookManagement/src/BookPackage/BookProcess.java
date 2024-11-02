package BookPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class BookProcess {
	private String url = "jdbc:sqlserver://TEDDY\\QUANGANH:1433;databaseName=BookDB;encrypt=true;trustServerCertificate=true";
	private String userName = "sa";
	private String password = "123456";
	

	public Connection getCon()
	{
		Connection conn = null;
		
		try
		{
			conn = DriverManager.getConnection(url,userName, password);
			JOptionPane.showMessageDialog(null, "Kết nối thành công");
			
		}catch(SQLException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ket noi that bại");

		}
		
		
		
		return conn;
	}
	
	public List<Book> getBookByID(String id) throws SQLException 
	{
		List<Book> books = new ArrayList<>();
		Connection conn = getCon();
		String query;
		
		if(id != null)
		{
			  query = "SELECT * FROM tbBook WHERE id = ?";
		}else {
	        query = "SELECT * FROM tbBook";
		}
		
		PreparedStatement ps = conn.prepareStatement(query);
		if(id != null)
		{
			ps.setString(1, id);
		}
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			Book book = new Book(
					rs.getString("ID"),
					rs.getString("Name"),
					rs.getInt("Year"),
					rs.getString("Author"),
					rs.getDouble("Price"));
			books.add(book);
		}
		
		if(books.isEmpty())
		{
	        System.out.println("Không tìm thấy sách với ID: " + id);

		}
	  return books;
		
	}
	
	
	public void insertBook(Book book) throws SQLException
	{
	Connection conn = getCon();
	String query = "INSERT INTO tbBook (ID, Name, Year, Author, Price) VALUES (?, ?, ?, ?, ?)";
	PreparedStatement ps = conn.prepareStatement(query);
	ps.setString(1, book.getId());
	ps.setString(2, book.getName());
	ps.setInt(3, book.getYear());
    ps.setString(4, book.getAuthor());
    ps.setDouble(5, book.getPrice());
    
    int rowAffected = ps.executeUpdate();
    
    if(rowAffected > 0)
    {
        System.out.println("Cập nhật sách thành công!");

    }else {
        System.out.println("Cập nhật sách thất bại.");

    }
	}
	
	public void updateBook(String ID, String name, int Year,String Author, double Price) throws SQLException
	{
		Connection conn= getCon();
		String query = "Update tbBook set name = ? , Year = ?, Author = ?, Price = ? WHERE ID = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, name);
		 ps.setInt(2, Year);
		 ps.setString(3, Author);
		 ps.setDouble(4, Price);
		ps.setString(5, ID);
		
		int rowsAffected = ps.executeUpdate();
		
		 if (rowsAffected > 0) {
		        System.out.println("Cập nhật sách thành công!");
		    } else {
		        System.out.println("Cập nhật sách thất bại.");
		    }
		
	}
	
	public void deleteBook(String ID) throws SQLException
	{
		Connection conn = getCon();
		String query = "DELETE from tbBook where id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setString(1, ID);
		
		int rowsAffected = ps.executeUpdate();
		 if (rowsAffected > 0) {
		        System.out.println("Xoa sách thành công!");
		    } else {
		        System.out.println("Xoa sách thất bại.");
		    }
	}
}
