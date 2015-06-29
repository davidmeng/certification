package snail.certification.dao;

import java.util.List;

import snail.certification.dao.util.QueryPage;

public interface BaseDao<T> {
	
	public void save(T t) ; 
	public void delete(T t) ;
	public T getById(Integer id);
	public List<T> getAll();
	public List<T> getByPage(QueryPage queryPage);

}
