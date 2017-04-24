package moviez.mnf.com.movie.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import moviez.mnf.com.movie.Activity.TvDetailActivity;
import moviez.mnf.com.movie.R;
import moviez.mnf.com.movie.tools.Config;
import moviez.mnf.com.movie.tools.Utils;

/**
 * Created by Muneef on 09/05/15.
 */
public class RecycleAdapterTv extends RecyclerView.Adapter<RecycleAdapterTv.ViewHolder>  {
    private List<moviez.mnf.com.movie.DataSet.TV.list.Result> mDataset;
    Context c;
    //t.setText(Html.fromHtml(first + next));
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View viewi;
        public final TextView text;//usersC;
        public final ImageView image;
       // public final RatingBar rate;
        public final CardView cv;
        public final RelativeLayout rlContainer;

       // public final TextView dateR;
        public ViewHolder(View v) {
            super(v);
            viewi = v;
            cv = (CardView) v.findViewById(R.id.tvcard);
            text = (TextView) v.findViewById(R.id.tvTitle);
            image  = (ImageView) v.findViewById(R.id.tvPoster);
          //  rate = (RatingBar) v.findViewById(R.id.tvRate);
            rlContainer = (RelativeLayout) v.findViewById(R.id.tag_container);
           // dateR = (TextView) v.findViewById(R.id.datelist);




        }
    }

    public RecycleAdapterTv(Context mContext) {
        this.c = mContext;
        this.mDataset = new ArrayList<moviez.mnf.com.movie.DataSet.TV.list.Result>();
    }
    public void addItems(List<moviez.mnf.com.movie.DataSet.TV.list.Result> newItems) {
        this.mDataset.addAll(newItems);
        // this.mDataset = newItems;
        //  Toast.makeText(c, "added in re "+mDataset.size(), Toast.LENGTH_LONG).show();
//notifyDataSetChanged();
        notifyItemRangeInserted(this.mDataset.size(), newItems.size());
    }
    public void reData(List<moviez.mnf.com.movie.DataSet.TV.list.Result> newIt){
        this.mDataset =newIt;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tv_list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters


        ViewHolder vh = new ViewHolder(v);
        return vh;


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        if(mDataset.get(position).getPosterPath()!=null){
            //im.displayImage("http://image.tmdb.org/t/p/w500" + mDataset.get(position).getPosterPath().toString(), holder.image);
            Utils.loadImage(holder.image,mDataset.get(position).getPosterPath().toString(),4);

            Glide
                    .with(c)
                    .load(Config.IMAGE_BASE_URL+"w185"+mDataset.get(position).getPosterPath().toString())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    // .signature(new StringSignature(String.valueOf(System.currentTimeMillis())))

                    .into(new SimpleTarget<Bitmap>(com.bumptech.glide.request.target.Target.SIZE_ORIGINAL, com.bumptech.glide.request.target.Target.SIZE_ORIGINAL) {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                            //holder.moviePoster.setImageBitmap(resource); // Possibly runOnUiThread()
                            if(holder.rlContainer!=null){
                                Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                                    public void onGenerated(Palette p) {
                                        // Use generated instance
                                        holder.rlContainer.setBackgroundColor(p.getDarkVibrantColor(c.getResources().getColor(R.color.grey700)));
                                        //holder.tvRating.setTextColor(Config.manipulateColor(p.getDarkVibrantColor(mContext.getResources().getColor(R.color.white)),0.9f));
                                    }
                                });
                            }
                        }
                    });

        }if(mDataset.get(position).getName()!=null){
            holder.text.setText(mDataset.get(position).getName().toString());
        }
        if(mDataset.get(position).getVoteAverage()!=null){
            Float ra = mDataset.get(position).getVoteAverage()/2;
            //holder.rate.setRating(ra);
        }

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent strt = new Intent(c, TvDetailActivity.class);
                strt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if(mDataset.get(position).getId()!=null) {
                    strt.putExtra("id", mDataset.get(position).getId().toString());
                }
                c.startActivity(strt);
            }
        });






    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
