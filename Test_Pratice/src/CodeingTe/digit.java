package CodeingTe;

import java.util.Scanner;

public class digit {
	public static void main(String[] args) {
	 int n ;
	 Scanner digit = new Scanner(System.in);
	 
	 n = digit.nextInt();
	 
	 while(n>1) {
		 
		 if(n/1000>=10){
			 System.out.println("그외 5자리 이상의 수");
			 break;
		} 
		 else if(n /1000 >= 1) {
			 System.out.println("4자리 수");
			  break;
		 }else if(n/100>=1) {
			 System.out.println("3자리 수 ");
			 break;
		 }else if(n/10 >=1) {
			 System.out.println("2자리 수");
			 break;
		 }else if (n/1 >= 1) {
			 System.out.println("1자리 수");
			 break;
		 }
	 	}
	}
}
