package GGV;

public  class Giangvien extends Person {
	 private String donVi;  // Đơn vị công tác
	 private int soCt;
	 
	 public Giangvien()
	 {}
	 public Giangvien(String MaDD, String HoTen, String GioiTinh, String donvi, int soCt)
	 {
		 super(MaDD,HoTen,GioiTinh);
		 this.donVi = donvi;
		 this.soCt = soCt;
	 }
	public String getDonVi() {
		return donVi;
	}
	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}
	public int getSoCt() {
		return soCt;
	}
	public void setSoCt(int soCt) {
		this.soCt = soCt;
	}
	public String xetThuong(int soCt)
	{
		return super.Xetthuong(soCt);
	}
}
