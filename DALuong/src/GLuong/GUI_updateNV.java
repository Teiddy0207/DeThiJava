package GLuong;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class GUI_updateNV extends JFrame {
	private JTextField txtID,txtName,txtLuong;
	private JComboBox cmbDiaChi;
	private DefaultTableModel tableModel;
	private JTable gvTable;
	private JButton btnAdd, btnRemove ,btnUpdate;
	public GUI_updateNV()
	{
		setTitle("Quan ly giang vien");
		setLayout(new BorderLayout());
		
	
		
		
		txtID = new JTextField();
		txtName = new JTextField();
		cmbDiaChi = new JComboBox<> (new String[] {"Ha noi", "Hai Phong", "Nam Dinh"});
		txtLuong = new JTextField();
		
		JPanel inputPannel = new JPanel(new GridLayout(6,2));
		inputPannel.add(new JLabel("Ma Nhan vien: "));
		inputPannel.add(txtID);
		inputPannel.add(new JLabel("Ho va ten: "));
		inputPannel.add(txtName);
		inputPannel.add(new JLabel("Dia chi: "));
		inputPannel.add(cmbDiaChi);
		inputPannel.add(new JLabel("Luong: "));
		inputPannel.add(txtLuong);
		
		
		String[] columnNames = {"MaNV", "Hoten", "Diachi","Luong"};
		tableModel = new DefaultTableModel (columnNames,0);
		gvTable = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(gvTable);
		
		
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
		btnAdd= new JButton("Update GV");
		btnRemove = new JButton("Search GV");

		buttonPanel.add(btnAdd);
		buttonPanel.add(btnRemove);

		
		add(inputPannel,BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		setSize(700,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		loadData();
		
		btnAdd.addActionListener(e -> updateNV());
		btnRemove.addActionListener(e -> searchNV());
		gvTable.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event)
			{
				if(!event.getValueIsAdjusting())
				{
					selectedRowData();	
				}
			}
		});
		
	}
	
	
	private void loadData()
	{
		try {
		XLLuong xl = new XLLuong();
		List<Nhanvien> nvs = xl.getNVbyMa(null);
		
		tableModel.setRowCount(0);
		for(Nhanvien nv:  nvs)
		{
			Object[] rowData = {
					nv.getId(),
					nv.getName(),
					nv.getDiaChi(),
					nv.getLuong()
			};
			tableModel.addRow(rowData);
		}
		}	catch(SQLException e)
		{
			e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Error loading data" + e.getErrorCode(), "Error", JOptionPane.ERROR_MESSAGE);

			}
		}
		
	private void selectedRowData ()
	{
		int selectedRow = gvTable.getSelectedRow();
		if(selectedRow != -1)
		{
			String MaDD = (String) tableModel.getValueAt(selectedRow, 0);
			String HoTen = (String) tableModel.getValueAt(selectedRow, 1);
			String Diachi = (String) tableModel.getValueAt(selectedRow, 2);
			float Luong = (float) tableModel.getValueAt(selectedRow, 3);
		
			
			txtID.setText(MaDD);
			txtName.setText(HoTen);
			cmbDiaChi.setSelectedItem(Diachi);
			txtLuong.setText(String.valueOf(Luong));
	
			
			
			
		}
	}

	private void updateNV()
	{
		int selectedRow = gvTable.getSelectedRow();
		if(selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Cho biet dong co sach de cap nhat");
			return;
		}
		try {
			String originalMaDD =(String) tableModel.getValueAt(selectedRow, 0);
			String MaDD = txtID.getText();
			String HoTen = txtName.getText();
			String Diachi = (String) cmbDiaChi.getSelectedItem();
			int Luong = Integer.parseInt(txtLuong.getText());
			
			Nhanvien nv = new Nhanvien(MaDD,HoTen, Diachi, Luong);
			XLLuong xl = new XLLuong();
			
			if (!originalMaDD.equals(MaDD) && xl.checkID(MaDD)) {
	            JOptionPane.showMessageDialog(this, "ID mới đã tồn tại. Không thể thay đổi thành ID này.");
	            return;
	        }
	     
			
			if(!xl.checkID(MaDD))
			{
				JOptionPane.showMessageDialog(this, "Mã giảng viên không tồn tại trong cơ sở dữ liệu.");
	            return;
			}
			
			boolean check = xl.update(nv);
			//gv.update(MaDD, HoTen, gt, DonVi, SoTC);
			if(check )
			{
				
				JOptionPane.showMessageDialog(this, "Cap nhat thanh cong");
				tableModel.setValueAt(HoTen, selectedRow , 1 );
				tableModel.setValueAt(Diachi, selectedRow, 2);
				tableModel.setValueAt(Luong, selectedRow, 3);

			}else {
				JOptionPane.showMessageDialog(this, "Cap nhat that bai");
				
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error updating GV: " + e.getMessage());
		}catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Luong must be a number.");
        }
	}
	
	private void searchNV()
	{
		String MaNV = txtID.getText();
		if (MaNV.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên để tìm kiếm.");
	        return;
	    }
		XLLuong xl = new XLLuong();
		Nhanvien nv = xl.search(MaNV);
		if (nv != null) {
	        tableModel.setRowCount(0); // Xóa dữ liệu cũ trong bảng
	        tableModel.addRow(new Object[]{nv.getId(), nv.getName(), nv.getDiaChi(), nv.getLuong()});
	    } else {
	        JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên với mã: " + MaNV);
	    }
	}


	public static void main(String[] args) {
		
		 new GUI_updateNV();
	}
	

}
