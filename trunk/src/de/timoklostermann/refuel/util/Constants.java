package de.timoklostermann.refuel.util;

public interface Constants {
	public static final String LOGIN_NAME = "loginName";
	public static final String LOGIN_PASSWORD = "loginPw";
	
	public static final String REGISTER_NAME = "registerName";
	public static final String REGISTER_EMAIL = "registerEmail";
	public static final String REGISTER_PW = "registerPw";
	
	public static final String VEHICLE_USER = "vehicleUser";
	public static final String VEHICLE_MAKE = "vehicleMarque";
	public static final String VEHICLE_MODEL = "vehicleModel";
	public static final String VEHICLE_NAME = "vehicleName";
	public static final String VEHICLE_YEAR = "vehicleYear";
	public static final String VEHICLE_TYPE_ID = "vehicleType";
	public static final String VEHICLE_DISTANCE_UNIT = "vehicleDistanceUnit";
	public static final String VEHICLE_QUANTITY_UNIT = "vehicleQuantityUnit";
	public static final String VEHICLE_CONSUMPTION_UNIT = "vehicleConsumptionUnit";
	public static final String VEHICLE_CURRENCY = "vehicleCurrency";
	public static final String VEHICLE_NAMES = "vehicleNames";
	
	public static final String REQUEST_TYPE = "requestType";
	public static final int REQUEST_TYPE_VEHICLE_SAVE_DEFAULT = 0;
	public static final int REQUEST_TYPE_VEHICLE_GET_DEFAULT = 1;
	public static final int REQUEST_TYPE_VEHICLE_GET_ALL_LIST = 2;
	
	public static final String JSON_SUCCESS = "SUCCESS";
	public static final String JSON_ERROR = "ERROR";
	
	public static final int ERROR_NO_CONNECTION = 1;
	public static final int ERROR_UNEXPECTED = 2;
	
	public static final int ERROR_USER_EXISTS = 3;
	public static final int ERROR_USER_EXISTS_NOT = 4;
	public static final int ERROR_PW_WRONG = 5;
	
	public static final int ERROR_VEHICLE_EXISTS_NOT = 6;
}
