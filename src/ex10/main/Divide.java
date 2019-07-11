package ex10.main;

import java.util.Map;

public class Divide implements ComputationalNode {

	private ComputationalNode upper, lower;

	public Divide(ComputationalNode upper, ComputationalNode lower) {
		this.upper = upper;
		this.lower = lower;
	}

	@Override
	public double evaluate(String unknowns) {
		return upper.evaluate(unknowns) / lower.evaluate(unknowns);
	}

	@Override
	public double evaluate(Map<String, Double> unknowns) {
		// TODO Auto-generated method stub
		return upper.evaluate(unknowns) / lower.evaluate(unknowns);
	}

	@Override
	public ComputationalNode derivative(String unknown) {
		return new Divide(new Subtract(new Multiply(upper.derivative(unknown), lower),
				new Multiply(upper, lower.derivative(unknown))), new Power(lower, 2));
	}

	@Override
	public ComputationalNode cleanUp() {
		upper = upper.cleanUp();
		lower = lower.cleanUp();
		// TODO Auto-generated method stub
		if (lower.isZero()) {
			// Exception
			throw new IllegalArgumentException();
		}
		if (upper.isZero()) {
			return new Constant(0);
		}
		return this;
	}

	@Override
	public boolean isZero() {
		return false;
	}

	@Override
	public boolean isOne() {
		return false;
	}

	@Override
	public String toString() {
		return "(" + upper + ")/(" + lower + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lower == null) ? 0 : lower.hashCode());
		result = prime * result + ((upper == null) ? 0 : upper.hashCode());
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
		Divide other = (Divide) obj;
		if (lower == null) {
			if (other.lower != null)
				return false;
		} else if (!lower.equals(other.lower))
			return false;
		if (upper == null) {
			if (other.upper != null)
				return false;
		} else if (!upper.equals(other.upper))
			return false;
		return true;
	}

}
