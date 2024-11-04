package PReview;

public abstract class Tailieu {
    protected String maSach;
    protected String tenSach;

    public Tailieu() {}

    public Tailieu(String maSach, String tenSach) {
        this.maSach = maSach;
        this.tenSach = tenSach;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public abstract double thanhtien(double giaB, int namXB);
}
