package rml.model;

/**
 * Created by bblink on 2017/4/28.
 */
public class UserBookDetails extends BookDetails {
    private int borrowState;

    @Override
    public int getBorrowState() {
        return borrowState;
    }

    @Override
    public void setBorrowState(int borrowState) {
        this.borrowState = borrowState;
    }
}
