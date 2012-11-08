package de.timoklostermann.refuel;

import java.util.Calendar;

import de.timoklostermann.refuel.fragments.DatePickerFragment;
import android.os.Bundle;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class EditFillingActivity extends Activity implements OnDateSetListener{

	Button btn_date;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_filling);
        
        btn_date = (Button) this.findViewById(R.id.btn_new_filling_date);
        
        Calendar c = Calendar.getInstance();
        btn_date.setText(c.get(Calendar.DAY_OF_MONTH) + "." + (c.get(Calendar.MONTH)+1) + "." + c.get(Calendar.YEAR));
        
        btn_date.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				DialogFragment dpFragment = new DatePickerFragment();

				dpFragment.show(ft, "dialog");
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_edit_filling, menu);
        return true;
    }

	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		btn_date.setText(dayOfMonth + "." + (monthOfYear+1) + "." + year);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.menu_edit_filling_save:
			Intent intent = new Intent(this,SwipeActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
