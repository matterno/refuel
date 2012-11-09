package de.timoklostermann.refuel.interfaces;

import org.json.JSONObject;

public interface LoginTaskCompleteListener {
	
    /**
     * Callback method for the login task.
     * @param obj
     */
	public void onLoginTaskComplete(JSONObject obj);
}
