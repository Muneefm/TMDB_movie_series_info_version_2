package moviez.mnf.com.movie.Fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import fr.castorflex.android.circularprogressbar.CircularProgressDrawable;
import moviez.mnf.com.movie.Adapters.RecycleAdapterTv;
import moviez.mnf.com.movie.AppController;
import moviez.mnf.com.movie.DataSet.TV.list.TvDataList;
import moviez.mnf.com.movie.MainActivity;
import moviez.mnf.com.movie.R;
import moviez.mnf.com.movie.SpacesItemDecoration;
import moviez.mnf.com.movie.tools.GridSpacingItemDecoration;
import moviez.mnf.com.movie.tools.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInstanceTv extends Fragment implements ObservableScrollViewCallbacks {

    public Gson gson = new Gson();
    Boolean loading=true;
    public TvDataList feedfirst;
    int page =1;
    RecycleAdapterTv adapterN;
    ObservableRecyclerView lv;
    GridLayoutManager grid;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    CircularProgressBar smothCirTv;
    TvDataList feed;
    public static final String ARG_INITIAL_POSITION = "ARG_INITIAL_POSITION";

    String BaseUrl = "http://api.themoviedb.org/3/tv/popular?api_key=7cf008680165ec352b68dce08866495f";

    public static FragmentInstanceTv getInstance(int pos) {
        String TAG = "FragmentInstanceTv";

        FragmentInstanceTv fragmentInstance = new FragmentInstanceTv();
        Bundle bundle = new Bundle();
        Log.e(TAG,"value of pos is "+pos);
        bundle.putInt("position", pos);


        fragmentInstance.setArguments(bundle);

        return  fragmentInstance;

        // Required empty public constructor
    }

    public FragmentInstanceTv() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Activity parentActivity = getActivity();
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.AppThemeMovieDetail);
        LayoutInflater localInflater =  inflater.cloneInContext(contextThemeWrapper);

        // Inflate the layout for this fragment
        View v= localInflater.inflate(R.layout.fragment_instance_tv, container, false);
        Bundle bundle = getArguments();
        if(bundle.getInt("position")==0){
            BaseUrl = "http://api.themoviedb.org/3/tv/on_the_air?api_key=7cf008680165ec352b68dce08866495f"; //"http://api.themoviedb.org/3/movie/upcoming?api_key=7cf008680165ec352b68dce08866495f"
        }else if(bundle.getInt("position")==1){
            BaseUrl = "http://api.themoviedb.org/3/tv/popular?api_key=7cf008680165ec352b68dce08866495f"; //"http://api.themoviedb.org/3/movie/now_playing?api_key=7cf008680165ec352b68dce08866495f"
        }else if(bundle.getInt("position")==2){
            BaseUrl = "http://api.themoviedb.org/3/tv/top_rated?api_key=7cf008680165ec352b68dce08866495f";  //"http://api.themoviedb.org/3/movie/popular?api_key=7cf008680165ec352b68dce08866495f"
        }else  if(bundle.getInt("position")==3){
            BaseUrl = "http://api.themoviedb.org/3/tv/airing_today?api_key=7cf008680165ec352b68dce08866495f"; //"http://api.themoviedb.org/3/movie/top_rated?api_key=7cf008680165ec352b68dce08866495f"
        }
        adapterN = new RecycleAdapterTv(getActivity());
        lv = (ObservableRecyclerView) v.findViewById(R.id.tvlist);
        smothCirTv = (CircularProgressBar) v.findViewById(R.id.smothCirTv);


        if (parentActivity instanceof ObservableScrollViewCallbacks) {
            // Scroll to the specified position after layout
            Bundle args = getArguments();
            if (args != null && args.containsKey(ARG_INITIAL_POSITION)) {
                final int initialPosition = args.getInt(ARG_INITIAL_POSITION, 0);
                ScrollUtils.addOnGlobalLayoutListener(lv, new Runnable() {
                    @Override
                    public void run() {
                        // scrollTo() doesn't work, should use setSelection()
                        //lv.setSelection(initialPosition);
                        lv.scrollVerticallyToPosition(initialPosition);
                    }
                });
            }
            lv.setScrollViewCallbacks((ObservableScrollViewCallbacks) parentActivity);
        }

        return v;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density  = getResources().getDisplayMetrics().density;
        float dpWidth  = outMetrics.widthPixels / density;
        int columns = Math.round(dpWidth/170);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);

        grid = new GridLayoutManager(getActivity(),columns,GridLayoutManager.VERTICAL,false);
        //lv.addItemDecoration(new SpacesItemDecoration(8));
        lv.setHasFixedSize(true);
        lv.setLayoutManager(mLayoutManager);
        lv.addItemDecoration(new GridSpacingItemDecoration(1, Utils.dpToPx(1,getActivity()), true));


        lv.setScrollViewCallbacks(this);
        lv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                visibleItemCount = grid.getChildCount();
                totalItemCount = grid.getItemCount();
                pastVisiblesItems = grid.findFirstVisibleItemPosition();
                if (loading) {
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        loading = false;
                        page++;

                        makeJsonArrayRequest(BaseUrl + "&page=" + page);
                        smothCirTv.setIndeterminateDrawable(new CircularProgressDrawable
                                .Builder(getActivity())
                                .colors(getResources().getIntArray(R.array.colorsSmooth)).sweepSpeed(5f).build());

                        //    Toast.makeText(getActivity(), "reached end  ", Toast.LENGTH_SHORT).show();
                        //  loadMoreData();
                    }
                }
            }
        });


        lv.setAdapter(adapterN);

        makeJsonArrayRequest(BaseUrl);



    }

    private void makeJsonArrayRequest(String urlnew) {
        smothCirTv.setVisibility(View.VISIBLE);
        ((CircularProgressDrawable)smothCirTv.getIndeterminateDrawable()).start();

        // showpDialog();
        // JSONObject req=new JsonObjectRequest()
        JsonObjectRequest req = new JsonObjectRequest(com.android.volley.Request.Method.GET, urlnew, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //  Toast.makeText(getActivity(),"name",Toast.LENGTH_SHORT).show();

                ((CircularProgressDrawable)smothCirTv.getIndeterminateDrawable()).progressiveStop();
                smothCirTv.setVisibility(View.INVISIBLE);
                feed = gson.fromJson(response.toString(), TvDataList.class);
                //feed = feedfirst;

                //  Toast.makeText(getActivity(), "toasting ", Toast.LENGTH_SHORT).show();

                //  Toast.makeText(getActivity(), feed.getData().get(2).getCaption().getText(), Toast.LENGTH_SHORT).show();
                //adpfeed = new adapterFeed(feed.getData(), getActivity());

                adapterN.addItems(feed.getResults());
                loading=true;
                //lv.setAdapter(adapterN);
//                Toast.makeText(getActivity(),"added",Toast.LENGTH_LONG).show();

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(), "Error  " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                ((CircularProgressDrawable)smothCirTv.getIndeterminateDrawable()).progressiveStop();
                smothCirTv.setVisibility(View.INVISIBLE);

            }
        });


        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }


    @Override
    public void onScrollChanged(int i, boolean b, boolean b1) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
/*        android.support.v7.app.ActionBar ab =((MainActivity) getActivity()).getSupportActionBar();
        if (scrollState == ScrollState.UP) {

            if (ab.isShowing()) {
                ab.hide();
            }
        } else if (scrollState == ScrollState.DOWN) {
            if (!ab.isShowing()) {
                ab.show();
            }
        }*/

    }
}
