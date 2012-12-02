package de.timoklostermann.refuel.net;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Toast;
import de.timoklostermann.refuel.R;
import de.timoklostermann.refuel.SwipeActivity;
import de.timoklostermann.refuel.interfaces.RequestCallback;
import de.timoklostermann.refuel.util.Constants;

/**
 * An asynchronous task for logging in the user. If finished, it calls
 * onLoginTaskComplete with its result.
 * 
 * @author Timo Klostermann
 * 
 */
public class VehicleRequest extends RequestTask {
	private ProgressDialog progress;

	RequestCallback callback;

	public VehicleRequest(RequestCallback callback) {
		super();

		this.callback = callback;

		this.servletURL = "vehicle";
	}

	@Override
	protected void onPreExecute() {
		this.progress = new ProgressDialog(callback.getContext());
		if(callback instanceof SwipeActivity) {
			progress.setMessage(callback.getContext().getString(R.string.vehicle_getting));
		} else {
			progress.setMessage(callback.getContext().getString(R.string.vehicle_saving));
		}
		
		progress.show();
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		if (progress.isShowing()) {
			progress.dismiss();
		}
		try {
			JSONObject obj = this.get();
			if (obj == null) {
				if (errorcode == Constants.ERROR_NO_CONNECTION) {
					Toast.makeText(
							callback.getContext(),
							callback.getContext().getString(
									R.string.error_no_connection),
							Toast.LENGTH_SHORT).show();
				} else {
					Log.e("LoginRequest", "Error in onPostExcecute()");
					Toast.makeText(
							callback.getContext(),
							callback.getContext().getString(R.string.error_unexpected),
							Toast.LENGTH_SHORT).show();
				}
				return;
			}
			callback.onRequestComplete(obj);
		} catch (Exception e) {
			Log.e("VehicleRequest", "Error in onPostExcecute()");
			e.printStackTrace();
			Toast.makeText(callback.getContext(),
					callback.getContext().getString(R.string.error_unexpected),
					Toast.LENGTH_SHORT).show();
		}
	}
}