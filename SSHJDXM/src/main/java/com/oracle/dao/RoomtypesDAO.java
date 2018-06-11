package com.oracle.dao;

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

import com.oracle.entity.Roomtypes;

/**
 * A data access object (DAO) providing persistence and search support for
 * Roomtypes entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.oracle.entity.Roomtypes
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class RoomtypesDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RoomtypesDAO.class);
	// property constants
	public static final String ROOMTYPE = "roomtype";
	public static final String PRICE = "price";

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

	public void save(Roomtypes transientInstance) {
		log.debug("saving Roomtypes instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Roomtypes persistentInstance) {
		log.debug("deleting Roomtypes instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Roomtypes findById(java.lang.Integer id) {
		log.debug("getting Roomtypes instance with id: " + id);
		try {
			Roomtypes instance = (Roomtypes) getCurrentSession().get(
					"com.oracle.entity.Roomtypes", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Roomtypes> findByExample(Roomtypes instance) {
		log.debug("finding Roomtypes instance by example");
		try {
			List<Roomtypes> results = (List<Roomtypes>) getCurrentSession()
					.createCriteria("com.oracle.entity.Roomtypes")
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
		log.debug("finding Roomtypes instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Roomtypes as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Roomtypes> findByRoomtype(Object roomtype) {
		return findByProperty(ROOMTYPE, roomtype);
	}

	public List<Roomtypes> findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	public List findAll() {
		log.debug("finding all Roomtypes instances");
		try {
			String queryString = "from Roomtypes";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Roomtypes merge(Roomtypes detachedInstance) {
		log.debug("merging Roomtypes instance");
		try {
			Roomtypes result = (Roomtypes) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Roomtypes instance) {
		log.debug("attaching dirty Roomtypes instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Roomtypes instance) {
		log.debug("attaching clean Roomtypes instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RoomtypesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RoomtypesDAO) ctx.getBean("RoomtypesDAO");
	}
}