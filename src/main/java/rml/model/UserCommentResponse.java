package rml.model;

public class UserCommentResponse {

	private String image;
	private String name;
	private String content;
	private long commentTime;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(long commentTime) {
		this.commentTime = commentTime;
	}
}
