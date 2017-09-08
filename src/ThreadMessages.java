import java.util.Scanner;

public class ThreadMessages{// implements Runnable {
	
	Scanner sc;
	public ThreadMessages() {
		sc = new Scanner(System.in);
	}
	
	public String getMessage() {
		return sc.nextLine();
	}
	
	/*@Override
	public void run() {
		while(true) getMessage();
	}*/
}
