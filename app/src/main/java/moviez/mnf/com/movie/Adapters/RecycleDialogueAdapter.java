package moviez.mnf.com.movie.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import moviez.mnf.com.movie.DataSet.TV.itemDetail.Season;
import moviez.mnf.com.movie.R;
import moviez.mnf.com.movie.tools.Config;
import moviez.mnf.com.movie.tools.Utils;

/**
 * Created by Muneef on 12/05/15.
 */
public class RecycleDialogueAdapter extends RecyclerView.Adapter<RecycleDialogueAdapter.ViewHolder>  {

    Context c;
    List<Season> seasons;
    ImageLoader im;


    //t.setText(Html.fromHtml(first + next));
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View viewi;
        public final TextView date,epi,sea;
        public final ImageView poster;

        public ViewHolder(View v) {
            super(v);
            viewi = v;
             date = (TextView) v.findViewById(R.id.datediaair);
            epi = (TextView) v.findViewById(R.id.episodeCountDia);
            sea = (TextView) v.findViewById(R.id.seasonCountDia);


            poster = (ImageView) v.findViewById(R.id.posterdia);





        }
    }
    public RecycleDialogueAdapter(Context mContext) {
        this.c = mContext;
        this.seasons = new ArrayList<Season>();
    }
    public void addItems(List<Season> newItems) {
        this.seasons.addAll(newItems);
        // this.mDataset = newItems;
        //  Toast.makeText(c, "added in re "+mDataset.size(), Toast.LENGTH_LONG).show();
//notifyDataSetChanged();
        notifyItemRangeInserted(this.seasons.size(), newItems.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dialogue_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
         im = ImageLoader.getInstance();
        im.init(ImageLoaderConfiguration.createDefault(c));


        ViewHolder vh = new ViewHolder(v);
        return vh;    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        if(seasons.get(position).getAirDate()!=null) {
            holder.date.setText(seasons.get(position).getAirDate().toString());
        }
        if(seasons.get(position).getEpisodeCount()!=null){
            holder.epi.setText(seasons.get(position).getEpisodeCount().toString());
        }
        if(seasons.get(position).getSeasonNumber()!=null){
            holder.sea.setText((seasons.get(position).getSeasonNumber()+1)+"");
        }

        if(seasons.get(position).getPosterPath()!=null){
            //im.displayImage("http://image.tmdb.org/t/p/w500"+seasons.get(position).getPosterPath().toString(), holder.poster);
            Utils.loadImage(holder.poster, seasons.get(position).getPosterPath().toString(),2);
        }


    }

    @Override
    public int getItemCount() {
        return seasons.size();
    }


}
