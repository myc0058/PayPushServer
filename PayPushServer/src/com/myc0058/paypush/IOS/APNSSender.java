package com.myc0058.paypush.IOS;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javapns.Push;
import javapns.communication.ConnectionToAppleServer;
import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.PushedNotifications;

import com.myc0058.paypush.settings.PayPushGlobalConsts;

/**
 * APNS Sender.
 *
 * @author YoungChul Mo
 * @since   2016-04-24
 */

public final class APNSSender {
	private APNSSender() { }
	
	private static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
	}
	
	private static final int TOKEN_SIZE = 64;
	private static final int SLEEP_TIME = 500;
	/**
	 * Send a APNS Message to APNS Server.
	 * 
	 * @param pushMessage pushMessage
	 * @param deviceTokens deviceTokens
	 * @param isDevelopMode isDevelopMode
	 * @param extras extras
	 * @return result for sending message
	 */
	public static PushedNotifications sendPushs(
			String pushMessage, 
			List<String> deviceTokens, 
			boolean isDevelopMode, 
			Map<String, String> extras) {
		
		String msgContents = pushMessage;

		try {
			PushNotificationPayload payload = PushNotificationPayload.complex();
			payload.addAlert(msgContents);
			payload.addBadge(1);
			payload.addSound("default");
			
			if (extras != null) {
				Iterator<String> keys = extras.keySet().iterator();
				while (keys.hasNext()) {
					String key = keys.next();
					String value = extras.get(key);
					
					payload.addCustomDictionary(key, value);
				}
			}
			
			String certificate = null;
			if (isDevelopMode) {
				certificate = PayPushGlobalConsts.DEV_CERTIFICATE;
			} else {
				certificate = PayPushGlobalConsts.PRO_CERTIFICATE;
			}
			
			String apnsAddress = null;
			if (isDevelopMode) {
				apnsAddress = PayPushGlobalConsts.DEV_APNSADDRESS;
			} else {
				apnsAddress = PayPushGlobalConsts.PRO_APNSADDRESS;
			}
			
			AppleNotificationServerBasicImpl appleServerImple = 
					new AppleNotificationServerBasicImpl(
						certificate, 
						PayPushGlobalConsts.CERTI_PASSWORD,
						ConnectionToAppleServer.KEYSTORE_TYPE_PKCS12,
						apnsAddress,
						Integer.parseInt(PayPushGlobalConsts.APNSPORT)); 
			
			PushNotificationManager manager = new PushNotificationManager();
			manager.initializeConnection(appleServerImple);
			
			PushedNotifications pushedNotifications = new PushedNotifications();
			List<Device> basicDeviceList = new ArrayList<Device>();

			if (deviceTokens.size() > pushedNotifications.getMaxRetained()) {
				pushedNotifications.setMaxRetained(deviceTokens.size());
			}
			
			for (int i = 0; i < deviceTokens.size(); i++) {
				String token = deviceTokens.get(i);
				if (token == null || token.isEmpty() || token.equals("")) {
					break;
				}
				
				if (token.length() != TOKEN_SIZE) {
					continue;
				}
				
				Device device = new BasicDevice(token);
				
				device.setDeviceId(token);
				basicDeviceList.add(device);
			}
				
			pushedNotifications.addAll(
					manager.sendNotifications(payload, basicDeviceList));
			
			sleep(SLEEP_TIME);
				
			manager.stopConnection();
			//////////////////////////////////////////////////////////
			
			return pushedNotifications;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Send a APNS message.
	 * 
	 * @param isDevelopMode isDevelopMode
	 * @param deviceToken deviceToken
	 * @param pushMessage pushMessage
	 * @param extras extras
	 * @return result for sending a message.
	 */
	public static PushedNotification sendPush(boolean isDevelopMode, 
			String deviceToken, 
			String pushMessage, 
			Map<String, String> extras) {
		try {
			PushNotificationPayload payload = PushNotificationPayload.complex();
			payload.addAlert(pushMessage);
			payload.addBadge(-1);
			payload.addSound("default");
				
			if (extras != null) {
				Iterator<String> keys = extras.keySet().iterator();
				while (keys.hasNext()) {
					String key = keys.next();
					String value = extras.get(key);
					
					payload.addCustomDictionary(key, value);
				}
			}

			String certificate = null;
			if (isDevelopMode) {
				certificate = PayPushGlobalConsts.DEV_CERTIFICATE;
			} else {
				certificate = PayPushGlobalConsts.PRO_CERTIFICATE;
			}
			
			PushedNotifications notifications = 
					Push.payload(payload, 
							certificate, 
							PayPushGlobalConsts.CERTI_PASSWORD, 
							!isDevelopMode, 
							deviceToken);
				
			return notifications.get(0);
				
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;
	}
}
