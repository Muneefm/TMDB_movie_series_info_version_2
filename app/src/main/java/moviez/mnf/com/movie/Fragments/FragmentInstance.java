package moviez.mnf.com.movie.Fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import moviez.mnf.com.movie.Activity.MovieDetails;
import moviez.mnf.com.movie.Adapters.ListAdapter;
import moviez.mnf.com.movie.Adapters.RecylcleAdapter;
import moviez.mnf.com.movie.AppController;
import moviez.mnf.com.movie.DataSet.first.DataMain;
import moviez.mnf.com.movie.MainActivity;
import moviez.mnf.com.movie.R;
import moviez.mnf.com.movie.tools.RecyclerTouchListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInstance extends Fragment implements ObservableScrollViewCallbacks {

    Boolean loading=true;
    public Gson gson = new Gson();
    public DataMain feedfirst;
    int page =1;
    ListAdapter adapter;
    RecylcleAdapter adapterN;
    ObservableRecyclerView lv;
    private LinearLayoutManager mLayoutManager;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    CircularProgressBar smothCir;
    //protected ProgressWheel progressWheel;


    public static final String ARG_INITIAL_POSITION = "ARG_INITIAL_POSITION";
//    android.support.v7.app.ActionBar ab =((MainActivity) getActivity()).getSupportActionBar();

    String BaseUrl = "http://api.themoviedb.org/3/movie/upcoming?api_key=7cf008680165ec352b68dce08866495f";

    public static FragmentInstance getInstance(int pos) {
        String TAG = "FragmentInstance";


        FragmentInstance fragmentInstance = new FragmentInstance();
        Bundle bundle = new Bundle();
        Log.e(TAG,"value of pos is "+pos);
        bundle.putInt("position", pos);


        fragmentInstance.setArguments(bundle);

        return  fragmentInstance;

        // Required empty public constructor
    }

    public FragmentInstance() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Activity parentActivity = getActivity();
        // Inflate the layout for this fragment
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.AppThemeMovieDetail);
        LayoutInflater localInflater =  inflater.cloneInContext(contextThemeWrapper);

        View v= localInflater.inflate(R.layout.fragment_fragment_instance, container, false);
        Bundle bundle = getArguments();
      //  if(bundle.i!=null) {
            if (bundle.getInt("position") == 0) {
                BaseUrl = "http://api.themoviedb.org/3/movie/now_playing?api_key=7cf008680165ec352b68dce08866495f";
            } else if (bundle.getInt("position") == 1) {
                BaseUrl = "http://api.themoviedb.org/3/movie/upcoming?api_key=7cf008680165ec352b68dce08866495f"; //"http://api.themoviedb.org/3/movie/now_playing?api_key=7cf008680165ec352b68dce08866495f"
            } else if (bundle.getInt("position") == 2) {
                BaseUrl = "http://api.themoviedb.org/3/movie/popular?api_key=7cf008680165ec352b68dce08866495f";  //"http://api.themoviedb.org/3/movie/popular?api_key=7cf008680165ec352b68dce08866495f"
            } else if (bundle.getInt("position") == 3) {
                BaseUrl = "http://api.themoviedb.org/3/movie/top_rated?api_key=7cf008680165ec352b68dce08866495f"; //"http://api.themoviedb.org/3/movie/top_rated?api_key=7cf008680165ec352b68dce08866495f"
            }
    //    }
        adapter = new ListAdapter(getActivity());
        adapterN = new RecylcleAdapter(getActivity());

        lv = (ObservableRecyclerView) v.findViewById(R.id.movieList);
        smothCir = (CircularProgressBar) v.findViewById(R.id.smothCir);
        //progressWheel = (ProgressWheel) v.findViewById(R.id.progress_wheel);


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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLayoutManager = new LinearLayoutManager(getActivity());

        lv.setHasFixedSize(false);
        lv.setLayoutManager(mLayoutManager);
        Bundle bundle = getArguments();

        lv.setScrollViewCallbacks(this);
        lv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                visibleItemCount = mLayoutManager.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
                if (loading) {
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        loading = false;
                        page++;

                        makeJsonArrayRequest(BaseUrl + "&page=" + page);

                        //((CircularProgressDrawable)smothCir.getIndeterminateDrawable()).start();

                        //progressWheel.setVisibility(View.VISIBLE);
                        //    Toast.makeText(getActivity(), "reached end  ", Toast.LENGTH_SHORT).show();
                        //  loadMoreData();
                    }
                }
            }
        });



        makeJsonArrayRequest(BaseUrl);
        lv.setAdapter(adapterN);
        //Toast.makeText(getActivity(),"inside adapter set",Toast.LENGTH_LONG).show();
        lv.addOnItemTouchListener(new RecyclerTouchListener(getContext(), lv, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                // Movie movie = movieList.get(position);
              //  Toast.makeText(c, position + " is selected!", Toast.LENGTH_SHORT).show();
              //  getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main,new NoteFragment().newInstance(list.get(position).getIdn().toString(),"")).addToBackStack("note").commit();
               Log.e("TAG","click position = "+position);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));    }

    private void makeJsonArrayRequest(String urlnew) {
        smothCir.setVisibility(View.VISIBLE);
        ((CircularProgressDrawable)smothCir.getIndeterminateDrawable()).start();
        // showpDialog();
        // JSONObject req=new JsonObjectRequest()
        JsonObjectRequest req = new JsonObjectRequest(com.android.volley.Request.Method.GET, urlnew, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //  Toast.makeText(getActivity(),"name",Toast.LENGTH_SHORT).show();
                DataMain feed;
               // progressWheel.setVisibility(View.INVISIBLE);



                feedfirst = gson.fromJson(response.toString(), DataMain.class);
                ((CircularProgressDrawable)smothCir.getIndeterminateDrawable()).progressiveStop();
                smothCir.setVisibility(View.INVISIBLE);
                feed = feedfirst;

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
                Toast.makeText(getActivity(), "Error  "+volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                ((CircularProgressDrawable)smothCir.getIndeterminateDrawable()).progressiveStop();
                smothCir.setVisibility(View.INVISIBLE);
            }
        });


        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }


    @Override
    public void onScrollChanged(int i, boolean b, boolean b2) {

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
        } */
    }
}
