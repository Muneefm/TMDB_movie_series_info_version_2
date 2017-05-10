package moviez.mnf.com.movie.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import moviez.mnf.com.movie.Activity.MovieDetails;
import moviez.mnf.com.movie.DataSet.first.Result;
import moviez.mnf.com.movie.R;
import moviez.mnf.com.movie.tools.Config;
import moviez.mnf.com.movie.tools.Utils;

/**
 * Created by Muneef on 03/05/15.
 */
public class RecylcleAdapter extends RecyclerView.Adapter<RecylcleAdapter.ViewHolder> {


    private List<Result> mDataset;
    Context c;
    String next = "<font color='#EE0000'>red</font>";
    //t.setText(Html.fromHtml(first + next));
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View viewi;
        public final TextView text,usersC;
        public final ImageView image;
        public final RatingBar rate;
        public final CardView cv;
        public final TextView dateR;
        public final TextView dateTag;

        public ViewHolder(View v) {
            super(v);
            viewi = v;
            cv = (CardView) v.findViewById(R.id.itemCard);
            text = (TextView) v.findViewById(R.id.movieTitle);
            image  = (ImageView) v.findViewById(R.id.poster);
            rate = (RatingBar) v.findViewById(R.id.ratingBar);
            usersC = (TextView) v.findViewById(R.id.userc);
            dateR = (TextView) v.findViewById(R.id.datelist);
            dateTag = (TextView) v.findViewById(R.id.dateTag);




        }
    }

    public RecylcleAdapter(Context mContext) {
        this.c = mContext;
        this.mDataset = new ArrayList<Result>();
    }
    public void addItems(List<Result> newItems) {
        this.mDataset.addAll(newItems);
       // this.mDataset = newItems;
      //  Toast.makeText(c, "added in re "+mDataset.size(), Toast.LENGTH_LONG).show();
//notifyDataSetChanged();
        notifyItemRangeInserted(this.mDataset.size(), newItems.size());
    }

    public void reData(List<Result> newIt){
        this.mDataset =newIt;
     //   Toast.makeText(c, "adapter size "+mDataset.size(), Toast.LENGTH_LONG).show();
        notifyDataSetChanged();
    }

    @Override
    public RecylcleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Typeface PTSans=Typeface.createFromAsset(c.getAssets(), "fonts/RopaSans-Regular.ttf");
        Typeface questrialFace=Typeface.createFromAsset(c.getAssets(), "fonts/Questrial-Regular.ttf");

        holder.text.setTypeface(PTSans);
        holder.dateTag.setTypeface(questrialFace);


        holder.text.setText(mDataset.get(position).getTitle());
        ImageLoader im = ImageLoader.getInstance();
        im.init(ImageLoaderConfiguration.createDefault(c));


              /*  Intent str = new Intent(c, MovieDetails.class);
                //str.putextra("your_extra","your_class_value");
                str.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                str.putExtra("id", mDataset.get(position).getId().toString());
                c.startActivity(str);
          */

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent str = new Intent(c, MovieDetails.class);
                //str.putextra("your_extra","your_class_value");
                //str.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               // str.putExtra("id", mDataset.get(position).getId().toString());
             //   c.startActivity(str);

                //Toast.makeText(c," position is "+mDataset.get(position).getId().toString(),Toast.LENGTH_LONG).show();
                Intent str = new Intent(c, MovieDetails.class);
                //str.putextra("your_extra","your_class_value");
                str.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                str.putExtra("id", mDataset.get(position).getId().toString());
                c.startActivity(str);
            }
        });

        //Toast.makeText(c, "Recycle", Toast.LENGTH_LONG).show();
            if(mDataset.get(position).getReleaseDate()!=null){
                holder.dateR.setTypeface(questrialFace);
                holder.dateR.setText(mDataset.get(position).getReleaseDate().toString());
            }
        if(mDataset.get(position).getPosterPath()!=null) {
           // im.displayImage("http://image.tmdb.org/t/p/w500"+mDataset.get(position).getPosterPath().toString(), holder.image);
            Utils.loadImage(holder.image, mDataset.get(position).getPosterPath().toString(),4);
        }
        if(mDataset.get(position).getVoteAverage()!=null){
          Float ra =   mDataset.get(position).getVoteAverage()/2;
            holder.rate.setRating(ra);
        }
        if(mDataset.get(position).getVoteCount()!=null){
            String col = mDataset.get(position).getVoteCount().toString();
            if(col!="0") {
                String coll = "<font color='#2196f3'>" + col + "</font>";
                holder.usersC.setText(Html.fromHtml(coll + " users"));
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
