package GSach;

public  class Sach extends Tailieu {
	private int NamXB;
    private double GiaB;

    public Sach() {}

    public Sach(String Mas, String TenS, int NamXB, double GiaB) {
        super(Mas, TenS);
        this.NamXB = NamXB;
        this.GiaB = GiaB;
    }

    public int getNamXB() {
        return NamXB;
    }

    public void setNamXB(int namXB) {
        NamXB = namXB;
    }

    public double getGiaB() {
        return GiaB;
    }

    public void setGiaB(double giaB) {
        GiaB = giaB;
    }

	@Override
	public double Thanhtien(double GiaB, int namXB) {
		
		if(namXB < 2015) {
		return (GiaB * 0.85);
		}else {
			return (GiaB * 0.95);
		}
	}
}
