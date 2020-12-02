package layer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This is just an event stub, feel free to expand it if needed.
 */
@Entity
@Table(name = "tbEvent")
public class Event {
	@Id @Column(name="id") @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "nVarType", nullable = false)
    private final String type;
	
	@Column(name = "nVarTimestamp", nullable = false)
    private final long timestamp;

    public Event(String type, long timestamp) {
        this.type = type;
        this.timestamp = timestamp;
    }

    public String type() {
        return type;
    }

    public long timestamp() {
        return timestamp;
    }
}
