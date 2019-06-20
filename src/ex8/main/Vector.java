package ex8.main;

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class Vector {

	/**
	 * The vectors 2D coordinates
	 */
	public double x;
	public double y;

	/**
	 * Creates a new Vector
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Creates a new vector - based on the current vector - that is rotated
	 * counterclockwisely.
	 * 
	 * @param alpha - radians that the vector will be rotated by counterclockwisely.
	 * @return a vector rotated by alpha.
	 */
	public Vector rotated(double alpha) {
		return new Vector(x * Math.cos(alpha) + y * -Math.sin(alpha), x * Math.sin(alpha) + y * Math.cos(alpha));
	}

	/**
	 * Creates a new vector - based oh the current vector - that is scaled.
	 * 
	 * @param s - factor to scale the vector.
	 * @return a vector scaled by s.
	 */
	public Vector scaled(double s) {
		return new Vector(x * s, y * s);
	}

	/**
	 * Creates a new Vector that is the sum of both.
	 * 
	 * @param v - second vector.
	 * @return a vector as sum of both.
	 */
	public Vector added(Vector v) {
		return new Vector(this.x + v.x, this.y + v.y);
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	@Override
	protected Vector clone() {
		return new Vector(x, y);
	}
}