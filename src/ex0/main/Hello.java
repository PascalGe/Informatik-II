package ex0.main;

public class Hello {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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