package backjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static class Queue{
		private int front;
		private int back;
		private int[] array;
		
		public Queue(int queueSize) {
			array = new int[queueSize];
			front = -1;
			back = -1;
		}
		
		public void push(int X) {
			if(back == array.length -1)
				throw new RuntimeException("큐가 다 찼습니다.");
			array[++back] = X;
			
		}
		
		/**
		 * public int pop() {
		            if(back == -1)
		                return -1;
		
		            int temp = array[++front];
		            array[front] = -1;
		
		            if(front == back) {
		                front = -1;
		                back = -1;
		            }
		
		            return temp;
		        }
		 */
		public int pop() {
			if(back == -1)
				return -1;
			
			int temp = array[++front];
			
			if(front == back) {
				front = -1;
				back = -1;
			}
			
			return temp;
		}
		
		public int empty() {
			if(size() == 0) {
				return 1;
			}else {
				return 0;
			}
		}
		
		public int size() {
			return back - front;
		}
		
		/**
		 * 소스상의 front의 경우 시작점이지 데이터를 가지고 있지 않음  
		 */
		public int front() {
			if(size() == 0) {
				return front;
			}else if(front == -1) {
				return array[0];
			}else {
				return array[front + 1];
			}
		}
		
		public int back() {
			if(size() == 0) {
				return -1;
			}else {
				return array[back];
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(bf.readLine());
		Queue queue = new Queue(N);
		String s = null;
		String[] scan = null;
		
		for(int i = 0; i < N; i++) {
			s = bf.readLine();
			scan = s.split(" ");
			
			if( scan[0].equals("push")) {
				queue.push(Integer.parseInt(scan[1]));
			}else if( scan[0].equals("pop")) {
				bw.write(queue.pop() + "\n");
			}else if(scan[0].equals("front")) {
				bw.write(queue.front() + "\n");
			}else if(scan[0].equals("size")) {
				bw.write(queue.size() + "\n");
			}else if(scan[0].equals("back")) {
				bw.write(queue.back() + "\n");
			}else if(scan[0].equals("empty")) {
				bw.write(queue.empty() + "\n");
			}
		}
		bw.flush();
		bw.close();
	}
}
