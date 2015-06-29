package snail.certification.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.beans.factory.annotation.Autowired;

import snail.certification.dao.util.BeanUtils;
import snail.certification.dao.util.CriteriaAliasObject;
import snail.certification.dao.util.OrderByUtil;
import snail.certification.dao.util.QueryPage;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	private Class<T> entityClass;
	
	@Autowired
	protected SessionFactory sessionFactory;

	protected BaseDaoImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	@Override
	public void save(T o) {

		sessionFactory.getCurrentSession().merge(o);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		return sessionFactory.getCurrentSession().createCriteria(entityClass).list();
	}

	@Override
	public List<T> getByPage(QueryPage queryPage) {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
		for (Iterator<Criterion> i = queryPage.getCriterionList().iterator(); i
				.hasNext();) {
			criteria.add((Criterion) i.next());
		}

		Map<String, Criteria> aliasMap = new HashMap<String, Criteria>();
		for (Iterator<CriteriaAliasObject> i = queryPage.getCritieraAliasList()
				.iterator(); i.hasNext();) {
			CriteriaAliasObject alias = i.next();

			Criteria criteriaAlias = null;
			if (aliasMap.containsKey(alias.getAliasName()) == true) {
				criteriaAlias = (Criteria) aliasMap.get(alias.getAliasName());
			} else {
				criteriaAlias = criteria.createAlias(alias.getObjectName(),
						alias.getAliasName());
				aliasMap.put(alias.getAliasName(), criteriaAlias);
			}
			criteriaAlias.add(alias.getCriterion());
		}

		return pagedQuery(queryPage, criteria);
	}

	@SuppressWarnings("rawtypes")
	public List<T> pagedQuery(QueryPage queryCtx, Criteria criteria) {
		int pageSize = queryCtx.getPageSize();
		if (pageSize <= 0) {
			pageSize = QueryPage.ROWCOUNT_PER_PAGE;
		}

		int targetPage = queryCtx.getTargetPage();

		int pageCount = 0;
		int recordCount = 0;
		CriteriaImpl impl = (CriteriaImpl) criteria;
		Projection projection = impl.getProjection();
		List orderEntries;
		try {
			orderEntries = (List) BeanUtils.forceGetProperty(impl,
					"orderEntries");
			BeanUtils.forceSetProperty(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			throw new InternalError(" Runtime Exception impossibility throw ");
		}

		Object obj = criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		if (obj == null) {
			return null;
		}
		recordCount = Integer.parseInt(obj.toString());

		criteria.setProjection(projection);
		if (projection == null) {
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}

		try {
			BeanUtils.forceSetProperty(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			throw new InternalError(" Runtime Exception impossibility throw ");
		}
		queryCtx.setRecordCount(recordCount);
		pageCount = recordCount / pageSize;
		if (recordCount % pageSize > 0)
			pageCount++;

		if (pageCount < targetPage) {

			targetPage = pageCount;
		}

		queryCtx.setPageCount(pageCount);
		int startIndex = targetPage * pageSize;
		if (queryCtx.isSorted()) {
			OrderByUtil.sortCriteria(criteria, queryCtx.getSortMap(),
					queryCtx.getAliasMap());
		}

		@SuppressWarnings("unchecked")
		List<T> list = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();

		return list;
	}

	@Override
	public void delete(T t) {

		sessionFactory.getCurrentSession().delete(t);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getById(Integer id) {
		
		return (T) sessionFactory.getCurrentSession().get(entityClass, id);
	}
}
