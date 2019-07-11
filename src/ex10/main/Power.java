package ex10.main;

import java.util.Map;

public class Power implements ComputationalNode {

	private ComputationalNode base;
	private double exponent;

	public Power(ComputationalNode base, double exponent) {
		this.base = base;
		this.exponent = exponent;
	}

	@Override
	public double evaluate(String unknowns) {
		return Math.pow(base.evaluate(unknowns), exponent);
	}

	@Override
	public double evaluate(Map<String, Double> unknowns) {
		return Math.pow(base.evaluate(unknowns), exponent);
	}

	@Override
	public ComputationalNode derivative(String unknown) {
		return new Multiply(new Multiply(new Constant(exponent), new Power(base, exponent - 1)),
				base.derivative(unknown));
	}

	@Override
	public ComputationalNode cleanUp() {
		// TODO Auto-generated method stub
		base = base.cleanUp();
		if (base.isZero()) {
			return new Constant(0);
		}
		if (base.isOne()) {
			return new Constant(1);
		}
		if (exponent == 1) {
			return base;
		}
		if (exponent == 0) { // Unused?
			return new Constant(1);
		}
		return this;
	}

	@Override
	public boolean isZero() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOne() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return "(" + base + ")^" + exponent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((base == null) ? 0 : base.hashCode());
		long temp;
		temp = Double.doubleToLongBits(exponent);
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
		Power other = (Power) obj;
		if (base == null) {
			if (other.base != null)
				return false;
		} else if (!base.equals(other.base))
			return false;
		if (Double.doubleToLongBits(exponent) != Double.doubleToLongBits(other.exponent))
			return false;
		return true;
	}

}