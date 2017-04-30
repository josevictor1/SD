
public class Message{
	String message = "aaaaaaaaaa";
	int len = message.length();
	int count;
	boolean use = false;
}



public class Node extends Exception implements Runnable {

	Node next;



	public void run() {
		try {
		   while (true) {

			   putMessage();
			   sleep(1000);
		   }
	   } catch (InterruptedException e) {
	   }
	}

	private synchronized  Message getMessage() throws InterruptedException{
		notify();
		while
	}

	private synchronized Message putMessage() throws InterruptedException{

	}


}
public class Main {
	public static void main(String args[]) {
		Thread t[] = new Thread[30];
		try{
				for(int i=0; i<30; i++) {
					t[i] = new Thread(new Node(b));
					t[i].start();
				}

				for(int j=0; j<30; j++) {
					t[j].join();
				}
		}catch(InterruptedException e) {
		}
	}

}
