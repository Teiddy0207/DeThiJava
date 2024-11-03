package GLuong;

public class Nhanvien extends Person {
	private String DiaChi;
	private float Luong ;
	
	public Nhanvien()
	{
		
	}
	public Nhanvien(String id, String name , String DiaChi, float Luong)
	{
		super(id, name);
		this.DiaChi = DiaChi;
		this.Luong = Luong;
	}
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	public float getLuong() {
		return Luong;
	}
	public void setLuong(float luong) {
		Luong = luong;
	}
}
