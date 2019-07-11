package ex10.main;

import java.util.Map;

public class Multiply implements ComputationalNode {

	private ComputationalNode left, right;

	public Multiply(ComputationalNode left, ComputationalNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public double evaluate(String unknowns) {
		return left.evaluate(unknowns) * right.evaluate(unknowns);
	}

	@Override
	public double evaluate(Map<String, Double> unknowns) {
		return left.evaluate(unknowns) * right.evaluate(unknowns);
	}

	@Override
	public ComputationalNode derivative(String unknown) {
		return new Add(new Multiply(left.derivative(unknown), right), new Multiply(left, right.derivative(unknown)));
	}

	@Override
	public ComputationalNode cleanUp() {
		left = left.cleanUp();
		right = right.cleanUp();
		if (left.isOne()) {
			return right;
		}
		if (right.isOne()) {
			return left;
		}
		if (left.isZero() || right.isZero()) {
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
		return left + " * " + right;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
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
		Multiply other = (Multiply) obj;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		return true;
	}

}
