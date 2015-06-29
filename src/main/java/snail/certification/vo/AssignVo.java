package snail.certification.vo;

public class AssignVo implements Comparable<AssignVo> {
	private int subCategoryId;
	private String subCategoryDescr;
	private int remainQuantity;
	private int requireQuantity;

	public int getSubCategoryId() {
		return this.subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryDescr() {
		return this.subCategoryDescr;
	}

	public void setSubCategoryDescr(String subCategoryDescr) {
		this.subCategoryDescr = subCategoryDescr;
	}

	public int getRemainQuantity() {
		return this.remainQuantity;
	}

	public void setRemainQuantity(int remainQuantity) {
		this.remainQuantity = remainQuantity;
	}

	public int getRequireQuantity() {
		return this.requireQuantity;
	}

	public void setRequireQuantity(int requireQuantity) {
		this.requireQuantity = requireQuantity;
	}

	public double getRate() {
		double r = this.requireQuantity;

		if (this.requireQuantity == 0) {
			r = 0.5D;
		}

		return r / this.remainQuantity;
	}

	public int compareTo(AssignVo o) {
		AssignVo a = (AssignVo) o;
		return new Double(getRate()).compareTo(new Double(a.getRate())) * -1;
	}
}
