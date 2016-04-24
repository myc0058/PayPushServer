/*
 * 여러개의 IOS 푸쉬를 PayPushServer로 HttpRequest를 보낼때 사용되는 자료형입니다. 
 * 참조 : PayPushServletCommands.IOS_PUSHS
 * APNS에 대한 설명은 IOS APNS 개발자 페이지에서 확인하시기 바랍니다.
 * 받을때 사용되는 클래스는 IOSPushsResponse 입니다.
 * 어떤 값을 채워야 할지 고민이 되신다면 PayPushServer 프로젝트에서 
 * Tests 폴더안에 UseCase를 확인해보시기 바랍니다.
 */

package com.myc0058.paypush.Params;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A param for sending APNS messages.
 *
 * @author YoungChul Mo
 * @since   2016-04-24
 */

public class IOSPushsParam {
	private List<String> deviceTokens = new ArrayList<String>();
	
	private String pushMessage;
	
	/**
	 * Optional datas.
	 * APNS.sendPushs(...) 함수 참조
	 * 
	 */
	
	private Map<String, String> extras = new HashMap<String, String>();
	
	/**
	 * if you want to see more detail information in Response then true
	 * or not false.
	 */
	private boolean isMoreDetail;
	
	private boolean isDevelopMode;
	
	public String getPushMessage() {
		return pushMessage;
	}
	public void setPushMessage(String pushMessage) {
		this.pushMessage = pushMessage;
	}
	public List<String> getDeviceTokens() {
		return deviceTokens;
	}
	public void setDeviceTokens(List<String> deviceTokens) {
		this.deviceTokens = deviceTokens;
	}
	public boolean isDevelopMode() {
		return isDevelopMode;
	}
	public void setDevelopMode(boolean isDevelopMode) {
		this.isDevelopMode = isDevelopMode;
	}
	public Map<String, String> getExtras() {
		return extras;
	}
	public void setExtras(Map<String, String> extras) {
		this.extras = extras;
	}
	public boolean isMoreDetail() {
		return isMoreDetail;
	}
	public void setMoreDetail(boolean isMoreDetail) {
		this.isMoreDetail = isMoreDetail;
	}
}
