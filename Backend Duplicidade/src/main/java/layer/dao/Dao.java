package layer.dao;

import java.math.BigInteger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import layer.entity.Event;
import layer.teste.Obra;


public class Dao {

	public static SessionFactory factory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Event.class)
			.addAnnotatedClass(Obra.class)
			.buildSessionFactory();


	protected Session session = factory.getCurrentSession();
	
	public void endTransaction() {
		
		factory.getCurrentSession().getTransaction().commit();
		factory.getCurrentSession().close();
	}
	
	//solucao mais generica do mundo pqp
	public long sequence() {
		BigInteger b = (BigInteger) session.createSQLQuery("select next value for Sequencia").uniqueResult();
		return b.longValue() ;
	}
	
	protected  void iniciarOperacao(){
		session.beginTransaction();
	}
	
	protected  void finalizarOperacao() {
		
		session.getTransaction().commit();
		session.close();
	}
	
	
	
}
