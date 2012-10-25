package de.timoklostermann.refuel;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class VehicleActivity extends ListActivity {

	String[] vehicles = {"1","2"};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_vehicle);
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, vehicles);
    	setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_vehicle, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
        		Intent intent = new Intent(this, SwipeActivity.class);
        		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        		startActivity(intent);
                return true;
            case R.id.menu_addVehicle:
            	intent = new Intent(this, NewVehicleActivity.class);
            	startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	//TODO save choosen vehicle in SQLite-DB
    	
    	Intent intent = new Intent(this, SwipeActivity.class);
    	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	startActivity(intent);  	
    	super.onListItemClick(l, v, position, id);
    }

}
