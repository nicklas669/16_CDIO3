package dtu.shared;

/**
 * <p>
 * FieldVerifier validates that the name the user enters is valid.
 * </p>
 * <p>
 * This class is in the <code>shared</code> package because we use it in both
 * the client code and on the server. On the client, we verify that the name is
 * valid before sending an RPC request so the user doesn't have to wait for a
 * network round trip to get feedback. On the server, we verify that the name is
 * correct to ensure that the input is correct regardless of where the RPC
 * originates.
 * </p>
 * <p>
 * When creating a class that is used on both the client and the server, be sure
 * that all code is translatable and does not use native JavaScript. Code that
 * is not translatable (such as code that interacts with a database or the file
 * system) cannot be compiled into client-side JavaScript. Code that uses native
 * JavaScript (such as Widgets) cannot be run on the server.
 * </p>
 */
public class FieldVerifier {

	/**
	 * Verifies that the specified name is valid for our service.
	 * 
	 * In this example, we only require that the name is at least four
	 * characters. In your application, you can use more complex checks to ensure
	 * that usernames, passwords, email addresses, URLs, and other fields have the
	 * proper syntax.
	 * 
	 * @param name the name to validate
	 * @return true if valid, false if invalid
	 */
	
	public static boolean isValidID(String id) {
		if (id == null) return false;
		if (id.length() == 0) return false;
		try {
			if (Integer.valueOf(id) >= 1 && Integer.valueOf(id) <= 99999999) return true;
		} catch (NumberFormatException e) {
			return false;
		}
		return false;
	}
	
	public static boolean isValidName(String name) {
		if (name == null) {
			return false;
		}
		if (name.length() == 0)
			return false;
		// max 20 karakterer
		return name.length() <= 20;
	}
	
	public static boolean isValidInitials(String ini) {
		if (ini == null) return false;
		if (ini.length() == 0) return false;
		// max 3 characters
		return ini.length() <= 3;
	}
	
	public static boolean isValidCpr(String cpr) {
		if (cpr == null) return false;
		if (cpr.length() == 0) return false;
		// must be 11 chars (CDIO oplæg siger 10? Ingen bindestreg?)
		return cpr.length() == 11;
	}
	
	public static boolean isValidPass(String pass) {
		if (pass == null) return false;
		if (pass.length() == 0) return false;
		// min. 7 & max. 8 characters
//		return pass.length() >= 7 && pass.length() <= 8;
		return pass.length() == 7 || pass.length() == 8;
	}
}
