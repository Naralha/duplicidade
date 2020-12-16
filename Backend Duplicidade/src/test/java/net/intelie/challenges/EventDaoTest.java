package net.intelie.challenges;

import static org.junit.Assert.*;

import org.junit.Test;

import layer.dao.EventStoreDao;
import layer.flush.EventIteratorImpl;
import old.layer.entity.Event;

public class EventDaoTest {
	private Event e1 = new Event("sales", 123l);
	private Event e2 = new Event("s_type", 124l);
	private Event e3 = new Event("s_type", 125l);
	private static EventStoreDao eventStoreDao = new EventStoreDao();
	
	
	@Test
	public void queryTest() {
		EventIteratorImpl listIteratorOut = new EventIteratorImpl();
		listIteratorOut.add(e2);
		listIteratorOut.add(e3);
		
		eventStoreDao.factory.getCurrentSession().beginTransaction();

		eventStoreDao.insert(e1);
		eventStoreDao.insert(e2);
		eventStoreDao.insert(e3);
		
		try(EventIteratorImpl listIterator =  (EventIteratorImpl) eventStoreDao.query(e2.type(), e2.timestamp(),e3.timestamp())){
			
			assertEquals(listIterator,listIteratorOut);
			
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		
		//invert timestamp test
		try(EventIteratorImpl listIterator =  (EventIteratorImpl) eventStoreDao.query(e3.type(), e3.timestamp(),e2.timestamp())){
			
			assertEquals(listIterator,listIteratorOut);
			
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		
		eventStoreDao.endTransaction();
		
	}
	
}
