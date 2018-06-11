package com.oracle.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.entity.Entrepot;

/**
 * A data access object (DAO) providing persistence and search support for
 * Entrepot entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.oracle.entity.Entrepot
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Repository
public class EntrepotDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EntrepotDAO.class);
	// property constants
	public static final String SN = "sn";
	public static final String PHONE = "phone";
	public static final String ADDRESS = "address";

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

	public void save(Entrepot transientInstance) {
		log.debug("saving Entrepot instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Entrepot persistentInstance) {
		log.debug("deleting Entrepot instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Entrepot findById(java.lang.Integer id) {
		log.debug("getting Entrepot instance with id: " + id);
		try {
			Entrepot instance = (Entrepot) getCurrentSession().get(
					"com.oracle.entity.Entrepot", id);
			Hibernate.initialize(instance.getStaffs());
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Entrepot> findByExample(Entrepot instance) {
		log.debug("finding Entrepot instance by example");
		try {
			List<Entrepot> results = (List<Entrepot>) getCurrentSession()
					.createCriteria("com.oracle.entity.Entrepot")
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
		log.debug("finding Entrepot instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Entrepot as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Entrepot> findBySn(Object sn) {
		return findByProperty(SN, sn);
	}

	public List<Entrepot> findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List<Entrepot> findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List findAll() {
		log.debug("finding all Entrepot instances");
		try {
			String queryString = "from Entrepot";
			Query queryObject = getCurrentSession().createQuery(queryString);
//			Hibernate.initialize();
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List getAll() {
		String hql = " from Entrepot e left join fetch e.staffs sta ";
		return getCurrentSession().createQuery(hql).list();
	}

	public Entrepot merge(Entrepot detachedInstance) {
		log.debug("merging Entrepot instance");
		try {
			Entrepot result = (Entrepot) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Entrepot instance) {
		log.debug("attaching dirty Entrepot instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Entrepot instance) {
		log.debug("attaching clean Entrepot instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static EntrepotDAO getFromApplicationContext(ApplicationContext ctx) {
		return (EntrepotDAO) ctx.getBean("EntrepotDAO");
	}

}