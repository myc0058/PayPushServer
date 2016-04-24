/*
 * 한개 안드로이드 푸쉬를 PayPushServer로 HttpRequest를 보낼때 사용되는 자료형입니다. 
 * 참조 : PayPushServletCommands.ANDRO_PUSH
 * GCM에 대한 설명은 안드로이드 GCM 개발자 페이지에서 확인하시기 바랍니다.
 * 받을때 사용되는 클래스는 AndroPushResponse 입니다.
 * 어떤 값을 채워야 할지 고민이 되신다면 PayPushServer 프로젝트에서 
 * Tests 폴더안에 UseCase를 확인해보시기 바랍니다.
 */

package com.myc0058.paypush.Params;

import java.util.HashMap;
import java.util.Map;

/**
 * A param for sending a GCM message.
 *
 * @author YoungChul Mo
 * @since   2016-04-24
 */

public class AndroPushParam {
	/**
	 * Registration ID.
	 */
	private String registrationID;

	/**
	 * Optional data list.
	 */
	private Map<String, String> dataList = new HashMap<String, String>();

	/**
	 * @return data list
	 */
	public final Map<String, String> getDataList() {
		return dataList;
	}

	/**
	 * @param dataList dataList
	 */
	public final void setDatas(final Map<String, String> dataList) {
		this.dataList = dataList;
	}

	/**
	 * @return registrationID
	 */
	public final String getRegistrationID() {
		return registrationID;
	}

	/**
	 * @param registrationID registrationID
	 */
	public final void setRegistrationID(final String registrationID) {
		this.registrationID = registrationID;
	}
}
