package de.timoklostermann.refuel;

import java.util.Calendar;

import de.timoklostermann.refuel.fragments.DatePickerFragment;
import de.timoklostermann.refuel.util.Constants;
import de.timoklostermann.refuel.util.Unit;
import android.os.Bundle;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

public class EditFillingActivity extends Activity implements OnDateSetListener {

	Button btn_date;
	
	Spinner sp_fuelType;

	private Unit[] fuelTypeUnits;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_filling);

		btn_date = (Button) this.findViewById(R.id.btn_new_filling_date);
		sp_fuelType = (Spinner) this.findViewById(R.id.sp_edit_filling_fuelType);
		
		Calendar c = Calendar.getInstance();
		btn_date.setText(c.get(Calendar.DAY_OF_MONTH) + "."
				+ (c.get(Calendar.MONTH) + 1) + "." + c.get(Calendar.YEAR));

		btn_date.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				DialogFragment dpFragment = new DatePickerFragment();

				dpFragment.show(ft, "dialog");
			}
		});
		
		fuelTypeUnits = this.getFuelTypeUnits();
		
		ArrayAdapter<Unit> fuelTypeAdapter = new ArrayAdapter<Unit>(this, android.R.layout.simple_spinner_item, fuelTypeUnits);
		fuelTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_fuelType.setAdapter(fuelTypeAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_edit_filling, menu);
		return true;
	}

	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		btn_date.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_edit_filling_save:
			Intent intent = new Intent(this, SwipeActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
	
	private Unit[] getFuelTypeUnits() {
		return new Unit[] {
				new Unit(Constants.FUEL_TYPE_UNIT_UNLEADED, getString(R.string.fuel_type_unleaded)),
				new Unit(Constants.FUEL_TYPE_UNIT_PREMIUM_UNLEADED_95, getString(R.string.fuel_type_premium_unleaded_95)),
				new Unit(Constants.FUEL_TYPE_UNIT_PREMIUM_UNLEADED_100, getString(R.string.fuel_type_premium_unleaded_100)),
				new Unit(Constants.FUEL_TYPE_UNIT_SUPER_UNLEADED, getString(R.string.fuel_type_super_unleaded)),
				new Unit(Constants.FUEL_TYPE_UNIT_SUPER_PLUS_UNLEADED, getString(R.string.fuel_type_super_plus_unleaded)),
				new Unit(Constants.FUEL_TYPE_UNIT_E10, getString(R.string.fuel_type_e10)),
				new Unit(Constants.FUEL_TYPE_UNIT_BIO_ETHANOL, getString(R.string.fuel_type_bio_ethanol)),
				new Unit(Constants.FUEL_TYPE_UNIT_BIO_DIESEL, getString(R.string.fuel_type_bio_diesel)),
				new Unit(Constants.FUEL_TYPE_UNIT_DIESEL, getString(R.string.fuel_type_diesel)),
				new Unit(Constants.FUEL_TYPE_UNIT_PREMIUM_DIESEL, getString(R.string.fuel_type_premium_diesel)),
				new Unit(Constants.FUEL_TYPE_UNIT_VEGETABLE_OIL, getString(R.string.fuel_type_vegetable_oil)),
				new Unit(Constants.FUEL_TYPE_UNIT_LPG, getString(R.string.fuel_type_lpg)),
				new Unit(Constants.FUEL_TYPE_UNIT_CNGH, getString(R.string.fuel_type_cngh)),
				new Unit(Constants.FUEL_TYPE_UNIT_CNGL, getString(R.string.fuel_type_cngl))};
	}
}
