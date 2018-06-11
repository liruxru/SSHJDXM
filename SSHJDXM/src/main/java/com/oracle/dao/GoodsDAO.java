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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.entity.Entrepot;
import com.oracle.entity.Goods;

/**
 * A data access object (DAO) providing persistence and search support for Goods
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.oracle.entity.Goods
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class GoodsDAO {
	private static final Logger log = LoggerFactory.getLogger(GoodsDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String FULLNAME = "fullname";
	public static final String NUM = "num";
	public static final String PRICE = "price";
	public static final String IMGCOVER = "imgcover";

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

	public void save(Goods transientInstance) {
		log.debug("saving Goods instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Goods persistentInstance) {
		log.debug("deleting Goods instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Goods findById(java.lang.Integer id) {
		log.debug("getting Goods instance with id: " + id);
		try {
			Goods instance = (Goods) getCurrentSession().get(
					"com.oracle.entity.Goods", id);
			Hibernate.initialize(instance.getEntrepot());
			Hibernate.initialize(instance.getGoodstypes());
			Hibernate.initialize(instance.getProvider());
			Hibernate.initialize(instance.getGoodsimgs());
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public Goods getGoodById(Integer id) {
		String hql ="from Goods g left join fetch g.entrepot ent "
				+ " left join fetch g.provider pro "
				+ " left join fetch g.goodstypes type "
				+ " left join fetch g.goodsimgs img where g.id= ? ";
		
		return (Goods) (getCurrentSession().createQuery(hql).setInteger(0, id).list().isEmpty()?null:getCurrentSession().createQuery(hql).setInteger(0, id).list().get(0));
		
	}


	public List<Goods> findByExample(Goods instance) {
		log.debug("finding Goods instance by example");
		try {
			List<Goods> results = (List<Goods>) getCurrentSession()
					.createCriteria("com.oracle.entity.Goods")
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
		log.debug("finding Goods instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Goods as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Goods> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Goods> findByFullname(Object fullname) {
		return findByProperty(FULLNAME, fullname);
	}

	public List<Goods> findByNum(Object num) {
		return findByProperty(NUM, num);
	}

	public List<Goods> findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	public List<Goods> findByImgcover(Object imgcover) {
		return findByProperty(IMGCOVER, imgcover);
	}

	public List findAll() {
		log.debug("finding all Goods instances");
		try {
			String queryString = "from Goods ";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List<Goods> findAll(Integer offset, Integer length) {
		log.debug("finding all Goods instances");
		try {
			String queryString = "from Goods g "
					+ " left join fetch g.goodstypes "
					+ " left join fetch g.entrepot "
					+ " left join fetch g.provider";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setFirstResult(offset);
			queryObject.setMaxResults(length);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
//	public List getAll() {
//		String hql = " from Goods g left join fetch g.entrepot en "
//				+ " left join fetch g.provider pr "
//				+ " left join fetch g.goodstypes type";
//		return getCurrentSession().createQuery(hql).list();
//	}

	public Goods merge(Goods detachedInstance) {
		log.debug("merging Goods instance");
		try {
			Goods result = (Goods) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Goods instance) {
		log.debug("attaching dirty Goods instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Goods instance) {
		log.debug("attaching clean Goods instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static GoodsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (GoodsDAO) ctx.getBean("GoodsDAO");
	}

	public List<Goods> findAll(int i) {
		log.debug("finding all Goods instances");
		try {
			String queryString = " select distinct g from Goods g left join fetch g.goodsimgs gi"
					+ "left join fetch g.goodstypes gt ";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void del(Integer imgId, Integer goodId) {
		String sql="delete from Goodsimg where id=? and goods=? ";
		Query query=getCurrentSession().createSQLQuery(sql).setInteger(0, imgId).setInteger(1, goodId);
		query.executeUpdate();
		
	}

	public Integer countId() {
		String hql = " select count(id) from Goods ";
		Long countId = (Long) getCurrentSession().createQuery(hql).uniqueResult();
		return Integer.parseInt(countId.toString());
	}

	public List<Goods> getGoodsByEntrepotId(Integer entrepotId) {
		String hql = " from Goods g where g.entrepot= ? ";
		return getCurrentSession().createQuery(hql).setInteger(0, entrepotId).list();
	}

	public List<Goods> getGood() {
		String hql = " from Goods g where g.num < 50";
		return getCurrentSession().createQuery(hql).list();
	}
}