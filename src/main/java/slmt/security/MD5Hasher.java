package slmt.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hasher {
	
	public static String hash(String input) {
		try {
			// MD5 Hash
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			
			// converts to string
			BigInteger number = new BigInteger(1, messageDigest);
			StringBuilder hashtext = new StringBuilder();
			hashtext.append(number.toString(16));
			
			// prepends zeros to 32 characters
			while (hashtext.length() < 32)
				hashtext.append("0");
			return hashtext.toString().toLowerCase();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
