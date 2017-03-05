package moviez.mnf.com.movie.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

import moviez.mnf.com.movie.Activity.Config;
import moviez.mnf.com.movie.DataSet.Video.Result;
import moviez.mnf.com.movie.R;

/**
 * Created by Muneef on 16/05/15.
 */
public class VedoRecycleAdapter  extends RecyclerView.Adapter<VedoRecycleAdapter.ViewHolder>  {

List<Result> mData;
    Context c;
    private static final int RECOVERY_DIALOG_REQUEST = 1;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View viewi;



       public final YouTubePlayerView youTubeView;


        public ViewHolder(View v) {
            super(v);
            viewi = v;



            youTubeView = (YouTubePlayerView) v.findViewById(R.id.youtube_view);
        }
    }
    public VedoRecycleAdapter(Context mContext) {
        this.c = mContext;
        this.mData = new ArrayList<Result>();
    }
    public void addItems(List<Result> newItems) {
        this.mData.addAll(newItems);
        // this.mDataset = newItems;
        //  Toast.makeText(c, "added in re "+mDataset.size(), Toast.LENGTH_LONG).show();
//notifyDataSetChanged();
        notifyItemRangeInserted(this.mData.size(), newItems.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_items, parent, false);
        // set the view's size, margins, paddings and layout parameters


        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        if(mData.get(position).getKey()!=null&&!mData.get(position).getKey().equals("")) {

            holder.youTubeView.initialize(Config.YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
                    if (!wasRestored) {

                        // loadVideo() will auto play video
                        // Use cueVideo() method, if you don't want to play it automatically
                        youTubePlayer.loadVideo(mData.get(position).getKey().toString());

                        // Hiding player controls
                       // youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
                    }
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                 //   Toast.makeText(c,"Initialisation Failed",Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
