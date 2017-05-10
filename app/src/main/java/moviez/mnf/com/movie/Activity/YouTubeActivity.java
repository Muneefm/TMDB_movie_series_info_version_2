package moviez.mnf.com.movie.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import fr.castorflex.android.circularprogressbar.CircularProgressDrawable;
import moviez.mnf.com.movie.AppController;
import moviez.mnf.com.movie.DataSet.Video.Result;
import moviez.mnf.com.movie.DataSet.Video.VideoData;
import moviez.mnf.com.movie.R;

public class YouTubeActivity extends AppCompatActivity {
    List<Result> resultList;
    Gson gson = new Gson();
    VideoData videoData;
    CircularProgressBar smothCirVid;
   // private YouTubePlayerView youTubeView;
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube);
        Intent intent = getIntent();
         key = intent.getExtras().getString("id");
      //  youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        smothCirVid= (CircularProgressBar) findViewById(R.id.smoothCirVid);
        //smoothCirVid
        //Log.e("TAG","video req url = "+"http://api.themoviedb.org/3/movie/"+key+"/videos?api_key=7cf008680165ec352b68dce08866495f");


        makeVideoRequest("http://api.themoviedb.org/3/movie/"+key+"/videos?api_key=7cf008680165ec352b68dce08866495f");

    }

    void makeVideoRequest(String url){
        smothCirVid.setVisibility(View.VISIBLE);
        ((CircularProgressDrawable)smothCirVid.getIndeterminateDrawable()).start();

        JsonObjectRequest reqthree = new JsonObjectRequest(com.android.volley.Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                videoData = gson.fromJson(response.toString(), VideoData.class);
                ((CircularProgressDrawable)smothCirVid.getIndeterminateDrawable()).progressiveStop();
                smothCirVid.setVisibility(View.INVISIBLE);
                 resultList= videoData.getResults();
                if(resultList.size()!=0){
                   // youTubeView.initialize(Config.YOUTUBE_API_KEY, YouTubeActivity.this);
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + resultList.get(0).getKey()));
                    startActivity(intent);
                    finish();
                }

               // showDialogueVideo(videoData.getResults());
                //videoAdapter.addItems(videoData.getResults());


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Error  " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });

        AppController.getInstance().addToRequestQueue(reqthree);

    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_you_tube, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/*
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
            youTubePlayer.loadVideo(resultList.get(0).getKey().toString());

            // Hiding player controls
           // youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }*/
}
