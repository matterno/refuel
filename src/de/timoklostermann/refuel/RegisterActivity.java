package de.timoklostermann.refuel;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class RegisterActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        getActionBar().setDisplayHomeAsUpEnabled(true); // Show little back icon in action bar
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_register, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
    	case android.R.id.home:
    		Intent intent = new Intent(this, LoginActivity.class);
    		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		return true;
    	case R.id.menu_clear:
    		//TODO clear
    	}
    	return super.onOptionsItemSelected(item);
    }
}
