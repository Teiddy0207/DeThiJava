package GSach;

import java.sql.Connection;
import java.util.List;

public interface ISach {
    Connection getCon();
    List<Sach> getSA();
    List<Sach> getSAbyNXBGB(String nhaXB, double giaB);
}
