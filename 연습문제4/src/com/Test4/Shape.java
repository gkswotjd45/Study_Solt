package com.Test4;

import java.security.ProtectionDomain;

public abstract class Shape {
	
	public Shape() {
		
	}
	public Shape(Point point) {
		this.point = point;
	}
	
	protected Point point;
	
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	public abstract double getArea();  //도형 면적
	
	public abstract double getCircumference(); //도형 둘레
	
}
