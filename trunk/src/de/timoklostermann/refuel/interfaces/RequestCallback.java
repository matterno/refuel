package de.timoklostermann.refuel.interfaces;

import org.json.JSONObject;

import android.content.Context;

public interface RequestCallback {
	
    /**
     * Callback method for task.
     * @param obj
     */
	public void onRequestComplete(JSONObject obj);
	
    /**
     * Callback method to get the application context.
     * @param obj
     */
	public Context getContext();
}
