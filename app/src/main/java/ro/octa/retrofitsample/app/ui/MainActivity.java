package ro.octa.retrofitsample.app.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ro.octa.retrofitsample.app.R;
import ro.octa.retrofitsample.app.RetrofitSampleApplication;
import ro.octa.retrofitsample.app.ui.adapter.AlbumsAdapter;
import ro.octa.retrofitsample.app.ui.adapter.PhotosAdapter;
import ro.octa.retrofitsample.commons.model.Album;
import ro.octa.retrofitsample.commons.model.Photo;

/**
 * @author Octa on 9/21/2015.
 */
public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter presenter;
    private ListView listView;

    private PhotosAdapter photosAdapter;
    private List<Photo> photos = new ArrayList<>();
    private AlbumsAdapter albumsAdapter;
    private List<Album> albums = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // change the client endpoint to a Fake Online REST API for Testing and Prototyping
        RetrofitSampleApplication.getInstance().setEndpoint("http://jsonplaceholder.typicode.com");

        initView();

        presenter = new MainPresenterImpl(this);
        presenter.getAlbums();
    }

    private void initView() {
        listView = (ListView) findViewById(android.R.id.list);

        photosAdapter = new PhotosAdapter(MainActivity.this, photos);
        albumsAdapter = new AlbumsAdapter(MainActivity.this, albums);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getAdapter() instanceof AlbumsAdapter) {
                    Album album = (Album) parent.getAdapter().getItem(position);
                    presenter.getPhotos(album.getId());
                } else {
                    listView.setAdapter(albumsAdapter);
                    albumsAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void showError(String error) {
        Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void addAlbums(List<Album> albumList) {
        albumsAdapter.clear();
        albums.addAll(albumList);
        listView.setAdapter(albumsAdapter);
        albumsAdapter.notifyDataSetChanged();
    }


    @Override
    public void addPhotos(List<Photo> images) {
        photosAdapter.clear();
        photos.addAll(images);
        listView.setAdapter(photosAdapter);
        photosAdapter.notifyDataSetChanged();
    }
}
