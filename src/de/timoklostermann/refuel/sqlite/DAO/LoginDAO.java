package de.timoklostermann.refuel.sqlite.DAO;

import de.timoklostermann.refuel.sqlite.entity.Login;
import de.timoklostermann.refuel.sqlite.helper.LoginHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDAO {
	private SQLiteDatabase database;
	private LoginHelper loginHelper;
	private String[] allColumns = { LoginHelper.COLUMN_ID,
			LoginHelper.COLUMN_NAME, LoginHelper.COLUMN_PASSWORD };

	public LoginDAO(Context context) {
		loginHelper = new LoginHelper(context);
	}

	public void open() throws SQLException {
		database = loginHelper.getWritableDatabase();
	}

	public void close() {
		loginHelper.close();
	}

	public Login createLogin(String name, String password) {
		ContentValues values = new ContentValues();
		values.put(LoginHelper.COLUMN_NAME, name);
		values.put(LoginHelper.COLUMN_PASSWORD, password);

		long insertId = database.insert(LoginHelper.TABLE_LOGIN, null, values);
		Cursor cursor = database.query(LoginHelper.TABLE_LOGIN, allColumns,
				LoginHelper.COLUMN_ID + " = " + insertId, null, null, null,
				null);
		cursor.moveToFirst();
		Login newLogin = cursorToLogin(cursor);
		cursor.close();
		return newLogin;
	}

	public void deleteLogin(Login login) {
		long id = login.getId();
		database.delete(LoginHelper.TABLE_LOGIN, LoginHelper.COLUMN_ID + " = "
				+ id, null);
	}

	public Login getLastLogin() {
		Cursor cursor = database.query(LoginHelper.TABLE_LOGIN, allColumns,
				null, null, null, null, null);
		if(cursor.moveToLast()) {
			Login lastLogin = cursorToLogin(cursor);
			cursor.close();
			return lastLogin;
		} else {
			return null;
		}
	}

	private Login cursorToLogin(Cursor cursor) {
		Login login = new Login();
		login.setId(cursor.getLong(0));
		login.setName(cursor.getString(1));
		login.setPassword(cursor.getString(2));
		return login;
	}
}
