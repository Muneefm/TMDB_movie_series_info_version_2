package moviez.mnf.com.movie;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.github.ksoichiro.android.observablescrollview.CacheFragmentStatePagerAdapter;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import moviez.mnf.com.movie.Activity.AuthenticationActivity;
import moviez.mnf.com.movie.Activity.SearchActivity;
import moviez.mnf.com.movie.Activity.TvDetailActivity;
import moviez.mnf.com.movie.Adapters.ViewPagerAdapter;
import moviez.mnf.com.movie.Adapters.ViewPagerAdapterTv;
import moviez.mnf.com.movie.Fragments.FragmentInstance;
import moviez.mnf.com.movie.Fragments.NavigationDrawerFragment;
import moviez.mnf.com.movie.Tabs.SlidingTabLayout;


public class MainActivity extends ActionBarActivity implements ObservableScrollViewCallbacks,NavigationDrawerFragment.NavigationDrawerCallbacks {
Toolbar toolbar;
    CharSequence Titles[]={"Top Rated","Popular","Now Playing","Upcoming"};  //"Upcoming","Now Playing","Popular","Top Rated"
    CharSequence TitlesTv[]={"On Air","Popular","Top Rated","Airing Today"};  //"Upcoming","Now Playing","Popular","Top Rated"

    ViewPagerAdapter mPagerAdapter;
    ViewPagerAdapterTv mPagerAdapterTv;
    SlidingTabLayout slidingTabLayout;
    int Numboftabs =5;
    ViewPager pager;
    DrawerLayout drawerLayout;

    private NavigationView navigationView;

    int kk =1;
    TabLayout tabLayout;

