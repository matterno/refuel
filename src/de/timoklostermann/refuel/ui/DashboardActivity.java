package de.timoklostermann.refuel.ui;

import de.timoklostermann.refuel.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
 
public class DashboardActivity extends Activity {
    Button btnLogout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        
        btnLogout = (Button) this.findViewById(R.id.btnLogout);
        
        btnLogout.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(DashboardActivity.this,SwipeActivity.class);
				startActivity(intent);
			}
		});
    }
}