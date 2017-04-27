package rml.model;

public class UserBookState {

	private String id ;
	private String uid;
	private int state;
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
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "UserBookState [id=" + id + ", uid=" + uid + ", state=" + state + "]";
	}
}
