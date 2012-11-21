package de.timoklostermann.refuel;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import de.timoklostermann.refuel.fragments.FillingFragment;
import de.timoklostermann.refuel.fragments.StatisticsFragment;
import de.timoklostermann.refuel.fragments.VehicleFragment;
import de.timoklostermann.refuel.interfaces.RequestCallback;
import de.timoklostermann.refuel.net.VehicleRequest;
import de.timoklostermann.refuel.util.Constants;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SwipeActivity extends FragmentActivity implements RequestCallback {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	private ViewPager mViewPager;

	private String userName;

	private String vehicleName;

	private int vehicleYear;

	private String vehicleMake;

	private String vehicleModel;

	private int vehicleType;

	private int vehicleDistanceUnit;

	private int vehicleQuantityUnit;

	private int vehicleConsumptionUnit;

	private String vehicleCurrency;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_swipe);

		userName = getIntent().getExtras().getString(Constants.LOGIN_NAME);

		// Create the adapter that will return a fragment for each of the three
		// primary sections
		// of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// Send a vehicle request that gets the default vehicle
		VehicleRequest req = new VehicleRequest(this);
		try {
			req.execute(new BasicNameValuePair(Constants.REQUEST_TYPE,
					Constants.REQUEST_TYPE_VEHICLE_GET_DEFAULT + ""),
					new BasicNameValuePair(Constants.VEHICLE_USER,
							this.userName));
		} catch (Exception e) {
			Toast.makeText(this, getString(R.string.error_unexpected),
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_swipe, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_changeVehicle:
			Intent intent = new Intent(this, ChooseVehicleActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra(Constants.LOGIN_NAME, this.userName);
			startActivity(intent);
			break;
		case R.id.menu_logout:
			intent = new Intent(this, LoginActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onRequestComplete(JSONObject obj) {
		// TODO Start NewVehicle-Activity if no vehicle in DB

		try {
			if (!obj.getBoolean(Constants.JSON_SUCCESS)) {

				switch (obj.getInt(Constants.JSON_ERROR)) {
				case Constants.ERROR_VEHICLE_EXISTS_NOT:
					Toast.makeText(this,
							getString(R.string.vehicle_error_not_found),
							Toast.LENGTH_SHORT).show();
					
					// TODO NewVehicleActivity
					break;
				}
				return;
			}
			
			vehicleName = obj.getString(Constants.VEHICLE_NAME);
			vehicleYear = Integer.parseInt(obj.getString(Constants.VEHICLE_YEAR));
			vehicleMake = obj.getString(Constants.VEHICLE_MAKE);
			vehicleModel = obj.getString(Constants.VEHICLE_MODEL);
			vehicleType = Integer.parseInt(obj.getString(Constants.VEHICLE_TYPE_ID));
			vehicleDistanceUnit = Integer.parseInt(obj.getString(Constants.VEHICLE_DISTANCE_UNIT));
			vehicleQuantityUnit = Integer.parseInt(obj.getString(Constants.VEHICLE_QUANTITY_UNIT));
			vehicleConsumptionUnit = Integer.parseInt(obj.getString(Constants.VEHICLE_CONSUMPTION_UNIT));
			vehicleCurrency = obj.getString(Constants.VEHICLE_CURRENCY);
		} catch (Exception e) {
			Toast.makeText(this,
					getString(R.string.error_unexpected),
					Toast.LENGTH_SHORT).show();
		}
		
		
		
		// TODO Get Filling data
	}

	@Override
	public Context getContext() {
		return this;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the primary sections of the app.
	 */
	private class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			switch (i) {
			case 0:
				return new FillingFragment();
			case 1:
				return new StatisticsFragment();
			case 2:
				Bundle arguments = new Bundle();
				arguments.putString(Constants.VEHICLE_NAME, SwipeActivity.this.vehicleName);
				arguments.putInt(Constants.VEHICLE_YEAR, SwipeActivity.this.vehicleYear);
				arguments.putString(Constants.VEHICLE_MAKE, SwipeActivity.this.vehicleMake);
				arguments.putString(Constants.VEHICLE_MODEL, SwipeActivity.this.vehicleModel);
				arguments.putInt(Constants.VEHICLE_TYPE_ID, SwipeActivity.this.vehicleType); 
				arguments.putInt(Constants.VEHICLE_DISTANCE_UNIT, SwipeActivity.this.vehicleDistanceUnit);
				arguments.putInt(Constants.VEHICLE_QUANTITY_UNIT, SwipeActivity.this.vehicleQuantityUnit);
				arguments.putInt(Constants.VEHICLE_CONSUMPTION_UNIT, SwipeActivity.this.vehicleConsumptionUnit);
				arguments.putString(Constants.VEHICLE_CURRENCY, SwipeActivity.this.vehicleCurrency);
				VehicleFragment fragment = new VehicleFragment();
				fragment.setArguments(arguments);
				return fragment;
			default:
				return null;
			}

		}

		@Override
		public int getCount() {
			return 3;
		}

		@SuppressLint("DefaultLocale")
		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return getString(R.string.title_fragment_filling).toUpperCase();
			case 1:
				return getString(R.string.title_fragment_statistics)
						.toUpperCase();
			case 2:
				return getString(R.string.title_fragment_vehicle).toUpperCase();
			}
			return null;
		}
	}
}
