package ro.octa.retrofitsample.app.ui;

import java.util.List;

import ro.octa.retrofitsample.commons.model.Album;
import ro.octa.retrofitsample.commons.model.Photo;

/**
 * @author Octa on 9/21/2015.
 */
public interface MainView {

    void showError(String error);

    void addAlbums(List<Album> albums);

    void addPhotos(List<Photo> photos);
}
