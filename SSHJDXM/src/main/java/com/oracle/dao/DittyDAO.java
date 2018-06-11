package com.oracle.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
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

import com.oracle.entity.Ditty;

/**
 * A data access object (DAO) providing persistence and search support for Ditty
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.oracle.entity.Ditty
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Repository
public class DittyDAO {
	private static final Logger log = LoggerFactory.getLogger(DittyDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String FULLNAME = "fullname";
	public static final String NUM = "num";
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

	public void save(Ditty transientInstance) {
		log.debug("saving Ditty instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Ditty persistentInstance) {
		log.debug("deleting Ditty instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Ditty findById(java.lang.Integer id) {
		log.debug("getting Ditty instance with id: " + id);
		try {
			Ditty instance = (Ditty) getCurrentSession().get(
					"com.oracle.entity.Ditty", id);
			Hibernate.initialize(instance.getProvider());
			Hibernate.initialize(instance.getEntrepot());
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Ditty> findByExample(Ditty instance) {
		log.debug("finding Ditty instance by example");
		try {
			List<Ditty> results = (List<Ditty>) getCurrentSession()
					.createCriteria("com.oracle.entity.Ditty")
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
		log.debug("finding Ditty instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Ditty as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Ditty> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Ditty> findByFullname(Object fullname) {
		return findByProperty(FULLNAME, fullname);
	}

	public List<Ditty> findByNum(Object num) {
		return findByProperty(NUM, num);
	}

	public List<Ditty> findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	public List findAll() {
		log.debug("finding all Ditty instances");
		try {
			String queryString = "from Ditty";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List getAll() {
		String hql = " from Ditty d left join fetch d.entrepot en left join fetch d.provider pro ";
		return getCurrentSession().createQuery(hql).list();
	}

	public Ditty merge(Ditty detachedInstance) {
		log.debug("merging Ditty instance");
		try {
			Ditty result = (Ditty) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Ditty instance) {
		log.debug("attaching dirty Ditty instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Ditty instance) {
		log.debug("attaching clean Ditty instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DittyDAO getFromApplicationContext(ApplicationContext ctx) {
		return (DittyDAO) ctx.getBean("DittyDAO");
	}

	public List<Ditty> getDittyByEntrepotId(Integer entrepotId) {
	    String hql = " from Ditty d where d.entrepot= ? ";	    
		return getCurrentSession().createQuery(hql).setInteger(0, entrepotId).list();
	}

	public List<Ditty> getDitty() {
		 String hql = " from Ditty d where d.num < 50";	    
		 return getCurrentSession().createQuery(hql).list();
	}
}