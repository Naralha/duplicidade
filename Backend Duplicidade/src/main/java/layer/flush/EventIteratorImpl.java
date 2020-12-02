package layer.flush;

import java.util.ArrayList;

import layer.entity.Event;

public class EventIteratorImpl extends ArrayList<Event> implements EventIterator {

	private int count = 0; 
	
	@Override
	public boolean moveNext() {
		if(count == this.size() - 1) {
			return false;
		}else {
				count++;
				return true;
		}
	}

	@Override
	public Event current() throws IllegalStateException{
		if(this.size() > 0) {
			return this.get(count);
		}else {
			return null;
		}
	}

	@Override
	public void remove() throws IllegalStateException{
		if(this.size() > 0) {
			if(count == this.size() - 1){
				this.remove(count);
				count--;
			}else {
				this.remove(count);
			} 
			
		}
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

}