package ex0.main;

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class Hello {

	public static void main(String[] args) {
		System.out.println("Pascal Gepperth");
		System.out.println("6 + 2 = " + plus(6,2));
		
		int i = 5;
		double d = 100.25;
		
		System.out.println(i*d);
	}

	public static int plus(int a, int b) {
		return a+b;
	}
}