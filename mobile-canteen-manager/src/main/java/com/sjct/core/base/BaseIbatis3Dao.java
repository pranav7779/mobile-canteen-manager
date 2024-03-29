package com.sjct.core.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import com.sjct.common.orm.Page;
import com.sjct.common.orm.PageRequest;
import com.sjct.common.util.PropertyUtils;

public abstract class BaseIbatis3Dao<E, PK extends Serializable> extends DaoSupport implements EntityDao<E, PK> {
	protected final Log log = LogFactory.getLog(getClass());

	private SqlSessionFactory sqlSessionFactory;
	private SqlSessionTemplate sqlSessionTemplate;

	protected void checkDaoConfig() throws IllegalArgumentException {
		Assert.notNull(sqlSessionFactory, "sqlSessionFactory must be not null");
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
		this.sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public Object getById(PK primaryKey) {
		Object object = getSqlSessionTemplate().selectOne(getFindByPrimaryKeyStatement(), primaryKey);
		return object;
	}

	public void deleteById(PK id) {
		int affectCount = getSqlSessionTemplate().delete(getDeleteStatement(),id);
	}

	public void save(E entity) {
		prepareObjectForSaveOrUpdate(entity);
		int affectCount = getSqlSessionTemplate().insert(getInsertStatement(),entity);
	}

	public void update(E entity) {
		prepareObjectForSaveOrUpdate(entity);
		int affectCount = getSqlSessionTemplate().update(getUpdateStatement(),entity);
	}

	/**
	 * 用于子类覆盖,在insert,update之前调用,一般检查SQL注入
	 * 
	 * @param o
	 */
	protected void prepareObjectForSaveOrUpdate(E o) {
		
	}

	public String getIbatisMapperNamesapce() {
		return com.sjct.common.util.ReflectionUtils.getSuperClassGenricType(getClass()).getSimpleName();
	}

	public String getFindByPrimaryKeyStatement() {
		return getIbatisMapperNamesapce() + ".getById";
	}

	public String getInsertStatement() {
		return getIbatisMapperNamesapce() + ".insert";
	}

	public String getUpdateStatement() {
		return getIbatisMapperNamesapce() + ".update";
	}

	public String getDeleteStatement() {
		return getIbatisMapperNamesapce() + ".delete";
	}

	public String getCountStatementForPaging(String statementName) {
		return statementName + ".count";
	}

	protected Page pageQuery(String statementName, PageRequest pageRequest) {
		return pageQuery(getSqlSessionTemplate(), statementName,
				getCountStatementForPaging(statementName), pageRequest);
	}

	public static Page pageQuery(SqlSessionTemplate sqlSessionTemplate,
			String statementName, String countStatementName,
			PageRequest pageRequest) {

		Number totalCount = (Number) sqlSessionTemplate.selectOne(
				countStatementName, pageRequest);
		if (totalCount == null || totalCount.longValue() <= 0) {
			return new Page(pageRequest, 0);
		}

		Page page = new Page(pageRequest, totalCount.intValue());

		// 其它分页参数,用于不喜欢或是因为兼容性而不使用方言(Dialect)的分页用户使用.
		// 与getSqlMapClientTemplate().queryForList(statementName,
		// parameterObject)配合使用
		Map filters = new HashMap();
		filters.put("offset", page.getFirstResult());
		filters.put("pageSize", page.getPageSize());
		filters.put("lastRows", page.getFirstResult() + page.getPageSize());
		filters.put("sortColumns", pageRequest.getSortColumns());

		Map parameterObject = PropertyUtils.describe(pageRequest);
		filters.putAll(parameterObject);

		List list = sqlSessionTemplate.selectList(statementName, filters, page
				.getFirstResult(), page.getPageSize());
		page.setResult(list);
		return page;
	}

	public List findAll() {
		throw new UnsupportedOperationException();
	}

	public boolean isUnique(E entity, String uniquePropertyNames) {
		throw new UnsupportedOperationException();
	}

	public void flush() {
		// ignore
	}

	public static class SqlSessionTemplate {
		SqlSessionFactory sqlSessionFactory;

		public SqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
			this.sqlSessionFactory = sqlSessionFactory;
		}

		public Object execute(SqlSessionCallback action) {
			SqlSession session = null;
			try {
				session = sqlSessionFactory.openSession();
				Object result = action.doInSession(session);
				return result;
			} finally {
				if (session != null)
					session.close();
			}
		}

		public Object selectOne(final String statement, final Object parameter) {
			return execute(new SqlSessionCallback() {
				public Object doInSession(SqlSession session) {
					return session.selectOne(statement, parameter);
				}
			});
		}

		public List selectList(final String statement, final Object parameter,
				final int offset, final int limit) {
			return (List) execute(new SqlSessionCallback() {
				public Object doInSession(SqlSession session) {
					return session.selectList(statement, parameter,
							new RowBounds(offset, limit));
				}
			});
		}

		public int delete(final String statement, final Object parameter) {
			return (Integer) execute(new SqlSessionCallback() {
				public Object doInSession(SqlSession session) {
					return session.delete(statement, parameter);
				}
			});
		}

		public int update(final String statement, final Object parameter) {
			return (Integer) execute(new SqlSessionCallback() {
				public Object doInSession(SqlSession session) {
					return session.update(statement, parameter);
				}
			});
		}

		public int insert(final String statement, final Object parameter) {
			return (Integer) execute(new SqlSessionCallback() {
				public Object doInSession(SqlSession session) {
					return session.insert(statement, parameter);
				}
			});
		}
	}

	public static interface SqlSessionCallback {
		public Object doInSession(SqlSession session);
	}

}