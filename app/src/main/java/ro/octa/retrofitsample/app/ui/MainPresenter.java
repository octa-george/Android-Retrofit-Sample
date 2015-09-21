package ro.octa.retrofitsample.app.ui;

/**
 * @author Octa on 9/21/2015.
 */
public interface MainPresenter {

    void getAlbums();

    void getPhotos();

    void getPhotos(long albumId);
}
