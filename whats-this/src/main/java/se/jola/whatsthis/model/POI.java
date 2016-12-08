package se.jola.whatsthis.model;

//@Entity
//@EntityListeners(AuditingEntityListener.class)
public class POI extends AbstractEntity {

    private String name;

    private String type = "";

    private String description = "";

    protected POI() {
    }

    public POI(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public String getType() {
	return type;
    }

    public String getDescription() {
	return description;
    }

    public POI setName(String name) {
	this.name = name;
	return this;
    }

    public POI setType(String type) {
	this.type = type;
	return this;
    }

    public POI setDescription(String description) {
	this.description = description;
	return this;
    }

    @Override
    public boolean equals(Object obj) {

	if (this == obj) {
	    return true;
	}

	if (obj instanceof POI) {

	    POI otherPoi = (POI) obj;

	    return name.equals(otherPoi.getName()) && type.equals(otherPoi.getType())
		    && description.equals(otherPoi.getDescription());
	}

	return false;
    }

    @Override
    public int hashCode() {
	int result = 17;
	result += 31 * name.hashCode();
	result += 31 * type.hashCode();
	result += 31 * description.hashCode();
	return result;
    }
}
