package Lunch_Part1;

class Tv{
	
	public Tv() {}
	
	public Tv(String name, int price, String description) {
		super();
		this.price = price;
		this.description = description;
		this.name = name;
	}
	
	private int price;
	private String description;
	private String name;
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name + " " + price +" " + description; 
	}
}

public class Lunch_P7 {

	public static void main(String[] args) {
		
		Tv toArray []  = new Tv	[3];
		toArray[0] = new Tv("INFINIA", 1500000, "LED TV");
		toArray[1] = new Tv("XCANVAS", 1000000, "LCD TV");
		toArray[2] = new Tv("CINEMA", 2000000, "3D TV");
		

		for(Tv t: toArray) {
			System.out.println(t);
			}
		System.out.println("가격의 합 : " + (toArray[0].getPrice()+toArray[1].getPrice()+toArray[2].getPrice()));
		}
	}
