package de.timoklostermann.refuel.fragments;

import de.timoklostermann.refuel.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StatisticsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

    	getFillingsFromSharedPreferences();
    	
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }
    
    @Override
    public void onResume() {
    	// TODO Calculate statistics data from fillings and show them
    	super.onResume();
    }
    
    private void getFillingsFromSharedPreferences() {
    	// TODO Get data from shared preferences
    }
}
