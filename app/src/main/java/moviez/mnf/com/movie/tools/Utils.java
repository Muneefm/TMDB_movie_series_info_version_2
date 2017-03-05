package moviez.mnf.com.movie.tools;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import moviez.mnf.com.movie.R;

/**
 * Created by Muneef on 27/11/15.
 */
public class Utils {
    public static void loadImage(ImageView view, String url,int quality) {
        if(url!=null && !url.equals("")) {
         /*   Picasso.with(view.getContext())
                    .load(url)
                    .error(R.mipmap.ic_cross_grey)
                    .placeholder(R.drawable.dwn)
                    .into(view);*/
            String baseImg = Config.IMAGE_BASE_URL;
            switch (quality){
                case 1:
                    baseImg+="w45";
                    break;
                case 2:
                    baseImg+="w92";
                    break;
                case 3:
                    baseImg+="w154";
                    break;
                case 4:
                    baseImg+="w185";
                    break;
                case 5:
                    baseImg+="w300";
                    break;
                case 6:
                    baseImg+="w342";
                    break;
                case 7:
                    baseImg+="w500";
                    break;
                default:
                    baseImg+="w500";
                    break;
            }
           // Log.e("tag","base url = "+baseImg+"  qualtiy = "+quality+" final url ="+baseImg+url);


            Glide
                    .with(view.getContext())
                    .load(baseImg+url)
                    .centerCrop()
                    .error(R.mipmap.ic_cross_grey)
                    .placeholder(R.drawable.dwn)
                    .crossFade()
                    .into(view);
        }

    }



    public static int dpToPx(int dp, Context c) {
        Resources r = c.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
