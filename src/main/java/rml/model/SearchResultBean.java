package rml.model;

/**
 * Created by BBLink on 2017/5/2.
 */
public class SearchResultBean {


    private String[] author;
    private BookImages images;
    private String id;
    private String publisher;
    private String title;

    public String[] getAuthor() {
        return author;
    }

    public void setAuthor(String[] author) {
        this.author = author;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(id);
    }

    @Override
    public boolean equals(Object obj) {

        SearchResultBean searchResult =(SearchResultBean)obj;
        return searchResult.id.equals(getId());
    }
}
