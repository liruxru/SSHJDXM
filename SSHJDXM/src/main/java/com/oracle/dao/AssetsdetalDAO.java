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

import com.oracle.entity.Assetsdetal;

/**
 * A data access object (DAO) providing persistence and search support for
 * Assetsdetal entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.oracle.entity.Assetsdetal
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class AssetsdetalDAO {
	private static final Logger log = LoggerFactory
			.getLogger(AssetsdetalDAO.class);
	// property constants
	public static final String SN = "sn";
	public static final String JINZHANG = "jinzhang";
	public static final String CHUZHANG = "chuzhang";
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

	public void save(Assetsdetal transientInstance) {
		log.debug("saving Assetsdetal instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Assetsdetal persistentInstance) {
		log.debug("deleting Assetsdetal instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Assetsdetal findById(java.lang.Integer id) {
		log.debug("getting Assetsdetal instance with id: " + id);
		try {
			Assetsdetal instance = (Assetsdetal) getCurrentSession().get(
					"com.oracle.entity.Assetsdetal", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Assetsdetal> findByExample(Assetsdetal instance) {
		log.debug("finding Assetsdetal instance by example");
		try {
			List<Assetsdetal> results = (List<Assetsdetal>) getCurrentSession()
					.createCriteria("com.oracle.entity.Assetsdetal")
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
		log.debug("finding Assetsdetal instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Assetsdetal as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Assetsdetal> findBySn(Object sn) {
		return findByProperty(SN, sn);
	}

	public List<Assetsdetal> findByJinzhang(Object jinzhang) {
		return findByProperty(JINZHANG, jinzhang);
	}

	public List<Assetsdetal> findByChuzhang(Object chuzhang) {
		return findByProperty(CHUZHANG, chuzhang);
	}

	public List<Assetsdetal> findBySum(Object sum) {
		return findByProperty(SUM, sum);
	}

	public List findAll() {
		log.debug("finding all Assetsdetal instances");
		try {
			String queryString = "from Assetsdetal";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Assetsdetal merge(Assetsdetal detachedInstance) {
		log.debug("merging Assetsdetal instance");
		try {
			Assetsdetal result = (Assetsdetal) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Assetsdetal instance) {
		log.debug("attaching dirty Assetsdetal instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Assetsdetal instance) {
		log.debug("attaching clean Assetsdetal instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AssetsdetalDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AssetsdetalDAO) ctx.getBean("AssetsdetalDAO");
	}
}