package moviez.mnf.com.movie.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.pnikosis.materialishprogress.ProgressWheel;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import fr.castorflex.android.circularprogressbar.CircularProgressDrawable;
import moviez.mnf.com.movie.AppController;
import moviez.mnf.com.movie.DataSet.CastDetail.CrewDetailsData;
import moviez.mnf.com.movie.R;

public class ImageViewActivity extends ActionBarActivity {

    ImageView foto;
    protected ProgressWheel progressWheel;

    //cast
    ImageView pro;
    TextView ovrview,date,place,name;
    CrewDetailsData data;
    RelativeLayout mainRel;

    Button downloadImg;
    public Gson gson = new Gson();
    ImageView down,share;
    CircularProgressBar smothCirCast;
    TextView  birthTag,tagplace,tagOvr;


    ImageLoader im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String key = intent.getExtras().getString("id");
        String keyTwo = intent.getExtras().getString("key");
        im = ImageLoader.getInstance();
        im.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
        if(keyTwo.equals("1")) {
            setContentView(R.layout.activity_image_view);
            foto = (ImageView) findViewById(R.id.img);
            down = (ImageView) findViewById(R.id.downlo);
            share =(ImageView) findViewById(R.id.share);
            im.displayImage("http://image.tmdb.org/t/p/w500" + key, foto);

         final   String dupliKey = key;
            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    File imageFile = ImageLoader.getInstance().getDiskCache().get("http://image.tmdb.org/t/p/w500" + dupliKey);
                    Uri uri = Uri.parse(imageFile.toString());
                    Intent intent = new Intent(Intent.ACTION_SEND);


                    intent.setType("image/png");

                    //intent.putExtra(Intent.EXTRA_SUBJECT, item.getMessage());
                    //  intent.putExtra(Intent.EXTRA_TEXT, item.getMessage());

                    intent.putExtra(Intent.EXTRA_STREAM, uri);

                    startActivity(Intent.createChooser(intent, "Share image with"));

                }
            });

            down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


            File imageFile = ImageLoader.getInstance().getDiskCache().get("http://image.tmdb.org/t/p/w500" + dupliKey);
            Uri uri = Uri.parse(imageFile.toString());
            File dir = new File(Environment.getExternalStorageDirectory(),"Movie Buzz");

            try{
                if(dir.mkdir()) {
                    System.out.println("Directory created");

                    Log.e("TAG", "inside try" + Environment.getExternalStorageDirectory()+"  uri "+uri);
                } else {
                    System.out.println("Directory is not created");
                    Log.e("TAG", "inside else try" + Environment.getExternalStorageDirectory()+" uri "+uri);

                }
            }catch(Exception e){
                e.printStackTrace();
            }


            //start
            File sourceLocation = new File (uri.toString());
            //File targetLocation = new File (Environment.getExternalStorageDirectory()+"/Movie Buzz/"+getRandomNum()+".jpg");
              File targetLocation = checkExistFile();

            Log.e("TAG", "sourceLocation: " + sourceLocation);
            Log.e("TAG", "targetLocation: " + targetLocation);
       if(targetLocation.exists()){
        Log.e("TAG", " identify:  exist " + targetLocation);


       }else{
            Log.e("TAG", " identify:  not exis " + targetLocation);
        }

            if(sourceLocation.exists()) {
                try {

                    InputStream in = null;
                    in = new FileInputStream(sourceLocation);
                    OutputStream out = new FileOutputStream(targetLocation);
                    byte[] buf = new byte[1024];
                    int len;

                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }

                    in.close();
                    out.close();

                    Log.e("TAG", "Copy file successful.");
                    Toast.makeText(getApplicationContext(),"Image Downloaded",Toast.LENGTH_LONG).show();

                } catch (FileNotFoundException e) {
                    Log.e("TAG", "File not found.");
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                Log.e("TAG", "Copy file failed. Source file missing.");
            }


                // Copy the bits from instream to outstream


                }
            });


           // Log.e("TAG", "" + Environment.getExternalStorageDirectory() + Environment.getDataDirectory());


        }else if(keyTwo.equals("2")){
            setContentView(R.layout.cast_detail);
            //Tag
              birthTag = (TextView) findViewById(R.id.birthTag);
              tagplace = (TextView) findViewById(R.id.tagplace);
              tagOvr = (TextView) findViewById(R.id.tagOvr);

            pro =(ImageView) findViewById(R.id.imgpro);
            ovrview = (TextView) findViewById(R.id.ovr);
            date = (TextView) findViewById(R.id.birthday);
            place = (TextView) findViewById(R.id.place);
            name = (TextView) findViewById(R.id.namecast);
            mainRel = (RelativeLayout) findViewById(R.id.mainRel);
            smothCirCast = (CircularProgressBar) findViewById(R.id.smothCirCast);


makeJsonCrewRequest("http://api.themoviedb.org/3/person/"+key+"?api_key=7cf008680165ec352b68dce08866495f");

        }

    }
    public void makeJsonCrewRequest(String uu){
        smothCirCast.setVisibility(View.VISIBLE);
        ((CircularProgressDrawable)smothCirCast.getIndeterminateDrawable()).start();
        JsonObjectRequest reqst = new JsonObjectRequest(com.android.volley.Request.Method.GET, uu,null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                data = gson.fromJson(response.toString(), CrewDetailsData.class);

                ((CircularProgressDrawable)smothCirCast.getIndeterminateDrawable()).progressiveStop();
                smothCirCast.setVisibility(View.INVISIBLE);

                mainRel.setVisibility(View.VISIBLE);
                im.displayImage("http://image.tmdb.org/t/p/w500" + data.getProfilePath(), pro);
                pro.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent strt = new Intent(ImageViewActivity.this,ImageViewActivity.class);
                        strt.putExtra("id",data.getProfilePath().toString());
                        strt.putExtra("key","1");
                        startActivity(strt);
                    }
                });

                if(data.getBirthday()!=null) {
                    birthTag.setVisibility(View.VISIBLE);
                    date.setText(data.getBirthday().toString());
                }
                if(data.getBiography()!=null&&!data.getBiography().toString().equals("")){
                    tagOvr.setVisibility(View.VISIBLE);
                    ovrview.setText(data.getBiography().toString());
                }
                if(data.getPlaceOfBirth()!=null){
                    tagplace.setVisibility(View.VISIBLE);
                    place.setText(data.getPlaceOfBirth().toString());
                }
                if(data.getName()!=null){
                    name.setText(data.getName().toString());
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Error  "+volleyError.getMessage(), Toast.LENGTH_SHORT).show();

                ((CircularProgressDrawable)smothCirCast.getIndeterminateDrawable()).progressiveStop();
                smothCirCast.setVisibility(View.INVISIBLE);
            }
        });

        AppController.getInstance().addToRequestQueue(reqst);

    }

    public int getRandomNum(){

        int min = 65;
        int max = 2000;

        Random r = new Random();
        int i1 = r.nextInt(max - min + 1) + min;
        return i1;

    }

    public File checkExistFile(){
        File targetLocationFun = new File (Environment.getExternalStorageDirectory()+"/Movie Buzz/"+getRandomNum()+".jpg");

        if(targetLocationFun.exists()){
            checkExistFile();

        }else{
            return targetLocationFun;
        }
        return null;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_view, menu);
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
}
