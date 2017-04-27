package rml.model;

import rml.request.UserComment;

public class CommentItem {

	private UserComment userComment;
	private BookDetails bookDetails;
	public UserComment getUserComment() {
		return userComment;
	}
	public void setUserComment(UserComment userComment) {
		this.userComment = userComment;
	}
	public BookDetails getBookDetails() {
		return bookDetails;
	}
	public void setBookDetails(BookDetails bookDetails) {
		this.bookDetails = bookDetails;
	}
	@Override
	public String toString() {
		return "CommentItem [userComment=" + userComment + ", bookDetails=" + bookDetails + "]";
	}
	
}
