package com.oracle.dao;

import java.sql.Timestamp;
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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.entity.Goodsimg;

/**
 * A data access object (DAO) providing persistence and search support for
 * Goodsimg entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.oracle.entity.Goodsimg
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Repository
public class GoodsimgDAO {
	private static final Logger log = LoggerFactory
			.getLogger(GoodsimgDAO.class);
	// property constants
	public static final String GOODSIMG = "goodsimg";

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

	public void save(Goodsimg transientInstance) {
		log.debug("saving Goodsimg instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Goodsimg persistentInstance) {
		log.debug("deleting Goodsimg instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Goodsimg findById(java.lang.Integer id) {
		log.debug("getting Goodsimg instance with id: " + id);
		try {
			Goodsimg instance = (Goodsimg) getCurrentSession().get(
					"com.oracle.entity.Goodsimg", id);
			Hibernate.initialize(instance.getGoodsimg());
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Goodsimg> findByExample(Goodsimg instance) {
		log.debug("finding Goodsimg instance by example");
		try {
			List<Goodsimg> results = (List<Goodsimg>) getCurrentSession()
					.createCriteria("com.oracle.entity.Goodsimg")
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
		log.debug("finding Goodsimg instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Goodsimg as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Goodsimg> findByGoodsimg(Object goodsimg) {
		return findByProperty(GOODSIMG, goodsimg);
	}

	public List findAll() {
		log.debug("finding all Goodsimg instances");
		try {
			String queryString = "from Goodsimg";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Goodsimg merge(Goodsimg detachedInstance) {
		log.debug("merging Goodsimg instance");
		try {
			Goodsimg result = (Goodsimg) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Goodsimg instance) {
		log.debug("attaching dirty Goodsimg instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Goodsimg instance) {
		log.debug("attaching clean Goodsimg instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static GoodsimgDAO getFromApplicationContext(ApplicationContext ctx) {
		return (GoodsimgDAO) ctx.getBean("GoodsimgDAO");
	}
}