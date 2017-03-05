package moviez.mnf.com.movie.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import moviez.mnf.com.movie.DataSet.first.Result;
import moviez.mnf.com.movie.R;

/**
 * Created by Muneef on 02/05/15.
 */
public class ListAdapter extends BaseAdapter {
    List<Result> dataSets;
    Context c;
    public ListAdapter(Context context){
        this.c = context;
        dataSets = new ArrayList<Result>();

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return dataSets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public void addItems(List<Result> newItems) {
        //this.dataSets.addAll(newItems);
       // notifyDataSetChanged(this.dataSets.size(), newItems.size());
        this.dataSets = newItems;
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.list_text, parent, false);

        TextView title = (TextView) v.findViewById(R.id.exmple);
        title.setText("this is test");
        //ImageView image = (ImageView) v.findViewById(R.id.poster);
        //title.setText(dataSets.get(position).getTitle());

        return v;
    }
}
