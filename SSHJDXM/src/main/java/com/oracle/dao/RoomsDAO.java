package com.oracle.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.entity.Rooms;
import com.oracle.entity.Roomusers;

/**
 * A data access object (DAO) providing persistence and search support for Rooms
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.oracle.entity.Rooms
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class RoomsDAO {
	private static final Logger log = LoggerFactory.getLogger(RoomsDAO.class);
	// property constants
	public static final String NO = "no";
	public static final String DEPOSIT = "deposit";
	public static final String PHONE = "phone";
	public static final String IMGCOVER = "imgcover";
	public static final String ROOMSTATUS = "roomstatus";
	public static final String POSITION = "position";

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

	public void save(Rooms transientInstance) {
		log.debug("saving Rooms instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Rooms persistentInstance) {
		log.debug("deleting Rooms instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Rooms findById(java.lang.Integer id) {
		log.debug("getting Rooms instance with id: " + id);
		try {
			Rooms instance = (Rooms) getCurrentSession().get(
					"com.oracle.entity.Rooms", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Rooms> findByExample(Rooms instance) {
		log.debug("finding Rooms instance by example");
		try {
			List<Rooms> results = (List<Rooms>) getCurrentSession()
					.createCriteria("com.oracle.entity.Rooms")
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
		log.debug("finding Rooms instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Rooms as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Rooms> findByNo(Object no) {
		return findByProperty(NO, no);
	}

	public List<Rooms> findByDeposit(Object deposit) {
		return findByProperty(DEPOSIT, deposit);
	}

	public List<Rooms> findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List<Rooms> findByImgcover(Object imgcover) {
		return findByProperty(IMGCOVER, imgcover);
	}

	public List<Rooms> findByRoomstatus(Object roomstatus) {
		return findByProperty(ROOMSTATUS, roomstatus);
	}

	public List<Rooms> findByPosition(Object position) {
		return findByProperty(POSITION, position);
	}

	public List findAll() {
		log.debug("finding all Rooms instances");
		try {
			String queryString = "from Rooms";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Rooms merge(Rooms detachedInstance) {
		log.debug("merging Rooms instance");
		try {
			Rooms result = (Rooms) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Rooms instance) {
		log.debug("attaching dirty Rooms instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Rooms instance) {
		log.debug("attaching clean Rooms instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RoomsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RoomsDAO) ctx.getBean("RoomsDAO");
	}

	public List<Rooms> findAll(int i) {
		log.debug("finding all Rooms instances");
		try {
			String queryString = "select distinct r from Rooms r left "
					+ " join fetch r.roomtypes rt left join fetch r.roomselects rsl left join fetch"
					+ " r.roomstatus rst order by r.id ";
			Query queryObject = getCurrentSession().createQuery(queryString);			
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Rooms findById(Integer roomId, int i) {
		String hql="select distinct r from Rooms r left join fetch r.roomtypes rt left join fetch r.roomstatus left join "
				+ "fetch r.roomselects rsl where r.id=:id";
		Query queryObject = getCurrentSession().createQuery(hql).setInteger("id", roomId);			
		return (Rooms) queryObject.uniqueResult();
	}
	
	//lv
	public List<Rooms> findAlllv(Integer i) { 
		log.debug("finding all Rooms instances");
		try {
			String queryString = "select distinct r from Rooms r "
					+ " left join fetch r.roomtypes rt "
					+ " left join fetch r.roomselects rsl "
					+ " left join fetch r.roomstatus rst "
					+ " where r.position=:position ";
			Query queryObject = getCurrentSession().createQuery(queryString).setInteger("position", i);			
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	//根据状态查房间
	public List<Rooms> findAllByStatues(Integer position,Integer roomstatus) {     //findAllByStatues
		log.debug("finding all Rooms instances");
		try {
			String queryString = "select distinct r from Rooms r left "
					+ " join fetch r.roomtypes rt left join fetch r.roomselects rsl left join fetch"
					+ " r.roomstatus rst where r.position=:position and r.roomstatus=:roomstatus ";
//			String queryString = "select distinct r from Rooms r left "
//					+ " join fetch r.roomtypes rt left join fetch r.roomselects rsl left join fetch"
//					+ " r.roomstatus rst where r.no like:no ";
//			String a="l"+i;
//			Query queryObject = getCurrentSession().createQuery(queryString).setString("no", "%"+a+"%");			
			Query queryObject = getCurrentSession().createQuery(queryString).setInteger("position", position).setInteger("roomstatus", roomstatus);			
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	//根据类型查房间
	public List<Rooms> findAllByTypes(Integer position,Integer roomstypes) {
		log.debug("finding all Rooms instances");
		try {
			String queryString = "select distinct r from Rooms r left "
					+ " join fetch r.roomtypes rt left join fetch r.roomselects rsl left join fetch"
					+ " r.roomstatus rst where r.position=:position and rt.id=:roomstypes ";
			Query queryObject = getCurrentSession().createQuery(queryString).setInteger("position", position).setInteger("roomstypes", roomstypes);			
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<Rooms> findAllByType(Integer typeId) {
		String hql = " select distinct r from Rooms r left join fetch r.roomtypes rt "
				+ " left join fetch r.roomselects rsl left join fetch r.roomstatus rst "
				+ " where r.roomtypes.id=:typeId ";
		return getCurrentSession().createQuery(hql).setInteger("typeId", typeId).list();
	}
	
	public Long allRooms(){
		String hql = "select count(r.id) from Rooms r ";
		return (Long) getCurrentSession().createQuery(hql).uniqueResult();
	}
	public Long countRooms(Integer roomstatus){
		String hql = "select count(r.id) from Rooms r where roomstatus=:roomstatus ";
		return (Long) getCurrentSession().createQuery(hql).setInteger("roomstatus", roomstatus).uniqueResult();
	}
	
	
	/**
	 * 用户退房时查询房间内用户
	 * @param roomId
	 * @param b
	 * @return
	 */
	public Rooms findById(Integer roomId, boolean b) {
		String hql="select distinct r from Rooms r "
				+ " left join fetch r.roomuserses ru "
				+ "	left join fetch  ru.usersputong  up "
				+ " left join fetch up.sex usex "
				+ " left join fetch up.userses u "
				+ " left join fetch r.customerorderses o "
				+ " left join fetch o.orderstatus ost "
				+ " left join fetch o.customerorderitemses oi "
				+ " left join fetch oi.goods oig  "
				+ "	left join fetch o.usersputong "
				+ " where r.id=:id  and  "
				+ " o.orderstatus in (1,3)";
		return (Rooms) getCurrentSession().createQuery(hql).
				setInteger("id", roomId).uniqueResult();
	}

	public List<Rooms> findAllUser() {
		String hql="select distinct r from Rooms r left join fetch r.roomuserses ru"
				+ " left join fetch ru.usersputong up where ru is not null ";
		return getCurrentSession().createQuery(hql).list();
	}

	public Rooms findRoomSelectByRoomId(Integer id) {
		String hql="select distinct r from Rooms r  "
				+ " left join fetch r.roomselects rsl "
				+ " left join fetch rsl.users ru "
				+ " left join fetch ru.usersputong "
				+ " where r.id=:id";
		Query queryObject = getCurrentSession().createQuery(hql).setInteger("id",id);			
		return (Rooms) queryObject.uniqueResult();
	}
	/**
	 * 修改房间状态
	 * @param room 
	 */
	public void alterRoomStatus(Rooms room) {
		String hql="update Rooms r set r.roomstatus=:roomstatus where r.id=:id";
		Query queryObject = getCurrentSession().createQuery(hql).setInteger("id",room.getId()).setInteger("roomstatus", room.getRoomstatus().getId());			
		queryObject.executeUpdate();
		
	}

	public List<Rooms> findAllByStatues(Integer roomstatus) {
		log.debug("finding all Rooms instances");
		try {
			String queryString = "select distinct r from Rooms r left "
					+ " join fetch r.roomtypes rt left join fetch r.roomselects rsl left join fetch "
					+ " r.roomstatus rst where r.roomstatus=:roomstatus ";		
			Query queryObject = getCurrentSession().createQuery(queryString).setInteger("roomstatus", roomstatus);			
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}