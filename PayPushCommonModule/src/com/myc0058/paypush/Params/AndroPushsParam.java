/*
 * 여러개의 안드로이드 푸쉬를 한꺼번에 PayPushServer로 
 * HttpRequest를 보낼때 사용되는 자료형입니다. 
 * 참조 : PayPushServletCommands.ANDRO_PUSHS
 * GCM에 대한 설명은 안드로이드 GCM 개발자 페이지에서 확인하시기 바랍니다.
 * 받을때 사용되는 클래스는 AndroPushResponse 입니다.
 * 어떤 값을 채워야 할지 고민이 되신다면 PayPushServer 프로젝트에서 
 * Tests 폴더안에 UseCase를 확인해보시기 바랍니다.
 */

package com.myc0058.paypush.Params;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A param for sending GCM Messages.
 *
 * @author YoungChul Mo
 * @since   2016-04-24
 */

public class AndroPushsParam {
	/**
	 * registration Id list.
	 */
	private List<String> registrationIDs = new ArrayList<String>();
	
	/**
	 * Optional data list.
	 */
	private Map<String, String> dataList = new HashMap<String, String>();
	
	/**
	 * if you want to see more detail information in Response then true
	 * or not false.
	 */
	
	private boolean isMoreDetail;
	
	public final List<String> getRegistrationIDs() {
		return registrationIDs;
	}
	public void setRegistrationIDs(List<String> registrationIDs) {
		this.registrationIDs = registrationIDs;
	}
	public Map<String, String> getDataList() {
		return dataList;
	}
	public void setDatas(Map<String, String> dataList) {
		this.dataList = dataList;
	}
	public boolean isMoreDetail() {
		return isMoreDetail;
	}
	public void setMoreDetail(boolean isMoreDetail) {
		this.isMoreDetail = isMoreDetail;
	}
}
