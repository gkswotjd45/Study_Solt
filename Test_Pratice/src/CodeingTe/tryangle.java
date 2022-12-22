package CodeingTe;

public class tryangle {
	
	public tryangle() {
		
	}
	static void triangleLB(int n) {
		
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<=i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	
	}
	static void triangleLu(int n) {
		
		for(int i = 0; i<n; i++) {
			for(int j = n; j>i; j--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	static void triangleRu(int n) {
		
		for(int i = 0; i<n; i++) {
			for(int j = n; j>(5-i); j--) {
				System.out.print(" ");
			}
			for(int k = n; k>i; k--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	
	static void triangleRB(int n) {
		
		for(int i = 0; i<n; i++) {
			for(int j = 1; j<(n-i); j++) {
				System.out.print(" ");
			}
			for(int k = n; k>=(5-i) ; k--) {
				System.out.print("*");
			}
			System.out.println();
		}
	
	}
	static void spira(int n) {
		for(int i = 1; i<=n; i++) {
			for(int j=0; j<(n-i); j++) {
				System.out.print(" ");
			}
			for(int k=1; k<=(i-1)*2+1;k++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	static void nspira(int n) {
		for(int i = 1; i<=n; i++) {
			for(int j=0; j<(n-i); j++) {
				System.out.print(" ");
			}
			for(int k=1; k<=(i-1)*2+1;k++) {
				System.out.print(i);
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		tryangle tr = new tryangle();
		
		//tr.triangleLB(5);
		//tr.triangleLu(5);
		//tr.triangleRu(5);
		//tr.triangleRB(5);
		tr.nspira(4);
	}

}
