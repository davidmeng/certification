package snail.certification.dao.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;



public class QueryPage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int ROWCOUNT_PER_PAGE = 10;
	
	private ThreadLocal<BaseQueryPage> threadLocalPage = new ThreadLocal<BaseQueryPage>();

	public BaseQueryPage getPage() {
		return (BaseQueryPage)this.threadLocalPage.get();
	}

	public void setPage(BaseQueryPage baseQueryPage) {
		this.threadLocalPage.set(baseQueryPage);
	}
	
	public List<Criterion> getCriterionList() {
		return getPage().getCriterionList();
	}

	public void setCriterionList(List<Criterion> criterionList) {
		getPage().setCriterionList(criterionList);
	}
	
	//Like
	public void addLikeSearch(String field,String value) {
		getPage().getCriterionList().add(Restrictions.like(field, "%" + value + "%"));
	}
	
	public void addBeforeLikeSearch(String field,String value) {
		getPage().getCriterionList().add(Restrictions.like(field, value ,MatchMode.START));
	}
	
	//����
	public void addEqualSearch(String field,Object value) {
		getPage().getCriterionList().add(Restrictions.eq(field, value));
		
	}
	//������
	public void addNotEqualSearch(String field,Object value) {
		getPage().getCriterionList().add(Restrictions.not(Restrictions.eq(field, value)));
		
	}
	/**
	 * 
	 * @param field
	 * @param value
	 */
	public void addInSearch(String field,Object[] value){
		getPage().getCriterionList().add(Restrictions.in(field, value));
	}
	
	/**
	 * 
	 * @param field
	 * @param value
	 */
	public void addNotInSearch(String field,Object[] value){
		getPage().getCriterionList().add(Restrictions.not(Restrictions.in(field, value)));
	}
	
	//Or
	public void addOrSearch(String field1,Object value1,String field2,Object[] value2 ){
		Criterion exp1 = null;
		
		if(value1 == null) {
			exp1 = Restrictions.isNull(field1);
		}
		else {
			exp1 = Restrictions.eq(field1, value1);
		}
		
		getPage().getCriterionList().add(Restrictions.or(exp1, Restrictions.in(field2, value2)));
	}
	
	public void addOrSearch(String field1,Object value1,String field2,Object value2 ){
		Criterion exp1 = null;
		Criterion exp2 = null;
		
		if(value1 == null) {
			exp1 = Restrictions.isNull(field1);
		}
		else {
			exp1 = Restrictions.eq(field1, value1);
		}
		if(value2 == null) {
			exp2 = Restrictions.isNull(field2);
		}
		else {
			exp2 = Restrictions.eq(field2, value2);
		}
		
		getPage().getCriterionList().add(Restrictions.or(exp1, exp2));
	}
	
	//
	/**
	 * ����}��field and ����Ȼ��͵����or����
	 */
	public void addFirstAndLastOrSearch(String andFiled1,Object andValue1,String andFiled2,Object andValue2,String orField,Object orValue) {
		Criterion exp1 = null;
		Criterion exp2 = null;
		Criterion exp3 = null;
		Criterion exp4 = null;
		Criterion exp5 = null;
		
		if(andValue1 == null) {
			exp1 = Restrictions.isNull(andFiled1);
		}
		else {
			exp1 = Restrictions.eq(andFiled1, andValue1);
		}
		if(andValue2 == null) {
			exp2 = Restrictions.isNull(andFiled2);
		}
		else {
			exp2 = Restrictions.eq(andFiled2, andValue2);
		}
		
		exp3 = Restrictions.and(exp1, exp2);
		
		//�ٽ���or����
		exp4 = Restrictions.eq(orField, orValue);
		
		exp5 = Restrictions.or(exp3,exp4);
		
		getPage().getCriterionList().add(exp5);
	}
	
	//Between
	public void addBetweenSearch(String field,Object lo,Object hi){
		getPage().getCriterionList().add(Restrictions.between(field, lo, hi));
	}
	//���ڵ���
	public void addGreatEqualSearch(String field,Object value){
		getPage().getCriterionList().add(Restrictions.ge(field,value));
	}
	//����
	public void addGreatSearch(String field,Object value){
		getPage().getCriterionList().add(Restrictions.gt(field,value));
	}
	//С�ڵ���
	public void addLessEqualSearch(String field,Object value){
		getPage().getCriterionList().add(Restrictions.le(field,value));
	}
	//С��
	public void addLessSearch(String field,Object value){
		getPage().getCriterionList().add(Restrictions.lt(field,value));
	}
	//���ڵ��� �ֶ�
	public void addGreatEqualPropertySearch(String propertyName,String otherPropertyName){
		getPage().getCriterionList().add(Restrictions.geProperty(propertyName, otherPropertyName));
	}
	//���� �ֶ�
	public void addGreatPropertySearch(String propertyName,String otherPropertyName){
		getPage().getCriterionList().add(Restrictions.gtProperty(propertyName, otherPropertyName));
	}
	//sql���
	public void addSqlSearch(String sql){
		getPage().getCriterionList().add(Restrictions.sqlRestriction(sql));
	}
	//����Ϊ��
	public void addIsNull (String field)
	{
		getPage().getCriterionList().add(Restrictions.isNull(field));
	}
	//���Բ�Ϊ��
	public void addIsNotNull (String field)
	{
		getPage().getCriterionList().add(Restrictions.isNotNull(field));
	}
	//����
	public void addSort(String name,boolean isAsc){
		String sortType = "";
		
		if(isAsc == true) {
			sortType = "asc";
		}
		else {
			sortType = "desc";
		}
		
		Map<String,String> sortMap = getPage().getSortMap();
		if(sortMap == null){
			sortMap = new HashMap<String,String>();
		}
		sortMap.put(name,sortType);
		getPage().setSortMap(sortMap);
	}
	
	public int getPageSize() {
		return getPage().getPageSize();
	}

	public void setPageSize(int pageSize) {
		getPage().setPageSize(pageSize);
	}

	public int getPageCount() {
		return getPage().getPageCount();
	}

	public void setPageCount(int pageCount) {
		getPage().setPageCount(pageCount);
	}

	public int getRecordCount() {
		return getPage().getRecordCount();
	}

	public void setRecordCount(int recordCount) {
		getPage().setRecordCount(recordCount);
	}

	public int getTargetPage() {
		return getPage().getTargetPage();
	}

	public void setTargetPage(int targetPage) {
		getPage().setTargetPage(targetPage);
	}
	
	public Map<String,String> getSortMap() {
		return getPage().getSortMap();
	}

	public void setSortMap(Map<String,String> sortMap) {
		getPage().setSortMap(sortMap);
	}
	
	public Map<String,String> getAliasMap() {
		if(getPage().getAliasMap() == null) {
			getPage().setAliasMap(new HashMap<String,String>());
		}
		
		return getPage().getAliasMap();
	}

	public void setAliasMap(Map<String,String> aliasMap) {
		getPage().setAliasMap(aliasMap);
	}
	
	public boolean isSorted(){
		Map<String,String> sortMap = getPage().getSortMap();
		
		if(sortMap == null || sortMap.size() == 0) {
			return false;
		}
		
		return true;
	}

	public List<CriteriaAliasObject> getCritieraAliasList() {
		return getPage().getCriteriaAliasObjectList();
	}

	public void setCritieraAliasList(List<CriteriaAliasObject> critieraAliasList) {
		getPage().setCriteriaAliasObjectList(critieraAliasList);
	}
	
	private void addAliasToMap(String objectName,String aliasName) {
		if(getPage().getAliasMap() == null) {
			getPage().setAliasMap(new HashMap<String,String>());
		}
		
		if(getPage().getAliasMap().containsKey(objectName)) {
			return;
		}
		
		getPage().getAliasMap().put(objectName, aliasName);
	}
	
	//����
	public void addAliasEqualSearch(String objectName,String aliasName,String field,Object value) {
		CriteriaAliasObject obj = new CriteriaAliasObject();
		obj.setObjectName(objectName);
		obj.setAliasName(aliasName);
		obj.setCriterion(Restrictions.eq(aliasName + "." + field, value));
		getPage().getCriteriaAliasObjectList().add(obj);
		
		addAliasToMap(objectName,aliasName);
	}
	
	//������
	public void addAliasNotEqualSearch(String objectName,String aliasName,String field,Object value) {
		CriteriaAliasObject obj = new CriteriaAliasObject();
		obj.setObjectName(objectName);
		obj.setAliasName(aliasName);
		obj.setCriterion(Restrictions.not(Restrictions.eq(aliasName + "." + field, value)));
		getPage().getCriteriaAliasObjectList().add(obj);
		
		addAliasToMap(objectName,aliasName);
	}
	
	//ģ��
	public void addAliasLikeSearch(String objectName,String aliasName,String field,Object value) {
		CriteriaAliasObject obj = new CriteriaAliasObject();
		obj.setObjectName(objectName);
		obj.setAliasName(aliasName);
		obj.setCriterion(Restrictions.like(aliasName + "." + field, "%" + value + "%"));
		getPage().getCriteriaAliasObjectList().add(obj);
		
		addAliasToMap(objectName,aliasName);
	}
	
	//����
	public void addAliasInSearch(String objectName,String aliasName,String field,Object[] value) {
		CriteriaAliasObject obj = new CriteriaAliasObject();
		obj.setObjectName(objectName);
		obj.setAliasName(aliasName);
		obj.setCriterion(Restrictions.in(aliasName + "." + field, value));
		getPage().getCriteriaAliasObjectList().add(obj);
		
		addAliasToMap(objectName,aliasName);
	}
	
	public QueryPage(int pageSize, int targetPage, Map<String,String> sortMap) {
		BaseQueryPage page = new BaseQueryPage();
		page.setPageSize(pageSize);
		page.setTargetPage(targetPage);
		page.setSortMap(sortMap);
		this.setPage(page);
	}
	
	public QueryPage(int targetPage, Map<String,String> sortMap) {		
		BaseQueryPage page = new BaseQueryPage();
		page.setTargetPage(targetPage);
		page.setSortMap(sortMap);
		this.setPage(page);
	}
	
	public QueryPage(int targetPage) {		
		BaseQueryPage page = new BaseQueryPage();
		page.setTargetPage(targetPage);
		this.setPage(page);		
	}
	
	public static QueryPage initQueryPage(HttpServletRequest request ){
		
		int targetPage = 0 ; 
	
		String targetPageStr = request.getParameter("targetPageStr");
		if (targetPageStr!=null && !targetPageStr.equals("")){
			
			targetPage = (new Integer(targetPageStr)).intValue(); 
		}
		
		QueryPage page = new QueryPage(targetPage);
		return page ; 
	}
	
	
}