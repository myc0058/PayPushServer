package com.myc0058.paypush.Response;

/**
 * A param for sending a APNS message.
 *
 * @author YoungChul Mo
 * @since   2016-04-24
 */

public class IOSPushResponse {
	/**
	 * isSuccess가 false 이면 전송 실패 자세한 원인은 statusCode변수에 있음.
	 * statusCode IOS 개발자 센터에서 확인하시기 바랍니다.
	 */
	
	private boolean isSuccess;
	 
	private int statusCode;
	
	private String deviceToken;
	
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
}
