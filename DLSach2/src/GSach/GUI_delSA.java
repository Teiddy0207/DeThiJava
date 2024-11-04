package GSach;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class GUI_delSA extends JFrame {
	 private JComboBox<String> cmbNamXB;
	    private JButton btnDelete;
	    private JTable table;
	    private DefaultTableModel tableModel;
		private JTextField txtMaS,txtName,txtGiaB;
	
	
	
	
	public GUI_delSA()
	{
		setTitle("Quan ly sach");
		setLayout(new BorderLayout());
		
		cmbNamXB = new JComboBox <> (new String[] {"2019", "2020", "2021"});
		btnDelete = new JButton("Xoa sach");
		
		tableModel = new DefaultTableModel(new String[] {"MaS", "TenS", "NamXB", "GiaB", "ThanhTien"},0);
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		
		JPanel controlPanel = new JPanel();
		controlPanel.add(new JLabel("Nam xb: "));
		controlPanel.add(cmbNamXB);
	    controlPanel.add(btnDelete);
	    add(controlPanel, BorderLayout.NORTH);
	    add(scrollPane, BorderLayout.CENTER);
	    
	    
	    setSize(600,400);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
        setVisible(true);
        load();
        btnDelete.addActionListener(e -> DeleteSach());
	}
	
	private void load()
	{
		XLSach xl = new XLSach();
		List<Sach> ListSA = xl.getSA();
		
			
			tableModel.setRowCount(0);
			for(Sach sa : ListSA)
			{
				Object [] rowData = {
						sa.getMas(),
						sa.getTenS(),
						sa.getNamXB(),
						sa.getGiaB(),
						sa.Thanhtien((double) sa.getGiaB(),sa.getNamXB())
					
						
				};
				tableModel.addRow(rowData);
 			}
		}
	
	private void DeleteSach()
	{
		int selectedYear = Integer.parseInt((String) cmbNamXB.getSelectedItem());
		XLSach xl = new XLSach();
		
		
		int cf = JOptionPane.showConfirmDialog(this, 
				"Bạn có chắc chắn muốn xóa tất cả sách xuất bản năm " + selectedYear + " không?", 
		        "Xác nhận xóa", 
		        JOptionPane.YES_NO_OPTION
				);
		if(cf == JOptionPane.YES_OPTION)
		{
			xl.delete(selectedYear);
			load();
		}
		
	}
	
	private void AddSach()
	{
		try {
			String MaS = txtMaS.getText();
			String HoTen = txtName.getText();
			int NamXB = (int) cmbNamXB.getSelectedItem();
			double GiaBan = Double.parseDouble(txtGiaB.getText());
			
			XLSach xl = new XLSach();
			xl.add(new Sach(MaS,HoTen,NamXB, GiaBan));
			tableModel.addRow(new Object[] {MaS,HoTen,NamXB, GiaBan} );
			
		}catch(SQLException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error + " + e.getMessage());
		}catch(NumberFormatException  e)
		{
			JOptionPane.showMessageDialog(this, "Price must be a number.");
		}
	}
	
	private void UpdateSach() throws SQLException
	{
	int selectedRow = table.getSelectedRow();
	
	if(selectedRow == -1)
	{
		JOptionPane.showMessageDialog(this, "vui long chon ban ghi can cap nhat");
		return;
	}
	String OriMaS = (String) tableModel.getValueAt(selectedRow, 0);
	String MaS = txtMaS.getText();
	String HoTen = txtName.getText();
	int NamXB = (int) cmbNamXB.getSelectedItem();
	double GiaBan = Double.parseDouble(txtGiaB.getText());
	
	Sach sa = new Sach(MaS,HoTen,NamXB,GiaBan);
	
	XLSach xl = new XLSach();
	if(!OriMaS.equals(MaS) && !xl.CheckID(MaS)) {
		JOptionPane.showMessageDialog(this, "ID mới đã tồn tại. Không thể thay đổi thành ID này.");
        return;
	}
	
	boolean check = xl.update(sa);
	
	if(check)
	{
		tableModel.setValueAt(HoTen, selectedRow, 1);
		tableModel.setValueAt(NamXB, selectedRow, 2);
		tableModel.setValueAt(GiaBan, selectedRow, 3);
		
	
	}else {
		JOptionPane.showMessageDialog(this, "Cap nhat that bai");
		
	}
	
	}
	
	private void selectedRowData()
	{
		int sl = table.getSelectedRow();
		if(sl != -1)
		{
			String MaS = (String) tableModel.getValueAt(sl, 0);
			String HoTen = (String) tableModel.getValueAt(sl, 1);
			int NamXB = (int) tableModel.getValueAt(sl, 2);
			double GiaBan = (double) tableModel.getValueAt(sl, 3);
			
			txtMaS.setText(MaS);
			txtName.setText(HoTen);
			cmbNamXB.setSelectedItem(NamXB);
			txtGiaB.setText(String.valueOf(GiaBan));
		
			
			
		}
	}
	public static void main(String[] args) {
	
	new	GUI_delSA();
	}

}
