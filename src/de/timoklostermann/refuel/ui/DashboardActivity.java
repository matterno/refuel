package de.timoklostermann.refuel.ui;

import de.timoklostermann.refuel.R;
import de.timoklostermann.refuel.net.UserService;
import de.timoklostermann.refuel.sqlite.UserHandler;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
 
public class DashboardActivity extends Activity {
    UserService userFunctions;
    Button btnLogout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        /**
         * Dashboard Screen for the application
         * */
        // Check login status in database
        userFunctions = new UserService();
        UserHandler db = new UserHandler(getApplicationContext());
        if(db.getRowCount() > 0){
       // user already logged in show databoard
            setContentView(R.layout.activity_dashboard);
            btnLogout = (Button) findViewById(R.id.btnLogout);
 
            btnLogout.setOnClickListener(new View.OnClickListener() {
 
                public void onClick(View arg0) {
                	UserHandler db = new UserHandler(getApplicationContext());
                	db.resetTables();
                    Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                    login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(login);
                    // Closing dashboard screen
                    finish();
                }
            });
 
        }else{
            // user is not logged in show login screen
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(login);
            // Closing dashboard screen
            finish();
        }
    }
}