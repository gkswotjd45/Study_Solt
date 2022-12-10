package com.Test4;

public class Rectangle extends Shape implements Movable {

	public Rectangle() {
		
	}
	public Rectangle(int width, int x, int y) {
		super(new Point(x,y)); // 상위클래스의 생성자로 접근해야함, 해당 클래스의 필드 값으로 접근할 수 있도록 함.
		this.width = width;
	}

	private int width;
	
	
	@Override
	public void move(int x, int y) {  //x,y 좌표 의 임의 값보다 더 이동.
		// x와 y의 좌표값을 변경하는 것.
		Point p = getPoint(); // Point 객체는 가져올려면 get와 set을 가져와야 하는데, x,y 값만 단독으로 갖고 올수 없음므로 point 객체를 갖고 온다음.
		p.setX(p.getX()+ x+1);//현재 p의 x좌표를 갖고와서 입력된  x보다 +1 만큼 더 이동.
		p.setY(p.getY()+y+1); //현재 y좌표를 갖고와서 입력된  y보다 +1 만큼 더 이동.
		setPoint(p);
	}

	@Override
	public double getArea() {  //사각형의 면적 길이 * 길이 
			return Math.round(Math.pow(width, 2));
	}

	@Override
	public double getCircumference() {  // 사각형의 둘레 한변의 길이 * 4
		return Math.round(4 * width);
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "   " + getWidth() + "   "+  
					getPoint().getX() + "   " + getPoint().getY() + "   " + 
					getArea() + "   "+ getCircumference() ; //  this.getClass().getSimpleName() 현재 인스턴스를 파생시킨 Class 정보의 이름을 기져오는 메서드.
		//pointer 객체 안에 x, y 좌표가 있음.
		
	}

}
