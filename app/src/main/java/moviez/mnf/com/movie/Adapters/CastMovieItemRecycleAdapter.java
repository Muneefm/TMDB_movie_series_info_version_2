package moviez.mnf.com.movie.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import moviez.mnf.com.movie.Activity.MovieDetails;
import moviez.mnf.com.movie.DataSet.CastCrew.CastCrewData;
import moviez.mnf.com.movie.DataSet.CastMovieTv.Cast;
import moviez.mnf.com.movie.DataSet.movieReview.Result;
import moviez.mnf.com.movie.R;
import moviez.mnf.com.movie.tools.Config;
import moviez.mnf.com.movie.tools.Utils;

/**
 * Created by Muneef on 10/01/16.
 */
public class CastMovieItemRecycleAdapter  extends RecyclerView.Adapter<CastMovieItemRecycleAdapter.ViewHolder>  {

    List<Cast> mData;
    Context c;
    static String TAG = "CMIRA";
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View viewi;
        TextView movieName,character;
        ImageView posterImage;
        CardView cView;

        public ViewHolder(View v) {
            super(v);
            viewi = v;
            movieName = (TextView) v.findViewById(R.id.castmTitle);
            posterImage = (ImageView) v.findViewById(R.id.castmPoster);
            cView = (CardView) v.findViewById(R.id.castmcard);
            character = (TextView) v.findViewById(R.id.castChar);
        }
    }


    public CastMovieItemRecycleAdapter(Context mContext,List<Cast> castMvList) {
        this.c = mContext;
        this.mData = castMvList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cast_movie_item, parent, false);
        // set the view's size, margins, paddings and layout parameters


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.movieName.setText(mData.get(position).getTitle().toString());
        Utils.loadImage(holder.posterImage,mData.get(position).getPosterPath(),3);
        if(!mData.get(position).getCharacter().equals(""))
        holder.character.setText("as "+mData.get(position).getCharacter().toString());

        holder.cView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent str = new Intent(c, MovieDetails.class);
                //str.putextra("your_extra","your_class_value");
                str.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                str.putExtra("id", mData.get(position).getId().toString());
                c.startActivity(str);
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.e(TAG,"adapter count = "+mData.size());
        return mData.size();
    }


}
