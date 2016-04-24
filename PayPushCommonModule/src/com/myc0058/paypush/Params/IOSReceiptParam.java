/*
 * IOS 결재 영수증을 유효성 체크할때 사용하는 클래스입니다.
 * 참조 : PayPushServletCommands.IOS_RECEIPT
 * 자세한 멤버 변수의 설명은 IOS 개발자 페이지에서 확인바랍니다.
 * 어떤 값을 채워야 할지 고민이 되신다면 PayPushServer 프로젝트에서 
 * Tests 폴더안에 UseCase를 확인해보시기 바랍니다.
 */

package com.myc0058.paypush.Params;

/**
 * A param for checking a IOS Receipt.
 *
 * @author YoungChul Mo
 * @since   2016-04-24
 */

public class IOSReceiptParam {
	//영수증
	private String receipt;
	
	//DevelopMode이면 true ProductionMode이면 false
	private boolean isDevelopMode; 
	
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	public boolean isDevelopMode() {
		return isDevelopMode;
	}
	public void setDevelopMode(boolean isDevelopMode) {
		this.isDevelopMode = isDevelopMode;
	}
}
