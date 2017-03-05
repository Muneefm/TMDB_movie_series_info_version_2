package moviez.mnf.com.movie.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import fr.castorflex.android.circularprogressbar.CircularProgressDrawable;
import moviez.mnf.com.movie.Adapters.CastMovieItemRecycleAdapter;
import moviez.mnf.com.movie.Adapters.CastTvItemRecycleAdapter;
import moviez.mnf.com.movie.AppController;
import moviez.mnf.com.movie.DataSet.CastDetail.CrewDetailsData;
import moviez.mnf.com.movie.DataSet.CastMovieTv.CastMovie;
import moviez.mnf.com.movie.DataSet.CastMovieTv.tv.CastTv;
import moviez.mnf.com.movie.R;
import moviez.mnf.com.movie.tools.*;
import moviez.mnf.com.movie.tools.Config;

public class ScrollingActivity extends AppCompatActivity {
    CircleImageView pro;
    TextView ovrview,date,place,name,popularity;
    CrewDetailsData data;
    CastMovie castMoveData;
    CastTv castTvData;
    RelativeLayout mainRel;
    RecyclerView movieRecycle,tvRecycleView;
    public Gson gson = new Gson();
    ImageView down,share;
    CircularProgressBar smothCirCast;
    TextView  birthTag,tagplace,tagOvr,tagTvRec,tagMovieRec;
    CastMovieItemRecycleAdapter castMoviewAdapter;
    CastTvItemRecycleAdapter castTvadapter;
    CardView propickTwo;
    private LinearLayoutManager mLayoutManager,mLayoutManagerTv;
    static String TAG = "SA";
    Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        c = getApplicationContext();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String key = intent.getExtras().getString("id");
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        birthTag = (TextView) findViewById(R.id.birthTag);
        tagplace = (TextView) findViewById(R.id.tagplace);
        tagOvr = (TextView) findViewById(R.id.tagOvr);
        popularity = (TextView) findViewById(R.id.popula);

        tagMovieRec = (TextView) findViewById(R.id.tagMovieRec);
        tagTvRec = (TextView) findViewById(R.id.tagTvRec);

