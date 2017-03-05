package moviez.mnf.com.movie.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.google.gson.Gson;
import com.nineoldandroids.view.ViewHelper;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import org.json.JSONObject;

import java.io.File;
import java.util.List;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import fr.castorflex.android.circularprogressbar.CircularProgressDrawable;
import moviez.mnf.com.movie.Adapters.RecycleDialogueAdapter;
import moviez.mnf.com.movie.Adapters.RecycleViewCastCrew;
import moviez.mnf.com.movie.Adapters.RecycleViewGalleryAdapter;
import moviez.mnf.com.movie.AppController;
import moviez.mnf.com.movie.CoolAnimatedBitmapDisplayer;
import moviez.mnf.com.movie.DataSet.CastCrew.CastCrewData;
import moviez.mnf.com.movie.DataSet.Posters.MoviePosters;
import moviez.mnf.com.movie.DataSet.TV.itemDetail.Season;
import moviez.mnf.com.movie.DataSet.TV.itemDetail.TvItemDet;
import moviez.mnf.com.movie.DataSet.first.DataMain;
import moviez.mnf.com.movie.R;
import moviez.mnf.com.movie.tools.*;
import moviez.mnf.com.movie.tools.Config;

public class TvDetailActivity extends ActionBarActivity implements ObservableScrollViewCallbacks {
    private View mToolbarView,dividerOne,dividerTwo;
    private ImageView mImageView,poster;


    Button showSeason;

    private ObservableScrollView mScrollView;
    private int mParallaxImageHeight;
    RelativeLayout startRela,detRel,descCont,showSeaCont,moviinfRel;
    MoviePosters postersImages;
    CastCrewData castCrewData;
    public Gson gson = new Gson();
    TextView titleName,rate,numSeason,numEpisodes,first,lastAir;
    ImageLoader im;

    ImageLoader imPoster;
    RecycleViewCastCrew adapterCrew;
    TextView Description,tagLine,gen,status,date,lang,budget;
    RecycleViewGalleryAdapter adapterR;
    // TAG
    TextView movieFoto,movieCrew;
    TvItemDet movieDetails;
    RecycleDialogueAdapter diaAdapter;

    CircularProgressBar smothCirTvDet;

