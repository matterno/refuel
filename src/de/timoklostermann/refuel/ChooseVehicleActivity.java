package de.timoklostermann.refuel;

import de.timoklostermann.refuel.util.Constants;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ChooseVehicleActivity extends ListActivity {

	private String[] vehicles = {"1","2"};
	
	private String userName;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        this.userName = getIntent().getExtras().getString(Constants.LOGIN_NAME);
        Log.d("ChooseVehicle username", userName);
        
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
        		break;
            case R.id.menu_addVehicle:
            	intent = new Intent(this, NewVehicleActivity.class);
            	intent.putExtra(Constants.LOGIN_NAME, this.userName);
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
