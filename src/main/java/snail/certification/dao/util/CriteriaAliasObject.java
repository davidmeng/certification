package snail.certification.dao.util;

import org.hibernate.criterion.Criterion;

public class CriteriaAliasObject {
	private String objectName;
	
	private String aliasName;
	
	private Criterion criterion;

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	
	public Criterion getCriterion() {
		return criterion;
	}

	public void setCriterion(Criterion criterion) {
		this.criterion = criterion;
	}


}
