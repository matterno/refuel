package de.timoklostermann.refuel;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import de.timoklostermann.refuel.fragments.FillingFragment;
import de.timoklostermann.refuel.fragments.StatisticsFragment;
import de.timoklostermann.refuel.fragments.VehicleFragment;
import de.timoklostermann.refuel.interfaces.RequestCallback;
import de.timoklostermann.refuel.net.VehicleRequestTask;
import de.timoklostermann.refuel.util.Constants;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

public class SwipeActivity extends FragmentActivity implements RequestCallback,
		ActionBar.OnNavigationListener {

	private static final String BUNDLE_SELECTED_NAVIGATION_INDEX = "selectedNavigationIndex";
	
	private SectionsPagerAdapter mSectionsPagerAdapter;

	private ViewPager mViewPager;

	private SpinnerAdapter mSpinnerAdapter;

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

	private long vehicleID;

	private ArrayList<String> vehicles;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_swipe);
		
		restoreVehicleDataFromSharedPreferences();

		// Create the adapter that will return a fragment for each of the three
		// primary sections
		// of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// Put car navigation into actionBar
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		actionBar.setDisplayShowTitleEnabled(false);

		// Check if this onCreate restores a state (e.g. orientation change)
		if (savedInstanceState != null) {
			// Get in onSaveInstanceState saved vehicle names list and saved navigation index
			vehicles = savedInstanceState
					.getStringArrayList(Constants.VEHICLE_NAMES);
			setNavigationListAdapter(vehicles);
			actionBar.setSelectedNavigationItem(savedInstanceState
					.getInt(BUNDLE_SELECTED_NAVIGATION_INDEX));
		} else {
			// Send a vehicle request that gets all vehicle
			Log.d("SwipeActivity", "Requesting all vehicle names");
			requestAllVehicle();
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
		case R.id.menu_addVehicle:
			Intent intent = new Intent(this, NewVehicleActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		Log.d("SwipeActivity", "onNavigationItemSelected");
		
		// If the choosen list item is the same, do nothing
		if(!vehicles.get(itemPosition).equals(vehicleName)) {
			vehicleName = vehicles.get(itemPosition);

			// save Name in SharedPreferences
			SharedPreferences prefs = getSharedPreferences(
					LoginActivity.PREFERENCES, MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putString(Constants.VEHICLE_NAME, vehicleName);
			editor.commit();

			this.requestGetVehicle();
		}
		return true;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		Log.d("SwipeActivity", "onSaveInstanceState");

		outState.putStringArrayList(Constants.VEHICLE_NAMES, vehicles);
		outState.putInt(BUNDLE_SELECTED_NAVIGATION_INDEX, getActionBar().getSelectedNavigationIndex());

		super.onSaveInstanceState(outState);
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

					// TODO NewVehicleActivity on getting all
					// else ??
					break;
				}
				return;
			}

			switch (obj.getInt(Constants.REQUEST_TYPE)) {
			case Constants.REQUEST_TYPE_VEHICLE_GET_ALL_LIST:
				JSONArray array = obj.getJSONArray(Constants.VEHICLE_NAMES);

				// Getting list of all vehicle names
				vehicles = new ArrayList<String>();
				for (int i = 0; i < array.length(); i++) {
					vehicles.add((String) array.get(i));
				}

				// Set new SpinnerAdapter
				setNavigationListAdapter(vehicles);

				// Get previously selected vehicle to select it again
				SharedPreferences prefs = getSharedPreferences(
						LoginActivity.PREFERENCES, MODE_PRIVATE);

				String vehicle = prefs.getString(Constants.VEHICLE_NAME, "");
				if (vehicles.contains(vehicle)) {
					getActionBar().setSelectedNavigationItem(
							vehicles.indexOf(vehicle));
				}

				break;
			case Constants.REQUEST_TYPE_VEHICLE_GET:
				vehicleName = obj.getString(Constants.VEHICLE_NAME);
				vehicleYear = Integer.parseInt(obj
						.getString(Constants.VEHICLE_YEAR));
				vehicleMake = obj.getString(Constants.VEHICLE_MAKE);
				vehicleModel = obj.getString(Constants.VEHICLE_MODEL);
				vehicleType = Integer.parseInt(obj
						.getString(Constants.VEHICLE_TYPE_ID));
				vehicleDistanceUnit = Integer.parseInt(obj
						.getString(Constants.VEHICLE_DISTANCE_UNIT));
				vehicleQuantityUnit = Integer.parseInt(obj
						.getString(Constants.VEHICLE_QUANTITY_UNIT));
				vehicleConsumptionUnit = Integer.parseInt(obj
						.getString(Constants.VEHICLE_CONSUMPTION_UNIT));
				vehicleCurrency = obj.getString(Constants.VEHICLE_CURRENCY);
				vehicleID = obj.getLong(Constants.VEHICLE_KEY);

				saveVehicleDataToSharedPreferences();
				mSectionsPagerAdapter.notifyDataSetChanged();

				// TODO Get Filling data

				Log.d("Get_data vehicleName", vehicleName);
				Log.d("Get_data vehicleYear", vehicleYear + "");
				Log.d("Get_data vehicleMake", vehicleMake);
				break;
			case Constants.REQUEST_TYPE_VEHICLE_UPDATE:
				requestAllVehicle();
				break;
			}

		} catch (Exception e) {
			Log.d("SwipeActivity", "Error in vehicle request");
			e.printStackTrace();
			Toast.makeText(this, getString(R.string.error_unexpected),
					Toast.LENGTH_SHORT).show();

			// TODO Go to login
		}
	}

	@Override
	protected void onStop() {
		SharedPreferences prefs = getSharedPreferences(
				LoginActivity.PREFERENCES, MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(Constants.VEHICLE_NAME, vehicleName);
		editor.commit();
		super.onPause();
	}

	@Override
	public Context getContext() {
		return this;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the primary sections of the app.
	 */
	private class SectionsPagerAdapter extends FragmentStatePagerAdapter {

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
				saveVehicleDataToSharedPreferences();
				VehicleFragment fragment = new VehicleFragment();
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

		@Override
		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}
	}

	private void requestAllVehicle() {
		VehicleRequestTask req = new VehicleRequestTask(this);
		try {
			req.execute(new BasicNameValuePair(Constants.REQUEST_TYPE,
					Constants.REQUEST_TYPE_VEHICLE_GET_ALL_LIST + ""),
					new BasicNameValuePair(Constants.USER_NAME, this.userName));
		} catch (Exception e) {
			Log.d("SwipeActivity", "Error in all vehicle request");
			Toast.makeText(this, getString(R.string.error_unexpected),
					Toast.LENGTH_SHORT).show();
		}
	}

	private void requestGetVehicle() {
		Log.d("SwipeActivity", "Requesting vehicle with " + vehicleName);
		// Send a vehicle request that gets the default vehicle
		VehicleRequestTask req = new VehicleRequestTask(this);
		try {
			req.execute(
					new BasicNameValuePair(Constants.REQUEST_TYPE,
							Constants.REQUEST_TYPE_VEHICLE_GET + ""),
					new BasicNameValuePair(Constants.VEHICLE_NAME, vehicleName),
					new BasicNameValuePair(Constants.USER_NAME, this.userName));
		} catch (Exception e) {
			Log.d("SwipeActivity", "Error in get vehicle request");
			Toast.makeText(this, getString(R.string.error_unexpected),
					Toast.LENGTH_SHORT).show();
		}
	}

	private void saveVehicleDataToSharedPreferences() {
		Log.d("SwipeActivity", "saveVehicleDataToSharedPreferences");
		SharedPreferences prefs = getSharedPreferences(
				LoginActivity.PREFERENCES, MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(Constants.VEHICLE_NAME, vehicleName);
		editor.putInt(Constants.VEHICLE_YEAR, vehicleYear);
		editor.putString(Constants.VEHICLE_MAKE, vehicleMake);
		editor.putString(Constants.VEHICLE_MODEL, vehicleModel);
		editor.putInt(Constants.VEHICLE_TYPE_ID, vehicleType);
		editor.putInt(Constants.VEHICLE_DISTANCE_UNIT, vehicleDistanceUnit);
		editor.putInt(Constants.VEHICLE_QUANTITY_UNIT, vehicleQuantityUnit);
		editor.putInt(Constants.VEHICLE_CONSUMPTION_UNIT, vehicleConsumptionUnit);
		editor.putString(Constants.VEHICLE_CURRENCY, vehicleCurrency);
		editor.putLong(Constants.VEHICLE_KEY, vehicleID);
		editor.commit();
	}
	
	/**
	 * Called to restore all saved shared preferences.
	 */
	private void restoreVehicleDataFromSharedPreferences() {
		Log.d("SwipeActivity", "saveVehicleDataToSharedPreferences");
		SharedPreferences prefs = getSharedPreferences(
				LoginActivity.PREFERENCES, MODE_PRIVATE);
		
		userName = prefs.getString(Constants.LOGIN_NAME, null);
		
		vehicleName = prefs.getString(Constants.VEHICLE_NAME, null);
		vehicleName = prefs.getString(Constants.VEHICLE_NAME, null);
		vehicleYear = prefs.getInt(Constants.VEHICLE_YEAR, 0);
		vehicleMake = prefs.getString(Constants.VEHICLE_MAKE, null);
		vehicleModel = prefs.getString(Constants.VEHICLE_MODEL, null);
		vehicleType = prefs.getInt(Constants.VEHICLE_TYPE_ID, 0);
		vehicleDistanceUnit = prefs.getInt(Constants.VEHICLE_DISTANCE_UNIT, 0);
		vehicleQuantityUnit = prefs.getInt(Constants.VEHICLE_QUANTITY_UNIT, 0);
		vehicleConsumptionUnit = prefs.getInt(Constants.VEHICLE_CONSUMPTION_UNIT, 0);
		vehicleCurrency = prefs.getString(Constants.VEHICLE_CURRENCY, null);
		vehicleID = prefs.getLong(Constants.VEHICLE_KEY, 0);
	}

	private void setNavigationListAdapter(ArrayList<String> vehicles) {
		mSpinnerAdapter = new ArrayAdapter<String>(getActionBar()
				.getThemedContext(),
				android.R.layout.simple_spinner_dropdown_item, vehicles);
		getActionBar().setListNavigationCallbacks(mSpinnerAdapter, this);
	}
}
