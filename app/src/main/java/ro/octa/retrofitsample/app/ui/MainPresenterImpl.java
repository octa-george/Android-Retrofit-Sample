package ro.octa.retrofitsample.app.ui;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ro.octa.retrofitsample.api.service.SampleService;
import ro.octa.retrofitsample.app.RetrofitSampleApplication;
import ro.octa.retrofitsample.commons.model.Album;
import ro.octa.retrofitsample.commons.model.Photo;

/**
 * @author Octa on 9/21/2015.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView view;

    public MainPresenterImpl(MainView mainView) {
        this.view = mainView;
    }

    @Override
    public void getAlbums() {
        RetrofitSampleApplication.getInstance().getSampleService().getAlbums(new Callback<List<Album>>() {
            @Override
            public void success(List<Album> albums, Response response) {
                view.addAlbums(albums);
            }

            @Override
            public void failure(RetrofitError error) {
                view.showError("Can't fetch any albums...");
            }
        });
    }

    @Override
    public void getPhotos() {
        RetrofitSampleApplication.getInstance().getSampleService().getPhotos(new Callback<List<Photo>>() {
            @Override
            public void success(List<Photo> photos, Response response) {
                view.addPhotos(photos);
            }

            @Override
            public void failure(RetrofitError error) {
                view.showError("Can't fetch any photos...");
            }
        });
    }

    @Override
    public void getPhotos(long albumId) {
        String url = "http://jsonplaceholder.typicode.com";
        SampleService sampleService = RetrofitSampleApplication.getInstance().getHostAdapter(url).create(SampleService.class);

        sampleService.getAlbumPhotos(albumId, new Callback<List<Photo>>() {
            @Override
            public void success(List<Photo> photos, Response response) {
                view.addPhotos(photos);
            }

            @Override
            public void failure(RetrofitError error) {
                view.showError("Can't fetch any photos for this album...");
            }
        });
    }
}
