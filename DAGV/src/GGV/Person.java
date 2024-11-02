package GGV;

public abstract class Person {
	private String MaDD;
	private String HoTen;
	private String GioiTinh;
	
	public Person()
	{}
	public Person(String MaDD, String HoTen, String GioiTinh)
	{
		this.MaDD = MaDD;
		this.HoTen = HoTen;
		this.GioiTinh = GioiTinh;
	}
	public String getMaDD() {
		return MaDD;
	}
	public void setMaDD(String maDD) {
		MaDD = maDD;
	}
	public String getHoTen() {
		return HoTen;
	}
	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}
	public String getGioiTinh() {
		return GioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}
	
	public String  Xetthuong(int soct)
	{
		return (soct > 10) ? "Khen thuong" : "Khong khen thuong";
	}
	
}
