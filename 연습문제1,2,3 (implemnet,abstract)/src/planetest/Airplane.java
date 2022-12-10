package planetest;

public class Airplane extends Plane {
	
	public Airplane() {
		
	}
	
	public Airplane(String planeName, int fuelSize) {
		super(planeName,fuelSize);
	}
	
	@Override
	public void filght(int distance) {
		// 10 운항시 30 감소
		// 연료량은 운행거리  * 3 만큼 줄이면 됨.
		setFulSize(getFulSize() - distance  *3 ); // 현재 연료값을 가져오고 - 운항거리에 대한 * 3 값을 setter 값을 반환해주는 역할.
												 // 10km 운행 30 감소  20km 운행 60 감소
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
}
