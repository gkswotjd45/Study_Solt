package CodeingTe;
import java.util.Scanner;

public class square {
public static void main(String[] args) {
	
		int n;
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i = 0; i<n ; i++) {
			for(int j =0; j<n;j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
