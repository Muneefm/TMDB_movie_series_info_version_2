package moviez.mnf.com.movie.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import moviez.mnf.com.movie.DataSet.movieReview.Result;
import moviez.mnf.com.movie.R;

/**
 * Created by Muneef on 06/05/15.
 */
public class RecycleReviewAdapter extends RecyclerView.Adapter<RecycleReviewAdapter.ViewHolder> {
    Context c;
    List<Result> mData;

    ImageLoader im;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View viewi;
        public final TextView name;
        public final TextView review;
        public ViewHolder(View v) {
            super(v);
            viewi = v;
            name =(TextView) v.findViewById(R.id.author);
            review =(TextView) v.findViewById(R.id.reviewContent);



        }
    }

    public RecycleReviewAdapter(Context mContext) {
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
                .inflate(R.layout.dialogue_review_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        im = ImageLoader.getInstance();
        im.init(ImageLoaderConfiguration.createDefault(c));


        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(mData.get(position).getAuthor()!=null) {
            holder.name.setText(mData.get(position).getAuthor().toString());
        }
        if(mData.get(position).getContent()!=null){
            holder.review.setText(mData.get(position).getContent().toString());
        }


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