    private RecyclerView mRecyclerView,mRecyc,mRecycReview;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager,mLayoutManagerCast,mLayoutManagerRev;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbarTV));
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        String key = intent.getExtras().getString("id");
      //  Toast.makeText(getApplication()," key got = "+key,Toast.LENGTH_LONG).show();
        //ID
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        titleName = (TextView) findViewById(R.id.nameTV);
        mToolbarView =findViewById(R.id.toolbarTV);
        mImageView = (ImageView) findViewById(R.id.imageTv);
        Description =(TextView) findViewById(R.id.descripTV);
        descCont =(RelativeLayout) findViewById(R.id.descContTv);
        startRela=(RelativeLayout) findViewById(R.id.contiTv);
        budget = (TextView) findViewById(R.id.country);
        date = (TextView) findViewById(R.id.firstAirTv);
        status = (TextView) findViewById(R.id.statusTv);
        rate =(TextView) findViewById(R.id.rateTv);
        lang = (TextView) findViewById(R.id.languageTv);
        lastAir = (TextView) findViewById(R.id.lastAit);
        movieCrew = (TextView) findViewById(R.id.tagCrewtv);
        movieFoto = (TextView) findViewById(R.id.fototv);
        showSeason = (Button) findViewById(R.id.showSeasonBu);
        showSeaCont = (RelativeLayout) findViewById(R.id.seasonDetRel);
        moviinfRel = (RelativeLayout) findViewById(R.id.moviinfRel);
        dividerOne = findViewById(R.id.divideroneTv);
        dividerTwo = findViewById(R.id.dividetwoTv);
        smothCirTvDet= (CircularProgressBar) findViewById(R.id.smothCirTvDet);
        ((CircularProgressDrawable)smothCirTvDet.getIndeterminateDrawable()).start();


        gen =(TextView) findViewById(R.id.generTV);
        numSeason = (TextView) findViewById(R.id.numSeason);
        numEpisodes = (TextView) findViewById(R.id.numEpisodes);
        poster = (ImageView) findViewById(R.id.posterTv);
        mRecyclerView = (RecyclerView) findViewById(R.id.recTV);
        mRecyc = (RecyclerView) findViewById(R.id.castRecTV);
        adapterR = new RecycleViewGalleryAdapter(getApplicationContext());
        adapterCrew = new RecycleViewCastCrew(getApplicationContext());
        mLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagerCast
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapterR);
        mRecyc.setHasFixedSize(false);
        mRecyc.setLayoutManager(mLayoutManagerCast);
        mRecyc.setAdapter(adapterCrew);

        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.primary)));

        mScrollView = (ObservableScrollView) findViewById(R.id.scrollTV);
        mScrollView.setScrollViewCallbacks(this);
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.parallax_image_height);


        makeJsonArrayRequest(Config.BASE_URL+"tv/"+key+"?api_key="+Config.API_KEY);
        makeJsonImageRequest(Config.BASE_URL+"tv/" + key + "/images?api_key=" + Config.API_KEY);
        makeJsonCrewRequest(Config.BASE_URL+"tv/" + key + "/credits?api_key="+Config.API_KEY);
   /*     im = ImageLoader.getInstance();
        im.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
        File cacheDir = StorageUtils.getCacheDirectory(getApplicationContext(), true);
        ImageLoaderConfiguration localImageLoaderConfigurationTwo =
                new ImageLoaderConfiguration.Builder(
                        getApplicationContext()).defaultDisplayImageOptions(
                        new DisplayImageOptions.Builder()
                                .resetViewBeforeLoading(true)
                                .showImageOnFail(R.mipmap.ic_imgcan)
                                .cacheInMemory(true)
                                .cacheOnDisk(true)
                                .considerExifParams(true)
                                .displayer(new CoolAnimatedBitmapDisplayer(300L))
                                .build())
                        // .discCacheFileNameGenerator(new Md5FileNameGenerator())
                        .tasksProcessingOrder(QueueProcessingType.LIFO)
                        .memoryCache(new WeakMemoryCache())
                                //   .discCache(new UnlimitedDiscCache(localFile))
                                // .discCacheSize(52428800)
                                // .discCacheFileCount(100)
                        .diskCache(new UnlimitedDiscCache(cacheDir)) // default
                        .diskCacheSize(50 * 1024 * 1024)
                        .diskCacheFileCount(100)
                        .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default

                        .build();
        ImageLoader.getInstance().init(localImageLoaderConfigurationTwo);

        imPoster = ImageLoader.getInstance();
        imPoster.init(localImageLoaderConfigurationTwo);*/

    }


   public void showDialogue(List<Season> seasonList){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogue_tv_season);

       diaAdapter = new RecycleDialogueAdapter(getApplicationContext());
       LinearLayoutManager mLayoutManagerDia = new LinearLayoutManager(getApplicationContext());


        RecyclerView mRecyclerViewDia=(RecyclerView) dialog.findViewById(R.id.seasonRec);


       mRecyclerViewDia.setHasFixedSize(false);
       mRecyclerViewDia.setLayoutManager(mLayoutManagerDia);
       mRecyclerViewDia.setAdapter(diaAdapter);
       diaAdapter.addItems(seasonList);



      dialog.show();

    }



    private void makeJsonArrayRequest(String urlnew) {
        // showpDialog();
        // JSONObject req=new JsonObjectRequest()
        JsonObjectRequest req = new JsonObjectRequest(com.android.volley.Request.Method.GET, urlnew, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //  Toast.makeText(getActivity(),"name",Toast.LENGTH_SHORT).show();
                DataMain feed;

                movieDetails = gson.fromJson(response.toString(), TvItemDet.class);

                showSeaCont.setVisibility(View.VISIBLE);

                showSeason.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialogue(movieDetails.getSeasons());
                    }
                });


                titleName.setText(movieDetails.getName());
                if (movieDetails.getOverview() != null) {
                    descCont.setVisibility(View.VISIBLE);
                    Description.setText(movieDetails.getOverview());
                }
                dividerTwo.setVisibility(View.VISIBLE);
                dividerOne.setVisibility(View.VISIBLE);

                if(movieDetails.getNumberOfSeasons()!=null){
                    numSeason.setText(movieDetails.getNumberOfSeasons().toString());
                }if(movieDetails.getNumberOfEpisodes()!=null){
                    numEpisodes.setText(movieDetails.getNumberOfEpisodes().toString());
                }
             /*   if(movieDetails.getTagline()!=null) {
                    tagLine.setVisibility(View.VISIBLE);
                    tagLine.setText(movieDetails.getTagline());
                }*/

                if (movieDetails.getVoteAverage() != null) {
                    startRela.setVisibility(View.VISIBLE);
                    rate.setText(movieDetails.getVoteAverage().toString() + "/10");
                }

                moviinfRel.setVisibility(View.VISIBLE);
                if (movieDetails.getOriginCountry() != null) {
                    budget.setText(movieDetails.getOriginCountry().toString());
                }
                if (movieDetails.getFirstAirDate() != null) {
                    date.setText(movieDetails.getFirstAirDate().toString());
                }
                if (movieDetails.getStatus() != null) {
                    //detRel.setVisibility(View.VISIBLE);
                    status.setText(movieDetails.getStatus().toString());
                }
                if (movieDetails.getOriginalLanguage() != null) {
                    lang.setText(movieDetails.getOriginalLanguage());
                }
                if(movieDetails.getLastAirDate()!=null){
                    lastAir.setText(movieDetails.getLastAirDate().toString());
                }


                List<moviez.mnf.com.movie.DataSet.TV.itemDetail.Genre> genres = movieDetails.getGenres();
                if (genres.size() == 1) {
                    gen.setText(genres.get(0).getName());
                } else if (genres.size() == 2) {
                    gen.setText(genres.get(0).getName() + " | " + genres.get(1).getName());
                } else if (genres.size() == 3) {
                    gen.setText(genres.get(0).getName() + " | " + genres.get(1).getName() + " | " + genres.get(2).getName());
                }

               // imPoster.displayImage("http://image.tmdb.org/t/p/w500" + movieDetails.getPosterPath(), mImageView);
                Utils.loadImage(mImageView,movieDetails.getPosterPath(),7);
                //im.displayImage("http://image.tmdb.org/t/p/w500" + movieDetails.getBackdropPath(), poster);
                Utils.loadImage(poster,movieDetails.getBackdropPath(),5);
                mImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent str = new Intent(TvDetailActivity.this, ImageViewActivity.class);
                        str.putExtra("id", movieDetails.getPosterPath().toString());
                        str.putExtra("key", "1");
                        startActivity(str);
                    }
                });
                poster.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent str = new Intent(TvDetailActivity.this, ImageViewActivity.class);
                        str.putExtra("id", movieDetails.getBackdropPath().toString());
                        str.putExtra("key", "1");
                        startActivity(str);
                    }
                });


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Error  " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });

        AppController.getInstance().addToRequestQueue(req);
    }


    public void makeJsonImageRequest(String uu){
        JsonObjectRequest reqtwo = new JsonObjectRequest(com.android.volley.Request.Method.GET, uu, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                movieFoto.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.VISIBLE);
                postersImages = gson.fromJson(response.toString(), MoviePosters.class);

                adapterR.addItems(postersImages.getBackdrops());

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {


                Toast.makeText(getApplicationContext(), "Error  "+volleyError.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });

        AppController.getInstance().addToRequestQueue(reqtwo);

    }



    public void makeJsonCrewRequest(String uu){
        JsonObjectRequest reqthree = new JsonObjectRequest(com.android.volley.Request.Method.GET, uu, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                movieCrew.setVisibility(View.VISIBLE);
                mRecyc.setVisibility(View.VISIBLE);
                ((CircularProgressDrawable)smothCirTvDet.getIndeterminateDrawable()).progressiveStop();

                castCrewData = gson.fromJson(response.toString(), CastCrewData.class);

                adapterCrew.addItems(castCrewData.getCast());

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Error  "+volleyError.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });

        AppController.getInstance().addToRequestQueue(reqthree);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tv_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.searchTv) {

            Intent str =new Intent(TvDetailActivity.this,SearchActivity.class);
            str.putExtra("key","2");
            startActivity(str);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onScrollChanged(mScrollView.getCurrentScrollY(), false, false);
    }


    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = getResources().getColor(R.color.primary);
        float alpha = 1 - (float) Math.max(0, mParallaxImageHeight - scrollY) / mParallaxImageHeight;
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));
        ViewHelper.setTranslationY(mImageView, scrollY / 2);
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }
}
