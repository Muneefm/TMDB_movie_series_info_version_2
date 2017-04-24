package moviez.mnf.com.movie;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by Muneef on 02/05/15.
 */
public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();
    private FirebaseAnalytics mFirebaseAnalytics;

    private RequestQueue mRequestQueue;

    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-7269223551241818~8475147486");
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        File cacheDir = StorageUtils.getCacheDirectory(getApplicationContext(), true);
        ImageLoaderConfiguration localImageLoaderConfiguration =
                new ImageLoaderConfiguration.Builder(
                        getApplicationContext()).defaultDisplayImageOptions(
                        new DisplayImageOptions.Builder()
                                .showImageOnLoading(R.drawable.dwn)
                                .showImageForEmptyUri(R.mipmap.ic_imgcan)
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


        ImageLoader.getInstance().init(localImageLoaderConfiguration);



    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


}
