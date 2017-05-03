package rml.model;

/**
 * Created by bblink on 2017/4/28.
 */
public class BookNexus<T> {
    private T t;
    private String id ;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
