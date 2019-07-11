package ex10.main;

import java.util.Map;

public class Variable implements ComputationalNode {

	private String unknown;

	public Variable(String unknown) {
		this.unknown = unknown;
	}

	@Override
	public double evaluate(String unknowns) {
		return evaluate(ComputationalNodeHelper.parseUnknowns(unknowns));
	}

	@Override
	public double evaluate(Map<String, Double> unknowns) {
		if (unknowns.containsKey(unknown)) {
			return unknowns.get(unknown);
		}
		throw new IllegalArgumentException();
	}

	@Override
	public ComputationalNode derivative(String unknown) {
		if (this.unknown == unknown) {
			return new Constant(1);
		}
		return new Constant(0);
	}

	@Override
	public ComputationalNode cleanUp() {
		return new Variable(unknown);
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
		return unknown;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((unknown == null) ? 0 : unknown.hashCode());
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
		Variable other = (Variable) obj;
		if (unknown == null) {
			if (other.unknown != null)
				return false;
		} else if (!unknown.equals(other.unknown))
			return false;
		return true;
	}

}
