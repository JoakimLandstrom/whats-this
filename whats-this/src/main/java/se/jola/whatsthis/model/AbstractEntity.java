package se.jola.whatsthis.model;


//@MappedSuperclass
public abstract class AbstractEntity {
	
//	@Id
//	@GeneratedValue
	protected Long id;
	
	public Long getId() {
		return id;
	}
	
}
