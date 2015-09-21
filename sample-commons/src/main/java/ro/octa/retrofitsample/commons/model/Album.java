package ro.octa.retrofitsample.commons.model;

/**
 * @author Octa on 9/21/2015.
 */
public class Album implements HasId<Long> {

    private long id;
    private long userId;
    private String title;

    @Override
    public Long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }
}
