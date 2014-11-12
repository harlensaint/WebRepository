package com.junsoftware.extra;

import java.util.List;

/**
 * 
 * @author harlensaint
 * 
 *         DAO层的抽象方法，把DAO的增删改差抽取出来
 * 
 *         2014年11月2日,下午7:20:10
 */
public interface IExtraDAO<T> {
	public void add(T entity);

	public void delete(T entity);

	public void update(T entity);

	public T findById(Long id);

	public List<T> findAll();
}
