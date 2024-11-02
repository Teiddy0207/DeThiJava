package GGV;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;

public class GUIGgv extends JFrame {
	private JTextField txtID,txtName,txtDonVi,txtSoCT;
	private JComboBox cmbGT;
	private DefaultTableModel tableModel;
	private JTable gvTable;
	private JButton btnAdd, btnRemove ,btnUpdate;
	public GUIGgv()
	{
		setTitle("Quan ly giang vien");
		setLayout(new BorderLayout());
		
	
		
		
		txtID = new JTextField();
		txtName = new JTextField();
		cmbGT = new JComboBox<> (new String[] {"nam", "nu"});
		txtDonVi = new JTextField();
		txtSoCT = new JTextField();
		
		JPanel inputPannel = new JPanel(new GridLayout(6,2));
		inputPannel.add(new JLabel("Ma Dinh Danh: "));
		inputPannel.add(txtID);
		inputPannel.add(new JLabel("Ho va ten: "));
		inputPannel.add(txtName);
		inputPannel.add(new JLabel("Gioi tinh: "));
		inputPannel.add(cmbGT);
		inputPannel.add(new JLabel("Don vi: "));
		inputPannel.add(txtDonVi);
		inputPannel.add(new JLabel("So Cong trinh: "));
		inputPannel.add(txtSoCT);
		
		String[] columnNames = {"MaDD", "Hoten", "GioiTinh","Donvi","SoCt"};
		tableModel = new DefaultTableModel (columnNames,0);
		gvTable = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(gvTable);
		
		
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
		btnAdd= new JButton("Add GV");
		btnRemove = new JButton("Delete GV");
		btnUpdate = new JButton("Update GV");
		buttonPanel.add(btnAdd);
		buttonPanel.add(btnRemove);
		buttonPanel.add(btnUpdate);
		
		
		add(inputPannel,BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		setSize(700,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		btnAdd.addActionListener(e -> addGV());
		btnRemove.addActionListener(e -> removeGV());
		btnUpdate.addActionListener(e -> updateGV());
		loadData();
		
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
			XLGV xlgv = new XLGV();
			List<Giangvien> gvs = xlgv.getGV(null, null);
			tableModel.setRowCount(0);
			for(Giangvien gv : gvs)
			{
				Object[] rowData = {
						gv.getMaDD(),
						gv.getHoTen(),
						gv.getGioiTinh(),
						gv.getDonVi(),
						gv.getSoCt()
						};
				tableModel.addRow(rowData);
				txtID.setText("");
			}

		}catch(SQLException e)
	{
		e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading data", "Error", JOptionPane.ERROR_MESSAGE);

	}
	}

	private void addGV ()
	{
		try {
			String MaDD = txtID.getText();
			String HoTen = txtName.getText();
			String gt = (String) cmbGT.getSelectedItem();
			String DonVi = txtDonVi.getText();
			int SoCT = Integer.parseInt(txtSoCT.getText());
			
			XLGV gv = new XLGV();
			gv.insertGV(new Giangvien (MaDD, HoTen, gt,DonVi,SoCT));
			
			tableModel.addRow(new Object [] {MaDD, HoTen, gt,DonVi,SoCT});
		}catch(SQLException e)
		{
			e.printStackTrace();
		JOptionPane.showMessageDialog(this, "Error + " + e.getMessage());
		
		}catch(NumberFormatException  e)
		{
			JOptionPane.showMessageDialog(this, "Price must be a number.");
		}
	}
	public static void main(String[] args) {
		
		 new GUIGgv();
	}
	
	
	private void removeGV() {
		int selectedRow = gvTable.getSelectedRow();
		if(selectedRow != -1)
		{
			String MaDD = tableModel.getValueAt(selectedRow, 0).toString();
			
			try {
				XLGV gv = new XLGV();
				gv.deleteGV(MaDD);
				JOptionPane.showMessageDialog(this, "Xoa thanh cong");
				loadData();
			}catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Lỗi khi xóa dữ liệu: " + e.getMessage());
	        }
		} else {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng để xóa.");
	    }
	}

	private void selectedRowData ()
	{
		int selectedRow = gvTable.getSelectedRow();
		if(selectedRow != -1)
		{
			String MaDD = (String) tableModel.getValueAt(selectedRow, 0);
			String HoTen = (String) tableModel.getValueAt(selectedRow, 1);
			String GioiTinh = (String) tableModel.getValueAt(selectedRow, 2);
			String DonVi = (String) tableModel.getValueAt(selectedRow, 3);
			int SoCT = (int) tableModel.getValueAt(selectedRow, 4);
			
			txtID.setText(MaDD);
			txtName.setText(HoTen);
			cmbGT.setSelectedItem(GioiTinh);
			txtDonVi.setText(DonVi);
			txtSoCT.setText(String.valueOf(SoCT));
			
			
			
		}
	}
	
	private void updateGV()
	{
		int selectedRow = gvTable.getSelectedRow();
		if(selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Cho biet dong co sach de cap nhat");
			return;
		}
		try {
			String MaDD = txtID.getText();
			String HoTen = txtName.getText();
			String gt = (String) cmbGT.getSelectedItem();
			String DonVi = txtDonVi.getText();
			int SoTC = Integer.parseInt(txtSoCT.getText());
			
			XLGV gv = new XLGV();
			gv.update(MaDD, HoTen, gt, DonVi, SoTC);
			
			tableModel.setValueAt(HoTen, selectedRow , 1 );
			tableModel.setValueAt(gt, selectedRow, 2);
			tableModel.setValueAt(DonVi, selectedRow, 3);
			tableModel.setValueAt(SoTC, selectedRow, 4);
			JOptionPane.showMessageDialog(this, "Giang Vien da cap nhat thanh cong");
			
		}catch(SQLException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error updating GV: " + e.getMessage());
		}catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "SoTC must be a number.");
        }
	}
}
