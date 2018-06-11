package com.oracle.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.bean.Search;
import com.oracle.entity.Users;

/**
 * A data access object (DAO) providing persistence and search support for Users
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.oracle.entity.Users
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class UsersDAO {
	private static final Logger log = LoggerFactory.getLogger(UsersDAO.class);
	// property constants
	public static final String LOGINNAME = "loginname";
	public static final String PASSWORD = "password";
	public static final String AGE = "age";
	public static final String PHONE = "phone";
	public static final String EMAIL = "email";
	public static final String ADDRESS = "address";
	public static final String JIFEN = "jifen";
	public static final String NUM = "num";

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

	public void save(Users transientInstance) {
		log.debug("saving Users instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Users persistentInstance) {
		log.debug("deleting Users instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Users findById(java.lang.Integer id) {
		log.debug("getting Users instance with id: " + id);
		try {
			Users instance = (Users) getCurrentSession().get(
					"com.oracle.entity.Users", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Users> findByExample(Users instance) {
		log.debug("finding Users instance by example");
		try {
			List<Users> results = (List<Users>) getCurrentSession()
					.createCriteria("com.oracle.entity.Users")
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
		log.debug("finding Users instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Users as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Users> findByLoginname(Object loginname) {
		return findByProperty(LOGINNAME, loginname);
	}
	
	public List<Users> findByLoginname(String loginname,int i) {
		String hql="select distinct u from  Users u left join fetch u.usersputong upt left join fetch u.usersvip uvip "
				+ " left join fetch upt.roomuserses rus left join fetch rus.rooms where u.loginname=:loginname ";
		return getCurrentSession().createQuery(hql).setString("loginname", loginname).list();
	}

	public List<Users> findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List<Users> findByAge(Object age) {
		return findByProperty(AGE, age);
	}

	public List<Users> findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List<Users> findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List<Users> findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List<Users> findByJifen(Object jifen) {
		return findByProperty(JIFEN, jifen);
	}

	public List<Users> findByNum(Object num) {
		return findByProperty(NUM, num);
	}

	public List findAll(Integer offset, Integer length, Search search) {
		log.debug("finding all Users instances");
		try {
			StringBuffer hql=new StringBuffer("from Users u left join fetch u.usersvip where 1=1 ");
			if (search!=null) {
				if (search.getCxusername()!=null && !"".equals(search.getCxusername())) {
					hql.append(" and u.username like :username");
				}
				if (search.getCxloginname()!=null && !"".equals(search.getCxloginname())) {
					hql.append(" and u.loginname =:loginname");
				}
				if (search.getVipId()!=null && search.getVipId()!=0) {
					hql.append(" and u.usersvip =:usersvip");
				}
				if (search.getCxminzhuce()!=null && !"".equals(search.getCxminzhuce())) {
					hql.append(" and u.createdate >:minzhuce" );
				}
				if (search.getCxmaxzhuce()!=null && !"".equals(search.getCxmaxzhuce())) {
					hql.append(" and u.createdate <:maxzhuce");
				}
				if (search.getCxminJifen()!=null && !"".equals(search.getCxminJifen())) {
					hql.append(" and u.jifen >:minJifen");
				}
				if (search.getCxmaxJifen()!=null && !"".equals(search.getCxmaxJifen())) {
					hql.append(" and u.jifen <:maxJifen");
				}
				if (search.getCxshengxu()!=null && !"".equals(search.getCxshengxu())) {
					hql.append(" order by u.jifen asc");
				}
				if (search.getCxjiangxu()!=null && !"".equals(search.getCxjiangxu())) {
					hql.append(" order by u.jifen desc");
				}
				
			}
			Query queryObject = getCurrentSession().createQuery(hql.toString());
			if (search!=null) {
				if (search.getCxusername()!=null && !"".equals(search.getCxusername())) {
					queryObject.setString("username", "%"+search.getCxusername()+"%");
				}
				if (search.getCxloginname()!=null && !"".equals(search.getCxloginname())) {
					queryObject.setString("loginname", search.getCxloginname());
				}
				if (search.getVipId()!=null && search.getVipId()!=0) {
					queryObject.setInteger("usersvip", search.getVipId());
				}
				if (search.getCxminzhuce()!=null && !"".equals(search.getCxminzhuce())) {
					queryObject.setTimestamp("minzhuce", search.getCxminzhuce());
				}
				if (search.getCxmaxzhuce()!=null && !"".equals(search.getCxmaxzhuce())) {
					queryObject.setTimestamp("maxzhuce", search.getCxmaxzhuce());
				}
				if (search.getCxminJifen()!=null && !"".equals(search.getCxminJifen())) {
					queryObject.setBigDecimal("minJifen", search.getCxminJifen());
				}
				if (search.getCxmaxJifen()!=null && !"".equals(search.getCxmaxJifen())) {
					queryObject.setBigDecimal("maxJifen", search.getCxmaxJifen());
				}
			}
			queryObject.setFirstResult(offset);
			queryObject.setMaxResults(length);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Users merge(Users detachedInstance) {
		log.debug("merging Users instance");
		try {
			Users result = (Users) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Users instance) {
		log.debug("attaching dirty Users instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Users instance) {
		log.debug("attaching clean Users instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UsersDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UsersDAO) ctx.getBean("UsersDAO");
	}
	
	/**
	 * 通过电话号码查询用户预定信息
	 * @param phone
	 * @return
	 */
	public List<Users> findByPhone(String  phone) {
		String hql="select distinct u from Users u left join fetch "
				+ "u.roomselects  urs left join fetch "
				+ " urs.rooms ur where u.phone=:phone ";
		Query query=getCurrentSession().createQuery(hql).setString("phone", phone);
		return query.list();
	}
	/**
	 * 通过普通用户id查询会员
	 * @param id
	 * @return
	 */
	public Users findByUserPutongId(Integer id) {
		String hql="from Users u left join fetch u.usersvip where u.usersputong=:id";
		Users user=null;
		try {
			user=(Users) getCurrentSession().createQuery(hql).setInteger("id", id).uniqueResult();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	/**
	 * 通过电话号码查询会员信息
	 * @param phone2
	 * @param i
	 * @return
	 */
	public Users findByPhone(String phone, int i) {
		String hql="from Users u left join fetch u.usersvip "
				+ " left join fetch u.usersputong up"
				+ " left join fetch up.sex usex"
				+ "  where u.phone=:phone";
		Users user=null;
		try {
			user=(Users)(Users) getCurrentSession().createQuery(hql).setString("phone", phone).uniqueResult();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			return user;
		}

	/**
	 * 获取总的记录数
	 * @param search 
	 * @return
	 */
	public Integer countId(Search search) {
		StringBuffer hql=new StringBuffer("select count(id) from Users u  where 1=1 ");
		if (search!=null) {
			if (search.getCxusername()!=null && !"".equals(search.getCxusername())) {
				hql.append(" and u.username like :username");
			}
			if (search.getCxloginname()!=null && !"".equals(search.getCxloginname())) {
				hql.append(" and u.loginname =:loginname");
			}
			if (search.getVipId()!=null && search.getVipId()!=0) {
				hql.append(" and u.usersvip =:usersvip");
			}
			if (search.getCxminzhuce()!=null && !"".equals(search.getCxminzhuce())) {
				hql.append(" and u.createdate >:minzhuce" );
			}
			if (search.getCxmaxzhuce()!=null && !"".equals(search.getCxmaxzhuce())) {
				hql.append(" and u.createdate <:maxzhuce");
			}
			if (search.getCxminJifen()!=null && !"".equals(search.getCxminJifen())) {
				hql.append(" and u.jifen >:minJifen");
			}
			if (search.getCxmaxJifen()!=null && !"".equals(search.getCxmaxJifen())) {
				hql.append(" and u.jifen <:maxJifen");
			}
			if (search.getCxshengxu()!=null && !"".equals(search.getCxshengxu())) {
				hql.append(" order by u.jifen asc");
			}
			if (search.getCxjiangxu()!=null && !"".equals(search.getCxjiangxu())) {
				hql.append(" order by u.jifen desc");
			}
			
		}
		Query queryObject = getCurrentSession().createQuery(hql.toString());
		if (search!=null) {
			if (search.getCxusername()!=null && !"".equals(search.getCxusername())) {
				queryObject.setString("username", "%"+search.getCxusername()+"%");
			}
			if (search.getCxloginname()!=null && !"".equals(search.getCxloginname())) {
				queryObject.setString("loginname", search.getCxloginname());
			}
			if (search.getVipId()!=null && search.getVipId()!=0) {
				queryObject.setInteger("usersvip", search.getVipId());
			}
			if (search.getCxminzhuce()!=null && !"".equals(search.getCxminzhuce())) {
				queryObject.setTimestamp("minzhuce", search.getCxminzhuce());
			}
			if (search.getCxmaxzhuce()!=null && !"".equals(search.getCxmaxzhuce())) {
				queryObject.setTimestamp("maxzhuce", search.getCxmaxzhuce());
			}
			if (search.getCxminJifen()!=null && !"".equals(search.getCxminJifen())) {
				queryObject.setBigDecimal("minJifen", search.getCxminJifen());
			}
			if (search.getCxmaxJifen()!=null && !"".equals(search.getCxmaxJifen())) {
				queryObject.setBigDecimal("maxJifen", search.getCxmaxJifen());
			}
			
		}
		Long countId=(Long)queryObject.uniqueResult();
		return Integer.parseInt(countId.toString());
	}
}