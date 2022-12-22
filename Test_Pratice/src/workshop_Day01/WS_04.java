package workshop_Day01;

import java.util.Scanner;

public class WS_04 {
	public static void main(String[] args) {
		
		char ch ='z';
		boolean b = false ; 
		/*
		if(ch == 'z' || ch =='Z') b = true;
		System.out.println((b));
		*/
		System.out.println("임의의 문자를 입력하세요");
		Scanner Moonja = new Scanner(System.in);
		ch = Moonja.next().charAt(0);
		
		if((ch >= 65 && ch<=90) || (ch >=90 && ch<=122)) {
			b= true;
		}
		System.out.println(b);
	}
}
