package moviez.mnf.com.movie.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import moviez.mnf.com.movie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageViewFragemnt extends Fragment {

    ImageView foto;

    public ImageViewFragemnt() {
        // Required empty public constructor
    }
    public static ImageViewFragemnt getInstance(String pos) {
        String TAG = "logging";

        ImageViewFragemnt fragmentInstance = new ImageViewFragemnt();
        Bundle bundle = new Bundle();
        //Log.e(TAG,"value of pos is "+pos);
        bundle.putString("id", pos);


        fragmentInstance.setArguments(bundle);

        return  fragmentInstance;

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_image_view_fragemnt, container, false);



        return v;
    }


}
