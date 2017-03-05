package moviez.mnf.com.movie.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import moviez.mnf.com.movie.Fragments.FragmentInstance;
import moviez.mnf.com.movie.Fragments.LoginFragment;
import moviez.mnf.com.movie.Fragments.RegisterFragment;


/**
 * Created by Muneef on 02/05/15.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created

int type =0;
    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb,int ty) {
        super(fm);
        this.type = ty;
        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
        if(type ==1) {
            FragmentInstance fragmentInstance = FragmentInstance.getInstance(position);
            return fragmentInstance;
        }else if(type ==2){
            Fragment fragmentInst;
            if(position==0) {
                 fragmentInst = new LoginFragment();
            }else{
                fragmentInst = new RegisterFragment();
            }
            return fragmentInst;
        }else{
            return null;
        }

    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}