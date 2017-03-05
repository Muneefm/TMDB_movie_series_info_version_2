package moviez.mnf.com.movie.Activity;

import android.content.Intent;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.google.gson.Gson;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import org.json.JSONObject;

import java.net.URLEncoder;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import fr.castorflex.android.circularprogressbar.CircularProgressDrawable;
import info.hoang8f.android.segmented.SegmentedGroup;
import moviez.mnf.com.movie.Adapters.RecycleAdapterTv;
import moviez.mnf.com.movie.Adapters.RecylcleAdapter;
import moviez.mnf.com.movie.AppController;
import moviez.mnf.com.movie.DataSet.TV.list.TvDataList;
import moviez.mnf.com.movie.DataSet.first.DataMain;
import moviez.mnf.com.movie.DataSet.first.Result;
import moviez.mnf.com.movie.R;
import moviez.mnf.com.movie.tools.*;
import moviez.mnf.com.movie.tools.Config;

public class SearchActivity extends ActionBarActivity {


    Toolbar toolbar;
    CircularProgressBar smothCirSearch;
    public Gson gson = new Gson();
    RecycleAdapterTv adapterTv;
   // EditText search;
    RecylcleAdapter adapter;
    ObservableRecyclerView lv;
    private LinearLayoutManager mLayoutManager;
    GridLayoutManager  tvlmanager;
    String BaseUrl,BaseUrltv;
    SegmentedGroup segmented2;
     String key;
    TextView noRes;
    String TAG ="SA";
    public SearchBox search;
    String searchQuer="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
         key = intent.getExtras().getString("key");
        search = (SearchBox) findViewById(R.id.searchbox);
        search.enableVoiceRecognition(this);
        search.setLogoText(getResources().getString(R.string.action_search));
        openSearch();
        lv = (ObservableRecyclerView) findViewById(R.id.searchResult);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    //    search = (EditText) findViewById(R.id.search);
        smothCirSearch= (CircularProgressBar) findViewById(R.id.smothCirSearch);
        noRes = (TextView) findViewById(R.id.noResSearch);
        setSupportActionBar(toolbar);
        lv.setHasFixedSize(false);
        Display display = this.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density  = getResources().getDisplayMetrics().density;
        float dpWidth  = outMetrics.widthPixels / density;
        int columns = Math.round(dpWidth/170);
         segmented2 = (SegmentedGroup) findViewById(R.id.segmented2);
        segmented2.setTintColor(getResources().getColor(R.color.teal));

        adapter = new RecylcleAdapter(getApplicationContext());
        adapterTv = new RecycleAdapterTv(getApplicationContext());
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        tvlmanager = new GridLayoutManager(getApplicationContext(),columns);
        BaseUrl = "http://api.themoviedb.org/3/search/movie?api_key=7cf008680165ec352b68dce08866495f&query=";
        BaseUrltv = "http://api.themoviedb.org/3/search/tv?api_key=7cf008680165ec352b68dce08866495f&query=";


        if(key.equals("1")){
            segmented2.check(R.id.button21);
            initiliaseMovie();
        }else if(key.equals("2")){
            segmented2.check(R.id.button22);
            initialiseTv();

        }
        search.setSearchListener(new SearchBox.SearchListener() {
            @Override
            public void onSearchOpened() {

            }

            @Override
            public void onSearchCleared() {

            }

            @Override
            public void onSearchClosed() {

            }

            @Override
            public void onSearchTermChanged(String s) {
            Log.e(TAG,"on search term changed term = "+s);
                if(s.equals(" ")){
                    Log.e(TAG,"search space term");
                }
                makeSearchResultRequest(s);
            }

            @Override
            public void onSearch(String s) {
                searchQuer = s;
            Log.e(TAG,"on search q = "+s);
                if (key.equals("1")) {
                    //      Toast.makeText(getApplicationContext(),"search one = ys "+search.getText().toString(),Toast.LENGTH_LONG).show();
                    //String query = URLEncoder.encode("apples oranges", "utf-8");
                    makeJsonCrewRequest(BaseUrl + Uri.encode(s));
                } else if (key.equals("2")) {
                    //    Toast.makeText(getApplicationContext(),"search two = "+search.getText().toString(),Toast.LENGTH_LONG).show();
                    makeTvSearch(BaseUrltv + Uri.encode(s));
                }

            }

            @Override
            public void onResultClick(SearchResult searchResult) {
                Log.e(TAG,"inside result click "+searchResult.title);
                if(key.equals("1")){
                    makeJsonCrewRequest(BaseUrl + Uri.encode(searchResult.title));
                }else if(key.equals("2")){
                    makeTvSearch(BaseUrltv + Uri.encode(searchResult.title));
                }
            }
        });

