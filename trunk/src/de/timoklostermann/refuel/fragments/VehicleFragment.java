package de.timoklostermann.refuel.fragments;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import de.timoklostermann.refuel.LoginActivity;
import de.timoklostermann.refuel.NewVehicleActivity;
import de.timoklostermann.refuel.R;
import de.timoklostermann.refuel.SwipeActivity;
import de.timoklostermann.refuel.interfaces.RequestCallback;
import de.timoklostermann.refuel.net.VehicleRequest;
import de.timoklostermann.refuel.util.Constants;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class VehicleFragment extends Fragment implements RequestCallback {

	private EditText edt_name;

	private EditText edt_year;

	private EditText edt_make;

	private EditText edt_model;

	private Spinner sp_distanceUnit;

	private Spinner sp_quantityUnit;

	private Spinner sp_consumptionUnit;

	private EditText edt_currency;

	private String userName;

	private long vehicleId;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_vehicle, container,
				false);

		// Declare that this fragment has additional Option Menu items
		this.setHasOptionsMenu(true);

		edt_name = (EditText) view.findViewById(R.id.edt_vehicle_name);
		edt_year = (EditText) view.findViewById(R.id.edt_vehicle_year);
		edt_make = (EditText) view.findViewById(R.id.edt_vehicle_make);
		edt_model = (EditText) view.findViewById(R.id.edt_vehicle_model);
		sp_distanceUnit = (Spinner) view.findViewById(R.id.sp_vehicle_distance);
		sp_quantityUnit = (Spinner) view.findViewById(R.id.sp_vehicle_quantity);
		sp_consumptionUnit = (Spinner) view
				.findViewById(R.id.sp_vehicle_consumption);
		edt_currency = (EditText) view.findViewById(R.id.edt_vehicle_currency);

		return view;
	}

	@Override
	public void onResume() {
		SharedPreferences prefs = getActivity().getSharedPreferences(
				LoginActivity.PREFERENCES, Context.MODE_PRIVATE);
		userName = prefs.getString(Constants.LOGIN_NAME, "");
		
		if (getActivity() instanceof SwipeActivity) {
			Log.d("onCreateView", "updateUI");
			updateUI(
					prefs.getString(Constants.VEHICLE_NAME, ""),
					prefs.getInt(Constants.VEHICLE_YEAR, 2000),
					prefs.getString(Constants.VEHICLE_MAKE, ""),
					prefs.getString(Constants.VEHICLE_MODEL, ""),
					prefs.getString(Constants.VEHICLE_CURRENCY, ""),
					prefs.getInt(Constants.VEHICLE_DISTANCE_UNIT, 0),
					prefs.getInt(Constants.VEHICLE_QUANTITY_UNIT, 0),
					prefs.getInt(Constants.VEHICLE_CONSUMPTION_UNIT, 0),
					0); 
			//TODO  prefs.getInt(Constants.VEHICLE_TYPE_ID, 0)		
			vehicleId = prefs.getLong(
					Constants.VEHICLE_KEY, 0);
		}
		
		super.onResume();
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_vehicle, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_saveVehicle:
			if (!validateVehicle()) {
				Toast.makeText(getActivity(),
						getResources().getString(R.string.error_input),
						Toast.LENGTH_SHORT).show();
				return super.onOptionsItemSelected(item);
			}

			if(getActivity() instanceof SwipeActivity) {
				updateVehicle();
			} else {
				saveVehicle();
			}
		}
		return super.onOptionsItemSelected(item);
	}

	private boolean validateVehicle() {
		String name = edt_name.getText().toString();
		String year = edt_year.getText().toString();
		String make = edt_make.getText().toString();
		String model = edt_model.getText().toString();
		String currency = edt_currency.getText().toString();

		if (name == null || name.isEmpty()) {
			return false;
		}
		if (year == null || year.isEmpty()) {
			return false;
		}
		if (Integer.parseInt(year) < 1800 || Integer.parseInt(year) > 2100) {
			return false;
		}
		if (make == null || make.isEmpty()) {
			return false;
		}
		if (model == null || model.isEmpty()) {
			return false;
		}
		if (currency == null || currency.isEmpty()) {
			return false;
		}
		return true;
	}

	public void onRequestComplete(JSONObject obj) {
		Toast.makeText(getActivity().getBaseContext(),
				getResources().getString(R.string.vehicle_toast_saved),
				Toast.LENGTH_SHORT).show();

		if (getActivity() instanceof NewVehicleActivity) {
			Intent intent = new Intent(getActivity(), SwipeActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra(Constants.LOGIN_NAME, this.userName);
			startActivity(intent);
		}
	}

	@Override
	public Context getContext() {
		return getActivity();
	}

	public void updateUI(String vehicleName, int vehicleYear, String vehicleMake, String vehicleModel, String vehicleCurrency, int vehicleDistanceUnit, int vehicleQuantityUnit, int vehicleConsumptionUnit, int vehicleType) {
		Log.d("updateUI with vehicleName", vehicleName);
		
		edt_name.setText(vehicleName);
		edt_year.setText(vehicleYear + "");
		edt_make.setText(vehicleMake);
		edt_model.setText(vehicleModel);
		edt_currency.setText(vehicleCurrency);
		sp_distanceUnit.setSelection(vehicleDistanceUnit);
		sp_quantityUnit.setSelection(vehicleQuantityUnit);
		sp_consumptionUnit.setSelection(vehicleConsumptionUnit);
		
		
		//TODO
		// sp_vehicleType.setSelectedItem(vehicleType);
	}

	private void saveVehicle() {
		// Send a vehicle request that saves the vehicle
		VehicleRequest req = new VehicleRequest(this);
		try {
			req.execute(new BasicNameValuePair(Constants.REQUEST_TYPE,
					Constants.REQUEST_TYPE_VEHICLE_SAVE + ""),
					new BasicNameValuePair(Constants.USER_NAME, this.userName),
					new BasicNameValuePair(Constants.VEHICLE_MAKE, edt_make
							.getText().toString()), new BasicNameValuePair(
							Constants.VEHICLE_MODEL, edt_model.getText()
									.toString()),
					new BasicNameValuePair(Constants.VEHICLE_NAME, edt_name
							.getText().toString()),
					new BasicNameValuePair(Constants.VEHICLE_YEAR, edt_year
							.getText().toString()),
					new BasicNameValuePair(Constants.VEHICLE_TYPE_ID, 0 + ""), // TODO!!!
					new BasicNameValuePair(Constants.VEHICLE_DISTANCE_UNIT,
							sp_distanceUnit.getSelectedItemId() + ""),
					new BasicNameValuePair(Constants.VEHICLE_QUANTITY_UNIT,
							sp_quantityUnit.getSelectedItemId() + ""),
					new BasicNameValuePair(Constants.VEHICLE_CONSUMPTION_UNIT,
							sp_consumptionUnit.getSelectedItemId() + ""),
					new BasicNameValuePair(Constants.VEHICLE_CURRENCY,
							edt_currency.getText().toString()));
		} catch (Exception e) {
			Log.d("VehicleFragment", "unexpected error");
			Toast.makeText(getActivity(),
					getResources().getString(R.string.error_unexpected),
					Toast.LENGTH_SHORT).show();
		}
	}
	
	private void updateVehicle() {
		// Send a vehicle request that saves the vehicle
		VehicleRequest req = new VehicleRequest(this);
		try {
			req.execute(new BasicNameValuePair(Constants.REQUEST_TYPE,
					Constants.REQUEST_TYPE_VEHICLE_UPDATE + ""),
					new BasicNameValuePair(Constants.USER_NAME, this.userName),
					new BasicNameValuePair(Constants.VEHICLE_MAKE, edt_make
							.getText().toString()), new BasicNameValuePair(
							Constants.VEHICLE_MODEL, edt_model.getText()
									.toString()),
					new BasicNameValuePair(Constants.VEHICLE_NAME, edt_name
							.getText().toString()),
					new BasicNameValuePair(Constants.VEHICLE_YEAR, edt_year
							.getText().toString()),
					new BasicNameValuePair(Constants.VEHICLE_TYPE_ID, 0 + ""), // TODO!!!
					new BasicNameValuePair(Constants.VEHICLE_DISTANCE_UNIT,
							sp_distanceUnit.getSelectedItemId() + ""),
					new BasicNameValuePair(Constants.VEHICLE_QUANTITY_UNIT,
							sp_quantityUnit.getSelectedItemId() + ""),
					new BasicNameValuePair(Constants.VEHICLE_CONSUMPTION_UNIT,
							sp_consumptionUnit.getSelectedItemId() + ""),
					new BasicNameValuePair(Constants.VEHICLE_CURRENCY,
							edt_currency.getText().toString()),
					new BasicNameValuePair(Constants.VEHICLE_KEY, vehicleId + ""));
		} catch (Exception e) {
			Log.d("VehicleFragment", "unexpected error");
			Toast.makeText(getActivity(),
					getResources().getString(R.string.error_unexpected),
					Toast.LENGTH_SHORT).show();
		}
	}
}
