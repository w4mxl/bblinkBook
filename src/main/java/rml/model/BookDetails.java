package rml.model;

import java.util.List;

public class BookDetails {
	
	private BookRating rating;
	private String[] author;
	private String pubdate;
	private List<BookTags> tags;
	private String catalog;
	private String ebook_url;
	private String pages;
	private BookImages images;
	private String id;
	private String publisher;
	private String isbn13;
	private String title;
	private String author_intro; 
	private String summary;
	private String price;
	private int borrowState;

	public int getBorrowState() {
		return borrowState;
	}

	public void setBorrowState(int borrowState) {
		this.borrowState = borrowState;
	}

	public BookRating getRating() {
		return rating;
	}
	public void setRating(BookRating rating) {
		this.rating = rating;
	}

	public String[] getAuthor() {
		return author;
	}
	public void setAuthor(String[] author) {
		this.author = author;
	}
	public String getPubdate() {
		return pubdate;
	}
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	public List<BookTags> getTags() {
		return tags;
	}
	public void setTags(List<BookTags> tags) {
		this.tags = tags;
	}
	public String getCatalog() {
		return catalog;
	}
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	public String getEbook_url() {
		return ebook_url;
	}
	public void setEbook_url(String ebook_url) {
		this.ebook_url = ebook_url;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	public BookImages getImages() {
		return images;
	}
	public void setImages(BookImages images) {
		this.images = images;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getIsbn13() {
		return isbn13;
	}
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor_intro() {
		return author_intro;
	}
	public void setAuthor_intro(String author_intro) {
		this.author_intro = author_intro;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "BookDetails [rating=" + rating + ", author=" + author + ", pubdate=" + pubdate + ", tags=" + tags
				+ ", catalog=" + catalog + ", ebook_url=" + ebook_url + ", pages=" + pages + ", images=" + images
				+ ", id=" + id + ", publisher=" + publisher + ", isbn13=" + isbn13 + ", title=" + title
				+ ", author_intro=" + author_intro + ", summary=" + summary + ", price=" + price + "]";
	}

	
	


}