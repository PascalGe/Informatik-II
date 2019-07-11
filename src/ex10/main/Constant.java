package ex10.main;

import java.util.Map;

public class Constant implements ComputationalNode {

	private double constant;

	public Constant(double constant) {
		this.constant = constant;
	}

	@Override
	public double evaluate(String unknowns) {
		return constant;
	}

	@Override
	public double evaluate(Map<String, Double> unknowns) {
		return constant;
	}

	@Override
	public ComputationalNode derivative(String unknown) {
		return new Constant(0);
	}

	@Override
	public ComputationalNode cleanUp() {
		return new Constant(constant);
	}

	@Override
	public boolean isZero() {
		return constant == 0;
	}

	@Override
	public boolean isOne() {
		return constant == 1;
	}

	@Override
	public String toString() {
		return Double.toString(constant);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(constant);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Constant other = (Constant) obj;
		if (Double.doubleToLongBits(constant) != Double.doubleToLongBits(other.constant))
			return false;
		return true;
	}
}