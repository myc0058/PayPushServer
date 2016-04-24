package com.myc0058.paypush.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * A param for sending GCM messages.
 *
 * @author YoungChul Mo
 * @since   2016-04-24
 */

public class AndroPushsResponse {
	private Integer successCount;
	private Integer failCount;
	private Integer canonicalCount;
	
	/**
	 * AndroPushsParam에서 isMoreDetail 변수가 true 이면 값이 채워진다.
	 */
	private List<AndroPushResponse> results = 
			new ArrayList<AndroPushResponse>();
	
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
	public Integer getCanonicalCount() {
		return canonicalCount;
	}
	public void setCanonicalCount(Integer canonicalCount) {
		this.canonicalCount = canonicalCount;
	}
	public List<AndroPushResponse> getResults() {
		return results;
	}
	public void setResults(List<AndroPushResponse> results) {
		this.results = results;
	}
}
