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
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.entity.Adminroles;
import com.oracle.entity.Admins;

/**
 * A data access object (DAO) providing persistence and search support for
 * Admins entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.oracle.entity.Admins
 * @author MyEclipse Persistence Tools
 */
@Transactional

public class AdminsDAO {
	private static final Logger log = LoggerFactory.getLogger(AdminsDAO.class);
	// property constants
	public static final String LOGINNAME = "loginname";
	public static final String PASSWORD = "password";

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

	public void save(Admins transientInstance) {
		log.debug("saving Admins instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Admins persistentInstance) {
		log.debug("deleting Admins instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Admins findById(java.lang.Integer id) {
		log.debug("getting Admins instance with id: " + id);
		try {
			Admins instance = (Admins) getCurrentSession().get(
					"com.oracle.entity.Admins", id);
			Hibernate.initialize(instance.getStaffs());
			Hibernate.initialize(instance.getAdminroleses());
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Admins> findByExample(Admins instance) {
		log.debug("finding Admins instance by example");
		try {
			List<Admins> results = (List<Admins>) getCurrentSession()
					.createCriteria("com.oracle.entity.Admins")
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
		log.debug("finding Admins instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Admins as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Admins> findByLoginname(Object loginname) {
		return findByProperty(LOGINNAME, loginname);
	}

	public List<Admins> findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findAll() {
		log.debug("finding all Admins instances");
		try {
			String queryString = "select distinct a from Admins a left join fetch a.adminroleses left join fetch a.staffs";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Admins merge(Admins detachedInstance) {
		log.debug("merging Admins instance");
		try {
			Admins result = (Admins) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Admins instance) {
		log.debug("attaching dirty Admins instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Admins instance) {
		log.debug("attaching clean Admins instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AdminsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AdminsDAO) ctx.getBean("AdminsDAO");
	}
	
	/*******************************************************************/
	public Admins findadminByLoginname(String loginname){
		String hql="from Admins a left join fetch a.adminroleses ar left join fetch ar.permissions left join fetch a.staffs  where a.loginname=:loginname";
		List<Admins> al= getCurrentSession().createQuery(hql).setString("loginname", loginname).list();
		return (Admins) (al.isEmpty()?null:al.get(0));
	}
	
	public void deleteAdminRole(Integer adminId, Integer roleId) {
		String sql="delete from adminadminrolse where admins = ? and adminroles = ?";
		getCurrentSession().createSQLQuery(sql).setInteger(0, adminId).setInteger(1, roleId).executeUpdate();
	}
}