    private View mHeaderView;
    private View mToolbarView;
    private int mBaseTranslationY;


    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout drawer;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mDrawerItmes;
    //private NavigationAdapter mPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent strt = new Intent(MainActivity.this, TvDetailActivity.class);
        //startActivity(strt);
         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar((toolbar));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        final View decorView = getWindow().getDecorView();
        final int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
      //  setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent searchIn = new Intent(MainActivity.this, SearchActivity.class);
                searchIn.putExtra("key", "1");
                startActivity(searchIn);
            }
        });
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawerOpen, R.string.drawerClosed) {


            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(actionBarDrawerToggle);
        mDrawerItmes = getResources().getStringArray(R.array.drawer_titles);

        //ViewCompat.setElevation(mHeaderView, getResources().getDimension(R.dimen.toolbar_elevation));
        mToolbarView = findViewById(R.id.toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
       // tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
       // tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
       // tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));

        pager = (ViewPager) findViewById(R.id.pager);
            mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, 4,1);

            mPagerAdapterTv = new ViewPagerAdapterTv(getSupportFragmentManager(), TitlesTv, 4);
   //         pager.setAdapter(mPagerAdapterTv);
        pager.setAdapter(mPagerAdapter);

        tabLayout.setupWithViewPager(pager);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decorView.setSystemUiVisibility(uiOptions);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawer.closeDrawers();
                // AppController.getInstance().getRequestQueue().stop();
                if (AppController.getInstance().getRequestQueue() != null) {
                    Log.e("QUE", "not null");
                } else {
                    Log.e("QUE", "null");
                }
                switch (menuItem.getItemId()) {

                    case R.id.movieItem:
                        pager.setAdapter(mPagerAdapter);
                        tabLayout.setupWithViewPager(pager);
                        break;
                    case R.id.seriesItem:
                        pager.setAdapter(mPagerAdapterTv);
                        tabLayout.setupWithViewPager(pager);
                        break;
                    case R.id.searchItem:
                        Intent searchIn = new Intent(MainActivity.this, SearchActivity.class);
                        searchIn.putExtra("key", "1");
                        startActivity(searchIn);
                        break;

                }
                return true;
            }
        });

       // slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        //slidingTabLayout.setDistributeEvenly(true);

        /// Drawer

      //  NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_frag);

       // drawerFragment.setUp(R.id.navigation_frag, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

    /*    slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.white);
            }
        });
        // Setting the ViewPager For the SlidingTabsLayout
        slidingTabLayout.setViewPager(pager);

        slidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
            }

            @Override
            public void onPageSelected(int i) {
              //  propagateToolbarState(toolbarIsShown());
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
*/
       // propagateToolbarState(toolbarIsShown());
    }



   /* private void propagateToolbarState(boolean isShown) {
        int toolbarHeight = mToolbarView.getHeight();

        // Set scrollY for the fragments that are not created yet
        mPagerAdapter.setScrollY(isShown ? 0 : toolbarHeight);

        // Set scrollY for the active fragments
        for (int i = 0; i < mPagerAdapter.getCount(); i++) {
            // Skip current item
            if (i == pager.getCurrentItem()) {
                continue;
            }

            // Skip destroyed or not created item
            Fragment f = mPagerAdapter.getItemAt(i);
            if (f == null) {
                continue;
            }

            ObservableRecyclerView listView = (ObservableRecyclerView) f.getView().findViewById(R.id.movieList);
            if (isShown) {
                // Scroll up
                if (0 < listView.getCurrentScrollY()) {
                   // listView.setSelection(0);
                    listView.scrollVerticallyToPosition(0);

                }
            } else {
                // Scroll down (to hide padding)
                if (listView.getCurrentScrollY() < toolbarHeight) {
                    //listView.setSelection(1);
                    listView.scrollVerticallyToPosition(1);
                }
            }
        }
    }

    /////////////////////

    private boolean toolbarIsShown() {
        return ViewHelper.getTranslationY(mHeaderView) == 0;
    }

    private boolean toolbarIsHidden() {
        return ViewHelper.getTranslationY(mHeaderView) == -mToolbarView.getHeight();
    }

    private void showToolbar() {
        float headerTranslationY = ViewHelper.getTranslationY(mHeaderView);
        if (headerTranslationY != 0) {
            ViewPropertyAnimator.animate(mHeaderView).cancel();
            ViewPropertyAnimator.animate(mHeaderView).translationY(0).setDuration(200).start();
        }
        propagateToolbarState(true);
    }

    private void hideToolbar() {
        float headerTranslationY = ViewHelper.getTranslationY(mHeaderView);
        int toolbarHeight = mToolbarView.getHeight();
        if (headerTranslationY != -toolbarHeight) {
            ViewPropertyAnimator.animate(mHeaderView).cancel();
            ViewPropertyAnimator.animate(mHeaderView).translationY(-toolbarHeight).setDuration(200).start();
        }
        propagateToolbarState(false);
    }

*/


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
//Toast.makeText(getApplicationContext(),"pos "+position,Toast.LENGTH_LONG).show();
        if(position==1){
            pager.setAdapter(mPagerAdapter);
            slidingTabLayout.setViewPager(pager);
        }else if(position==2){
            pager.setAdapter(mPagerAdapterTv);
            slidingTabLayout.setViewPager(pager);
            slidingTabLayout.setDistributeEvenly(true);

        }else if(position ==0){
            Intent searchIn = new Intent(MainActivity.this, SearchActivity.class);
            searchIn.putExtra("key", "1");
            startActivity(searchIn);
        }


    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

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
            Intent searchIn = new Intent(MainActivity.this, SearchActivity.class);

            if(pager.getAdapter()==mPagerAdapter) {
                searchIn.putExtra("key", "1");
                startActivity(searchIn);
                //  Toast.makeText(getApplicationContext()," equal  ",Toast.LENGTH_LONG).show();
            }else if(pager.getAdapter()==mPagerAdapterTv){
                searchIn.putExtra("key", "2");
                startActivity(searchIn);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        if (dragging) {
            int toolbarHeight = mToolbarView.getHeight();
            float currentHeaderTranslationY = ViewHelper.getTranslationY(mHeaderView);
            if (firstScroll) {
                if (-toolbarHeight < currentHeaderTranslationY) {
                    mBaseTranslationY = scrollY;
                }
            }
            float headerTranslationY = ScrollUtils.getFloat(-(scrollY - mBaseTranslationY), -toolbarHeight, 0);
            ViewPropertyAnimator.animate(mHeaderView).cancel();
            ViewHelper.setTranslationY(mHeaderView, headerTranslationY);
        }

    }


    @Override
    public void onDownMotionEvent() {

    }
 /*   private Fragment getCurrentFragment() {
        return mPagerAdapter.getItemAt(pager.getCurrentItem());
    }*/

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
     /*   mBaseTranslationY = 0;

        Fragment fragment=getCurrentFragment();
        if (fragment == null) {
            return;
        }
        View view = fragment.getView();
        if (view == null) {
            return;
        }

        int toolbarHeight = mToolbarView.getHeight();
        final ObservableRecyclerView listView = (ObservableRecyclerView) view.findViewById(R.id.movieList);
        if (listView == null) {
            return;
        }
        int scrollY = listView.getCurrentScrollY();
        if (scrollState == ScrollState.DOWN) {
            showToolbar();
        } else if (scrollState == ScrollState.UP) {
            if (toolbarHeight <= scrollY) {
                hideToolbar();
            } else {
                showToolbar();
            }
        } else {
            // Even if onScrollChanged occurs without scrollY changing, toolbar should be adjusted
            if (toolbarIsShown() || toolbarIsHidden()) {
                // Toolbar is completely moved, so just keep its state
                // and propagate it to other pages
                propagateToolbarState(toolbarIsShown());
            } else {
                // Toolbar is moving but doesn't know which to move:
                // you can change this to hideToolbar()
                showToolbar();
            }
        }

*/
    }



    private static class NavigationAdapter extends CacheFragmentStatePagerAdapter {

        private static final String[] TITLES = new String[]{"Now Running", "Upcoming", "New Release", "Latest"};

        private int mScrollY;           //"Now Running", "Upcoming", "New Release", "Latest"

        public NavigationAdapter(FragmentManager fm) {
            super(fm);
        }

        public void setScrollY(int scrollY) {
            mScrollY = scrollY;
        }

        @Override
        protected Fragment createItem(int position) {
            Fragment f = new FragmentInstance();
            if (0 < mScrollY) {
                Bundle args = new Bundle();
                args.putInt(FragmentInstance.ARG_INITIAL_POSITION, 1);
                f.setArguments(args);
            }
            return f;
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }
    }




}
