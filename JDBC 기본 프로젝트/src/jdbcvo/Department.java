package jdbcvo;

public class Department {

	public Department() {
		// TODO Auto-generated constructor stub
	}
	private String depart_name;
	
	private String depart_category;
	private int capacity;
	
	public Department( String depart_category, String depart_name,int capacity) {
		super();
		this.depart_name = depart_name;
		this.capacity = capacity;
		this.depart_category = depart_category;
	}

	public String getDepart_name() {
		return depart_name;
	}

	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}

	public int getDepart_capa() {
		return capacity;
	}

	public void setDepart_capa(int depart_capa) {
		this.capacity = depart_capa;
	}

	public String getDepart_category() {
		return depart_category;
	}

	public void setDepart_category(String depart_category) {
		this.depart_category = depart_category;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return depart_category +", "+ depart_name +", "+ capacity;
	}
	
}
