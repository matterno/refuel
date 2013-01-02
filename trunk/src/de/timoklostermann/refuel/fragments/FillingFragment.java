package de.timoklostermann.refuel.fragments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import de.timoklostermann.refuel.EditFillingActivity;
import de.timoklostermann.refuel.R;
import de.timoklostermann.refuel.adapter.Filling;
import de.timoklostermann.refuel.adapter.FillingAdapter;
import de.timoklostermann.refuel.util.StatisticsHelper;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class FillingFragment extends Fragment {
	
	private ListView lv_filling;
	
	private List<Filling> fillings;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	View view = inflater.inflate(R.layout.fragment_filling, container, false);
    	
    	lv_filling = (ListView) view.findViewById(R.id.listView1);
    	
    	// Populate that this Fragment wants to have a call to onCreateOptionsMenu()
    	this.setHasOptionsMenu(true);
    	
    	getFillingsFromSharedPreferences();
    	
    	sortFillingsDescending();
    	
    	// Set adapter to the list view.
    	FillingAdapter adapter = new FillingAdapter(inflater.getContext(), fillings);
    	lv_filling.setAdapter(adapter);
    	
    	// Set an click listener to the list view
    	lv_filling.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// Start new Intent
				Intent intent = new Intent(getActivity().getBaseContext(),EditFillingActivity.class);
				startActivity(intent);
			}
		});
    	
        return view;
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    	super.onCreateOptionsMenu(menu, inflater);
    	inflater.inflate(R.menu.fragment_filling, menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
    	case R.id.menu_addFilling:
    		// Start new Intent
    		Intent intent = new Intent(getActivity().getBaseContext(),EditFillingActivity.class);
    		startActivity(intent);
    	}
    	return super.onOptionsItemSelected(item);
    }
    
    private void getFillingsFromSharedPreferences() {
    	// TODO
    	// Test Data!
    	fillings = new ArrayList<Filling>();
    	fillings.add(new Filling(new Date(112,10,11),149500,1.59,13, true));
    	fillings.add(new Filling(new Date(112,11,01),150137,1.63,37, true));
    	
    	StatisticsHelper helper = new StatisticsHelper(fillings);
    	fillings = helper.generateConsumptionToPreviousList();
    }
    
    private void saveFillingsToSharedPreferences() {
    	// TODO
    }
    
	/**
	 * Sorts the filling descending by date.
	 */
	private void sortFillingsDescending() {
		Collections.sort(fillings, new Comparator<Filling>() {
			@Override
			public int compare(Filling lhs, Filling rhs) {
				if(lhs.getDate().after(rhs.getDate())) {
					return -1;
				} else if(lhs.getDate().before(rhs.getDate())) {
					return 1;
				} else {
					return 0;
				}
			}
		});
	}
}
