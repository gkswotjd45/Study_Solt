package com.Test4;

public class Circle extends Shape  implements Movable {
	
	public Circle() {
		
	}
	
	public Circle(int radius, int x, int y) {
		super (new Point(x,y)); // 상위 클래스인 shape에 생성자 Shape(Point point)로 접근할 수 있도록 수행.
		this.radius = radius;
	}
	
	private int radius;

	@Override
	public void move(int x, int y) {
		// x와 y의 좌표값을 변경하는 것.
				Point p = getPoint(); // Point 객체는 가져올려면 get와 set을 가져와야 하는데, x,y 값만 단독으로 갖고 올수 없음므로 point 객체를 갖고 온다음.
				p.setX(p.getX()+ x+2);//현재 p의 x좌표를 갖고와서 입력된  x보다 +2 만큼 더 이동.
				p.setY(p.getY()+y+2); //현재 y좌표를 갖고와서 입력된  y보다 +2 만큼 더 이동.
				setPoint(p); 
	}



	public void setRadius(int radius) { //좌표값 이동.
		this.radius = radius;
	}

	@Override
	public double getArea() {
		
		
		return Math.round(Math.PI * Math.pow(radius,2));  // 수학에서 사용하는 상수를 Math 클래스에서 이용/ pow = 제곱할 상수., math.round 반올림 수해
		// return (double)(radius * radius)* 3.141592;
	}

	@Override
	public double getCircumference() {
		return Math.round(2 * radius * Math.PI);
	}
	
	public int getRadius() {
		return radius;
	}
	

	@Override
	public String toString() {
		
		return this.getClass().getSimpleName() + "    " + getRadius() + "  "+  
		getPoint().getX() + "   " + getPoint().getY() + "   " + 
				getArea() + "   "+ getCircumference() ; //  this.getClass().getSimpleName() 현재 인스턴스를 파생시킨 Class 정보의 이름을 기져오는 메서드.
		//pointer 객체 안에 x, y 좌표가 있음.
		
		
	}

}
