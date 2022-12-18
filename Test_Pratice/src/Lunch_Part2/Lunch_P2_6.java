package Lunch_Part2;

import java.io.DataInputStream;

abstract class  Plane{
	
	public Plane() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Plane(String planeName, int fuelSize) {
		super();
		this.planeName = planeName;
		this.fuelSize = fuelSize;
	}
	
	private String planeName;
	private int fuelSize;
	
	public String getPlaneName() {
		return planeName;
	}
	public void setPlaneName(String planeName) {
		this.planeName = planeName;
	}
	public int getFuelSize() {
		return fuelSize;
	}
	public void setFuelSize(int fuelSize) {
		this.fuelSize = fuelSize;
	}
	
	public void refuel(int fuel) {
		setFuelSize(getFuelSize()+fuel);
	}
	public abstract void flight(int distance);
	
	@Override
	public String toString() {
		return planeName + " " + fuelSize;
	}
	
}
class Airplane extends Plane {

	public Airplane() {
		
	}
	
	public Airplane(String planeName, int fuelSize) {
		super(planeName, fuelSize);
	}


	@Override
	public void flight(int distance) {
		
		setFuelSize(getFuelSize()-(distance*3));
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
	
}

class Cargoplane extends Plane{
	public Cargoplane() {
		// TODO Auto-generated constructor stub
	}

	public Cargoplane(String planeName, int fuelSize) {
		super(planeName, fuelSize);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void flight(int distance) {
		setFuelSize(getFuelSize() - (distance*5));
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
}

public class Lunch_P2_6 {
	public static void main(String[] args) {
		
		Plane p [] = new Plane[2];
		
		p[0] = new Airplane("L747", 1000);
		p[1] = new Cargoplane("C40",1000);
		
		for(Plane c: p) {
			System.out.println(c);
		}
		p[0].flight(100);
		p[1].flight(100);
		System.out.println("------------");
		for(Plane c: p) {
			System.out.println(c);
		}
		
		p[0].refuel(200);
		p[1].refuel(200);
		
		System.out.println("------------");
		for(Plane c: p) {
			System.out.println(c);
		}
		
	}
}
