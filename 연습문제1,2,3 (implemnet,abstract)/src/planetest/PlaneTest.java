package planetest;

public class PlaneTest {

	public static void main(String[] args) {
		
		Plane air = new Airplane("L747",1000);
		Plane car = new Cargoplane("C40",1000);
		
		System.out.println("plane fuelSize");
		System.out.println(air); // air 객체를 toString으로 재정의를 수행했기때문에 객체의 초기화 값을 출력.
		System.out.println(car);
		
		air.filght(100);
		car.filght(100);
		System.out.println(air);
		System.out.print(car);
		
		air.refuel(200);
		car.refuel(200);
		
		System.out.println(air);
		System.out.println(car);
		
	}

}
