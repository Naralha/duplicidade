package layer.dao;

import java.util.ArrayList;
import java.util.List;

import layer.entity.Event;
import layer.flush.EventIterator;
import layer.flush.EventIteratorImpl;

public class EventStoreDao extends Dao implements EventStore {


	@Override
	public void insert(Event event) {
		this.factory.getCurrentSession().save(event);
	}

	@Override
	public void removeAll(String type) {
		this.factory.getCurrentSession().createSQLQuery("Delete tbEvent where nVarType ='"+type+"'").executeUpdate();
	}

	@Override
	public EventIterator query(String type, long startTime, long endTime) {
		EventIteratorImpl iteratorEvents = new EventIteratorImpl();
		List<Event> listEvent = new ArrayList<Event>();
		
		if(startTime < endTime) {
			listEvent = this.factory.getCurrentSession().createQuery("SELECT a FROM Event a where a.type ='"+type+"' and (a.timestamp >= '"+startTime+"' and a.timestamp <= '"+endTime+"')"
					, Event.class).getResultList();
		}else if(startTime > endTime) {
			listEvent = this.factory.getCurrentSession().createQuery("SELECT a FROM Event a where a.type ='"+type+"' and (a.timestamp >= '"+endTime+"'and a.timestamp <= '"+startTime+"')"
					, Event.class).getResultList();
		}else {
			listEvent = this.factory.getCurrentSession().createQuery("SELECT a FROM Event a where a.type ='"+type+"' and a.timestamp = '"+startTime+"'"
					, Event.class).getResultList();
		}
		
		iteratorEvents.addAll(listEvent);
		
		return iteratorEvents;
	}

}
