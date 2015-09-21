package ro.octa.retrofitsample.app.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ro.octa.retrofitsample.app.R;
import ro.octa.retrofitsample.commons.model.Photo;

/**
 * @author Octa on 9/21/2015.
 */
public class PhotosAdapter extends ArrayAdapter<Photo> {

    private final Context context;
    private final List<Photo> values;

    public PhotosAdapter(Context context, List<Photo> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        final PhotoViewHolder holder;

        // reuse views
        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            rowView = inflater.inflate(R.layout.list_item_home, null);
            // configure view holder
            holder = new PhotoViewHolder();

            if (rowView != null) {
                holder.title = (TextView) rowView.findViewById(R.id.list_item_home_text);
                holder.cover = (ImageView) rowView.findViewById(R.id.list_item_home_cover);

                rowView.setTag(holder);
            }
        } else {
            holder = (PhotoViewHolder) convertView.getTag();
        }
        if (rowView != null) {
            Photo photo = values.get(position);
            if (photo != null) {
                holder.title.setText(photo.getTitle());
                Picasso.with(context).load(photo.getThumbnailUrl()).fit().centerCrop().into(holder.cover);
            }
        }

        return rowView;
    }

    private static class PhotoViewHolder {
        public TextView title;
        public ImageView cover;
    }
}
