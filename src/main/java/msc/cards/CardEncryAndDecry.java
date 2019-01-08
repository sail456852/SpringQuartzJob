package msc.cards;

/**
 * 
 * @author Mercy
 *
 */
public class CardEncryAndDecry {
	public static void main(String[] args){
		String cenCard = encryMobileNumber("62220215009898787678");
		System.out.println(cenCard);
	}
	/**
     *  银行卡号码 加解密算法
     * @param data 银行卡号
     * @param bEncryptFlag 1 加密 0 解密
     * @param dateType  1 银行卡 2 身份证 3 手机号
     * @return
     */
	private static String datatrans(String data, int bEncryptFlag,Integer dateType) { 
		char[] strCardID = data.toCharArray();
        char[] strNewCardID = strCardID;
		char[] szKey = "394521863".toCharArray();
        Integer noEncryLen  =0;
        Integer startIndex = 0;
		if(dateType==1 ||dateType==2){//银行卡和身份证加密
            noEncryLen=10 ;//前6后四
            startIndex = 6;
        }else if(dateType==3){
            noEncryLen=7 ;//前3后四
            startIndex = 3;
        }
		int nLen = strCardID.length - noEncryLen;
		for (int i = 0; i < nLen ; i++) {
			if (bEncryptFlag == 1) {
				strNewCardID[i + startIndex] = (char) ((strCardID[i + startIndex] ^ szKey[i]) + 'A');
			} else {
				strNewCardID[i + startIndex] = (char) ((strCardID[i + startIndex] - 'A') ^ szKey[i]);
			}
		}
		return new String(strNewCardID);
	}
	
	/**
	 * 加密银行卡号或者身份证号
	 * @param cardNo
	 * @return
	 */
	public static String encryBankCardOrIdentityCard(String cardNo){
		return datatrans(cardNo,1,2);
	}
	
	/**
	 * 解密银行卡号或者身份证号
	 * @param ciphertext 密文
	 * @return
	 */
	public static String decryBankCardOrIdentityCard(String ciphertext){
		return datatrans(ciphertext,0,2);
	}
	
	/**
	 * 加密手机号
	 * @param mobileNo
	 * @return
	 */
	public static String encryMobileNumber(String mobileNo){
		return datatrans(mobileNo,1,3);
	}
	
	/**
	 * 解密手机号
	 * @param ciphertext
	 * @return
	 */
	public static String decryMobileNumber(String ciphertext){
		return datatrans(ciphertext,0,3);
	}
	
}
