package com.oracle.dao;

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

import com.oracle.entity.Sexs;

/**
 * A data access object (DAO) providing persistence and search support for Sexs
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.oracle.entity.Sexs
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class SexsDAO {
	private static final Logger log = LoggerFactory.getLogger(SexsDAO.class);
	// property constants
	public static final String SEX = "sex";

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

	public void save(Sexs transientInstance) {
		log.debug("saving Sexs instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Sexs persistentInstance) {
		log.debug("deleting Sexs instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Sexs findById(java.lang.Integer id) {
		log.debug("getting Sexs instance with id: " + id);
		try {
			Sexs instance = (Sexs) getCurrentSession().get(
					"com.oracle.entity.Sexs", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Sexs> findByExample(Sexs instance) {
		log.debug("finding Sexs instance by example");
		try {
			List<Sexs> results = (List<Sexs>) getCurrentSession()
					.createCriteria("com.oracle.entity.Sexs")
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
		log.debug("finding Sexs instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Sexs as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Sexs> findBySex(Object sex) {
		return findByProperty(SEX, sex);
	}

	public List findAll() {
		log.debug("finding all Sexs instances");
		try {
			String queryString = "from Sexs";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Sexs merge(Sexs detachedInstance) {
		log.debug("merging Sexs instance");
		try {
			Sexs result = (Sexs) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Sexs instance) {
		log.debug("attaching dirty Sexs instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Sexs instance) {
		log.debug("attaching clean Sexs instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SexsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SexsDAO) ctx.getBean("SexsDAO");
	}
}