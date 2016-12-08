package se.jola.whatsthis.model;

import javax.persistence.OneToMany;

public final class SavedPOI extends POI {

    @OneToMany
    private User user;

    private String userComment;

    private Rate rate;

    public enum Rate {
	VeryBad, Bad, Average, Good, Excellent;
    }

    protected SavedPOI() {
    }

    public SavedPOI(User user, Rate rate) {
	this.user = user;
	this.userComment = "";
	this.rate = rate;
    }

    @Override
    public boolean equals(Object obj) {

	if (this == obj) {
	    return true;
	}

	if (obj instanceof SavedPOI) {

	    SavedPOI otherPoi = (SavedPOI) obj;

	    return userComment.equals(otherPoi.getUserComment()) && rate.equals(otherPoi.getRate());
	}

	return false;
    }

    @Override
    public int hashCode() {
	int result = 17;
	result += 31 * userComment.hashCode();
	result += 31 * rate.hashCode();
	return result;
    }

    public User getUser() {
	return user;
    }

    public String getUserComment() {
	return userComment;
    }

    public Rate getRate() {
	return rate;
    }

    public SavedPOI setUser(User user) {
	this.user = user;
	return this;
    }

    public SavedPOI setUserComment(String userComment) {
	this.userComment = userComment;
	return this;
    }

    public SavedPOI setRate(Rate rate) {
	this.rate = rate;
	return this;
    }

}
