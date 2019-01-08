package msc.encription;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class SHA1Util {
	private static String byteToHexString(byte ib) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
				'b', 'c', 'd', 'e', 'f' };
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0F];
		ob[1] = Digit[ib & 0X0F];
		String s = new String(ob);
		return s;
	}

	private static String byteArrayToHexString(byte[] bytearray) {
		String strDigest = "";
		for (int i = 0; i < bytearray.length; i++) {
			strDigest += byteToHexString(bytearray[i]);
		}
		return strDigest;
	}

	public static String encrypt(String input) {
		try {
			MessageDigest alga = MessageDigest.getInstance("SHA-1");
			alga.update(input.getBytes("utf-8"));
			byte[] digesta = alga.digest();
			return byteArrayToHexString(digesta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void main(String[] args) throws UnsupportedEncodingException {

	}

}
