package de.timoklostermann.refuel.fragments;

import de.timoklostermann.refuel.EditFillingActivity;
import de.timoklostermann.refuel.R;
import de.timoklostermann.refuel.adapter.Filling;
import de.timoklostermann.refuel.adapter.FillingAdapter;
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
	
	//TODO reale Daten
	Filling[] testData = new Filling[] {new Filling("01.01.2012","7,9 l/100km","1,59 €/l", "37 l"), new Filling("02.02.2012","7,6 l/100km","1,63 €/l", "13 l")};

	ListView lv_filling;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	View view = inflater.inflate(R.layout.fragment_filling, container, false);
    	
    	lv_filling = (ListView) view.findViewById(R.id.listView1);
    	
    	// Populate that this Fragment wants to have a call to onCreateOptionsMenu()
    	this.setHasOptionsMenu(true);
    	
    	FillingAdapter adapter = new FillingAdapter(inflater.getContext(),testData);
    	lv_filling.setAdapter(adapter);
    	
    	lv_filling.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
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
    		Intent intent = new Intent(getActivity().getBaseContext(),EditFillingActivity.class);
    		startActivity(intent);
    	}
    	return super.onOptionsItemSelected(item);
    }
}
