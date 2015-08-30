package treeRepresentation;

public class Data {

	private long id;
	private int type;
	private int childAmount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Data(int type) {
		this.type = type;
		childAmount = 0;
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

}
