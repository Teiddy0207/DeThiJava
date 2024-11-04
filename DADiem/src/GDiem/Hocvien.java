package GDiem;

public class Hocvien {
    private String maHV;
    private String hoTen;
    private String gioiTinh;
    private String lop;
    private double diemTongKet;


    public Hocvien() {}

  
    public Hocvien(String maHV, String hoTen, String gioiTinh, String lop, double diemTongKet) {
        this.maHV = maHV;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.lop = lop;
        this.diemTongKet = diemTongKet;
    }

    // Getters và Setters
    public String getMaHV() { return maHV; }
    public void setMaHV(String maHV) { this.maHV = maHV; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }

    public String getLop() { return lop; }
    public void setLop(String lop) { this.lop = lop; }

    public double getDiemTongKet() { return diemTongKet; }
    public void setDiemTongKet(double diemTongKet) { this.diemTongKet = diemTongKet; }

   
    public String Ketqua() {
        return diemTongKet >= 20 ? "Đỗ" : "Trượt";
    }
}
