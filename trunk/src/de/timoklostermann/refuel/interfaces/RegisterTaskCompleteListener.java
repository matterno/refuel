package de.timoklostermann.refuel.interfaces;

import org.json.JSONObject;

public interface RegisterTaskCompleteListener {
	
    /**
     * Callback method for the register task.
     * @param obj
     */
	public void onRegisterTaskComplete(JSONObject obj);
}
