package ro.octa.retrofitsample.commons.model;

/**
 * @author Octa on 9/21/2015.
 */
public class Photo implements HasId<Long> {

    private long id;
    private long albumId;
    private String title;
    private String url;
    private String thumbnailUrl;

    @Override
    public Long getId() {
        return id;
    }

    public long getAlbumId() {
        return albumId;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
