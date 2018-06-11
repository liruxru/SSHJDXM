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

import com.oracle.entity.Usersnews;

/**
 * A data access object (DAO) providing persistence and search support for
 * Usersnews entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.oracle.entity.Usersnews
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class UsersnewsDAO {
	private static final Logger log = LoggerFactory
			.getLogger(UsersnewsDAO.class);
	// property constants
	public static final String NEWS = "news";
	public static final String ANSWER = "answer";

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

	public void save(Usersnews transientInstance) {
		log.debug("saving Usersnews instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Usersnews persistentInstance) {
		log.debug("deleting Usersnews instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Usersnews findById(java.lang.Integer id) {
		log.debug("getting Usersnews instance with id: " + id);
		try {
			Usersnews instance = (Usersnews) getCurrentSession().get(
					"com.oracle.entity.Usersnews", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Usersnews> findByExample(Usersnews instance) {
		log.debug("finding Usersnews instance by example");
		try {
			List<Usersnews> results = (List<Usersnews>) getCurrentSession()
					.createCriteria("com.oracle.entity.Usersnews")
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
		log.debug("finding Usersnews instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Usersnews as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Usersnews> findByNews(Object news) {
		return findByProperty(NEWS, news);
	}

	public List<Usersnews> findByAnswer(Object answer) {
		return findByProperty(ANSWER, answer);
	}

	public List findAll() {
		log.debug("finding all Usersnews instances");
		try {
			String queryString = "from Usersnews";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Usersnews merge(Usersnews detachedInstance) {
		log.debug("merging Usersnews instance");
		try {
			Usersnews result = (Usersnews) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Usersnews instance) {
		log.debug("attaching dirty Usersnews instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Usersnews instance) {
		log.debug("attaching clean Usersnews instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UsersnewsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UsersnewsDAO) ctx.getBean("UsersnewsDAO");
	}
}