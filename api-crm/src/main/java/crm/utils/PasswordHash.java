package crm.utils;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordHash {
	
	public static String HashPassSHA256(String password) throws NoSuchAlgorithmException, IOException {
		MessageDigest digest=null;
		String passHash=null;
		
		byte[] bSalt = Base64.getMimeDecoder().decode(Configuration.getConfig().getProperty("salt"));
		
		digest = MessageDigest.getInstance("SHA-256");
		digest.update(bSalt);
		passHash = new BigInteger(1, digest.digest(password.getBytes(StandardCharsets.UTF_8))).toString(16);

		return passHash;
	}
}
