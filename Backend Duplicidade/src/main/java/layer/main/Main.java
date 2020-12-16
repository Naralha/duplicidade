package layer.main;

import java.io.IOException;

import layer.dao.EventStoreDao;
import layer.dao.FuncionarioDao;
import layer.flush.EventIteratorImpl;
import layer.teste.Obra;
import old.layer.entity.Event;

public class Main{
	
//	public static EventStoreDao eventStoreDao = new EventStoreDao();
	
	public static void main(String[] args) throws IOException {
//		Thread t1 = new Thread(new ThreadTest());
//		t1.run();
		
		
//		Event e1 = new Event("sales", 123l);
//		Event e2 = new Event("s_type", 1);
//		Event e3 = new Event("s_type", 2);
//		
//		eventStoreDao.session.beginTransaction();
//
//		eventStoreDao.insert(e1);
//		eventStoreDao.insert(e2);
//		eventStoreDao.insert(e3);
//		
//		eventStoreDao.removeAll(e1.type());
		
//		try(EventIteratorImpl listIterator =  (EventIteratorImpl) eventStoreDao.query(e2.type(), 1,1)){
//			System.out.println(listIterator.current().type());
//			System.out.println(listIterator.current().timestamp());
//			listIterator.moveNext();
//			System.out.println(listIterator.current().type());
//			System.out.println(listIterator.current().timestamp());
//		} catch (Exception ee) {
//			ee.printStackTrace();
//		}
//		
		
//		System.out.println(e.type());
//		System.out.println(e.timestamp());
		
//		eventStoreDao.endTransaction();
//		fluxo1.valoresProp();
		FuncionarioDao funcionarioDao1 = new FuncionarioDao();
//		FuncionarioDao funcionarioDao2 = new FuncionarioDao();
//		FuncionarioDao funcionarioDao3 = new FuncionarioDao();
//		FuncionarioDao funcionarioDao4 = new FuncionarioDao();
		
//		funcionarioDao1.factory.getCurrentSession().beginTransaction();
//		funcionarioDao2.factory.getCurrentSession().beginTransaction();
//		funcionarioDao3.factory.getCurrentSession().beginTransaction();
//		funcionarioDao4.factory.getCurrentSession().beginTransaction();
		
		
		long inicio = System.currentTimeMillis();
		Obra f = new Obra();
		f.dividirLinhas();
		System.out.println(System.currentTimeMillis() - inicio);
		
		FluxoThread fluxo1 = new FluxoThread("split1",funcionarioDao1);
		FluxoThread fluxo2 = new FluxoThread("split2",funcionarioDao1);
		FluxoThread fluxo3 = new FluxoThread("split3",funcionarioDao1);
		FluxoThread fluxo4 = new FluxoThread("split4",funcionarioDao1);
		
		Thread threadMultiplicador1 = new Thread(fluxo1);
		Thread threadMultiplicador2 = new Thread(fluxo2);
		Thread threadMultiplicador3 = new Thread(fluxo3);
		Thread threadMultiplicador4 = new Thread(fluxo4);
		
		threadMultiplicador1.start();
		threadMultiplicador2.start();
		threadMultiplicador3.start();
		threadMultiplicador4.start();
		
		funcionarioDao1.factory.getCurrentSession().close();
//		funcionarioDao2.endTransaction();
//		funcionarioDao3.endTransaction();
//		funcionarioDao4.endTransaction();
		
	}
	
}
