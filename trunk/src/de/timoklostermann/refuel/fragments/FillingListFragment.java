package de.timoklostermann.refuel.fragments;

import de.timoklostermann.refuel.EditFillingActivity;
import de.timoklostermann.refuel.R;
import de.timoklostermann.refuel.adapter.Filling;
import de.timoklostermann.refuel.adapter.FillingAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FillingListFragment extends ListFragment {
	
	//TODO reale Daten
	Filling[] testData = new Filling[] {new Filling("01.01.2012","7,9 l/100km","1,59 €/l", "37 l"), new Filling("02.02.2012","7,6 l/100km","1,63 €/l", "13 l")};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	
    	
    	
    	// Populate that this Fragment wants to have a call to onCreateOptionsMenu()
    	this.setHasOptionsMenu(true);
    	
    	FillingAdapter adapter = new FillingAdapter(inflater.getContext(),testData);
    	setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    	Intent intent = new Intent(getActivity().getBaseContext(),EditFillingActivity.class);
    	startActivity(intent);
    	super.onListItemClick(l, v, position, id);
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
    		Intent intent = new Intent(getActivity().getBaseContext(),EditFillingActivity.class);
    		startActivity(intent);
    	}
    	return super.onOptionsItemSelected(item);
    }
}
