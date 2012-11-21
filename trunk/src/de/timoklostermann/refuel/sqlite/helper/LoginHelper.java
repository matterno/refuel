package de.timoklostermann.refuel.sqlite.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class LoginHelper extends SQLiteOpenHelper {

	public static final String TABLE_LOGIN = "login";
	
	public static final String COLUMN_ID = "_id";
	
	public static final String COLUMN_NAME = "name";
	
	public static final String COLUMN_PASSWORD = "password";
	
	private static final String DATABASE_NAME = "refuel";
	
	private static final int DATABASE_VERSION = 2;
	
	// Create statement for database
	private static final String DATABASE_CREATE = "create table " + TABLE_LOGIN + "(" + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_NAME + " text not null, " + COLUMN_PASSWORD + " text not null);";
	
	public LoginHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(LoginHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
		onCreate(db);
	}

}
