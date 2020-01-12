package backjoon;

import java.util.Scanner;

public class boj2161 {
	
	public class DequeNode{
		int data;
		DequeNode llink;
		DequeNode rlink;
		
		public DequeNode() {
			this.llink = null;
			this.rlink = null;
		}
		
		public DequeNode(int data) {
			this.data = data;
			this.llink = null;
			this.rlink = null;
		}
	}
	
	public class Deque{
		DequeNode front;
		DequeNode rear;
		
		public Deque() {
			front = null;
			rear = null;
		}
		
		public boolean isEmpty() {
			return (front==null);
		}
		
		public void insertFront(int item) {
			DequeNode node = new DequeNode(item);
			if(isEmpty()) {
				front = node;
				rear = node;
				node.rlink = null;
				node.llink = null;
			}else {
				node.rlink = front;
				node.llink = null;
				front.llink = node;
				front = node;
			}
		}
		
		public int deleteFront() {
			if(isEmpty()) {
				return 0;
			}else {
				int data = front.data;
				if(front.rlink == null) {
					front = null;
					rear = null;
				}else {
					front = front.rlink;
					front.llink = null;
				}
				return data;
			}
		}
		
		public void insertRear(int item) {
			DequeNode node = new DequeNode(item);
			if(isEmpty()) {
				front = node;
				rear = node;
				node.rlink = null;
				node.llink = null;
			}else {
				node.llink = rear;
				node.rlink = null;
				rear.rlink = node;
				rear = node;
			}
		}
		
		public int deleteRear() {
			if(isEmpty()) {
				return 0;
			}else {
				int data = rear.data;
				if(rear.llink == null) {
					front = null;
					rear = null;
				}else {
					rear = rear.llink;
					rear.rlink = null;
				}
				return data;
			}
		}
		
		public int peekRear() {
			if(isEmpty()) {
				return 0;
			}else {
				return rear.data;
			}
		}
		
		public int peekFront() {
			if(isEmpty()) {
				return 0;
			}else {
				return front.data;
			}
		}
		
		public void print() {
			if(isEmpty()) {
				
			}else {
				String str = "";
				DequeNode node = front;
				while(node != null) {
					str += node.data;
					str += " ";
					node = node.rlink;
				}
				System.out.println(str.substring(0, str.length() - 1));
			}
		}
	}
	
	public class ArrayStack{
		private int top;
		private int stackSize;
		private int stackArr[];
		
		public ArrayStack(int stackSize) {
			top = -1;
			this.stackSize = stackSize;
			stackArr = new int[this.stackSize];
		}
		
		public boolean isEmpty() {
			return (top == -1);
		}
		
		public boolean isFull() {
			return (top == this.stackSize-1);
		}
		
		public void push(int item) {
			if(isFull()) {
				
			}else {
				stackArr[++top] = item;
			}
		}
		
		public int pop() {
			if(isEmpty()) {
				return 0;
			}else {
				return stackArr[--top];
			}
		}
		
		public int peek() {
			if(isEmpty()) {
				return 0;
			}else {
				return stackArr[top];
			}
		}
		
		public void printStack() {
			if(isEmpty()) {
				System.out.println("Stack is Empty");
			}else {
				String str = "";
				for(int i = 0; i <= top; i++) {
					str += stackArr[i];
					str += " ";
				}
				System.out.println(str.substring(0, str.length() - 1));
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		int N = Integer.parseInt(s);

		boj2161 outer = new boj2161();
		boj2161.Deque Cards = outer.new Deque();
		boj2161.ArrayStack result = outer.new ArrayStack(N);
		
		for(int i = N; i > 0; i--) {
			Cards.insertFront(i);
		}
		
		while(true) {
			result.push(Cards.deleteFront());
			Cards.insertRear(Cards.deleteFront());
			int tmp = Cards.deleteRear();
			
			if(Cards.isEmpty()) {
				result.push(tmp);
				result.printStack();
				break;
			}else {
				Cards.insertRear(tmp);
			}
		}
		
	}

}
