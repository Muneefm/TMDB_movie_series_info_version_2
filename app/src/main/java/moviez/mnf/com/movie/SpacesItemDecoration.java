package moviez.mnf.com.movie;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Muneef on 10/05/15.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    public SpacesItemDecoration(int space) {
        this.space = space;
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;

        outRect.set(15, 15, 15, 15);
        
        // Add top margin only for the first item to avoid double space between items
        //if(parent.getChildPosition(view) == 0)
            //outRect.top = space;
    }

}
