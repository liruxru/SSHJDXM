package com.oracle.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.entity.Assets;

/**
 * A data access object (DAO) providing persistence and search support for
 * Assets entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.oracle.entity.Assets
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class AssetsDAO {
	private static final Logger log = LoggerFactory.getLogger(AssetsDAO.class);
	// property constants
	public static final String SUM = "sum";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Assets transientInstance) {
		log.debug("saving Assets instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Assets persistentInstance) {
		log.debug("deleting Assets instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Assets findById(java.lang.Integer id) {
		log.debug("getting Assets instance with id: " + id);
		try {
			Assets instance = (Assets) getCurrentSession().get(
					"com.oracle.entity.Assets", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Assets> findByExample(Assets instance) {
		log.debug("finding Assets instance by example");
		try {
			List<Assets> results = (List<Assets>) getCurrentSession()
					.createCriteria("com.oracle.entity.Assets")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Assets instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Assets as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Assets> findBySum(Object sum) {
		return findByProperty(SUM, sum);
	}

	public List findAll() {
		log.debug("finding all Assets instances");
		try {
			String queryString = "from Assets";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Assets merge(Assets detachedInstance) {
		log.debug("merging Assets instance");
		try {
			Assets result = (Assets) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Assets instance) {
		log.debug("attaching dirty Assets instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Assets instance) {
		log.debug("attaching clean Assets instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AssetsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AssetsDAO) ctx.getBean("AssetsDAO");
	}
	/****************************************************/
	/**
	 * 获取最新的余额
	 * @return
	 */
	public com.oracle.entity.Assets getLastAssets() {
		String hql="from Assets a where a.id=(select  max(id)  from Assets)";
		return (Assets) getCurrentSession().createQuery(hql).uniqueResult();
	}
}