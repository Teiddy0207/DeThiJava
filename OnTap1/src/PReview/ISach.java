package PReview;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public interface ISach {
	public Connection getCon();
	void insert(Sach sach);
	void update(Sach sach);
	void Search(String MaS);
	List<Sach> getAll();
	
}
