package de.timoklostermann.refuel;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends Activity {

	Button btn_login;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        btn_login = (Button) this.findViewById(R.id.btn_login_login);
        
        btn_login.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				//TODO validate input
				
				//TODO Start NewVehicle-Activity if now vehicle in DB
				Intent intent = new Intent(LoginActivity.this, SwipeActivity.class);
				startActivity(intent);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
    	case R.id.menu_register:
    		// Start RegisterActivity
    		Intent intent = new Intent(this,RegisterActivity.class);
    		startActivity(intent);
    	}
    	return super.onOptionsItemSelected(item);
    }
}
