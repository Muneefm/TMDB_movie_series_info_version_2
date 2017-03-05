package moviez.mnf.com.movie.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import moviez.mnf.com.movie.Adapters.ViewPagerAdapter;
import moviez.mnf.com.movie.R;

public class AuthenticationActivity extends AppCompatActivity {
    ViewPager pager;
    TabLayout tabLayout;
    ViewPagerAdapter mPagerAdapter;
    CharSequence Titles[]={"Sign In","Sign Up"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayoutAuth);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        pager = (ViewPager) findViewById(R.id.pagerAuth);
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, 2,2);
        pager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(pager);

/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

}
