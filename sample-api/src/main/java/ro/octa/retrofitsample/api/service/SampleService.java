package ro.octa.retrofitsample.api.service;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import ro.octa.retrofitsample.commons.model.Album;
import ro.octa.retrofitsample.commons.model.Photo;

/**
 * @author Octa on 9/21/2015.
 */
public interface SampleService {

    @GET("/albums")
    void getAlbums(Callback<List<Album>> callback);

    @GET("/albums/{albumId}/photos")
    void getAlbumPhotos(@Path("albumId") long id, Callback<List<Photo>> callback);

    @GET("/photos")
    void getPhotos(Callback<List<Photo>> callback);
}