        search.setMenuListener(new SearchBox.MenuListener() {
            @Override
            public void onMenuClick() {
                Log.e(TAG,"Menu click");
            }
        });

        segmented2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                search.clearSearchable();
                search.clearResults();
                switch (checkedId) {
                    case R.id.button21:
                     //   Toast.makeText(getApplicationContext(), "movie Button", Toast.LENGTH_LONG).show();

                        if (lv.getAdapter() != adapter) {
                            Log.e("TAG", "  1st adapt");
                            key = "1";

                            initiliaseMovie();
                            if (!searchQuer.equals("")) {
                                Log.e("TAG", "not null");
                                //Toast.makeText(getApplicationContext(),"this is search not null",Toast.LENGTH_LONG).show();
                               // ((CircularProgressDrawable) smothCirSearch.getIndeterminateDrawable()).start();

                                makeJsonCrewRequest(BaseUrl + Uri.encode(searchQuer.toString()));
                            } else {
                                Log.e("TAG", "search null");
                            }
                        }
                        return;
// this is the bes tt sjsd fsdf


                    case R.id.button22:
                    //    Toast.makeText(getApplicationContext(), "tv Button", Toast.LENGTH_LONG).show();
                        if (lv.getAdapter() != adapterTv) {
                            key = "2";
                            initialiseTv();
                            if (!searchQuer.equals("")) {
                               // Toast.makeText(getApplicationContext(), "this is search not null", Toast.LENGTH_LONG).show();
                                Log.e("TAG", "not null");

                                //((CircularProgressDrawable) smothCirSearch.getIndeterminateDrawable()).start();
                                makeTvSearch(BaseUrltv + Uri.encode(searchQuer.toString()));

                            }
                        }
                        return;

                }

            }
        });


     /*   search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (key.equals("1")) {
                  //      Toast.makeText(getApplicationContext(),"search one = ys "+search.getText().toString(),Toast.LENGTH_LONG).show();
                        //String query = URLEncoder.encode("apples oranges", "utf-8");
                        makeJsonCrewRequest(BaseUrl + Uri.encode(search.getText().toString()));
                    } else if (key.equals("2")) {
                    //    Toast.makeText(getApplicationContext(),"search two = "+search.getText().toString(),Toast.LENGTH_LONG).show();
                    makeTvSearch(BaseUrltv+Uri.encode(search.getText().toString()));
                    }


                    return true;
                }
                return false;
            }
        });



search.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //makeJsonCrewRequest(BaseUrl+s);
        //Log.e("TAG","String is ="+s);

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
});

*/
    }


 /*   public void initialiseAdapter(){
        if(key.equals("1")){
            lv.setLayoutManager(mLayoutManager);
            lv.setAdapter(adapter);
            segmented2.check(R.id.button21);
        }else if(key.equals("2")){
            segmented2.check(R.id.button22);
            lv.setLayoutManager(tvlmanager);
            lv.setAdapter(adapterTv);

        }

    }
    */
    public void initiliaseMovie(){

        lv.setAdapter(adapter);
        lv.setLayoutManager(mLayoutManager);

    }

    public void initialiseTv(){


        lv.setAdapter(adapterTv);
        lv.setLayoutManager(tvlmanager);

    }
    public void openSearch(){
        search.revealFromMenuItem(R.id.menu_Search, this);

    }
    public void closeSearch(){
        search.hideCircularly(this);

    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        Log.e(TAG, "back press");
       if(search.getSearchOpen()){
       closeSearch();
       }else{
           super.onBackPressed();
       }

    }


    public void makeSearchResultRequest(String term){
        AppController.getInstance().cancelPendingRequests("searchTag");
        String url = Config.BASE_URL+"search/";
        if (key.equals("1")){
            url = url+"movie?api_key="+Config.API_KEY;
        }else if(key.equals("2")){
            url = url+"tv?api_key="+Config.API_KEY;
        }
        url = url+"&query="+ Uri.encode(term);
        Log.e(TAG, "searchRequest url = " + url);
        JsonObjectRequest searchResultReq = new JsonObjectRequest(com.android.volley.Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.e(TAG, "searchRequest on response  ");
            if(key.equals("1")){
                DataMain searchFeed;
                searchFeed = gson.fromJson(jsonObject.toString(), DataMain.class);
                if (searchFeed.getResults().size() > 5) {
                    for (int ii = 0; ii < 5; ii++) {
                        SearchResult option = new SearchResult("" + searchFeed.getResults().get(ii).getTitle(), getResources().getDrawable(R.mipmap.ic_filmblack));
                        search.addSearchable(option);

                    }
                } else {
                    for (Result res : searchFeed.getResults()) {
                        SearchResult option = new SearchResult("" + res.getTitle(), getResources().getDrawable(R.mipmap.ic_filmblack));
                        search.addSearchable(option);

                    }
                }
            }else if(key.equals("2")){

                TvDataList searchtvFeed;
                searchtvFeed = gson.fromJson(jsonObject.toString(), TvDataList.class);
                if (searchtvFeed.getResults().size() > 5) {
                    for (int ii = 0; ii < 5; ii++) {
                        SearchResult option = new SearchResult("" + searchtvFeed.getResults().get(ii).getName(), getResources().getDrawable(R.mipmap.ic_filmblack));
                        search.addSearchable(option);
                    }
                } else {
                    for (moviez.mnf.com.movie.DataSet.TV.list.Result res : searchtvFeed.getResults()) {
                        SearchResult option = new SearchResult("" + res.getName(), getResources().getDrawable(R.mipmap.ic_filmblack));
                        search.addSearchable(option);

                    }
                }


            }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG,"searchRequest on error response  e = "+volleyError.getMessage());

            }
        });
        AppController.getInstance().addToRequestQueue(searchResultReq,"searchTag");

    }


    public void makeJsonCrewRequest(String uu){
        noRes.setVisibility(View.INVISIBLE);
        smothCirSearch.setVisibility(View.VISIBLE);
        ((CircularProgressDrawable)smothCirSearch.getIndeterminateDrawable()).start();
       // Toast.makeText(getApplicationContext(), "rqst inside the function  " , Toast.LENGTH_SHORT).show();

        AppController.getInstance().cancelPendingRequests("cen");
        JsonObjectRequest reqthree = new JsonObjectRequest(com.android.volley.Request.Method.GET, uu, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                 DataMain feedfirst;


                feedfirst = gson.fromJson(response.toString(), DataMain.class);
              //  Toast.makeText(getApplicationContext(), "onResponce  "+feedfirst.getTotalResults() , Toast.LENGTH_SHORT).show();

                ((CircularProgressDrawable)smothCirSearch.getIndeterminateDrawable()).progressiveStop();
                smothCirSearch.setVisibility(View.INVISIBLE);



                if(feedfirst.getTotalResults()>0) {
                    lv.setVisibility(View.VISIBLE);
                    adapter.reData(feedfirst.getResults());
                }else if(feedfirst.getTotalResults()==0){
                    noRes.setVisibility(View.VISIBLE);
                   // Toast.makeText(getApplicationContext(), "No Results" , Toast.LENGTH_SHORT).show();

                    lv.setVisibility(View.INVISIBLE);
                }
                }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Error  " , Toast.LENGTH_SHORT).show();
                ((CircularProgressDrawable)smothCirSearch.getIndeterminateDrawable()).progressiveStop();
                smothCirSearch.setVisibility(View.INVISIBLE);


            }
        });

        AppController.getInstance().addToRequestQueue(reqthree);

    }
    public void makeTvSearch(String uu){
        noRes.setVisibility(View.INVISIBLE);
        smothCirSearch.setVisibility(View.VISIBLE);
        ((CircularProgressDrawable)smothCirSearch.getIndeterminateDrawable()).start();
        AppController.getInstance().cancelPendingRequests("cen");

        JsonObjectRequest reqtv = new JsonObjectRequest(com.android.volley.Request.Method.GET, uu, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                TvDataList feedfirst;


                feedfirst = gson.fromJson(response.toString(), TvDataList.class);
                ((CircularProgressDrawable)smothCirSearch.getIndeterminateDrawable()).progressiveStop();
                smothCirSearch.setVisibility(View.INVISIBLE);
                if(feedfirst.getTotalResults()>0) {
                   // adapter.reData(feedfirst.getResults());
                    lv.setVisibility(View.VISIBLE);
                    adapterTv.reData(feedfirst.getResults());
                }else if(feedfirst.getTotalResults()==0){
                    noRes.setVisibility(View.VISIBLE);
                    lv.setVisibility(View.INVISIBLE);
                    //Toast.makeText(getApplicationContext(), "Sorry no result  ", Toast.LENGTH_SHORT).show();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Error  " , Toast.LENGTH_SHORT).show();
                ((CircularProgressDrawable)smothCirSearch.getIndeterminateDrawable()).progressiveStop();
                smothCirSearch.setVisibility(View.INVISIBLE);


            }
        });

        AppController.getInstance().addToRequestQueue(reqtv);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        Log.e(TAG,"oncreate option menu");
/*
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_searchid).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                makeJsonCrewRequest(BaseUrl + s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.e("TAG", "text changed this is " + s);

                return false;
            }
        });

*/


        return true;
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.e(TAG,"oncreate option item select");
        //noinspection SimplifiableIfStatement
       // if (id == R.id.action_settings) {
         //   return true;
        //}
        switch (id){
            case R.id.menu_Search:
                openSearch();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
