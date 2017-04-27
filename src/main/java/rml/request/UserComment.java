package rml.request;

public class UserComment  {

	private String content;
	private long commentTime;

	private String id;
	private String uid;
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
	public long getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(long commentTime) {
		this.commentTime = commentTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	
}
