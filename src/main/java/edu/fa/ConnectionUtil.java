package edu.fa;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.mysql.cj.xdevapi.SessionFactory;

public class ConnectionUtil {
	
	private static org.hibernate.SessionFactory sessionFactory = null;

	public static org.hibernate.SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(registry);
		}
		 return  sessionFactory;	
	}
}
