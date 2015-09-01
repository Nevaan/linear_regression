package treeRepresentation;

public class Data {

	/*
	 * Type:
	 * 10 - Add
	 * 11 - Substract
	 * 12 - Multiply
	 * 13 - Divide
	 *
	 * 20 - Constant
	 * 21 - Variable
	 *
	 */

	private long id;
	private int type;
	private int childAmount;
	private double constant;

	public Data(int type) {
		this.type = type;
	}

	public double getConstant() {
		return constant;
	}

	public void setConstant(double constant) {
		this.constant = constant;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getChildAmount() {
		return childAmount;
	}

	public void setChildAmount(int childAmount) {
		this.childAmount = childAmount;
	}

	@Override
	public String toString() {
		switch (type) {
		case 10:
			return "+";
		case 11:
			return "-";
		case 12:
			return "*";
		case 13:
			return "/";
		case 20:
			return Double.toString(constant);
		case 21:
			return "x";
		default:
			return "something gone badly wrong, incorrect type number";
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + childAmount;
		result = (int) (prime * result + constant);
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + type;
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
		Data other = (Data) obj;
		if (childAmount != other.childAmount)
			return false;
		if (constant != other.constant)
			return false;
		if (id != other.id)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}
