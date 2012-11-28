package de.timoklostermann.refuel;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import de.timoklostermann.refuel.interfaces.RequestCallback;
import de.timoklostermann.refuel.net.RequestTask;
import de.timoklostermann.refuel.util.Constants;
import de.timoklostermann.refuel.util.PasswordEncryption;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements RequestCallback {

	public static final String PREFERENCES = "refuelPreferences";

	private static final String PREF_REMEMBER = "rememberMe";

	private Button btn_login;

	private EditText edt_login;

	private EditText edt_pw;

	private CheckBox cb_remember;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		btn_login = (Button) this.findViewById(R.id.btn_login_login);
		edt_login = (EditText) this.findViewById(R.id.edt_login_name);
		edt_pw = (EditText) this.findViewById(R.id.edt_login_password);
		cb_remember = (CheckBox) this.findViewById(R.id.cb_login_remember);

		SharedPreferences prefs = getSharedPreferences(PREFERENCES,
				MODE_PRIVATE);
		if (prefs.getBoolean(PREF_REMEMBER, false)) {
			edt_login.setText(prefs.getString(Constants.LOGIN_NAME, ""));
			edt_pw.setText(prefs.getString(Constants.LOGIN_PASSWORD, ""));
		}

		btn_login.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// validate login form
				if (!validateLogin()) {
					Toast.makeText(LoginActivity.this,
							getResources().getString(R.string.error_input),
							Toast.LENGTH_SHORT).show();
					return;
				}

				// Send a login request
				LoginRequest req = new LoginRequest(LoginActivity.this);
				try {
					req.execute(
							new BasicNameValuePair(Constants.LOGIN_NAME,
									edt_login.getText().toString()),
							new BasicNameValuePair(Constants.LOGIN_PASSWORD,
									PasswordEncryption.encrypt(edt_pw.getText()
											.toString())));
				} catch (Exception e) {
					Toast.makeText(
							LoginActivity.this,
							getResources().getString(R.string.error_unexpected),
							Toast.LENGTH_SHORT).show();
				}
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
		switch (item.getItemId()) {
		case R.id.menu_register:
			// Start RegisterActivity
			Intent intent = new Intent(this, RegisterActivity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onStop() {
		super.onStop();

		SharedPreferences prefs = getSharedPreferences(PREFERENCES,
				MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(Constants.LOGIN_NAME, edt_login.getText().toString());
		editor.putString(Constants.LOGIN_PASSWORD, edt_pw.getText().toString());
		editor.putBoolean(PREF_REMEMBER, cb_remember.isChecked());
		
		editor.commit();
	}

	@Override
	public void onRequestComplete(JSONObject obj) {
		try {
			if (!obj.getBoolean(Constants.JSON_SUCCESS)) {

				switch (obj.getInt(Constants.JSON_ERROR)) {
				case Constants.ERROR_PW_WRONG:
					Toast.makeText(
							LoginActivity.this,
							getResources().getString(
									R.string.login_error_wrong_password),
							Toast.LENGTH_SHORT).show();
					break;
				case Constants.ERROR_USER_EXISTS_NOT:
					Toast.makeText(
							LoginActivity.this,
							getResources().getString(
									R.string.login_error_user_exists_not),
							Toast.LENGTH_SHORT).show();
					break;
				}
				return;
			}
		} catch (Exception e) {
			Log.e("LoginActivity", "Error in onRequestComplete()");
			e.printStackTrace();
			Toast.makeText(LoginActivity.this,
					getResources().getString(R.string.error_unexpected),
					Toast.LENGTH_SHORT).show();
		}

		Intent intent = new Intent(LoginActivity.this, SwipeActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

	@Override
	public Context getContext() {
		return this;
	}

	/**
	 * Looks if login name and passwort fit requirements.
	 * 
	 * @return true if ok, else false.
	 */
	private Boolean validateLogin() {
		String login = edt_login.getText().toString();
		String pw = edt_pw.getText().toString();

		if (login == null || login.isEmpty()) {
			return false;
		}
		if (pw == null || pw.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * An asynchronous task for logging in the user. If finished, it calls
	 * onLoginTaskComplete with its result.
	 * 
	 * @author Timo Klostermann
	 * 
	 */
	private class LoginRequest extends RequestTask {
		private ProgressDialog progress;

		RequestCallback callback;

		public LoginRequest(RequestCallback callback) {
			super();

			this.callback = callback;
			this.servletURL = "login";
		}

		@Override
		protected void onPreExecute() {
			this.progress = new ProgressDialog(callback.getContext());
			progress.setMessage(getResources().getString(
					R.string.login_processing));
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
								LoginActivity.this,
								getResources().getString(
										R.string.error_no_connection),
								Toast.LENGTH_SHORT).show();
					} else {
						Log.e("LoginRequest", "Error in onPostExcecute()");
						Toast.makeText(
								LoginActivity.this,
								getResources().getString(
										R.string.error_unexpected),
								Toast.LENGTH_SHORT).show();
					}
					return;
				}
				callback.onRequestComplete(obj);
			} catch (Exception e) {
				Log.e("LoginRequest", "Error in onPostExcecute()");
				Toast.makeText(LoginActivity.this,
						getResources().getString(R.string.error_unexpected),
						Toast.LENGTH_SHORT).show();
			}
		}
	}
}
