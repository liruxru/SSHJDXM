package com.oracle.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.entity.Goodstypes;

/**
 * A data access object (DAO) providing persistence and search support for
 * Goodstypes entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.oracle.entity.Goodstypes
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Repository
public class GoodstypesDAO {
	private static final Logger log = LoggerFactory
			.getLogger(GoodstypesDAO.class);
	// property constants
	public static final String TYPENAME = "typename";

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

	public void save(Goodstypes transientInstance) {
		log.debug("saving Goodstypes instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Goodstypes persistentInstance) {
		log.debug("deleting Goodstypes instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Goodstypes findById(java.lang.Integer id) {
		log.debug("getting Goodstypes instance with id: " + id);
		try {
			Goodstypes instance = (Goodstypes) getCurrentSession().get(
					"com.oracle.entity.Goodstypes", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Goodstypes> findByExample(Goodstypes instance) {
		log.debug("finding Goodstypes instance by example");
		try {
			List<Goodstypes> results = (List<Goodstypes>) getCurrentSession()
					.createCriteria("com.oracle.entity.Goodstypes")
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
		log.debug("finding Goodstypes instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Goodstypes as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Goodstypes> findByTypename(Object typename) {
		return findByProperty(TYPENAME, typename);
	}

	public List findAll() {
		log.debug("finding all Goodstypes instances");
		try {
			String queryString = "from Goodstypes";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Goodstypes merge(Goodstypes detachedInstance) {
		log.debug("merging Goodstypes instance");
		try {
			Goodstypes result = (Goodstypes) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Goodstypes instance) {
		log.debug("attaching dirty Goodstypes instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Goodstypes instance) {
		log.debug("attaching clean Goodstypes instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static GoodstypesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (GoodstypesDAO) ctx.getBean("GoodstypesDAO");
	}
}