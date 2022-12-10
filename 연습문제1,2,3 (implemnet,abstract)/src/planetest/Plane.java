package planetest;

public abstract class Plane {
	
	public Plane() {
		
	}
	private String planeName; //getter 와 Setter는 private 사용시 명시 해줘야 함.
	private int fulSize;
	
	public Plane(String PlaneName, int fuel) {
		this.fulSize = fuel;
		this.planeName = PlaneName;
	}
	
	public String getPlaneName() {
		return planeName;
	}

	public void setPlaneName(String planeName) {
		this.planeName = planeName;
	}

	public int getFulSize() {
		return fulSize;
	}

	public void setFulSize(int fulSize) {
		this.fulSize = fulSize;
	}

	public void refuel(int fuel) {
		this.fulSize += fuel;
	}
	
	public abstract void filght(int distance);
	
	
	public String show() {
		return planeName + " " + fulSize; 
	}
}
