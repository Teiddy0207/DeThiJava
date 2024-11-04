package GSach;

public abstract class Tailieu {
	private String Mas;
	private String TenS;
	
    public Tailieu() {
    	
    }

  
    public Tailieu(String Mas, String TenS) {
        this.Mas = Mas;
        this.TenS = TenS;
    
    }
    
    
    
    public abstract double  Thanhtien(double GiaB , int namXB);

	public String getMas() {
		return Mas;
	}

	public void setMas(String mas) {
		Mas = mas;
	}

	public String getTenS() {
		return TenS;
	}

	public void setTenS(String tenS) {
		TenS = tenS;
	}

}
