package de.timoklostermann.refuel.util;

public interface Constants {
	public static final String LOGIN_NAME = "loginName";
	public static final String LOGIN_PASSWORD = "loginPw";
	
	public static final String REGISTER_NAME = "registerName";
	public static final String REGISTER_EMAIL = "registerEmail";
	public static final String REGISTER_PW = "registerPw";
	
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
	public static final String VEHICLE_KEY = "vehicleKey";
	
	public static final String USER_NAME = "userName";
	
	public static final String REQUEST_TYPE = "requestType";
	public static final int REQUEST_TYPE_VEHICLE_SAVE = 0;
	public static final int REQUEST_TYPE_VEHICLE_GET = 1;
	public static final int REQUEST_TYPE_VEHICLE_GET_ALL_LIST = 2;
	public static final int REQUEST_TYPE_VEHICLE_UPDATE = 3;
	
	public static final int REQUEST_TYPE_USER_SAVE_DEFAULT_VEHICLE = 0;
	
	public static final String JSON_SUCCESS = "SUCCESS";
	public static final String JSON_ERROR = "ERROR";
	
	public static final int ERROR_NO_CONNECTION = 1;
	public static final int ERROR_UNEXPECTED = 2;
	
	public static final int ERROR_USER_EXISTS = 3;
	public static final int ERROR_USER_EXISTS_NOT = 4;
	public static final int ERROR_PW_WRONG = 5;
	
	public static final int ERROR_VEHICLE_EXISTS_NOT = 6;
	
	public static final int CONSUMPTION_UNIT_MPG = 0;
	public static final int CONSUMPTION_UNIT_KPG = 1;
	public static final int CONSUMPTION_UNIT_MPIG = 2;
	public static final int CONSUMPTION_UNIT_KPIG = 3;
	public static final int CONSUMPTION_UNIT_MPL = 4;
	public static final int CONSUMPTION_UNIT_GP100KM = 5;
	public static final int CONSUMPTION_UNIT_LP100KM = 6;
	public static final int CONSUMPTION_UNIT_IGP100KM = 7;
	
	public static final int DISTANCE_UNIT_KILOMETERS = 0;
	public static final int DISTANCE_UNIT_MILES = 1;
	
	public static final int QUANTITY_UNIT_LITERS = 0;
	public static final int QUANTITY_UNIT_GALLONS = 1;
	public static final int QUANTITY_UNIT_IMPERIAL_GALLONS = 2;
	
	public static final int FUEL_TYPE_UNIT_UNLEADED = 0;
	public static final int FUEL_TYPE_UNIT_PREMIUM_UNLEADED_95 = 1;
	public static final int FUEL_TYPE_UNIT_PREMIUM_UNLEADED_100 = 2;
	public static final int FUEL_TYPE_UNIT_SUPER_UNLEADED = 3;
	public static final int FUEL_TYPE_UNIT_SUPER_PLUS_UNLEADED = 4;
	public static final int FUEL_TYPE_UNIT_E10 = 5;
	public static final int FUEL_TYPE_UNIT_BIO_ETHANOL = 6;
	public static final int FUEL_TYPE_UNIT_BIO_DIESEL = 7;
	public static final int FUEL_TYPE_UNIT_DIESEL = 8;
	public static final int FUEL_TYPE_UNIT_PREMIUM_DIESEL = 9;
	public static final int FUEL_TYPE_UNIT_VEGETABLE_OIL = 10;
	public static final int FUEL_TYPE_UNIT_LPG = 11;
	public static final int FUEL_TYPE_UNIT_CNGH = 12;
	public static final int FUEL_TYPE_UNIT_CNGL = 13;
}
