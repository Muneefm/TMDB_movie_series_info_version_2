package moviez.mnf.com.movie.Fragments;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;


import moviez.mnf.com.movie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    public static String preName = "test";
    public static String USER_LEARNED_DRAWER = "lean";

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private View containerView;
    private NavigationDrawerCallbacks mCallbacks;
    private int mCurrentSelectedPosition = 0;
    RelativeLayout relMovie,tvrel;

    public  NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer= Boolean.valueOf(readFromPref(getActivity(),USER_LEARNED_DRAWER,"false"));

        if(savedInstanceState!=null){
            mFromSavedInstanceState =true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        //selectItem(1);
        relMovie = (RelativeLayout) v.findViewById(R.id.movierel);
        tvrel = (RelativeLayout) v.findViewById(R.id.reltv);

             /*   selectItem(2);
                mDrawerLayout.closeDrawers();
     */




        return v;
    }


    public void setUp(int fragmentId,DrawerLayout up,Toolbar tool) {
        containerView =getActivity().findViewById(fragmentId);

        mDrawerLayout =up;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),up,tool,R.string.navigation_drawer_open,R.string.navigation_drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!mUserLearnedDrawer){
                    mUserLearnedDrawer =true;
                    saveToPreferences(getActivity(),USER_LEARNED_DRAWER,mUserLearnedDrawer+"");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();

            }
        };

        if(!mUserLearnedDrawer&&!mFromSavedInstanceState){
             mDrawerLayout.openDrawer(containerView);
        }
          mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
       mDrawerToggle.syncState();
            }
        });


    }

    public static void saveToPreferences(Context c,String prefName,String prefValue){
        SharedPreferences sharedPreferences = c.getSharedPreferences(preName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(prefName, prefValue);
        editor.apply();
    }

    public static String readFromPref(Context context,String prefName,String defValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(preName,Context.MODE_PRIVATE);
        return sharedPreferences.getString(prefName,defValue);

    }
    private void selectItem(int position) {
        mCurrentSelectedPosition = position;

        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(containerView);
        }
        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(position);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    public static interface NavigationDrawerCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onNavigationDrawerItemSelected(int position);
    }
}
