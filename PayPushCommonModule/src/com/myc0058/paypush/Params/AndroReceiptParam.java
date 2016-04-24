/*
 * 안드로이드 결재 영수증을 유효성 체크할때 사용하는 클래스입니다.
 * 참조 : PayPushServletCommands.ANDRO_RECEIPT
 * 자세한 멤버 변수의 설명은 안드로이드 개발자 페이지에서 확인바랍니다.
 * 어떤 값을 채워야 할지 고민이 되신다면 PayPushServer 프로젝트에서 
 * Tests 폴더안에 UseCase를 확인해보시기 바랍니다.
 */


package com.myc0058.paypush.Params;

/**
 * A param for Checking Android Receipt.
 *
 * @author YoungChul Mo
 * @since   2016-04-24
 */

public class AndroReceiptParam {
	private String signature;
	private String signedData;
	
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getSignedData() {
		return signedData;
	}
	public void setSignedData(String signedData) {
		this.signedData = signedData;
	}
}
