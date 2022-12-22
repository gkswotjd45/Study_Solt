package CodeingTe;

public class gugudan {
	public static void main(String[] args) {
		
		System.out.println("  |1  2  3  4  5   6   7   8   9");
		for(int i = 1; i<=9; i++) {
			System.out.print(i+ " |");
			for(int j = 1 ; j<=9 ; j++) {
				System.out.print(i * j + "  ");
			}
			System.out.println();
		}
		System.out.println("-----------------------------------");
		System.out.println("  |1  2  3  4  5   6   7   8   9");
		for(int i = 1; i<=9; i++) {
			System.out.print(i+ " |");
			for(int j = 1 ; j<=9 ; j++) {
				System.out.print(i + j + "  ");
			}
			System.out.println();
		}
	}
}
