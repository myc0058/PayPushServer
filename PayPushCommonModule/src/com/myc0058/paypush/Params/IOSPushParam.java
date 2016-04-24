/*
 * 한개의 IOS 푸쉬를 PayPushServer로 HttpRequest를 보낼때 사용되는 자료형입니다. 
 * 참조 : PayPushServletCommands.IOS_PUSH
 * APNS에 대한 설명은 IOS APNS 개발자 페이지에서 확인하시기 바랍니다.
 * 받을때 사용되는 클래스는 IOSPushResponse 입니다.
 * 어떤 값을 채워야 할지 고민이 되신다면 PayPushServer 프로젝트에서 
 * Tests 폴더안에 UseCase를 확인해보시기 바랍니다.
 */

package com.myc0058.paypush.Params;

import java.util.HashMap;
import java.util.Map;

/**
 * A param for sending a APNS Message.
 *
 * @author YoungChul Mo
 * @since   2016-04-24
 */

public class IOSPushParam {
	private String deviceToken;
	private String pushMessage; 

	/* 
	 * 추가로 넣을 데이타
	 * APNS.sendPush(...) 함수 참조
	 * */
	private Map<String, String> extras = new HashMap<String, String>();
	
	private boolean isDevelopMode;
	
	public final boolean isDevelopMode() {
		return isDevelopMode;
	}
	public final void setDevelopMode(final boolean isDevelopMode) {
		this.isDevelopMode = isDevelopMode;
	}
	public final String getDeviceToken() {
		return deviceToken;
	}
	public final void setDeviceToken(final String deviceToken) {
		this.deviceToken = deviceToken;
	}
	public String getPushMessage() {
		return pushMessage;
	}
	public void setPushMessage(String pushMessage) {
		this.pushMessage = pushMessage;
	}
	public Map<String, String> getExtras() {
		return extras;
	}
	public void setExtras(Map<String, String> extras) {
		this.extras = extras;
	}
}
