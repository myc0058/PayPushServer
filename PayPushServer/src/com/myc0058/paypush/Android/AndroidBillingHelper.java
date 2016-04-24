package com.myc0058.paypush.Android;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Android Billing Helper.
 *
 * @author YoungChul Mo
 * @since   2016-04-24
 */

public class AndroidBillingHelper {
	private String publicKey;
	private String packageName;

	private String outOrderId; // 검증후 채워짐
	private String outProductId;
	private String outPurchaseTime;
	private String outPurchaseState;

	/**
	 * hide default contructor.
	 */
	@SuppressWarnings("unused")
	private AndroidBillingHelper() {
		throw new AssertionError();
	}

	/**
	 * Constructor.
	 * 
	 * @param packageName packageName APK Package Name
	 * @param publicKey publicKey RSA Public key
	 */
	public AndroidBillingHelper(String packageName, String publicKey) {
		outOrderId = "";
		outProductId = "";
		outPurchaseTime = "";
		outPurchaseState = "";

		this.publicKey = publicKey;
		this.packageName = packageName;
	}

	/**
	 * Check IOS receipt validation.
	 * 
	 * @param signature signature 
	 * @param signedData signedData
	 * @return if receipt is validated then true or not false
	 */
	public boolean validation(String signature, String signedData) {
		if (isVaildReceipt(signature, signedData)) {
			return false;
		}

		if (isValidBilling(signedData)) {
			return false;
		}
		
		return true;
	}

	// 영수증이 정상인지 확인
	private boolean isVaildReceipt(String signature, String signedData) {
		PublicKey key = null;

		key = generatePublicKey(publicKey);

		if (key == null) {
			return false;
		}

		return verify(key, signedData, signature);
	}

	// 영수증 내용 파싱
	private boolean isValidBilling(String receipt) {
		if (receipt == null || receipt.equals("")) {
			return false;
		}

		boolean valid = false;
		JSONParser parser = new JSONParser();

		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> receiptMap = 
				(Map<String, Object>) parser.parse(receipt);

			String receiptPackageName = 
				receiptMap.get("packageName").toString();
			int purchaseState =
				Integer.parseInt(receiptMap.get("purchaseState").toString());
			/*
			 * int developerPayload =
			 * 	Integer.parseInt(m.get("developerPayload").toString());
			 */

			outOrderId = receiptMap.get("orderId").toString();
			outProductId = receiptMap.get("productId").toString();
			outPurchaseTime = receiptMap.get("purchaseTime").toString();
			outPurchaseState = String.valueOf(purchaseState);

			if (receiptPackageName.equals(packageName) 
					&& purchaseState == 0) {
				valid = true;
			} else {
				valid = false;
			}
		} catch (ParseException e) {
			valid = false;
		}

		return valid;
	}

	private PublicKey generatePublicKey(String publicKey) {
		try {
			byte[] decodedKey = Base64.decodeBase64(publicKey);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return keyFactory.generatePublic(
					new X509EncodedKeySpec(decodedKey));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (InvalidKeySpecException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private boolean verify(
			PublicKey publicKey, String signedData, String signature) {
		Signature sig;
		try {
			sig = Signature.getInstance("SHA1withRSA");
			sig.initVerify(publicKey);
			sig.update(signedData.getBytes());
			
			if (!sig.verify(Base64.decodeBase64(signature))) {
				return false;
			}
			
			return true;
		} catch (Exception e) {

		}
		return false;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public String getOutOrderId() {
		return outOrderId;
	}

	public void setOutOrderId(String outOrderId) {
		this.outOrderId = outOrderId;
	}

	public String getOutProductId() {
		return outProductId;
	}

	public void setOutProductId(String outProductId) {
		this.outProductId = outProductId;
	}

	public String getOutPurchaseTime() {
		return outPurchaseTime;
	}

	public void setOutPurchaseTime(String outPurchaseTime) {
		this.outPurchaseTime = outPurchaseTime;
	}

	public String getOutPurchaseState() {
		return outPurchaseState;
	}

	public void setOutPurchaseState(String outPurchaseState) {
		this.outPurchaseState = outPurchaseState;
	}
}
