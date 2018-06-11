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

import com.oracle.entity.Customercart;
import com.oracle.entity.Goods;
import com.oracle.entity.Rooms;
import com.oracle.entity.Users;

/**
 * A data access object (DAO) providing persistence and search support for
 * Customercart entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.oracle.entity.Customercart
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class CustomercartDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CustomercartDAO.class);
	// property constants
	public static final String GOODSNAME = "goodsname";
	public static final String GOODSNUM = "goodsnum";
	public static final String TOTALSUM = "totalsum";

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

	public void save(Customercart transientInstance) {
		log.debug("saving Customercart instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Customercart persistentInstance) {
		log.debug("deleting Customercart instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Customercart findById(java.lang.Integer id) {
		log.debug("getting Customercart instance with id: " + id);
		try {
			Customercart instance = (Customercart) getCurrentSession().get(
					"com.oracle.entity.Customercart", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Customercart> findByExample(Customercart instance) {
		log.debug("finding Customercart instance by example");
		try {
			List<Customercart> results = (List<Customercart>) getCurrentSession()
					.createCriteria("com.oracle.entity.Customercart")
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
		log.debug("finding Customercart instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Customercart as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Customercart> findByGoodsname(Object goodsname) {
		return findByProperty(GOODSNAME, goodsname);
	}

	public List<Customercart> findByGoodsnum(Object goodsnum) {
		return findByProperty(GOODSNUM, goodsnum);
	}

	public List<Customercart> findByTotalsum(Object totalsum) {
		return findByProperty(TOTALSUM, totalsum);
	}

	public List findAll() {
		log.debug("finding all Customercart instances");
		try {
			String queryString = "from Customercart";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Customercart merge(Customercart detachedInstance) {
		log.debug("merging Customercart instance");
		try {
			Customercart result = (Customercart) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Customercart instance) {
		log.debug("attaching dirty Customercart instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Customercart instance) {
		log.debug("attaching clean Customercart instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CustomercartDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (CustomercartDAO) ctx.getBean("CustomercartDAO");
	}

	public Customercart findByUserAndGoods(Goods goods, Users user) {
		String hql="from  Customercart c where c.goods=:goods and c.users=:users ";
		Query query=getCurrentSession().createQuery(hql).setInteger("goods", goods.getId()).setInteger("users", user.getId());
		return (Customercart) query.uniqueResult();
	}

	public List<Customercart> findByUserId(Integer id) {
		String hql=" from  Customercart c left join fetch c.goods  where c.users=:users ";
		Query query=getCurrentSession().createQuery(hql).setInteger("users",id);
		return query.list();
	}

	public void deleteById(Integer id) {
		String hql="delete from  Customercart c   where c.id=:id ";
		Query query=getCurrentSession().createQuery(hql).setInteger("id",id);
		query.executeUpdate();
		
	}
	
	/**
	 * 刘洋通过房间和商品获取购物车
	 * @param goods
	 * @param room
	 * @return
	 */
	public Customercart findByRoomAndGoods(Goods goods, Rooms room) {
		String hql="from  Customercart c where c.goods=:goods and c.rooms=:rooms ";
		Query query=getCurrentSession().createQuery(hql).setInteger("goods", goods.getId()).setInteger("rooms", room.getId());
		return (Customercart) query.uniqueResult();
	}

	
	
	
	/**
	 * 通过房间id查询商品购物车
	 * @param id
	 * @return
	 */
	public List<Customercart> findByRoom(Integer id) {
		String hql=" from  Customercart c left join fetch c.goods  where c.rooms=:rooms and c.users is null ";
		Query query=getCurrentSession().createQuery(hql).setInteger("rooms",id);
		return query.list();
	}
	/**
	 * 通过房间清空删除所有购物车
	 * @param roomId
	 * @return
	 */
	public void deleteByRoomId(Integer roomId) {
		String hql="delete  from  Customercart c where c.rooms=:rooms ";
		getCurrentSession().createQuery(hql).setInteger("rooms", roomId).executeUpdate();
	}
}