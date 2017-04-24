package moviez.mnf.com.movie.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
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
import com.robertlevonyan.views.chip.Chip;

import org.json.JSONObject;

import java.io.File;
import java.util.List;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import fr.castorflex.android.circularprogressbar.CircularProgressDrawable;
import moviez.mnf.com.movie.Adapters.RecycleReviewAdapter;
import moviez.mnf.com.movie.Adapters.RecycleViewCastCrew;
import moviez.mnf.com.movie.Adapters.RecycleViewGalleryAdapter;
import moviez.mnf.com.movie.Adapters.RecylcleAdapter;
import moviez.mnf.com.movie.Adapters.VedoRecycleAdapter;
import moviez.mnf.com.movie.AppController;
import moviez.mnf.com.movie.CoolAnimatedBitmapDisplayer;
import moviez.mnf.com.movie.DataSet.CastCrew.CastCrewData;
import moviez.mnf.com.movie.DataSet.Movie.Genre;
import moviez.mnf.com.movie.DataSet.Movie.MovieDataDet;
import moviez.mnf.com.movie.DataSet.Posters.MoviePosters;
import moviez.mnf.com.movie.DataSet.Video.VideoData;
import moviez.mnf.com.movie.DataSet.first.DataMain;
import moviez.mnf.com.movie.DataSet.movieReview.MovieReview;
import moviez.mnf.com.movie.DataSet.movieReview.Result;
import moviez.mnf.com.movie.R;
import moviez.mnf.com.movie.tools.*;
import moviez.mnf.com.movie.tools.Config;

public class MovieDetails extends ActionBarActivity implements ObservableScrollViewCallbacks{

    private ImageView mImageView,poster;
    private View mToolbarView;
    private ObservableScrollView mScrollView;
    private int mParallaxImageHeight;
    RelativeLayout startRela,detRel,descCont,bottomRel;
    MovieDataDet movieDetails;
    MoviePosters postersImages;
    CastCrewData castCrewData;
    public Gson gson = new Gson();
    TextView titleName,rate;
    ImageLoader im;
    ImageLoader imPoster;
    RecycleViewCastCrew adapterCrew;
    TextView Description,tagLine,gen,status,date,lang,budget,directedBy;
    RecycleViewGalleryAdapter adapterR;
    // TAG
    TextView movieFoto,movieCrew,noResultSimilar;
    MovieDetails ma;
    RecycleReviewAdapter adapt;
    Button showReview,showVideo,showSimilar;
    MovieReview reviewD;
    CircularProgressBar smothCirMovDet,smoothCirSimi;
    View divideOne,divideTow;
    VideoData videoData;
    VedoRecycleAdapter videoAdapter;
    RecylcleAdapter similarAdapter;
    String key;
  //  com.gc.materialdesign.views.Button visitHome;
    private RecyclerView mRecyclerView,mRecyc,mRecycReview;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager,mLayoutManagerCast,mLayoutManagerRev;
    Chip chip;
    LinearLayout generContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      //  setSupportActionBar((Toolbar) findViewById(R.id.toolbar));


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ma = new MovieDetails();
        Intent intent = getIntent();
         key = intent.getExtras().getString("id");


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        AdView mAdView = (AdView) findViewById(R.id.adViewMovie);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Toast.makeText(getApplicationContext(),"key is = "+key,Toast.LENGTH_LONG).show();
        directedBy = (TextView) findViewById(R.id.directed_tv);
        divideOne = (View) findViewById(R.id.dividerone);
        divideTow = (View) findViewById(R.id.dividetwo);
        lang = (TextView) findViewById(R.id.language);
        status = (TextView) findViewById(R.id.statusa);
        date = (TextView) findViewById(R.id.releaseDate);
        budget = (TextView) findViewById(R.id.budget);
        startRela = (RelativeLayout) findViewById(R.id.conti);
        detRel = (RelativeLayout) findViewById(R.id.detCont);
        descCont = (RelativeLayout) findViewById(R.id.descCont);
        showReview = (Button) findViewById(R.id.showReview);
        showSimilar = (Button) findViewById(R.id.showSimilar);
        bottomRel = (RelativeLayout) findViewById(R.id.bottonCont);
       // visitHome = (com.gc.materialdesign.views.Button) findViewById(R.id.visitHome);

        smothCirMovDet = (CircularProgressBar) findViewById(R.id.smothCirMovieDet);
        //TAG

        movieFoto = (TextView) findViewById(R.id.foto);
        movieCrew = (TextView) findViewById(R.id.tagCrew);

        rate = (TextView) findViewById(R.id.rate);

