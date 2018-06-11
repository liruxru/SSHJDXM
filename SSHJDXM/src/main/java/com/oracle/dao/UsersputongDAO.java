package com.oracle.dao;

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
import org.springframework.transaction.annotation.Transactional;

import com.oracle.entity.Usersputong;

/**
 * A data access object (DAO) providing persistence and search support for
 * Usersputong entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.oracle.entity.Usersputong
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class UsersputongDAO {
	private static final Logger log = LoggerFactory
			.getLogger(UsersputongDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String SEX = "sex";
	public static final String IDCARD = "idcard";

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

	public void save(Usersputong transientInstance) {
		log.debug("saving Usersputong instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Usersputong persistentInstance) {
		log.debug("deleting Usersputong instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Usersputong findById(java.lang.Integer id) {
		log.debug("getting Usersputong instance with id: " + id);
		try {
			Usersputong instance = (Usersputong) getCurrentSession().get(
					"com.oracle.entity.Usersputong", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Usersputong> findByExample(Usersputong instance) {
		log.debug("finding Usersputong instance by example");
		try {
			List<Usersputong> results = (List<Usersputong>) getCurrentSession()
					.createCriteria("com.oracle.entity.Usersputong")
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
		log.debug("finding Usersputong instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Usersputong as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Usersputong> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Usersputong> findBySex(Object sex) {
		return findByProperty(SEX, sex);
	}

	

	public List findAll() {
		log.debug("finding all Usersputong instances");
		try {
			String queryString = "from Usersputong";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Usersputong merge(Usersputong detachedInstance) {
		log.debug("merging Usersputong instance");
		try {
			Usersputong result = (Usersputong) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Usersputong instance) {
		log.debug("attaching dirty Usersputong instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Usersputong instance) {
		log.debug("attaching clean Usersputong instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UsersputongDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (UsersputongDAO) ctx.getBean("UsersputongDAO");
	}
	
	/**
	 * 通过身份证查找用户
	 * @param idcard
	 * @return
	 */
	public List<Usersputong> findByIdcard(String idcard) {
		String hql="from Usersputong u where u.idcard=:idcard";
		Query query=getCurrentSession().createQuery(hql).setString("idcard", idcard);
		return query.list();
		
	}
	/**
	 * 通过身份证查看用户住店信息
	 * @param idCard
	 * @param i
	 * @return
	 */
	public Usersputong findByIdcard(String idCard, int i) {
		String hql="select distinct up from Usersputong up"				
				+ " left join fetch up.userRoomOrder uro"
				+ " left join fetch uro.customerorders cro"
				+ " left join fetch cro.userRoomOrder uo"
				+ " left join uo.userputong uoup "				
				+ "where up.idcard=:idcard ";
		Query query=getCurrentSession().createQuery(hql).setString("idcard", idCard);
		return (Usersputong) query.uniqueResult();
	}
}