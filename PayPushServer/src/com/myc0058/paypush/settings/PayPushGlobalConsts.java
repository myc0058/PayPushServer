package com.myc0058.paypush.settings;

/**
 * Settings Constants.
 *
 * @author YoungChul Mo
 * @since   2016-04-24
 */

public final class PayPushGlobalConsts {
	private PayPushGlobalConsts() { }
	
	// Common
	//
	public static final String SERVER_PATH = 
			"http://127.0.0.1:2222/PayPushServer";
	
	// IOS
	public static final String DEV_CERTIFICATE = "/dev_key.p12";
	public static final String PRO_CERTIFICATE = "/pro_key.p12";
	public static final String CERTI_PASSWORD = "actoz";
	public static final String DEV_APNSADDRESS = 
			"gateway.sandbox.push.apple.com";
	public static final String PRO_APNSADDRESS = "gateway.push.apple.com";
	public static final String APNSPORT = "2195";

	public static final String PRO_RECEIPT_URL = 
			"https://buy.itunes.apple.com/verifyReceipt";
	public static final String DEV_RECEIPT_URL = 
			"https://sandbox.itunes.apple.com/verifyReceipt";

	// Android

	// 기기가 활성화 상태일 때 보여줄 것인지.
	public static final boolean DELAY_WHILE_IDLE = false;
	// 기기가 비활성화 상태일 때 GCM Storage에 보관되는 시간
	public static final int TIME_TO_LIVE = 1800;
	// 메세지 전송 실패시 재시도할 횟수
	public static final int RETRY = 3;
	public static final String GCM_API_KEY = 
			"AIzaSyBUWn7nKFaU6BYtr75nP9pJGnxxhTWqPL0";
	
	//패키지명
	public static final String PACKAGE_NAME = "com.neocyon.eraofheroes";
	
	public static final String RSA_PUBLIC_KEY = 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqqYXiDaR08tyo"
			+ "JPtEY7iBKFUw3lmzPzVLS6z3ssWwG6VWb0mCw3NAZ7RkEd3zJ5r9aOW1g"
			+ "da4ZK/UJ1zRujp4Qvw3cuaisONNxxyg8q02u/ttfRt5sE97TIFJO+LXKw"
			+ "3AudbodEBlY9v1Ybki6U3wK95Vc8jJ5Z+XBq2K7K0KphWnnLPz+2cT+iX"
			+ "aEnUJ7VDYA5aOnlZwkh6JmWqZCWFTwYXFsSlk9Eerci7o3dQWZ8AXn1uJ"
			+ "PBQswuxoFhWg5KGyAjzJuZE30lwb6ZJjVOFK2wTnMJ9+dAPhjyOZ5dV4Z"
			+ "6AQfeDzDbRGro648tZLq4iZszt5EwRMT+FoGNwzco4zwIDAQAB";
}