        mImageView = (ImageView) findViewById(R.id.image);
       // mToolbarView = findViewById(R.id.toolbar);
        titleName = (TextView) findViewById(R.id.nameT);
        poster = (ImageView) findViewById(R.id.posterIm);
        Description = (TextView) findViewById(R.id.descrip);
         adapt= new RecycleReviewAdapter(getApplicationContext());
        adapterR = new RecycleViewGalleryAdapter(getApplicationContext());
        adapterCrew = new RecycleViewCastCrew(getApplicationContext());
        mRecyc = (RecyclerView) findViewById(R.id.castRec);
        showVideo= (Button) findViewById(R.id.showVideo);
        generContainer = (LinearLayout) findViewById(R.id.gener_container);

        Typeface face=Typeface.createFromAsset(getAssets(), "fonts/FjallaOne-Regular.ttf");
        titleName.setTypeface(face);

        Typeface faceRate=Typeface.createFromAsset(getAssets(), "fonts/Righteous-Regular.ttf");
        rate.setTypeface(faceRate);
        Typeface faceTime=Typeface.createFromAsset(getAssets(), "fonts/QuattrocentoSans-Regular.ttf");

        Typeface faceDesc=Typeface.createFromAsset(getAssets(), "fonts/Abel-Regular.ttf");
        Description.setTypeface(faceDesc);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);



        showSimilar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogueSimilar();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vi = new Intent(MovieDetails.this,YouTubeActivity.class);
                vi.putExtra("id",key);
                startActivity(vi);
            }
        });
       /* showVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vi = new Intent(MovieDetails.this,YouTubeActivity.class);
                vi.putExtra("id",key);
                startActivity(vi);
            }
        });*/
        mLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagerCast
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagerRev
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView = (RecyclerView) findViewById(R.id.rec);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapterR);
        mRecyclerView.setNestedScrollingEnabled(false);

        mRecyc.setHasFixedSize(false);
        mRecyc.setLayoutManager(mLayoutManagerCast);
        mRecyc.setAdapter(adapterCrew);
        mRecyc.setNestedScrollingEnabled(false);


        gen = (TextView) findViewById(R.id.gener);
        tagLine = (TextView) findViewById(R.id.tag);
//        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.primary)));

       // mScrollView = (ObservableScrollView) findViewById(R.id.scroll);
