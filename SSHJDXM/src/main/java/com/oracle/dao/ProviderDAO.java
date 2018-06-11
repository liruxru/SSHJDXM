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

import com.oracle.entity.Provider;

/**
 * A data access object (DAO) providing persistence and search support for
 * Provider entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.oracle.entity.Provider
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Repository
public class ProviderDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ProviderDAO.class);
	// property constants
	public static final String PROVIDENAME = "providename";
	public static final String PROVIDEPHONE = "providephone";
	public static final String PROVIDEADDRESS = "provideaddress";

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

	public void save(Provider transientInstance) {
		log.debug("saving Provider instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Provider persistentInstance) {
		log.debug("deleting Provider instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Provider findById(java.lang.Integer id) {
		log.debug("getting Provider instance with id: " + id);
		try {
			Provider instance = (Provider) getCurrentSession().get(
					"com.oracle.entity.Provider", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Provider> findByExample(Provider instance) {
		log.debug("finding Provider instance by example");
		try {
			List<Provider> results = (List<Provider>) getCurrentSession()
					.createCriteria("com.oracle.entity.Provider")
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
		log.debug("finding Provider instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Provider as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Provider> findByProvidename(Object providename) {
		return findByProperty(PROVIDENAME, providename);
	}

	public List<Provider> findByProvidephone(Object providephone) {
		return findByProperty(PROVIDEPHONE, providephone);
	}

	public List<Provider> findByProvideaddress(Object provideaddress) {
		return findByProperty(PROVIDEADDRESS, provideaddress);
	}

	public List findAll() {
		log.debug("finding all Provider instances");
		try {
			String queryString = "from Provider";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Provider merge(Provider detachedInstance) {
		log.debug("merging Provider instance");
		try {
			Provider result = (Provider) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Provider instance) {
		log.debug("attaching dirty Provider instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Provider instance) {
		log.debug("attaching clean Provider instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ProviderDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ProviderDAO) ctx.getBean("ProviderDAO");
	}
}