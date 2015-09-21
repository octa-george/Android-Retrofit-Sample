package ro.octa.retrofitsample.app.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ro.octa.retrofitsample.commons.model.Album;

/**
 * @author Octa on 9/21/2015.
 */
public class AlbumsAdapter extends ArrayAdapter<Album> {

    private final Context context;
    private final List<Album> values;

    public AlbumsAdapter(Context context, List<Album> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        final AlbumViewHolder holder;

        // reuse views
        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            rowView = inflater.inflate(android.R.layout.simple_list_item_1, null);
            // configure view holder
            holder = new AlbumViewHolder();

            if (rowView != null) {
                holder.title = (TextView) rowView.findViewById(android.R.id.text1);

                rowView.setTag(holder);
            }
        } else {
            holder = (AlbumViewHolder) convertView.getTag();
        }
        if (rowView != null) {
            Album album = values.get(position);
            if (album != null) {
                holder.title.setText(album.getTitle());
            }
        }

        return rowView;
    }

    private static class AlbumViewHolder {
        public TextView title;
    }
}

