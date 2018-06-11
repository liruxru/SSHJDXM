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

import com.oracle.entity.Roomstatus;

/**
 * A data access object (DAO) providing persistence and search support for
 * Roomstatus entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.oracle.entity.Roomstatus
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class RoomstatusDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RoomstatusDAO.class);
	// property constants
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

	public void save(Roomstatus transientInstance) {
		log.debug("saving Roomstatus instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Roomstatus persistentInstance) {
		log.debug("deleting Roomstatus instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Roomstatus findById(java.lang.Integer id) {
		log.debug("getting Roomstatus instance with id: " + id);
		try {
			Roomstatus instance = (Roomstatus) getCurrentSession().get(
					"com.oracle.entity.Roomstatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Roomstatus> findByExample(Roomstatus instance) {
		log.debug("finding Roomstatus instance by example");
		try {
			List<Roomstatus> results = (List<Roomstatus>) getCurrentSession()
					.createCriteria("com.oracle.entity.Roomstatus")
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
		log.debug("finding Roomstatus instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Roomstatus as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Roomstatus> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findAll() {
		log.debug("finding all Roomstatus instances");
		try {
			String queryString = "from Roomstatus";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Roomstatus merge(Roomstatus detachedInstance) {
		log.debug("merging Roomstatus instance");
		try {
			Roomstatus result = (Roomstatus) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Roomstatus instance) {
		log.debug("attaching dirty Roomstatus instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Roomstatus instance) {
		log.debug("attaching clean Roomstatus instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RoomstatusDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RoomstatusDAO) ctx.getBean("RoomstatusDAO");
	}
}