        pro =(CircleImageView) findViewById(R.id.imgprocir);
        ovrview = (TextView) findViewById(R.id.ovr);
        date = (TextView) findViewById(R.id.birthday);
        place = (TextView) findViewById(R.id.place);
        name = (TextView) findViewById(R.id.namecast);
        mainRel = (RelativeLayout) findViewById(R.id.mainRel);
        smothCirCast = (CircularProgressBar) findViewById(R.id.smothCirCast);
        movieRecycle = (RecyclerView) findViewById(R.id.cast_movie_rec);
        tvRecycleView =(RecyclerView) findViewById(R.id.castTvRec);
        propickTwo = (CardView) findViewById(R.id.propickTwo);
        mLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagerTv
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        movieRecycle.setHasFixedSize(false);
        movieRecycle.setLayoutManager(mLayoutManager);
        movieRecycle.setNestedScrollingEnabled(false);
        tvRecycleView.setHasFixedSize(false);
        tvRecycleView.setLayoutManager(mLayoutManagerTv);
        tvRecycleView.setNestedScrollingEnabled(false);
       // makeJsonCrewRequest("http://api.themoviedb.org/3/person/"+key+"?api_key=7cf008680165ec352b68dce08866495f");
        makeJsonCrewRequest(Config.BASE_URL+"person/"+key+"?api_key="+Config.API_KEY);
        makeJsonCastMovieRequest(Config.BASE_URL + "person/" + key + "/movie_credits" + "?api_key=" + Config.API_KEY);
        makeJsonCastTvRequest(Config.BASE_URL+"person/"+key+"/tv_credits"+"?api_key="+Config.API_KEY);

    }


    public void makeJsonCrewRequest(String uu){
        smothCirCast.setVisibility(View.VISIBLE);
        Log.e("SA","cast request url = "+uu);
        ((CircularProgressDrawable)smothCirCast.getIndeterminateDrawable()).start();
        JsonObjectRequest reqst = new JsonObjectRequest(com.android.volley.Request.Method.GET, uu,null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                data = gson.fromJson(response.toString(), CrewDetailsData.class);

                ((CircularProgressDrawable)smothCirCast.getIndeterminateDrawable()).progressiveStop();
                smothCirCast.setVisibility(View.INVISIBLE);

                mainRel.setVisibility(View.VISIBLE);
                Log.e("ScrollingActivity","prof = "+data.getProfilePath());
                Utils.loadImage(pro,data.getProfilePath(),2);
                pro.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent strt = new Intent(ScrollingActivity.this, ImageViewActivity.class);
                        strt.putExtra("id", data.getProfilePath().toString());
                        strt.putExtra("key", "1");
                        startActivity(strt);
                    }
                });

                if(data.getBirthday()!=null) {
                    birthTag.setVisibility(View.VISIBLE);
                    date.setText(data.getBirthday().toString());
                }
                if(data.getBiography()!=null&&!data.getBiography().toString().equals("")){
                    propickTwo.setVisibility(View.VISIBLE);
                    tagOvr.setVisibility(View.VISIBLE);
                    ovrview.setText(data.getBiography().toString());
                }
                if(data.getPlaceOfBirth()!=null){
                    tagplace.setVisibility(View.VISIBLE);
                    place.setText(data.getPlaceOfBirth().toString());
                }
                if(data.getName()!=null){
                    name.setText(data.getName().toString());
                }if(data.getPopularity()!=null){
                    popularity.setText(data.getPopularity().toString());
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Error  " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();

                ((CircularProgressDrawable)smothCirCast.getIndeterminateDrawable()).progressiveStop();
                smothCirCast.setVisibility(View.INVISIBLE);
            }
        });

        AppController.getInstance().addToRequestQueue(reqst);

    }

    public void makeJsonCastMovieRequest(String urlc){
        smothCirCast.setVisibility(View.VISIBLE);
        Log.e("SA","cast request url = "+urlc);
        ((CircularProgressDrawable)smothCirCast.getIndeterminateDrawable()).start();
        JsonObjectRequest reqs = new JsonObjectRequest(com.android.volley.Request.Method.GET, urlc,null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.e(TAG, "cast moview response succes ");

                castMoveData = gson.fromJson(response.toString(), CastMovie.class);

                if(castMoveData.getCast().size()>0){

                    tagMovieRec.setVisibility(View.VISIBLE);
                    movieRecycle.setVisibility(View.VISIBLE);


                ((CircularProgressDrawable)smothCirCast.getIndeterminateDrawable()).progressiveStop();
                smothCirCast.setVisibility(View.INVISIBLE);

                castMoviewAdapter = new CastMovieItemRecycleAdapter(c,castMoveData.getCast());
                movieRecycle.setAdapter(castMoviewAdapter);
                }



            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Error  " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "cast moview response error ");
                ((CircularProgressDrawable)smothCirCast.getIndeterminateDrawable()).progressiveStop();
                smothCirCast.setVisibility(View.INVISIBLE);
            }
        });

        AppController.getInstance().addToRequestQueue(reqs);
    }

    public void makeJsonCastTvRequest(String urlc){
        smothCirCast.setVisibility(View.VISIBLE);
        Log.e("SA", "cast request url = " + urlc);
        ((CircularProgressDrawable)smothCirCast.getIndeterminateDrawable()).start();
        JsonObjectRequest reqs = new JsonObjectRequest(com.android.volley.Request.Method.GET, urlc,null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                castTvData = gson.fromJson(response.toString(), CastTv.class);
                if(castTvData.getCast().size()>0) {
                    tagTvRec.setVisibility(View.VISIBLE);
                    tvRecycleView.setVisibility(View.VISIBLE);


                    ((CircularProgressDrawable) smothCirCast.getIndeterminateDrawable()).progressiveStop();
                    smothCirCast.setVisibility(View.INVISIBLE);

                    castTvadapter = new CastTvItemRecycleAdapter(c, castTvData.getCast());
                    tvRecycleView.setAdapter(castTvadapter);
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Error  " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "cast moview response error ");
                ((CircularProgressDrawable)smothCirCast.getIndeterminateDrawable()).progressiveStop();
                smothCirCast.setVisibility(View.INVISIBLE);
            }
        });

        AppController.getInstance().addToRequestQueue(reqs);
    }


}
