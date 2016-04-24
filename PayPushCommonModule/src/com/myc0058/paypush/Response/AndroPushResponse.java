package com.myc0058.paypush.Response;

/**
 * A response for sending a GCM message.
 *
 * @author YoungChul Mo
 * @since   2016-04-24
 */

public class AndroPushResponse {
	private String registrationID;
	
	/**
	 * isSuccess가 false라면 실패 이때 자세한 원인은 errorCodeName변수에서 찾는다.
	 * http://stackoverflow.com/questions/20641393/gcm-error-codes.
	 */
	
	private boolean isSuccess;  
	
	/**
	 * 이변수가 공백이거나 null이 아니라면 기존 ID를 이 아이디로 바꿔야 한다.
	 */
	private String canonicalID;
	private String errorCodeName;
	
	public String getRegistrationID() {
		return registrationID;
	}
	public void setRegistrationID(String registrationID) {
		this.registrationID = registrationID;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getCanonicalID() {
		return canonicalID;
	}
	public void setCanonicalID(String canonicalID) {
		this.canonicalID = canonicalID;
	}
	public String getErrorCodeName() {
		return errorCodeName;
	}
	public void setErrorCodeName(String errorCodeName) {
		this.errorCodeName = errorCodeName;
	}
	
}
