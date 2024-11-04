package PReview;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class GUI extends JFrame {
	 private JTextField txtMaSach, txtTenSach, txtGiaB, txtTacGia;
	 private JComboBox<Integer> cbNamXB;
	 private JRadioButton rbNam, rbNu;
	 private JButton btnThem, btnSua, btnXoa, btnTim,btnTimBangGioiTinh;
	 private JTable table;
	 private DefaultTableModel tableModel;
	public  GUI()
	{
		setTitle("Quan ly sach");
		setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		 txtMaSach = new JTextField(10);
	     txtTenSach = new JTextField(15);
	     txtGiaB = new JTextField(10);
	     txtTacGia = new JTextField(15);
	     cbNamXB = new JComboBox <> (new Integer[] {2019, 2020, 2021});
	     rbNam = new JRadioButton("Nam");
	     rbNu = new JRadioButton("Nu");
	     ButtonGroup group = new ButtonGroup();
	     group.add(rbNam);
	     group.add(rbNu);
	     
	     	JPanel inputPannel = new JPanel(new GridLayout(6,2));
			inputPannel.add(new JLabel("Ma Sach: "));
			inputPannel.add(txtMaSach);
			inputPannel.add(new JLabel("Ten Sach: "));
			inputPannel.add(txtTenSach);
			inputPannel.add(new JLabel("Gia ban: "));
			inputPannel.add(txtGiaB);
			inputPannel.add(new JLabel("Tac gia: "));
			inputPannel.add(txtTacGia);
			inputPannel.add(new JLabel("Nam SX: "));
			inputPannel.add(cbNamXB);
			inputPannel.add(new JLabel("Gioi tinh: "));
			JPanel gd = new JPanel(new FlowLayout(FlowLayout.LEFT));
			gd.add(rbNam);
			gd.add(rbNu);
			inputPannel.add(gd);
	     
			 JPanel buttonPanel = new JPanel(new FlowLayout());
		        btnThem = new JButton("Thêm");
		        btnSua = new JButton("Sửa");
		        btnXoa = new JButton("Xóa");
		        btnTim = new JButton("Tìm kiếm");
		        btnTimBangGioiTinh = new JButton("Tim bang gioi tinh va xuat ban");
		        buttonPanel.add(btnThem);
		        buttonPanel.add(btnSua);
		        buttonPanel.add(btnXoa);
		        buttonPanel.add(btnTim);
		        buttonPanel.add(btnTimBangGioiTinh);
		        
		        String[] columnNames = {"MaS", "TenS", "NamXB","GiaB", "TacGia", "GioiTinh", "Thanh tien"};
				tableModel = new DefaultTableModel (columnNames,0);
				table = new JTable(tableModel);
				JScrollPane scrollPane = new JScrollPane(table);

		        
		     // Panel chính chứa input và các nút (topPanel)
		        add(inputPannel,BorderLayout.NORTH);
		        add(scrollPane, BorderLayout.CENTER);
				add(buttonPanel, BorderLayout.SOUTH);
				setVisible(true);
				 load();
				 
				 btnThem.addActionListener(e ->  {
					try {
						addSach();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
				 btnSua.addActionListener(e -> {
					try {
						updateSach();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
				 
				 btnXoa.addActionListener(e -> {
					try {
						deleteSach();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
				 
				 
				// btnTimBangGioiTinh.addActionListener(e -> searchBookByGTNXB());
				 btnTim.addActionListener(e -> searchSach());
			//	 cbNamXB.addActionListener(e -> updateTable());
			  //   txtGiaB.addActionListener(e -> updateTable());
				 table.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent event)
						{
							if(!event.getValueIsAdjusting())
							{
								selectedRowData();	
							}
						}
					});
					
				}
	
	
	public void load()
	{
		XLSach xl = new XLSach();
		List<Sach> ListSA = xl.getAll();
		
		tableModel.setRowCount(0);
		for(Sach sa: ListSA)
		{
			Object[] rowData = {
					sa.getMaSach(),
					sa.getTenSach(),
					sa.getNamXB(),
					sa.getGiaB(),
					sa.getTacGia(),
					sa.getGioiTinh(),
					sa.thanhtien((Double)sa.getGiaB(), sa.getNamXB())
					
			};
			tableModel.addRow(rowData);
			
		}
	}
	public void addSach() throws SQLException
	{
		try {
			String MaS = txtMaSach.getText();
			String TenS = txtTenSach.getText();
			int NamXB =(int) cbNamXB.getSelectedItem();
			double GiaB = Double.parseDouble(txtGiaB.getText());
			String TacGia = txtTacGia.getText();
			String Gioitinh;
			
			if(rbNam.isSelected()) {
				Gioitinh = "Nam";
			}else {
				Gioitinh = "Nữ";
			}
			double ThanhTien;
			if(NamXB < 2018)
			{
				ThanhTien = GiaB*0.85;
			}else {
				ThanhTien = GiaB*0.95;
			}
				
			Sach sa = new Sach(MaS,TenS,NamXB,GiaB,TacGia,Gioitinh);
			XLSach xl = new XLSach();
			xl.insert(sa);
			tableModel.addRow(new Object[] {MaS,TenS,NamXB,GiaB,TacGia,Gioitinh,ThanhTien});
			
			
			
			
		}catch(NumberFormatException e) {
	        JOptionPane.showMessageDialog(this, "Price must be a number.");

		}
	
	}

	public void updateSach() throws SQLException
	{
		int selectedRow = table.getSelectedRow();
		
		if(selectedRow == -1)
		{
			JOptionPane.showConfirmDialog(this, "Vui long nhan vao hang de cap nhat");
		}
		String MaScu = (String) tableModel.getValueAt(selectedRow, 0);
		String MaS = txtMaSach.getText();
		String TenS = txtTenSach.getText();
		int NamXB =(int) cbNamXB.getSelectedItem();
		double GiaB = Double.parseDouble(txtGiaB.getText());
		String TacGia = txtTacGia.getText();
		String Gioitinh = rbNam.isSelected() ? "Nam" : "Nữ";
		double ThanhTien;
		if(NamXB < 2018)
		{
			ThanhTien = GiaB*0.85;
		}else {
			ThanhTien = GiaB*0.95;
		}
		
		Sach sa = new Sach(MaS,TenS,NamXB,GiaB,TacGia,Gioitinh);
		XLSach xl = new XLSach();
		boolean check1 = xl.checkID(MaS);
		
		if(!MaScu.equals(MaS) && ! check1) {
			JOptionPane.showMessageDialog(this, "ID đã tồn tại. Không thể thay đổi thành ID này.");
	        return;
		}
		boolean check2 = xl.update(sa);
		if(check2)
		{
			tableModel.setValueAt(TenS, selectedRow, 1);
			tableModel.setValueAt(NamXB, selectedRow, 2);
			tableModel.setValueAt(GiaB, selectedRow, 3);
			tableModel.setValueAt(TacGia, selectedRow, 4);
			 tableModel.setValueAt(Gioitinh, selectedRow, 5); 
			 tableModel.setValueAt(ThanhTien, selectedRow, 6);
		}else {
			JOptionPane.showMessageDialog(this, "Cap nhat that bai");
			
		}
	
	}
		
	private void selectedRowData ()
	{
		int selectedRow =table.getSelectedRow();
		if(selectedRow != -1)
		{
			String MaS = (String) tableModel.getValueAt(selectedRow, 0);
			String TenS = (String) tableModel.getValueAt(selectedRow, 1);
			int NamXB = (int) tableModel.getValueAt(selectedRow, 2);
			double GiaB = (double) tableModel.getValueAt(selectedRow, 3);
			String TacGia = (String) tableModel.getValueAt(selectedRow, 4);
	        String Gioitinh = (String) tableModel.getValueAt(selectedRow, 5);

			
			
			txtMaSach.setText(MaS);
			txtTenSach.setText(TenS);
			cbNamXB.setSelectedItem(NamXB);
			txtGiaB.setText(String.valueOf(GiaB));
			txtTacGia.setText(TacGia);
			
			if ("Nam".equals(Gioitinh)) {
	            rbNam.setSelected(true);
	        } else {
	            rbNu.setSelected(true);
	        }
		}
	}

	private void deleteSach() throws SQLException
	{
		int selectedRow = table.getSelectedRow();
		String MaS = (String) tableModel.getValueAt(selectedRow, 0);
		
		if(selectedRow == -1)
		{
			JOptionPane.showConfirmDialog(this, "Vui long nhan vao hang xoa");
			return;
		}
		
		XLSach xl = new XLSach();
		int cf = JOptionPane.showConfirmDialog(this, "Ban co chac muon xoa", "xac nhan xoa", JOptionPane.YES_NO_OPTION);
		if(cf == JOptionPane.YES_OPTION) {
		xl.delete(MaS);
		JOptionPane.showMessageDialog(this, "Xoa thanh cong");
		load();
		}
		
		
		
	}
	
	private void searchSach()
	
	{
		String MaS = txtMaSach.getText();
		if(MaS.isEmpty()) {
			JOptionPane.showConfirmDialog(this, "Khong thay sach");
		return;
		}
		XLSach xl = new XLSach();
		Sach sa = xl.Search(MaS);
		if (sa != null) {
		tableModel.setRowCount(0);
		tableModel.addRow(new Object[] {
				sa.getMaSach(),
				sa.getTenSach(),
				sa.getNamXB(),
				sa.getGiaB(),
				sa.getTacGia(),
				sa.getGioiTinh()
				
				
		});
		
		} else {
	        JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên với mã: " + MaS);
	    }
	}
	
	 private void searchBooksByNXBGB() throws SQLException {
	        int NamXB = (int) cbNamXB.getSelectedItem();
	        double giaB = Double.parseDouble(txtGiaB.getText());   
	       

	        XLSach xl = new XLSach();
	        List<Sach> sachList = xl.getSachByNXBGB(NamXB, giaB);
	        tableModel.setRowCount(0);
	        for (Sach sa : sachList) {
	            tableModel.addRow(new Object[]{
	            		sa.getMaSach(),
	    				sa.getTenSach(),
	    				sa.getNamXB(),
	    				sa.getGiaB(),
	    				sa.getTacGia(),
	    				sa.getGioiTinh()
	            });
	        }

	    }
	 private void updateTable() {
	        try {
	        	searchBooksByNXBGB();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	
	 private void searchBookByGTNXB()
	 {
	 int NamXB = (int) cbNamXB.getSelectedItem();
		 String Gioitinh = rbNam.isSelected() ? "Nam" : rbNu.isSelected() ? "Nu" : null;
		 
		 XLSach xl = new XLSach();
		 
		 List<Sach> ListSA = xl.getSachByGT(NamXB, Gioitinh);
		 tableModel.setRowCount(0);
		 for(Sach sa : ListSA) {
			 Object[] rowData= {
					 sa.getMaSach(),
						sa.getTenSach(),
						sa.getNamXB(),
						sa.getGiaB(),
						sa.getTacGia(),
						sa.getGioiTinh(),
						sa.thanhtien((Double)sa.getGiaB(), sa.getNamXB())   		};
   		tableModel.addRow(rowData);
		 }
		 
		 
		 
		 
		 
	 }
	 public static void main(String[] args) {
		
		new GUI();
	}

}
