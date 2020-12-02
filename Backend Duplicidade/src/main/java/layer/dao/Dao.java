package layer.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import layer.entity.Event;
import layer.teste.Funcionario;


public class Dao {

	public static SessionFactory factory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Event.class)
			.addAnnotatedClass(Funcionario.class)
			.buildSessionFactory();

//	public Session session = factory.getCurrentSession();
	
	
	public void endTransaction() {
		
		factory.getCurrentSession().getTransaction().commit();
		factory.getCurrentSession().close();
	}
	
	
	
}
