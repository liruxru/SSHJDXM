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
import org.springframework.transaction.annotation.Transactional;

import com.oracle.entity.Customerorders;
import com.oracle.entity.Rooms;
import com.oracle.entity.Users;

/**
 * A data access object (DAO) providing persistence and search support for
 * Customerorders entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.oracle.entity.Customerorders
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class CustomerordersDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CustomerordersDAO.class);
	// property constants
	public static final String SN = "sn";
	public static final String SUM = "sum";

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

	public void save(Customerorders transientInstance) {
		log.debug("saving Customerorders instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Customerorders persistentInstance) {
		log.debug("deleting Customerorders instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Customerorders findById(java.lang.Integer id) {
		log.debug("getting Customerorders instance with id: " + id);
		try {
			Customerorders instance = (Customerorders) getCurrentSession().get(
					"com.oracle.entity.Customerorders", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Customerorders> findByExample(Customerorders instance) {
		log.debug("finding Customerorders instance by example");
		try {
			List<Customerorders> results = (List<Customerorders>) getCurrentSession()
					.createCriteria("com.oracle.entity.Customerorders")
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
		log.debug("finding Customerorders instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Customerorders as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Customerorders> findBySn(Object sn) {
		return findByProperty(SN, sn);
	}

	public List<Customerorders> findBySum(Object sum) {
		return findByProperty(SUM, sum);
	}

	public List findAll() {
		log.debug("finding all Customerorders instances");
		try {
			String queryString = "from Customerorders";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Customerorders merge(Customerorders detachedInstance) {
		log.debug("merging Customerorders instance");
		try {
			Customerorders result = (Customerorders) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Customerorders instance) {
		log.debug("attaching dirty Customerorders instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Customerorders instance) {
		log.debug("attaching clean Customerorders instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CustomerordersDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (CustomerordersDAO) ctx.getBean("CustomerordersDAO");
	}

	public List<Customerorders> listGoodsOrderByUser(Users user) {
		String hql=" select distinct c from Customerorders c left join fetch c.orderstatus cs "
				+ " left join fetch c.customerorderitemses oi"
				+ "  left join fetch oi.goods where c.users=:user and c.rooms is null";
				
		return getCurrentSession().createQuery(hql).setInteger("user", user.getId()).list();
	}
	
	/**
	 * 刘洋通过房间浏览商品订单
	 * @param room
	 * @return
	 */
	public List<Customerorders> listGoodsOrderByRoom(Rooms room) {
		String hql=" select distinct c from Customerorders c left join fetch c.orderstatus cs "
				+ " left join fetch c.customerorderitemses oi"
				+ "  left join fetch oi.goods where c.rooms=:rooms and c.usersputong is null"
				+ " and c.orderstatus in (1,5)";
				
		return getCurrentSession().createQuery(hql).setInteger("rooms", room.getId()).list();
		
	}
	

	/**
	 * 刘洋通过房间id修改房间商品订单状态
	 * @param roomId
	 */
	public void editGoodOrderByRoomId(Integer roomId, int orderstatus) {
		String hql="update Customerorders c set c.orderstatus=:orderstatus "
				+ "where c.orderstatus=1 and c.rooms=:rooms ";		
		getCurrentSession().createQuery(hql).setInteger("orderstatus", orderstatus).setInteger("rooms", roomId).executeUpdate();
	}
	/**
	 * 刘洋通过房间id修改房间订单状态
	 * @param roomId
	 */
	public void editRoomOrderByRoomId(Integer roomId, int orderstatus) {
		String hql="update Customerorders c set c.orderstatus=:orderstatus "
				+ "where c.orderstatus=3 and c.rooms=:rooms";		
		getCurrentSession().createQuery(hql).setInteger("orderstatus", orderstatus).setInteger("rooms", roomId).executeUpdate();
		
	}
	/**
	 * 刘洋通过房间id和房间订单状态查询订单
	 * @param roomId
	 */
	public Customerorders findByRoomIdAndStatus(Integer roomId, int i) {
		String hql="from Customerorders o where o.rooms=:rooms and o.orderstatus=:orderstatus ";
		
		return (Customerorders) getCurrentSession().createQuery(hql).
				setInteger("rooms", roomId).setInteger("orderstatus", i).uniqueResult();
	}

	/**
	 * 刘洋用户退房删除没有收货的订单
	 * @param roomId
	 */
	public void deleteGoodOrderByRoomId(Integer roomId, int i) {
		String hql="delete from Customerorders o "
				+ "where o.rooms=:rooms and o.orderstatus=:orderstatus ";		
		 getCurrentSession().createQuery(hql).
				setInteger("rooms", roomId).setInteger("orderstatus", i).executeUpdate();
		
	}
	/**
	 * 查询已经收获的订单
	 * @param roomId
	 * @param i
	 * @return
	 */
	public List<Customerorders> findGoodsOrderByRoomIdAndStatus(Integer roomId, int i) {
			String hql="from Customerorders o where o.rooms=:rooms and o.orderstatus=:orderstatus ";
		
		return getCurrentSession().createQuery(hql).
				setInteger("rooms", roomId).setInteger("orderstatus", i).list();
	}
}