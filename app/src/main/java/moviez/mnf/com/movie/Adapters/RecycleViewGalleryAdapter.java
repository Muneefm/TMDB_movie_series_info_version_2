package moviez.mnf.com.movie.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import moviez.mnf.com.movie.Activity.ImageViewActivity;
import moviez.mnf.com.movie.Activity.MovieDetails;
import moviez.mnf.com.movie.DataSet.Posters.Backdrop;
import moviez.mnf.com.movie.R;
import moviez.mnf.com.movie.tools.Config;
import moviez.mnf.com.movie.tools.Utils;

/**
 * Created by Muneef on 05/05/15.
 */
public class RecycleViewGalleryAdapter extends RecyclerView.Adapter<RecycleViewGalleryAdapter.ViewHolder> {
    Context c;
    List<Backdrop> mDataset;
    MovieDetails maa;
    ImageLoader im;
    //t.setText(Html.fromHtml(first + next));
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View viewi;
        public final CardView cv;
        public final ImageView image;
        public ViewHolder(View v) {
            super(v);
            viewi = v;
            image  = (ImageView) v.findViewById(R.id.imageAll);
            cv = (CardView) v.findViewById(R.id.itemCard);
        }
    }


    public RecycleViewGalleryAdapter(Context mContext) {
        this.c = mContext;
        this.mDataset = new ArrayList<Backdrop>();

    }
    public void addItems(List<Backdrop> newItems) {


        this.mDataset.addAll(newItems);

        // this.mDataset = newItems;
        //  Toast.makeText(c, "added in re " + mDataset.size(), Toast.LENGTH_LONG).show();
//notifyDataSetChanged();
        notifyItemRangeInserted(this.mDataset.size(), newItems.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        im = ImageLoader.getInstance();
        im.init(ImageLoaderConfiguration.createDefault(c));


        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

       // im.displayImage("http://image.tmdb.org/t/p/w500" + mDataset.get(position).getFilePath(), holder.image);
        Utils.loadImage(holder.image,  mDataset.get(position).getFilePath(),6);
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(c, ImageViewActivity.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                myIntent.putExtra("id",mDataset.get(position).getFilePath().toString());
                myIntent.putExtra("key","1");
                c.startActivity(myIntent);
               // ImageViewFragemnt frag = ImageViewFragemnt.getInstance("test");
               // maa.getSupportFragmentManager().beginTransaction().add(R.id.contkk,frag).commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
