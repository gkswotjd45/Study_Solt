package Lunch;

class Book {
	

	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Book(String category, String bookname, double bookprice, double bookDiscountRate) {
		super();
		this.category = category;
		this.bookname = bookname;
		this.bookprice = bookprice;
		this.bookDiscountRate = bookDiscountRate;
	}


	private String category;
	private String bookname;
	private double bookprice;
	private double bookDiscountRate;

	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getBookname() {
		return bookname;
	}


	public void setBookname(String bookname) {
		this.bookname = bookname;
	}


	public double getBookprice() {
		return bookprice;
	}


	public void setBookprice(double bookprice) {
		this.bookprice = bookprice;
	}


	public double getBookDiscountRate() {
		return bookDiscountRate;
	}


	public void setBookDiscountRate(double bookDiscountRate) {
		this.bookDiscountRate = bookDiscountRate;
	}


	public double getDiscountBookPrice () {
		
		return getBookprice()-(getBookprice() * (getBookDiscountRate()*0.01));
	}


	@Override
	public String toString() {

		return getCategory()+ " " + getBookname()+ " " + getBookprice()+" " + getBookDiscountRate();
	}
	
}

public class LunchEx_09 {
	
	public static void main(String[] args) {
		Book bookArray [] = new Book[5];
		bookArray[0] = new Book("IT","SQL Plus",50000,5.0);
		bookArray[1] = new  Book("IT","Java 2.0",40000,3.0);
		bookArray[2] = new  Book("IT","JSP Servlet",60000,6.0);
		bookArray[3] = new  Book("Nobel","davinicode",30000,10.0);
		bookArray[4] = new  Book("Nobel","cloven hoof",50000,15.0);
		
		for(Book b : bookArray) {
			System.out.println(b);
		}
		
		
		double Hapgye [] = new double [2];
		double Disc[] =  new double[2]; 
		int hap = 0;
		int discount = 0;
		
		for(Book C : bookArray) {
			if(C.getCategory() == "IT") {
				Hapgye[0] += C.getBookprice();
				Disc[0] += C.getDiscountBookPrice();
			}
			if(C.getCategory()== "Nobel") {
				Hapgye[1] += C.getBookprice();
				Disc[1] += C.getDiscountBookPrice();
			}
		}
		
		System.out.println("Nobel 카테고리의 책 가격 합 :" + Hapgye[1]);
		System.out.println("Nobel 카테고리의 할인된 책 가격 합 :" + Disc[1]);
		System.out.println("IT 카테고리의 책 가격 합 :" + Hapgye[0] );
		System.out.println("IT 카테고리의 할인된 책 가격 합 :" + Disc[0]);

	}
	
	
	
}
