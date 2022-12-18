package Lunch_Part2;

import java.util.HashMap;

import javax.swing.BoundedRangeModel;

abstract class Empolyee {
	
	public Empolyee() {
		
	}
	public Empolyee(String name, int number, String department, int salary) {
		super();
		this.name = name;
		this.number = number;
		this.department = department;
		this.salary = salary;
	}
	
	private String name;
	private int number;
	private String department;
	private int salary;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	abstract double tax();
	@Override
	public String toString() {
		return name + " " + number + " " + department + " " + salary;
	}
	
	
}

interface Bonus{
	public void incentive(int pay);
}

class Secretary extends Empolyee implements Bonus {
	public Secretary() {
		// TODO Auto-generated constructor stub
	}

	public Secretary(String name, int number, String department, int salary) {
		super(name, number, department, salary);
	}


	@Override
	double tax() {
		return getSalary() * 0.1;
	}

	@Override
	public void incentive(int pay) {
		setSalary((getSalary()+(int)(pay * 0.8)));
	}


}

class Sales extends Empolyee implements Bonus{


	public Sales() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sales(String name, int number, String deparment, int salary) {
		super(name, number, deparment, salary);
	}

	@Override
	public double tax() {
		return getSalary() * 0.13;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	public void incentive(int pay) {
		setSalary((getSalary()+(int)(pay * 1.2)));
		
	}
	
}

public class Lunch_P2_7 {
	public static void main(String[] args) {
		HashMap<Integer, Empolyee> map = new HashMap<Integer,Empolyee>();
		
		map.put(1,new Secretary("Hilery",1,"secretary",800)); 
		map.put(2,new Sales("Clinten",2,"sales",1200));
		
		for(Integer i :map.keySet()) {
			System.out.println(map.get(i));
		}
		
		for(Integer i :map.keySet()) {
			((Bonus)map.get(i)).incentive(100);
		}
		for(Integer i :map.keySet()) {
			System.out.println(map.get(i) + " " +map.get(i).tax());
		}
	}
}
