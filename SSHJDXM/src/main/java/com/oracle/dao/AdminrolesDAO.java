package com.oracle.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.entity.Adminroles;
import com.oracle.entity.Permission;

/**
 * A data access object (DAO) providing persistence and search support for
 * Adminroles entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.oracle.entity.Adminroles
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class AdminrolesDAO {
	private static final Logger log = LoggerFactory
			.getLogger(AdminrolesDAO.class);
	// property constants
	public static final String ROLENAME = "rolename";
	public static final String ROLEDESCRIPTION = "roledescription";

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

	public void save(Adminroles transientInstance) {
		log.debug("saving Adminroles instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Adminroles persistentInstance) {
		log.debug("deleting Adminroles instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Adminroles findById(java.lang.Integer id) {
		log.debug("getting Adminroles instance with id: " + id);
		try {
			Adminroles instance = (Adminroles) getCurrentSession().get(
					"com.oracle.entity.Adminroles", id);
			Hibernate.initialize(instance.getPermissions());
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Adminroles> findByExample(Adminroles instance) {
		log.debug("finding Adminroles instance by example");
		try {
			List<Adminroles> results = (List<Adminroles>) getCurrentSession()
					.createCriteria("com.oracle.entity.Adminroles")
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
		log.debug("finding Adminroles instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Adminroles as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Adminroles> findByRolename(Object rolename) {
		return findByProperty(ROLENAME, rolename);
	}

	public List<Adminroles> findByRoledescription(Object roledescription) {
		return findByProperty(ROLEDESCRIPTION, roledescription);
	}

	public List findAll() {
		log.debug("finding all Adminroles instances");
		try {
			String queryString = "select distinct ar from Adminroles ar left join fetch ar.permissions";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Adminroles merge(Adminroles detachedInstance) {
		log.debug("merging Adminroles instance");
		try {
			Adminroles result = (Adminroles) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Adminroles instance) {
		log.debug("attaching dirty Adminroles instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Adminroles instance) {
		log.debug("attaching clean Adminroles instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AdminrolesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AdminrolesDAO) ctx.getBean("AdminrolesDAO");
	}

	public List<Permission> findPermissionByurl(String url) {
		log.debug("attaching find Permission instance");
		try {
			String queryString = "select distinct p from Permission p where p.url like:url ";
			Query queryObject = getCurrentSession().createQuery(queryString).setString("url", "%"+url+"%");			
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void deleteAdminroleAndPer(Integer roleId, Integer perId) {
		String sql="delete from adminrolesper where adminroles = ? and permission = ?";
		getCurrentSession().createSQLQuery(sql).setInteger(0, roleId).setInteger(1, perId).executeUpdate();
		
	}
}