package de.timoklostermann.refuel.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.util.Base64;
import android.util.Log;

/**
 * Class for encrypting and decrypting passwords.
 * @author Timo Klostermann
 *
 */
public class PasswordEncryption {

	public static String encrypt(String password) {
		MessageDigest md = null;
        byte[] hashValue = null;
        
        try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(password.getBytes("UTF-8")); //UTF-8 is android default encoding
        } catch (NoSuchAlgorithmException e) {
        	Log.e("PasswordEncryption", "Unknown algorithmus");
		} catch (UnsupportedEncodingException e) {
			Log.e("PasswordEncryption", "Unsupported encoding");
		}
        byte rawByte[] = md.digest();
        hashValue = Base64.encode(rawByte,Base64.DEFAULT);
        
        return new String(hashValue);
	}
}
