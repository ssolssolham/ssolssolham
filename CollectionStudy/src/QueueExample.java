import java.util.LinkedList;
import java.util.Stack;

public class QueueExample {

	public static void main(String[] args) {
		LinkedList<String> queue = new LinkedList<>();
		queue.offer("AAAAA");
		queue.offer("BBBBB");
		queue.offer("CCCCC");
		queue.poll();
		queue.poll();
		System.out.println(queue.poll());
	}

}
