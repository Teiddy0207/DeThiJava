package GSach;

public class Sach {
    private String maS;
    private String tenS;
    private String nhaXB;
    private int namXB;
    private double giaB;

    // Constructor mặc định
    public Sach() {}

    // Constructor tường minh
    public Sach(String maS, String tenS, String nhaXB, int namXB, double giaB) {
        this.maS = maS;
        this.tenS = tenS;
        this.nhaXB = nhaXB;
        this.namXB = namXB;
        this.giaB = giaB;
    }

    // Getter và Setter
    public String getMaS() { return maS; }
    public void setMaS(String maS) { this.maS = maS; }
    
    public String getTenS() { return tenS; }
    public void setTenS(String tenS) { this.tenS = tenS; }
    
    public String getNhaXB() { return nhaXB; }
    public void setNhaXB(String nhaXB) { this.nhaXB = nhaXB; }
    
    public int getNamXB() { return namXB; }
    public void setNamXB(int namXB) { this.namXB = namXB; }
    
    public double getGiaB() { return giaB; }
    public void setGiaB(double giaB) { this.giaB = giaB; }

    
    public double khuyenMai() {
        if (namXB < 2019) {
            return giaB * 0.75; 
        }
        return giaB; 
    }
}
