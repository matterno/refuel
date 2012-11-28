package de.timoklostermann.refuel;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity implements RequestCallback {

	Button btn_register;
	
	EditText edt_name;
	
	EditText edt_password;
	
	EditText edt_password_repeat;
	
	EditText edt_email;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        getActionBar().setDisplayHomeAsUpEnabled(true); // Show little back icon in action bar
        
        btn_register = (Button) this.findViewById(R.id.btn_register_register);
        edt_name = (EditText) this.findViewById(R.id.edt_register_name);
        edt_password = (EditText) this.findViewById(R.id.edt_register_password);
        edt_password_repeat = (EditText) this.findViewById(R.id.edt_register_password_repeat);
        edt_email = (EditText) this.findViewById(R.id.edt_register_email);
        
        btn_register.setOnClickListener(new View.OnClickListener() {
			
        	
			public void onClick(View v) {
				if(!validateRegister()) {
					Toast.makeText(RegisterActivity.this, getResources().getString(R.string.error_input), Toast.LENGTH_SHORT).show();
					return;
				}
				
				RegisterRequest req = new RegisterRequest(RegisterActivity.this);
				
				try {
					req.execute(
							new BasicNameValuePair(Constants.REGISTER_NAME, edt_name.getText().toString()),
							new BasicNameValuePair(Constants.REGISTER_PW, PasswordEncryption.encrypt(edt_password.getText().toString())),
							new BasicNameValuePair(Constants.REGISTER_EMAIL, edt_email.getText().toString()));
				} catch (Exception e) {
					Toast.makeText(RegisterActivity.this, getResources().getString(R.string.error_unexpected), Toast.LENGTH_SHORT).show();
				}
			}
		});
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
    	case android.R.id.home:
    		Intent intent = new Intent(this, LoginActivity.class);
    		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		return true;
    	}
    	return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void onRequestComplete(JSONObject obj) {
    	try {
			if(!obj.getBoolean(Constants.JSON_SUCCESS)) {
				Toast.makeText(RegisterActivity.this, getResources().getString(R.string.register_error_user_found), Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			Toast.makeText(RegisterActivity.this, getResources().getString(R.string.error_unexpected), Toast.LENGTH_SHORT).show();
		}
    	
    	SharedPreferences prefs = getSharedPreferences(LoginActivity.PREFERENCES,
				MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(Constants.LOGIN_NAME, edt_name.getText().toString());
		editor.putString(Constants.LOGIN_PASSWORD, edt_password.getText().toString());
		
		editor.commit();
		
    	// TODO New User -> Create Vehicle first NewVehicleActivity.class
		Intent intent = new Intent(RegisterActivity.this, NewVehicleActivity.class);
		startActivity(intent);
	}
    
	@Override
	public Context getContext() {
		return this;
	}
    
    private boolean validateRegister() {
    	String name = edt_name.getText().toString();
		String pw = edt_password.getText().toString();
		String pw_repeat = edt_password_repeat.getText().toString();
		String email = edt_email.getText().toString();	
		
		if(name == null || name.isEmpty()) {
			return false;
		}
		if(pw == null || pw.isEmpty()) {
			return false;
		}
		if(pw_repeat == null || pw_repeat.isEmpty()) {
			return false;
		}
		if(!pw.equals(pw_repeat)) {
			return false;
		}
		if(email == null || email.isEmpty()) {
			return false;
		}
		return true;
	}
    
    /**
     * An asynchronous task for logging in the user.
     * If finished, it calls onLoginTaskComplete with its result.
     * @author Timo Klostermann
     *
     */
    private class RegisterRequest extends RequestTask {
    	private ProgressDialog progress;
    	
    	private RequestCallback callback;
    	
    	public RegisterRequest(RequestCallback callback) {
    		super();
    		
    		this.callback = callback;
    		this.servletURL = "register";
    	}
    	
    	@Override
    	protected void onPreExecute() {
    		this.progress = new ProgressDialog(callback.getContext());
    		progress.setMessage(getResources().getString(R.string.register_processing));
    		progress.show();
    	}
    	
    	@Override
    	protected void onPostExecute(JSONObject result) {
    		if(progress.isShowing()) {
    			progress.dismiss();
    		}
    		try {
    			JSONObject obj = this.get();
    			if(obj == null) {
    				if(errorcode == Constants.ERROR_NO_CONNECTION) {
    					Toast.makeText(RegisterActivity.this, getResources().getString(R.string.error_no_connection), Toast.LENGTH_SHORT).show();
    				} else {
    					Toast.makeText(RegisterActivity.this, getResources().getString(R.string.error_unexpected), Toast.LENGTH_SHORT).show();
    				}
    				return;
    			}
    			callback.onRequestComplete(obj);
			} catch (Exception e) {
				Toast.makeText(RegisterActivity.this, getResources().getString(R.string.error_unexpected), Toast.LENGTH_SHORT).show();
			}
    	}
    }
}
