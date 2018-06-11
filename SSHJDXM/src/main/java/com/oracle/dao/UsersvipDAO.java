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

import com.oracle.entity.Usersvip;

/**
 * A data access object (DAO) providing persistence and search support for
 * Usersvip entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.oracle.entity.Usersvip
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class UsersvipDAO {
	private static final Logger log = LoggerFactory
			.getLogger(UsersvipDAO.class);
	// property constants
	public static final String JIFEN = "jifen";
	public static final String LEVEL = "level";
	public static final String VIPNAME = "vipname";
	public static final String ROOMDISCOUNT = "roomdiscount";

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

	public void save(Usersvip transientInstance) {
		log.debug("saving Usersvip instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Usersvip persistentInstance) {
		log.debug("deleting Usersvip instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Usersvip findById(java.lang.Integer id) {
		log.debug("getting Usersvip instance with id: " + id);
		try {
			Usersvip instance = (Usersvip) getCurrentSession().get(
					"com.oracle.entity.Usersvip", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Usersvip> findByExample(Usersvip instance) {
		log.debug("finding Usersvip instance by example");
		try {
			List<Usersvip> results = (List<Usersvip>) getCurrentSession()
					.createCriteria("com.oracle.entity.Usersvip")
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
		log.debug("finding Usersvip instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Usersvip as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Usersvip> findByJifen(Object jifen) {
		return findByProperty(JIFEN, jifen);
	}

	public List<Usersvip> findByLevel(Object level) {
		return findByProperty(LEVEL, level);
	}

	public List<Usersvip> findByVipname(Object vipname) {
		return findByProperty(VIPNAME, vipname);
	}

	public List<Usersvip> findByRoomdiscount(Object roomdiscount) {
		return findByProperty(ROOMDISCOUNT, roomdiscount);
	}

	public List findAll() {
		log.debug("finding all Usersvip instances");
		try {
			String queryString = "from Usersvip";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Usersvip merge(Usersvip detachedInstance) {
		log.debug("merging Usersvip instance");
		try {
			Usersvip result = (Usersvip) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Usersvip instance) {
		log.debug("attaching dirty Usersvip instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Usersvip instance) {
		log.debug("attaching clean Usersvip instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UsersvipDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UsersvipDAO) ctx.getBean("UsersvipDAO");
	}
}