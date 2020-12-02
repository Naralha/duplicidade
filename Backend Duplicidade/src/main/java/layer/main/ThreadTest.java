package layer.main;

import layer.dao.EventStoreDao;
import layer.entity.Event;
import layer.flush.EventIteratorImpl;

public class ThreadTest implements Runnable{
	public static EventStoreDao eventStoreDao = new EventStoreDao();
	@Override
	public void run() {
		Event e1 = new Event("event1", 123l);
		Event e2 = new Event("event2", 124l);
		Event e3 = new Event("event3", 125l);
		
		eventStoreDao.factory.getCurrentSession().beginTransaction();

		eventStoreDao.insert(e1);
		eventStoreDao.insert(e2);
		eventStoreDao.insert(e3);
		
		try(EventIteratorImpl listIterator =  (EventIteratorImpl) eventStoreDao.query(e2.type(), e2.timestamp(), e2.timestamp())){
			System.out.println(listIterator.current().type());
			System.out.println(listIterator.current().timestamp());
			listIterator.moveNext();
			System.out.println(listIterator.current().type());
			System.out.println(listIterator.current().timestamp());
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		
	}

}
