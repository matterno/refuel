package de.timoklostermann.refuel;

import de.timoklostermann.refuel.fragments.FillingFragment;
import de.timoklostermann.refuel.fragments.StatisticsFragment;
import de.timoklostermann.refuel.fragments.VehicleFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class SwipeActivity extends FragmentActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
     * sections. We use a {@link android.support.v4.app.FragmentPagerAdapter} derivative, which will
     * keep every loaded fragment in memory. If this becomes too memory intensive, it may be best
     * to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        // Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_swipe, menu);
        return true;
    }

    


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the app.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
        	switch(i) {
        	case 0: return new FillingFragment();
        	case 1: return new StatisticsFragment();
        	case 2: return new VehicleFragment();
        	default: return null;
        	}
        	

        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: return getString(R.string.title_fragment_filling).toUpperCase();
                case 1: return getString(R.string.title_fragment_statistics).toUpperCase();
                case 2: return getString(R.string.title_fragment_vehicle).toUpperCase();
            }
            return null;
        }
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
    	case R.id.menu_changeVehicle:
    		Intent intent = new Intent(this, VehicleActivity.class);
    		startActivity(intent);
    		break;
    	case R.id.menu_logout:
    		// TODO SQLite no automatic login
    		intent = new Intent(this, LoginActivity.class);
    		startActivity(intent);
    	}
    	return super.onOptionsItemSelected(item);
    }
}
