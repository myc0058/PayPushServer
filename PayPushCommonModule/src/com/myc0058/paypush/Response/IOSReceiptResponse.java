package com.myc0058.paypush.Response;

/**
 * A param for checking a IOS Receipt.
 *
 * @author YoungChul Mo
 * @since   2016-04-24
 */

public class IOSReceiptResponse {
	private boolean validationResult;
	private String receipt;
	
	public boolean isValidationResult() {
		return validationResult;
	}

	public void setValidationResult(boolean validationResult) {
		this.validationResult = validationResult;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
}
