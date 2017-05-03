package rml.model;

import java.util.List;

/**
 * Created by bblink on 2017/5/3.
 */
public class userBookListResponse {
    private List<UserBookTime> reading;
    private List<UserBookTime> wantRead;
    private List<UserBookTime> readed;

    public List<UserBookTime> getReading() {
        return reading;
    }

    public void setReading(List<UserBookTime> reading) {
        this.reading = reading;
    }

    public List<UserBookTime> getWantRead() {
        return wantRead;
    }

    public void setWantRead(List<UserBookTime> wantRead) {
        this.wantRead = wantRead;
    }

    public List<UserBookTime> getReaded() {
        return readed;
    }

    public void setReaded(List<UserBookTime> readed) {
        this.readed = readed;
    }
}
