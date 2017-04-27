package rml.model;

public class UserBookFavourite {
	private String id;
	private String uid;
	private int favouriteState;
	private long favouriteData;
	public int getFavouriteState() {
		return favouriteState;
	}
	public void setFavouriteState(int favouriteState) {
		this.favouriteState = favouriteState;
	}
	public long getFavouriteData() {
		return favouriteData;
	}
	public void setFavouriteData(long favouriteData) {
		this.favouriteData = favouriteData;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}


}
