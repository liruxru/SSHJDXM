package com.oracle.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.entity.Repair;

/**
 * A data access object (DAO) providing persistence and search support for
 * Repair entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.oracle.entity.Repair
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class RepairDAO {
	private static final Logger log = LoggerFactory.getLogger(RepairDAO.class);
	// property constants
	public static final String DESCRIPTION = "description";
	public static final String NUM = "num";

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

	public void save(Repair transientInstance) {
		log.debug("saving Repair instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Repair persistentInstance) {
		log.debug("deleting Repair instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Repair findById(java.lang.Integer id) {
		log.debug("getting Repair instance with id: " + id);
		try {
			Repair instance = (Repair) getCurrentSession().get(
					"com.oracle.entity.Repair", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Repair> findByExample(Repair instance) {
		log.debug("finding Repair instance by example");
		try {
			List<Repair> results = (List<Repair>) getCurrentSession()
					.createCriteria("com.oracle.entity.Repair")
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
		log.debug("finding Repair instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Repair as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Repair> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<Repair> findByNum(Object num) {
		return findByProperty(NUM, num);
	}

	public List findAll() {
		log.debug("finding all Repair instances");
		try {
			String queryString = "from Repair";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Repair merge(Repair detachedInstance) {
		log.debug("merging Repair instance");
		try {
			Repair result = (Repair) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Repair instance) {
		log.debug("attaching dirty Repair instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Repair instance) {
		log.debug("attaching clean Repair instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RepairDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RepairDAO) ctx.getBean("RepairDAO");
	}
}