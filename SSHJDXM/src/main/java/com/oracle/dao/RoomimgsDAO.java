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

import com.oracle.entity.Roomimgs;

/**
 * A data access object (DAO) providing persistence and search support for
 * Roomimgs entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.oracle.entity.Roomimgs
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class RoomimgsDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RoomimgsDAO.class);
	// property constants
	public static final String ROOMIMG = "roomimg";

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

	public void save(Roomimgs transientInstance) {
		log.debug("saving Roomimgs instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Roomimgs persistentInstance) {
		log.debug("deleting Roomimgs instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Roomimgs findById(java.lang.Integer id) {
		log.debug("getting Roomimgs instance with id: " + id);
		try {
			Roomimgs instance = (Roomimgs) getCurrentSession().get(
					"com.oracle.entity.Roomimgs", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Roomimgs> findByExample(Roomimgs instance) {
		log.debug("finding Roomimgs instance by example");
		try {
			List<Roomimgs> results = (List<Roomimgs>) getCurrentSession()
					.createCriteria("com.oracle.entity.Roomimgs")
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
		log.debug("finding Roomimgs instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Roomimgs as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Roomimgs> findByRoomimg(Object roomimg) {
		return findByProperty(ROOMIMG, roomimg);
	}

	public List findAll() {
		log.debug("finding all Roomimgs instances");
		try {
			String queryString = "from Roomimgs";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Roomimgs merge(Roomimgs detachedInstance) {
		log.debug("merging Roomimgs instance");
		try {
			Roomimgs result = (Roomimgs) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Roomimgs instance) {
		log.debug("attaching dirty Roomimgs instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Roomimgs instance) {
		log.debug("attaching clean Roomimgs instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RoomimgsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RoomimgsDAO) ctx.getBean("RoomimgsDAO");
	}
}