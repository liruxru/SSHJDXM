package com.oracle.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.entity.UserRoomOrder;

@Transactional
public class UserRoomOrderDAO {
	private static final Logger log = LoggerFactory
			.getLogger(UserRoomOrderDAO.class);
	// property constants
	@Autowired
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

	public void save(UserRoomOrder transientInstance) {
		log.debug("saving UserRoomOrder instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public static UserRoomOrderDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserRoomOrderDAO) ctx.getBean("UserRoomOrderDAO");
	}
	
}
