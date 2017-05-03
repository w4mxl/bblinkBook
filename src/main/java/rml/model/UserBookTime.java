package rml.model;

/**
 * Created by bblink on 2017/5/3.
 */
public class UserBookTime {
    private BookDetails bookDetails;
    private Long creatTime;

    public BookDetails getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(BookDetails bookDetails) {
        this.bookDetails = bookDetails;
    }

    public Long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Long creatTime) {
        this.creatTime = creatTime;
    }
}
