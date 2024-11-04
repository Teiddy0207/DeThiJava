package PReview;

public class Sach extends Tailieu {
    private int namXB;
    private double giaB;
    private String tacGia;
    private String gioiTinh;

    public Sach() {}

    public Sach(String maSach, String tenSach, int namXB, double giaB, String tacGia, String gioiTinh) {
        super(maSach, tenSach);
        this.namXB = namXB;
        this.giaB = giaB;
        this.tacGia = tacGia;
        this.gioiTinh = gioiTinh;
    }

    public int getNamXB() {
        return namXB;
    }

    public void setNamXB(int namXB) {
        this.namXB = namXB;
    }

    public double getGiaB() {
        return giaB;
    }

    public void setGiaB(double giaB) {
        this.giaB = giaB;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @Override
    public double thanhtien(double giaB, int namXB) {
        if (namXB < 2015) {
            return giaB * 0.85;
        } else {
            return giaB * 0.95;
        }
    }
}
