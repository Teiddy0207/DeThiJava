package GDiem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IHocvien {
	Connection getCon();
	 List<Hocvien> getHVbyLop(String Lop) throws SQLException;
	 List<Hocvien> getHVbyLopGT(String Lop, String gioiTinh)  throws SQLException;
	
}
