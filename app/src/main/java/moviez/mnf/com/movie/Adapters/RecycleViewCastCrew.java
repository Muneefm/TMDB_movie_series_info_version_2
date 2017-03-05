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

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import moviez.mnf.com.movie.Activity.ImageViewActivity;
import moviez.mnf.com.movie.Activity.ScrollingActivity;
import moviez.mnf.com.movie.DataSet.CastCrew.Cast;
import moviez.mnf.com.movie.R;
import moviez.mnf.com.movie.tools.Config;
import moviez.mnf.com.movie.tools.Utils;

/**
 * Created by Muneef on 05/05/15.
 */
public class RecycleViewCastCrew extends RecyclerView.Adapter<RecycleViewCastCrew.ViewHolder>  {
        List<Cast> mDataset;
    ImageLoader im;
    Context c;
    String TAG ="RVC";
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View viewi;
        public final ImageView image;
        public final TextView name;
        public final CardView cv;

        public ViewHolder(View v) {
            super(v);
            viewi = v;
            image  = (ImageView) v.findViewById(R.id.propic);
            name =(TextView) v.findViewById(R.id.namecast);
            cv = (CardView) v.findViewById(R.id.itemCardcast);
        }
    }
    public RecycleViewCastCrew(Context mContext) {
        this.c = mContext;
        this.mDataset = new ArrayList<Cast>();
    }
    public void addItems(List<Cast> newItems) {
        this.mDataset.addAll(newItems);
        // this.mDataset = newItems;
        //  Toast.makeText(c, "added in re "+mDataset.size(), Toast.LENGTH_LONG).show();
//notifyDataSetChanged();
        notifyItemRangeInserted(this.mDataset.size(), newItems.size());
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cast_items, parent, false);
        // set the view's size, margins, paddings and layout parameters


        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {



        holder.name.setText(mDataset.get(position).getName());
        if(mDataset.get(position).getProfilePath()!=null){
           Utils.loadImage(holder.image,  mDataset.get(position).getProfilePath().toString(),3);
            Log.e(TAG,"iamge url  = "+mDataset.get(position).getProfilePath() +" name = "+mDataset.get(position).getName());
        }else{
            Utils.loadImage(holder.image, "",3);
            Log.e(TAG,"iamge url null  = "+mDataset.get(position).getProfilePath() +" name = "+mDataset.get(position).getName());

        }

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent str = new Intent(c, ScrollingActivity.class);
                str.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                str.putExtra("id",mDataset.get(position).getId().toString());
             //   str.putExtra("key","2");
                c.startActivity(str);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
