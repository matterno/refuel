package de.timoklostermann.refuel;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import de.timoklostermann.refuel.interfaces.LoginTaskCompleteListener;
import de.timoklostermann.refuel.net.RequestTask;
import de.timoklostermann.refuel.util.Constants;
import de.timoklostermann.refuel.util.SimpleCrypto;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements LoginTaskCompleteListener {

	Button btn_login;
	
	EditText edt_login;
	
	EditText edt_pw;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        btn_login = (Button) this.findViewById(R.id.btn_login_login);
        edt_login = (EditText) this.findViewById(R.id.edt_login_name);
        edt_pw = (EditText) this.findViewById(R.id.edt_login_password);
        
        //TODO check if remember checkbox is set. -> SQL
        
        btn_login.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(!validateLogin()) {
					Toast.makeText(LoginActivity.this, getResources().getString(R.string.error_input), Toast.LENGTH_SHORT).show();
					return;
				}
				
				LoginRequest req = new LoginRequest();

				try {
					req.execute(
							new BasicNameValuePair(Constants.LOGIN_NAME, edt_login.getText().toString()),
							new BasicNameValuePair(Constants.LOGIN_PASSWORD, SimpleCrypto.encrypt("seed",edt_pw.getText().toString())));
				} catch (Exception e) {
					Toast.makeText(LoginActivity.this, getResources().getString(R.string.error_unexpected), Toast.LENGTH_SHORT).show();
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
    	switch(item.getItemId()) {
    	case R.id.menu_register:
    		// Start RegisterActivity
    		Intent intent = new Intent(this,RegisterActivity.class);
    		startActivity(intent);
    	}
    	return super.onOptionsItemSelected(item);
    }
    
    public void onLoginTaskComplete(JSONObject obj) {
		try {
			if(!obj.getBoolean(Constants.JSON_SUCCESS)) {
				
				switch(obj.getInt(Constants.JSON_ERROR)) {
				case Constants.ERROR_PW_WRONG:
					Toast.makeText(LoginActivity.this, getResources().getString(R.string.login_error_wrong_password), Toast.LENGTH_SHORT).show();
					break;
				case Constants.ERROR_USER_EXISTS_NOT:
					Toast.makeText(LoginActivity.this, getResources().getString(R.string.login_error_user_exists_not), Toast.LENGTH_SHORT).show();
					break;
				}
				return;
			}
		} catch (Exception e) {
			Log.e("LoginActivity","Error in onLoginTaskComplete()");
			e.printStackTrace();
			Toast.makeText(LoginActivity.this, getResources().getString(R.string.error_unexpected), Toast.LENGTH_SHORT).show();
		}
		
		
		//TODO Get Vehicle data
		
		//TODO Start NewVehicle-Activity if no vehicle in DB
		Intent intent = new Intent(LoginActivity.this, SwipeActivity.class);
		startActivity(intent);
    }
    
    /**
     * Looks if login name and passwort fit requirements. 
     * @return true if ok, else false.
     */
    private Boolean validateLogin() {
    	String login = edt_login.getText().toString();
		String pw = edt_pw.getText().toString();
		
		if(login == null || login.isEmpty()) {
			return false;
		}
		if(pw == null || pw.isEmpty()) {
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
    private class LoginRequest extends RequestTask {
    	private ProgressDialog progress;
    	
    	public LoginRequest() {
    		super();
    		
    		this.servletURL = "login";
    	}
    	
    	@Override
    	protected void onPreExecute() {
    		this.progress = new ProgressDialog(LoginActivity.this);
    		progress.setMessage(getResources().getString(R.string.login_processing));
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
    					Toast.makeText(LoginActivity.this, getResources().getString(R.string.error_no_connection), Toast.LENGTH_SHORT).show();
    				} else {
    					Log.e("LoginRequest","Error in onPostExcecute()");
    					Toast.makeText(LoginActivity.this, getResources().getString(R.string.error_unexpected), Toast.LENGTH_SHORT).show();
    				}
    				return;
    			}
				LoginActivity.this.onLoginTaskComplete(obj);
			} catch (Exception e) {
				Log.e("LoginRequest","Error in onPostExcecute()");
				Toast.makeText(LoginActivity.this, getResources().getString(R.string.error_unexpected), Toast.LENGTH_SHORT).show();
			}
    	}
    }
}
