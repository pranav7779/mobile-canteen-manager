package com.sjct.core.base;

import java.io.Serializable;

import org.springframework.dao.DataAccessException;

public interface EntityDao<E, PK extends Serializable> {

	public Object getById(PK id) throws DataAccessException;

	public void deleteById(PK id) throws DataAccessException;

	/** 插入数据 */
	public void save(E entity) throws DataAccessException;

	/** 更新数据 */
	public void update(E entity) throws DataAccessException;

}