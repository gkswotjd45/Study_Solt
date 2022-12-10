package planetest;

public class Cargoplane extends Plane {
	
	public Cargoplane() {
		super();
	}
	public Cargoplane(String planeName, int fuelSize) {
		super(planeName, fuelSize);
	}
	
	@Override
	public void filght(int distance) {
	
			setFulSize(getFulSize() - (distance * 5)); 
			
			//getFulSize를 통해 연료 값을 가져오고 , 거리에 비례만큼  연료 감소 값을 setFulsize 으로 반환 수행
	}

	
}
