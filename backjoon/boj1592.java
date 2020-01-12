package backjoon;

import java.util.Scanner;

public class boj1592 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		String[] scan = s.split(" ");
		int N = Integer.parseInt(scan[0]);
		int M = Integer.parseInt(scan[1]);
		int L = Integer.parseInt(scan[2]);
		
		if(M == 1) {
			System.out.println(1);
		}
		else {
			int[] people = new int[N];
			for(int i = 0; i < N; i++) {
				people[i] = 0;
			}
			int ball = 0;
			people[ball] += 1;
			int how = 0;
			
			while(true) {
				if(people[ball] % 2 == 0) {
					ball -= L;
					if(ball < 0) {
						ball = (N + ball);
					}
				}else {
					ball += L;
					if(ball > (N - 1)) {
						ball = (ball - N);
					}
				}
				people[ball] += 1;
				how += 1;
				
				if(M == people[ball]) {
					System.out.println(how);
					break;
				}
			}
		}
	}

}
