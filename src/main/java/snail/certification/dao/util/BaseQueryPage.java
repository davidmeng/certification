package snail.certification.dao.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.Criterion;

public class BaseQueryPage {
	
	Map<String,String> sortMap = null;
	
	Map<String,String> aliasMap = null;

	int pageSize = QueryPage.ROWCOUNT_PER_PAGE;

	int pageCount;

	int recordCount;

	int targetPage;
	
	List<Criterion> criterionList = new ArrayList<Criterion>();
	
	List<CriteriaAliasObject> criteriaAliasObjectList = new ArrayList<CriteriaAliasObject>();
	
	public static BaseQueryPage initPage(HttpServletRequest request ){
		
		BaseQueryPage page = new BaseQueryPage(); 
		String targetPageStr = request.getParameter("targetPageStr");
		if (targetPageStr!=null && !targetPageStr.equals("")){
			
			page.setTargetPage((new Integer(targetPageStr)).intValue()); 
		}else {
			
			page.setTargetPage(1);
		}
		
		return page ; 
	}

	public Map<String,String> getSortMap() {
		return sortMap;
	}

	public void setSortMap(Map<String,String> sortMap) {
		this.sortMap = sortMap;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getTargetPage() {
		return targetPage;
	}

	public void setTargetPage(int targetPage) {
		this.targetPage = targetPage;
	}

	public List<Criterion> getCriterionList() {
		return criterionList;
	}

	public void setCriterionList(List<Criterion> criterionList) {
		this.criterionList = criterionList;
	}

	public List<CriteriaAliasObject> getCriteriaAliasObjectList() {
		return criteriaAliasObjectList;
	}

	public void setCriteriaAliasObjectList(List<CriteriaAliasObject> criteriaAliasObjectList) {
		this.criteriaAliasObjectList = criteriaAliasObjectList;
	}

	public Map<String,String> getAliasMap() {
		return aliasMap;
	}

	public void setAliasMap(Map<String,String> aliasMap) {
		this.aliasMap = aliasMap;
	}

	

}
