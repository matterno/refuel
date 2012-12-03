package de.timoklostermann.refuel.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import de.timoklostermann.refuel.util.Constants;

import android.os.AsyncTask;
import android.util.Log;

public abstract class RequestTask extends
		AsyncTask<NameValuePair, Void, JSONObject> {
	
	private static final String SERVERURL = "https://refuelbackend.appspot.com/";
	
	protected String servletURL = "";
	
	protected int errorcode;

	public RequestTask() {}
	
	@Override
	protected JSONObject doInBackground(NameValuePair... params) {
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response = null;
		
		try {
			HttpPost httpPost = new HttpPost(SERVERURL + this.servletURL);
			httpPost.setEntity(new UrlEncodedFormEntity(Arrays.asList(params)));
			response = httpclient.execute(httpPost);

		} catch (IOException e) {
			Log.e("IOException", "Error executing http request");
			return null;
		}
		
		JSONObject data = null;
		try {			
			data = new JSONObject(inputStreamToString(response.getEntity().getContent()));
		} catch (JSONException e) {
			errorcode = Constants.ERROR_UNEXPECTED;
			return null;
		} catch (IOException e) {
			errorcode = Constants.ERROR_NO_CONNECTION;
			return null;
		}
		
		
		return data;
	}
	
	private String inputStreamToString(final InputStream is)
			throws IOException {
		String line = "";
		StringBuilder total = new StringBuilder();

		// Wrap a BufferedReader around the InputStream
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));

		// Read response until the end
		while ((line = rd.readLine()) != null) {
			total.append(line);
		}

		// Return full string
		return total.toString();
	}
}
