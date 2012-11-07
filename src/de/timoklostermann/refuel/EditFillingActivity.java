package de.timoklostermann.refuel;

import java.util.Calendar;

import de.timoklostermann.refuel.fragments.DatePickerFragment;
import android.os.Bundle;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class EditFillingActivity extends Activity {

	Button btn_date;
	
	Button btn_save;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_filling);
        
        btn_date = (Button) this.findViewById(R.id.btn_new_filling_date);
        btn_save = (Button) this.findViewById(R.id.btn_new_filling_save);
        
        Calendar c = Calendar.getInstance();
        btn_date.setText(c.get(Calendar.DAY_OF_MONTH) + "." + (c.get(Calendar.MONTH)+1) + "." + c.get(Calendar.YEAR));
        
        btn_date.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				DialogFragment newFragment = new DatePickerFragment(EditFillingActivity.this.btn_date);
				newFragment.show(ft, "dialog");
			}
		});
        
        btn_save.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//TODO save
				
				Intent intent = new Intent(EditFillingActivity.this,SwipeActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_new_filling, menu);
        return true;
    }
}
