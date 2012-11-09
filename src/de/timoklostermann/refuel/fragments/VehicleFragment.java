package de.timoklostermann.refuel.fragments;

import de.timoklostermann.refuel.NewVehicleActivity;
import de.timoklostermann.refuel.R;
import de.timoklostermann.refuel.SwipeActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class VehicleFragment extends Fragment {
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

    	View view = inflater.inflate(R.layout.fragment_vehicle, container, false);
    	
    	// Declare that this fragment has additional Option Menu items
    	this.setHasOptionsMenu(true);
        return view;
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    	super.onCreateOptionsMenu(menu, inflater);
    	inflater.inflate(R.menu.fragment_vehicle, menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
    	case R.id.menu_saveVehicle:
    		//TODO speichern
    		Toast.makeText(getActivity().getBaseContext(), "Vehicle saved", Toast.LENGTH_SHORT).show(); //TODO I18N
    		
    		if(getActivity() instanceof NewVehicleActivity) {
    			Intent intent = new Intent(getActivity().getBaseContext(),SwipeActivity.class);
    			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    			startActivity(intent);
    		}	
    	}
    	return super.onOptionsItemSelected(item);
    }
}
