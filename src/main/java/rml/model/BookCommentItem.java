package rml.model;

import rml.request.UserComment;

public class BookCommentItem {

	private UserComment userComment;
	private BookCommentUserItem bookCommentUserItem;
	public UserComment getUserComment() {
		return userComment;
	}
	public void setUserComment(UserComment userComment) {
		this.userComment = userComment;
	}
	public BookCommentUserItem getBookCommentUserItem() {
		return bookCommentUserItem;
	}
	public void setBookCommentUserItem(BookCommentUserItem bookCommentUserItem) {
		this.bookCommentUserItem = bookCommentUserItem;
	}
	
}
