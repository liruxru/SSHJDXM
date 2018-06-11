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
import org.springframework.transaction.annotation.Transactional;

import com.oracle.entity.Caigou;

/**
 * A data access object (DAO) providing persistence and search support for
 * Caigou entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.oracle.entity.Caigou
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class CaigouDAO {
	private static final Logger log = LoggerFactory.getLogger(CaigouDAO.class);
	// property constants
	public static final String SN = "sn";
	public static final String SUM = "sum";
	public static final String STATUS = "status";

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

	public void save(Caigou transientInstance) {
		log.debug("saving Caigou instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Caigou persistentInstance) {
		log.debug("deleting Caigou instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Caigou findById(java.lang.Integer id) {
		log.debug("getting Caigou instance with id: " + id);
		try {
			Caigou instance = (Caigou) getCurrentSession().get(
					"com.oracle.entity.Caigou", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Caigou> findByExample(Caigou instance) {
		log.debug("finding Caigou instance by example");
		try {
			List<Caigou> results = (List<Caigou>) getCurrentSession()
					.createCriteria("com.oracle.entity.Caigou")
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
		log.debug("finding Caigou instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Caigou as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Caigou> findBySn(Object sn) {
		return findByProperty(SN, sn);
	}

	public List<Caigou> findBySum(Object sum) {
		return findByProperty(SUM, sum);
	}

	public List<Caigou> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findAll() {
		log.debug("finding all Caigou instances");
		try {
			String queryString = "from Caigou";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Caigou merge(Caigou detachedInstance) {
		log.debug("merging Caigou instance");
		try {
			Caigou result = (Caigou) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Caigou instance) {
		log.debug("attaching dirty Caigou instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Caigou instance) {
		log.debug("attaching clean Caigou instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CaigouDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CaigouDAO) ctx.getBean("CaigouDAO");
	}
}