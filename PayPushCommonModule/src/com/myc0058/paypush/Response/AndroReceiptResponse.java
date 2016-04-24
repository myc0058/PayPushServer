package com.myc0058.paypush.Response;

/**
 * A param for checking a IOS Receipt.
 *
 * @author YoungChul Mo
 * @since   2016-04-24
 */

public class AndroReceiptResponse {
	//유효한 영수증이라면 true
	private boolean validationResult;
	private String orderid;
	
	public boolean isValidationResult() {
		return validationResult;
	}

	public void setValidationResult(boolean validationResult) {
		this.validationResult = validationResult;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
}
