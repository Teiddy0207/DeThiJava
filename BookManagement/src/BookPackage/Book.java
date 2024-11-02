package BookPackage;

public class Book extends Document {
	private int year;
	private String author;
	private double price;
	public Book()
	{
		super();
		
	}
	
	public Book(String id, String name, int year, String author, double price)
	{
		super(id, name);
		this.year = year;
		this.author = author;
		this.price = price;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
