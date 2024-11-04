package GDiem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class GUI_findHV extends JFrame{
	private JComboBox<String> cmbLop;
    private JRadioButton rbNam, rbNu;
    private JButton btnTimKiem;
    private JTable table;
    private DefaultTableModel tableModel;
 

    
    public GUI_findHV() {
        setTitle("Quản lý học viên");
        setLayout(new BorderLayout());

        cmbLop = new JComboBox<>(new String[]{"63PM1", "63PM2", "63TH1", "63TH2"});
        rbNam = new JRadioButton("Nam");
        rbNu = new JRadioButton("Nữ");
        
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbNam);
        genderGroup.add(rbNu);
        btnTimKiem = new JButton("Tìm kiếm");

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Lớp: "));
        inputPanel.add(cmbLop);
        inputPanel.add(new JLabel("Giới tính: "));
        inputPanel.add(rbNam);
        inputPanel.add(rbNu);
        inputPanel.add(btnTimKiem);

        tableModel = new DefaultTableModel(new String[]{"Mã học viên", "Họ tên", "Giới tính", "Lớp", "Điểm", "Kết quả"}, 0);
        table = new JTable(tableModel);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        
        btnTimKiem.addActionListener(e -> {
			try {
				load();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
}
    
    private void load() throws SQLException
    {

//    	String Lop = (String) cmbLop.getSelectedItem();
//    	String gioitinh = rbNam.isSelected() ? "Nam" : rbNu.isSelected() ? "Nu" : null;
//    	
//    	XLDiem xl = new XLDiem();
//    	List<Hocvien> ListHocVien = gioitinh == null ? xl.getHVbyLop(Lop) : xl.getHVbyLopGT(Lop, gioitinh);
//    	tableModel.setRowCount(0);
//    	
//    	for(Hocvien hv : ListHocVien)
//    	{
//    		Object[] rowData = {
//                    hv.getMaHV(), hv.getHoTen(), hv.getGioiTinh(), hv.getLop(), hv.getDiemTongKet(), hv.Ketqua()
//
//    		};
//    		tableModel.addRow(rowData);
//    	}
    	
    	String Lop = (String) cmbLop.getSelectedItem();
    	String gioitinh = rbNam.isSelected() ? "Nam" : rbNu.isSelected()  ? "Nu" : null;
    	XLDiem xl = new XLDiem();
    	List<Hocvien> ListHV = gioitinh == null ? xl.getHVbyLop(Lop) : xl.getHVbyLopGT(Lop, gioitinh);
    	tableModel.setRowCount(0);
    	for(Hocvien hv : ListHV)
    	{
    		Object[] rowData= {
    				  hv.getMaHV(), hv.getHoTen(), hv.getGioiTinh(), hv.getLop(), hv.getDiemTongKet(), hv.Ketqua()
    		};
    		tableModel.addRow(rowData);
    	}
    	
    }
    public static void main(String[] args) {
        new GUI_findHV();
    }
}
    
    
