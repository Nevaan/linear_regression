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
	private int constant;

	public int getConstant() {
		return constant;
	}

	public void setConstant(int constant) {
		this.constant = constant;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Data(int type) {
		this.type = type;
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
			return Integer.toString(constant);
		case 21:
			return "x";
		default:
			return "something gone badly wrong, incorrect type number";
		}
		
	}

}
