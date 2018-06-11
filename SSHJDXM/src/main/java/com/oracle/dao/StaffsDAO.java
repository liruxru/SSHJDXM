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

import com.oracle.entity.Staffs;

/**
 * A data access object (DAO) providing persistence and search support for
 * Staffs entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.oracle.entity.Staffs
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Repository
public class StaffsDAO {
	private static final Logger log = LoggerFactory.getLogger(StaffsDAO.class);
	// property constants
	public static final String NO = "no";
	public static final String NAME = "name";
	public static final String SEX = "sex";
	public static final String AGE = "age";
	public static final String PHONE = "phone";
	public static final String ADDRESS = "address";
	public static final String WORKDESCRIPTION = "workdescription";
	public static final String SALARY = "salary";

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

	public void save(Staffs transientInstance) {
		log.debug("saving Staffs instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Staffs persistentInstance) {
		log.debug("deleting Staffs instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Staffs findById(java.lang.Integer id) {
		log.debug("getting Staffs instance with id: " + id);
		try {
			Staffs instance = (Staffs) getCurrentSession().get(
					"com.oracle.entity.Staffs", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Staffs> findByExample(Staffs instance) {
		log.debug("finding Staffs instance by example");
		try {
			List<Staffs> results = (List<Staffs>) getCurrentSession()
					.createCriteria("com.oracle.entity.Staffs")
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
		log.debug("finding Staffs instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Staffs as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Staffs> findByNo(Object no) {
		return findByProperty(NO, no);
	}

	public List<Staffs> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Staffs> findBySex(Object sex) {
		return findByProperty(SEX, sex);
	}

	public List<Staffs> findByAge(Object age) {
		return findByProperty(AGE, age);
	}

	public List<Staffs> findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List<Staffs> findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List<Staffs> findByWorkdescription(Object workdescription) {
		return findByProperty(WORKDESCRIPTION, workdescription);
	}

	public List<Staffs> findBySalary(Object salary) {
		return findByProperty(SALARY, salary);
	}

	public List findAll() {
		log.debug("finding all Staffs instances");
		try {
			String queryString = "from Staffs";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List<Staffs> findAll(Integer offset, Integer length) {
		log.debug("finding all Staffs instances");
		try {
			String queryString = "from Staffs";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setFirstResult(offset);
			queryObject.setMaxResults(length);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Staffs merge(Staffs detachedInstance) {
		log.debug("merging Staffs instance");
		try {
			Staffs result = (Staffs) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Staffs instance) {
		log.debug("attaching dirty Staffs instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Staffs instance) {
		log.debug("attaching clean Staffs instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static StaffsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (StaffsDAO) ctx.getBean("StaffsDAO");
	}

	public Integer count() {
		String hql = " select count(id) from Staffs";
		Long i = (Long) getCurrentSession().createQuery(hql).uniqueResult();
		return Integer.parseInt(i.toString());
	}
}