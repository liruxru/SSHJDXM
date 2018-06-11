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

import com.oracle.entity.Roomselect;
import com.oracle.entity.Users;

/**
 * A data access object (DAO) providing persistence and search support for
 * Roomselect entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.oracle.entity.Roomselect
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class RoomselectDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RoomselectDAO.class);
	// property constants
	public static final String CHARGEMETHOD = "chargemethod";
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

	public void save(Roomselect transientInstance) {
		log.debug("saving Roomselect instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Roomselect persistentInstance) {
		log.debug("deleting Roomselect instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Roomselect findById(java.lang.Integer id) {
		log.debug("getting Roomselect instance with id: " + id);
		try {
			Roomselect instance = (Roomselect) getCurrentSession().get(
					"com.oracle.entity.Roomselect", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Roomselect> findByExample(Roomselect instance) {
		log.debug("finding Roomselect instance by example");
		try {
			List<Roomselect> results = (List<Roomselect>) getCurrentSession()
					.createCriteria("com.oracle.entity.Roomselect")
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
		log.debug("finding Roomselect instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Roomselect as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Roomselect> findByChargemethod(Object chargemethod) {
		return findByProperty(CHARGEMETHOD, chargemethod);
	}

	public List<Roomselect> findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	public List findAll() {
		log.debug("finding all Roomselect instances");
		try {
			String queryString = "from Roomselect";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Roomselect merge(Roomselect detachedInstance) {
		log.debug("merging Roomselect instance");
		try {
			Roomselect result = (Roomselect) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Roomselect instance) {
		log.debug("attaching dirty Roomselect instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Roomselect instance) {
		log.debug("attaching clean Roomselect instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RoomselectDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RoomselectDAO) ctx.getBean("RoomselectDAO");
	}

	public List<Roomselect> findByUser(Users users) {
		String hql="from Roomselect rs left join fetch rs.rooms left join fetch rs.users where rs.users=:userId";
		Query query=getCurrentSession().createQuery(hql).setInteger("userId", users.getId());
		return query.list();
	}
}