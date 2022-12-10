package com.computer;

public interface Graphic {
	
	//field (public static final) - 어디에서나 접근할 수 있고, 상수형태로 값을 변경할 수 없는 형태.
	int a = 10; // 따로 명시하 않음 , 우조건 같이 생성됨.
	
	
	//method (public  abstract) - method public abstract 가 자동 명시. , 향후 추상클래스 상속시 오버라이딩 수행.
	public abstract double redering(int size);
	
	
	
	
}
