package net.intelie.challenges;

import static org.junit.Assert.*;

import org.junit.Test;

import layer.flush.EventIteratorImpl;
import layer.main.ThreadTest;
import old.layer.entity.Event;

public class EventItaratorTest {

	private Event e1 = new Event("sales", 123l);
	private Event e2 = new Event("s_type", 124l);
	private Event e3 = new Event("s_type", 125l);
	private EventIteratorImpl listIterator = new EventIteratorImpl();
	
	@Test
	public void eventIterator() {
		
		listIterator.add(e1);
		listIterator.add(e2);
		listIterator.add(e3);
		
		assertEquals(e1,listIterator.current());
		listIterator.moveNext();
		assertEquals(e2,listIterator.current());
		listIterator.moveNext();
		assertEquals(e3,listIterator.current());
		
		listIterator.remove();
		assertEquals(e2,listIterator.current());
	}
	
	//suposed to fail.
	@Test
	public void threadTest() {
		Thread t1 = new Thread(new ThreadTest());
		t1.run();
		Thread t2 = new Thread(new ThreadTest());
		t2.run();
	}
	
	
}
