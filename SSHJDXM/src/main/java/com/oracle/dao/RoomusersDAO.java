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

import com.oracle.bean.RoomUserMsg;
import com.oracle.entity.Roomusers;

/**
 * A data access object (DAO) providing persistence and search support for
 * Roomusers entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.oracle.entity.Roomusers
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class RoomusersDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RoomusersDAO.class);
	// property constants

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

	public void save(Roomusers transientInstance) {
		log.debug("saving Roomusers instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Roomusers persistentInstance) {
		log.debug("deleting Roomusers instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Roomusers findById(java.lang.Integer id) {
		log.debug("getting Roomusers instance with id: " + id);
		try {
			Roomusers instance = (Roomusers) getCurrentSession().get(
					"com.oracle.entity.Roomusers", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Roomusers> findByExample(Roomusers instance) {
		log.debug("finding Roomusers instance by example");
		try {
			List<Roomusers> results = (List<Roomusers>) getCurrentSession()
					.createCriteria("com.oracle.entity.Roomusers")
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
		log.debug("finding Roomusers instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Roomusers as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Roomusers instances");
		try {
			String queryString = "from Roomusers";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Roomusers merge(Roomusers detachedInstance) {
		log.debug("merging Roomusers instance");
		try {
			Roomusers result = (Roomusers) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Roomusers instance) {
		log.debug("attaching dirty Roomusers instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Roomusers instance) {
		log.debug("attaching clean Roomusers instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RoomusersDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RoomusersDAO) ctx.getBean("RoomusersDAO");
	}
	/**
	 * 刘洋通过房间id查找roomuser
	 * @param roomId
	 * @return
	 */
	public	List<Roomusers>  findByRoomId(Integer roomId) {
		String hql="from Roomusers r where r.rooms=:rooms ";
		return  getCurrentSession().createQuery(hql).setInteger("rooms", roomId).list();
	}

	/***************************************************************************************/
//	public List<RoomUserMsg> roomUserMsg() {
//		String hql="from Roomusers r  ";
//		return  getCurrentSession().createQuery(hql).list();
//	}
	
	
	
	
	
	
	
	
	
	
	
	
}