package rml.model;

public class BaseBook {

	private String bookId;
	private String imageUrl;
	private String bookName;
	private String bookRate;
	private int state;
	private int favourite;
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getFavourite() {
		return favourite;
	}
	public void setFavourite(int favourite) {
		this.favourite = favourite;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	} 
	public String getBookRate() {
		return bookRate;
	}
	public void setBookRate(String bookRate) {
		this.bookRate = bookRate;
	}	
}