//        mScrollView.setScrollViewCallbacks(this);
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.parallax_image_height);
        makeJsonArrayRequest("http://api.themoviedb.org/3/movie/" + key + "?api_key=7cf008680165ec352b68dce08866495f");
        makeJsonImageRequest("http://api.themoviedb.org/3/movie/" + key + "/images?api_key=7cf008680165ec352b68dce08866495f");
        makeJsonCrewRequest("http://api.themoviedb.org/3/movie/" + key + "/credits?api_key=7cf008680165ec352b68dce08866495f");
        makeJsonReiviewRequest("http://api.themoviedb.org/3/movie/" + key + "/reviews?api_key=7cf008680165ec352b68dce08866495f");

    /*    im = ImageLoader.getInstance();
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
        imPoster.init(localImageLoaderConfigurationTwo);
*/

    }



    public void showDialogue(List<Result>  re){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogue_review);

        //diaAdapter = new RecycleDialogueAdapter(getApplicationContext());


        LinearLayoutManager mLayoutManagerDia = new LinearLayoutManager(getApplicationContext());


        RecyclerView mRecyclerViewDia=(RecyclerView) dialog.findViewById(R.id.revieRec);


        mRecyclerViewDia.setHasFixedSize(false);
        mRecyclerViewDia.setLayoutManager(mLayoutManagerDia);
        mRecyclerViewDia.setAdapter(adapt);
        adapt.addItems(re);

        //mRecyclerViewDia.setAdapter(diaAdapter);
      //  diaAdapter.addItems(seasonList);



        dialog.show();
    }

    public void showDialogueSimilar(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogue_similar);

        //diaAdapter = new RecycleDialogueAdapter(getApplicationContext());
        similarAdapter= new RecylcleAdapter(getApplicationContext());

        LinearLayoutManager mLayoutManagerDia = new LinearLayoutManager(getApplicationContext());


        RecyclerView mRecyclerViewSim=(RecyclerView) dialog.findViewById(R.id.similarRec);
        noResultSimilar = (TextView) dialog.findViewById(R.id.noResSimilar);
        smoothCirSimi = (CircularProgressBar) dialog.findViewById(R.id.smothCirSimi);



        mRecyclerViewSim.setHasFixedSize(false);
        mRecyclerViewSim.setLayoutManager(mLayoutManagerDia);

        mRecyclerViewSim.setAdapter(similarAdapter);
        makeSimilarJson("http://api.themoviedb.org/3/movie/"+key+"/similar?api_key=7cf008680165ec352b68dce08866495f");

        //adapt.addItems(re);

        //mRecyclerViewDia.setAdapter(diaAdapter);
        //  diaAdapter.addItems(seasonList);



        dialog.show();
    }


    public void makeSimilarJson(String uu){
        smoothCirSimi.setVisibility(View.VISIBLE);
        ((CircularProgressDrawable)smoothCirSimi.getIndeterminateDrawable()).start();

        noResultSimilar.setVisibility(View.INVISIBLE);

        JsonObjectRequest reqtwo = new JsonObjectRequest(com.android.volley.Request.Method.GET, uu, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                DataMain dataMain;
                dataMain = gson.fromJson(response.toString(), DataMain.class);
                ((CircularProgressDrawable)smoothCirSimi.getIndeterminateDrawable()).progressiveStop();
                smoothCirSimi.setVisibility(View.INVISIBLE);
                if(dataMain.getTotalResults()==0){
                    noResultSimilar.setVisibility(View.VISIBLE);
                }
                similarAdapter.addItems(dataMain.getResults());

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {


                Toast.makeText(getApplicationContext(), "Error Network Issue  "+volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                ((CircularProgressDrawable)smoothCirSimi.getIndeterminateDrawable()).progressiveStop();
                smoothCirSimi.setVisibility(View.INVISIBLE);

            }
        });

        AppController.getInstance().addToRequestQueue(reqtwo);

    }




    private void makeJsonArrayRequest(String urlnew) {
        smothCirMovDet.setVisibility(View.VISIBLE);
        ((CircularProgressDrawable)smothCirMovDet.getIndeterminateDrawable()).start();

        // showpDialog();
        // JSONObject req=new JsonObjectRequest()
        JsonObjectRequest req = new JsonObjectRequest(com.android.volley.Request.Method.GET, urlnew, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //  Toast.makeText(getActivity(),"name",Toast.LENGTH_SHORT).show();
                DataMain feed;

                movieDetails = gson.fromJson(response.toString(), MovieDataDet.class);
                showSimilar.setVisibility(View.VISIBLE);
                ((CircularProgressDrawable)smothCirMovDet.getIndeterminateDrawable()).progressiveStop();
                smothCirMovDet.setVisibility(View.INVISIBLE);
                divideTow.setVisibility(View.VISIBLE);
//                divideOne.setVisibility(View.VISIBLE);
                titleName.setText(movieDetails.getTitle());
                if(movieDetails.getTagline()!=null){
                    directedBy.setVisibility(View.VISIBLE);
                    directedBy.setText(movieDetails.getTagline());
                }
                if(movieDetails.getOverview()!=null){
//                    descCont.setVisibility(View.VISIBLE);
                    Description.setText(movieDetails.getOverview());
                }
                if(movieDetails.getTagline()!=null) {
                  //  tagLine.setVisibility(View.VISIBLE);
                  //  tagLine.setText(movieDetails.getTagline());
                }
                if(movieDetails.getVoteAverage()!=null) {
                    startRela.setVisibility(View.VISIBLE);
                    rate.setText(movieDetails.getVoteAverage().toString() + "");
                }

                if(movieDetails.getBudget()!=null){
                    budget.setText(movieDetails.getBudget().toString());
                }
                if(movieDetails.getReleaseDate()!=null){
                    date.setText(movieDetails.getReleaseDate().toString());
                }
                if(movieDetails.getStatus()!=null){
                    detRel.setVisibility(View.VISIBLE);
                    status.setText(movieDetails.getStatus().toString());
                }
                if(movieDetails.getOriginalLanguage()!=null){
                    lang.setText(movieDetails.getOriginalLanguage());
                }

                List<Genre> genres= movieDetails.getGenres();

                for ( Genre gen:genres) {
                        chip = new Chip(getApplication());
                        chip.setChipText(gen.getName());

                        //  chip.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,9));

                        generContainer.addView(chip);

                }

              /*  if(genres.size()==1){
                    gen.setText(genres.get(0).getName());
                }else if(genres.size()==2){
                    gen.setText(genres.get(0).getName()+" | "+genres.get(1).getName());
                }else if(genres.size()==3){
                    gen.setText(genres.get(0).getName()+" | "+genres.get(1).getName()+" | "+genres.get(2).getName());
                }
*/
              //  imPoster.displayImage("http://image.tmdb.org/t/p/w500" + movieDetails.getBackdropPath(), mImageView);
               // im.displayImage("http://image.tmdb.org/t/p/w500" + movieDetails.getPosterPath(), poster);
                Utils.loadImage(mImageView, movieDetails.getPosterPath(),7);
//                Utils.loadImage(poster, movieDetails.getPosterPath(),5);
                mImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent str = new Intent(MovieDetails.this, ImageViewActivity.class);
                        str.putExtra("id", movieDetails.getBackdropPath().toString());
                        str.putExtra("key", "1");
                        startActivity(str);
                    }
                });
               /* poster.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent str = new Intent(MovieDetails.this,ImageViewActivity.class);
                        str.putExtra("id",movieDetails.getPosterPath().toString());
                        str.putExtra("key","1");
                        startActivity(str);
                    }
                });*/
                if(movieDetails.getHomepage()!=null) {
                    bottomRel.setVisibility(View.VISIBLE);
                  /*  visitHome.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String homeUrl = movieDetails.getHomepage().toString();
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(homeUrl));
                            startActivity(i);
                        }
                    });*/
                }


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Error Network Issue  " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();

                ((CircularProgressDrawable)smothCirMovDet.getIndeterminateDrawable()).progressiveStop();
                smothCirMovDet.setVisibility(View.INVISIBLE);
            }
        });




        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }

    public void makeJsonImageRequest(String uu){
        smothCirMovDet.setVisibility(View.VISIBLE);
        ((CircularProgressDrawable)smothCirMovDet.getIndeterminateDrawable()).start();

        JsonObjectRequest reqtwo = new JsonObjectRequest(com.android.volley.Request.Method.GET, uu, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                movieFoto.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.VISIBLE);
                postersImages = gson.fromJson(response.toString(), MoviePosters.class);
                ((CircularProgressDrawable)smothCirMovDet.getIndeterminateDrawable()).progressiveStop();
                smothCirMovDet.setVisibility(View.INVISIBLE);
                adapterR.addItems(postersImages.getBackdrops());

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {


                Toast.makeText(getApplicationContext(), "Error Network Issue  "+volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                ((CircularProgressDrawable)smothCirMovDet.getIndeterminateDrawable()).progressiveStop();
                smothCirMovDet.setVisibility(View.INVISIBLE);

            }
        });

        AppController.getInstance().addToRequestQueue(reqtwo);

    }

    public void makeJsonCrewRequest(String uu){
        smothCirMovDet.setVisibility(View.VISIBLE);
        ((CircularProgressDrawable)smothCirMovDet.getIndeterminateDrawable()).start();

        JsonObjectRequest reqthree = new JsonObjectRequest(com.android.volley.Request.Method.GET, uu, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                movieCrew.setVisibility(View.VISIBLE);
                mRecyc.setVisibility(View.VISIBLE);

                castCrewData = gson.fromJson(response.toString(), CastCrewData.class);
                ((CircularProgressDrawable)smothCirMovDet.getIndeterminateDrawable()).progressiveStop();
                smothCirMovDet.setVisibility(View.INVISIBLE);
                adapterCrew.addItems(castCrewData.getCast());

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Error Network Issue  "+volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                ((CircularProgressDrawable)smothCirMovDet.getIndeterminateDrawable()).progressiveStop();
                smothCirMovDet.setVisibility(View.INVISIBLE);

            }
        });

        AppController.getInstance().addToRequestQueue(reqthree);

    }

    public void makeJsonReiviewRequest(String uu){
        smothCirMovDet.setVisibility(View.VISIBLE);
        ((CircularProgressDrawable)smothCirMovDet.getIndeterminateDrawable()).start();

        JsonObjectRequest reqthree = new JsonObjectRequest(com.android.volley.Request.Method.GET, uu, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                reviewD = gson.fromJson(response.toString(), MovieReview.class);
                ((CircularProgressDrawable)smothCirMovDet.getIndeterminateDrawable()).progressiveStop();
                smothCirMovDet.setVisibility(View.INVISIBLE);

                if((reviewD.getTotalResults()!=null)) {
                    if (reviewD.getTotalResults() != 0) {
                      //  Toast.makeText(getApplicationContext(), " visi ", Toast.LENGTH_LONG).show();
                        showReview.setVisibility(View.VISIBLE);
                        showReview.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showDialogue(reviewD.getResults());

                            }
                        });
                    }
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Error Network Issue "+volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                ((CircularProgressDrawable)smothCirMovDet.getIndeterminateDrawable()).progressiveStop();
                smothCirMovDet.setVisibility(View.INVISIBLE);


            }
        });

        AppController.getInstance().addToRequestQueue(reqthree);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            Intent str =new Intent(MovieDetails.this,SearchActivity.class);
            str.putExtra("key","1");
            startActivity(str);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
      //  onScrollChanged(mScrollView.getCurrentScrollY(), false, false);
    }
    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
  /*      int baseColor = getResources().getColor(R.color.primary);
        float alpha = 1 - (float) Math.max(0, mParallaxImageHeight - scrollY) / mParallaxImageHeight;
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));
        ViewHelper.setTranslationY(mImageView, scrollY / 2);*/
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }


}
