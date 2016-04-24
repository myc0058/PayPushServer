package com.myc0058.paypush.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * A param for checking a IOS Receipt.
 *
 * @author YoungChul Mo
 * @since   2016-04-24
 */

public class IOSPushsResponse {
	private Integer successCount;
	private Integer failCount; 
	
	//IOSPushsParam에서 isMoreDetail 변수가 true이면 채워주는 변수
	//Map<DeviceToken, StatusCode>
	private Map<String, Integer> failData = new HashMap<String, Integer>();
	
	public Integer getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}
	public Integer getFailCount() {
		return failCount;
	}
	public void setFailCount(Integer failCount) {
		this.failCount = failCount;
	}
	public Map<String, Integer> getFailData() {
		return failData;
	}
	public void setFailData(Map<String, Integer> failData) {
		this.failData = failData;
	}
}
