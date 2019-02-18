/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package com.windea.utility.utils.hibernate;

import com.windea.study.hibernate.main.domain.*;
import com.windea.utility.base.annotation.Outlook;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Hibernate的工具类
 */
@Deprecated
public class HibernateUtils {
	private static final String INTERCEPTOR_CLASS = "hibernate.utils.interceptor_class";
	private static Log log = LogFactory.getLog(HibernateUtils.class);
	private static Configuration configuration;
	private static SessionFactory sessionFactory;

	//NOTE 在这里配置实体类
	private static Class[] annotatedClasses = {
			User.class, Client.class, LinkMan.class, User2.class, Role.class
	};

	static {
		try {
			//STEP 读取hibernate.cfg.xml文件，创建SessionFactory
			configuration = new Configuration();
			configuration.configure();
			//STEP 如果在配置文件中配置了拦截器，则将其设置到configuration对象中
			String interceptorName = configuration.getProperty(INTERCEPTOR_CLASS);
			if(interceptorName != null) {
				Class<?> interceptorClass = HibernateUtils.class.getClassLoader().loadClass(interceptorName);
				Interceptor interceptor = (Interceptor) interceptorClass.getConstructor().newInstance();
				configuration.setInterceptor(interceptor);
			}
			//STEP 加入通过注解方式实现持久化的实体类
			for(var clazz : annotatedClasses) {
				configuration.addAnnotatedClass(clazz);
			}
			//STEP 构建SessionFactory
			sessionFactory = configuration.buildSessionFactory();
			//			if(configuration.getProperty(Environment.SESSION_FACTORY_NAME) != null) {
			//				configuration.buildSessionFactory();
			//			} else {
			//				sessionFactory = configuration.buildSessionFactory();
			//			}
		} catch(Throwable e) {
			log.error("Building SessionFactory failed.", e);
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}


	/**
	 * 返回原始的Configuration对象的实例。
	 */
	public static Configuration getConfiguration() {
		return configuration;
	}


	/**
	 * 返回全局的SessionFactory对象的实例。
	 */
	public static SessionFactory getSessionFactory() {
		//针对系统是否将SessionFactory对象的实例绑定到JNDI，分别进行处理
		SessionFactory factory;
		String name = configuration.getProperty(AvailableSettings.SESSION_FACTORY_NAME);
		if(name != null) {
			log.debug("Looking up SessionFactory in JNDI.");
			try {
				sessionFactory = (SessionFactory) new InitialContext().lookup(name);
			} catch(NamingException e) {
				throw new RuntimeException();
			}
		}
		if(sessionFactory == null)
			throw new IllegalStateException("SessionFactory is not available.");
		return sessionFactory;
	}

	/**
	 * 关闭当前的SessionFactory，并且释放所有资源。
	 */
	public static void closeSessionFactory() {
		log.debug("Shutting down Hibernate.");
		configuration = null;
		if(sessionFactory != null && !sessionFactory.isClosed()) {
			sessionFactory.close();
			sessionFactory = null;
		}
	}

	/**
	 * 使用静态的Configuration对象重新创建SessionFactory。
	 */
	public static void rebuildSessionFactory() {
		log.debug("Using current Configuration for rebuild.");
		rebuildSessionFactory(configuration);
	}

	/**
	 * 使用指定的Configuration对象重新创建SessionFactory。
	 */
	public static void rebuildSessionFactory(@NotNull Configuration cfg) {
		log.debug("Rebuilding the SessionFactory for given Configuration.");
		synchronized(SessionFactory.class) {
			configuration = cfg;
			if(sessionFactory != null && !sessionFactory.isClosed()) {
				sessionFactory.close();
			}
			sessionFactory = cfg.buildSessionFactory();
			//			if(cfg.getProperty(Environment.SESSION_FACTORY_NAME) != null) {
			//				cfg.buildSessionFactory();
			//			} else {
			//				sessionFactory = cfg.buildSessionFactory();
			//			}
		}
	}


	/**
	 * 得到拦截器对象。
	 */
	public static Interceptor getInterceptor() {
		return configuration.getInterceptor();
	}

	/**
	 * 在当前SessionFactory中注册一个拦截器，然后重新构建。
	 */
	public static SessionFactory registerInterceptor(@NotNull Interceptor interceptor) {
		log.debug("Setting new global Hibernate interceptor and restarting.");
		configuration.setInterceptor(interceptor);
		rebuildSessionFactory();
		return getSessionFactory();
	}


	/**
	 * 执行一个事务。
	 * @param handler 事务操作的lambda session->void
	 */
	@Outlook(from = "spring")
	public static void doTransaction(@NotNull Consumer<Session> handler) {
		//得到与本地线程绑定的session
		var session = sessionFactory.getCurrentSession();
		var transaction = session.beginTransaction();
		try {
			handler.accept(session);
			transaction.commit();
		} catch(Exception e) {
			log.error("An exception happened in this transaction.");
			e.printStackTrace();
			transaction.rollback();
		}
	}

	/**
	 * 执行一个事务。
	 * @param handler 事务操作的lambda session->T
	 */
	@Outlook(from = "spring")
	public static <T> T doTransaction(@NotNull Function<Session, T> handler) {
		T result = null;
		//得到与本地线程绑定的session
		var session = sessionFactory.getCurrentSession();
		var transaction = session.beginTransaction();
		try {
			result = handler.apply(session);
			transaction.commit();
		} catch(Exception e) {
			log.error("An exception happened in this transaction.");
			e.printStackTrace();
			transaction.rollback();
		}
		return result;
	}

	/**
	 * 执行多个事务。
	 * @param handlers 多个事务操作的lambda session->void
	 */
	@Outlook(from = "spring")
	@SafeVarargs
	public static void doTransactions(@NotNull Consumer<Session>... handlers) {
		//得到与本地线程绑定国度session
		var session = sessionFactory.getCurrentSession();
		//循环处理事务
		for(var handler : handlers) {
			var transaction = session.beginTransaction();
			try {
				handler.accept(session);
				transaction.commit();
			} catch(Exception e) {
				log.error("An exception happened in a transaction.");
				transaction.rollback();
			}
		}
	}

	/**
	 * 执行多个事务。
	 * @param handlers 多个事务操作的lambda session->List&lt;Object&gt;
	 */
	@Outlook(from = "spring")
	@SafeVarargs
	@NotNull
	public static List doTransactions(@NotNull Function<Session, Object>... handlers) {
		List<Object> resultList = new ArrayList<>();
		//得到与本地线程绑定国度session
		var session = sessionFactory.getCurrentSession();
		//循环处理事务
		for(var handler : handlers) {
			var transaction = session.beginTransaction();
			try {
				resultList.add(handler.apply(session));
				transaction.commit();
			} catch(Exception e) {
				log.error("An exception happened in a transaction.");
				transaction.rollback();
			}
		}
		return resultList;
	}
}
