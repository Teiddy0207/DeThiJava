package GSach;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class GUI_gindSA extends JFrame {

    private JComboBox<String> cmbNhaXB;
    private JTextField txtGiaB;
    private JButton btnSearch;
    private JTable table;
    private DefaultTableModel tableModel;

    public GUI_gindSA() {
        setTitle("Quản lý sách");
        setLayout(new BorderLayout());

        // Tạo các thành phần giao diện
        cmbNhaXB = new JComboBox<>(new String[]{"Kim Đồng", "Giáo dục", "Thanh niên"});
        txtGiaB = new JTextField();
        btnSearch = new JButton("Tìm kiếm");
        
        // Đặt kích thước cho trường giá bán
        txtGiaB.setPreferredSize(new Dimension(150, 30));
        
        // Tạo panel nhập liệu
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Nhà xuất bản: "));
        inputPanel.add(cmbNhaXB);
        inputPanel.add(new JLabel("Giá bán: "));
        inputPanel.add(txtGiaB);
        inputPanel.add(btnSearch);

        // Cấu hình bảng và model bảng
        tableModel = new DefaultTableModel(new String[]{"Mã sách", "Tên sách", "Nhà xuất bản", "Năm xuất bản", "Giá bán", "Khuyến mại"}, 0);
        table = new JTable(tableModel);

        // Thêm các thành phần vào frame
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        // Tải tất cả sách ban đầu
        loadAllBooks();

        // Gọi tìm kiếm khi có thay đổi trong JComboBox hoặc JTextField
        cmbNhaXB.addActionListener(e -> updateTable());
        txtGiaB.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { updateTable(); }
            public void removeUpdate(DocumentEvent e) { updateTable(); }
            public void changedUpdate(DocumentEvent e) { updateTable(); }
        });

        // Xử lý sự kiện khi nhấn nút "Tìm kiếm"
        btnSearch.addActionListener(e -> {
            try {
                searchBooks();
            } catch (SQLException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Phương thức tải tất cả sách vào bảng
    private void loadAllBooks() {
        try {
            XLSach xl = new XLSach();
            List<Sach> sachList = xl.getSA();
            tableModel.setRowCount(0);
            for (Sach sach : sachList) {
                tableModel.addRow(new Object[]{
                    sach.getMaS(),
                    sach.getTenS(),
                    sach.getNhaXB(),
                    sach.getNamXB(),
                    sach.getGiaB(),
                    sach.khuyenMai()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void updateTable() {
        try {
            searchBooks();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

   
    private void searchBooks() throws SQLException {
        String nhaXB = (String) cmbNhaXB.getSelectedItem();
        String giaBText = txtGiaB.getText();        
        double giaB;
            giaB = Double.parseDouble(giaBText);

        XLSach xl = new XLSach();
        List<Sach> sachList = xl.getSAbyNXBGB(nhaXB, giaB);
        tableModel.setRowCount(0);
        for (Sach sach : sachList) {
            tableModel.addRow(new Object[]{
                sach.getMaS(),
                sach.getTenS(),
                sach.getNhaXB(),
                sach.getNamXB(),
                sach.getGiaB(),
                sach.khuyenMai()
            });
        }

    }

    public static void main(String[] args) {
        new GUI_gindSA();
    }
}
