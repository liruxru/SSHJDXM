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

import com.oracle.entity.Caigougoodsitems;

/**
 * A data access object (DAO) providing persistence and search support for
 * Caigougoodsitems entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.oracle.entity.Caigougoodsitems
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class CaigougoodsitemsDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CaigougoodsitemsDAO.class);
	// property constants
	public static final String GOODSNUM = "goodsnum";

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

	public void save(Caigougoodsitems transientInstance) {
		log.debug("saving Caigougoodsitems instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Caigougoodsitems persistentInstance) {
		log.debug("deleting Caigougoodsitems instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Caigougoodsitems findById(java.lang.Integer id) {
		log.debug("getting Caigougoodsitems instance with id: " + id);
		try {
			Caigougoodsitems instance = (Caigougoodsitems) getCurrentSession()
					.get("com.oracle.entity.Caigougoodsitems", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Caigougoodsitems> findByExample(Caigougoodsitems instance) {
		log.debug("finding Caigougoodsitems instance by example");
		try {
			List<Caigougoodsitems> results = (List<Caigougoodsitems>) getCurrentSession()
					.createCriteria("com.oracle.entity.Caigougoodsitems")
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
		log.debug("finding Caigougoodsitems instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Caigougoodsitems as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Caigougoodsitems> findByGoodsnum(Object goodsnum) {
		return findByProperty(GOODSNUM, goodsnum);
	}

	public List findAll() {
		log.debug("finding all Caigougoodsitems instances");
		try {
			String queryString = "from Caigougoodsitems";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Caigougoodsitems merge(Caigougoodsitems detachedInstance) {
		log.debug("merging Caigougoodsitems instance");
		try {
			Caigougoodsitems result = (Caigougoodsitems) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Caigougoodsitems instance) {
		log.debug("attaching dirty Caigougoodsitems instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Caigougoodsitems instance) {
		log.debug("attaching clean Caigougoodsitems instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CaigougoodsitemsDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (CaigougoodsitemsDAO) ctx.getBean("CaigougoodsitemsDAO");
	}
}