package com.myc0058.paypush.Controller;

import javapns.notification.PushedNotification;
import javapns.notification.PushedNotifications;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.myc0058.paypush.IOS.APNSSender;
import com.myc0058.paypush.IOS.IOSBillingHelper;
import com.myc0058.paypush.Params.IOSPushParam;
import com.myc0058.paypush.Params.IOSPushsParam;
import com.myc0058.paypush.Params.IOSReceiptParam;
import com.myc0058.paypush.Response.IOSPushResponse;
import com.myc0058.paypush.Response.IOSPushsResponse;
import com.myc0058.paypush.Response.IOSReceiptResponse;
import com.myc0058.paypush.Strings.PayPushServletCommands;

/**
 * Spring Controller for IOS.
 *
 * @author YoungChul Mo
 * @since   2016-04-24
 */

@Controller
public class IOSController {
	/**
	 * Check IOS Receipt Validation.
	 * 
	 * @param data data json string
	 * @return Response json string
	 */
	@RequestMapping(value = PayPushServletCommands.IOS_RECEIPT)
	public @ResponseBody
	String iosReceipt(@RequestParam("data") String data) {

		Gson gson = new GsonBuilder().serializeNulls().create();
		IOSReceiptParam param = gson.fromJson(data, IOSReceiptParam.class);

		IOSReceiptResponse response = new IOSReceiptResponse();
		boolean validationResult = IOSBillingHelper.validateReceipt(
				param.getReceipt(), param.isDevelopMode());
		response.setValidationResult(validationResult);
		return gson.toJson(response, IOSReceiptResponse.class);
	}

	/**
	 * Send a APNS message.
	 * 
	 * @param data data json string
	 * @return Response json string
	 */
	@RequestMapping(value = PayPushServletCommands.IOS_PUSH)
	public @ResponseBody
	String iosSendPush(@RequestParam("data") String data) {

		Gson gson = new GsonBuilder().serializeNulls().create();
		IOSPushParam param = gson.fromJson(data, IOSPushParam.class);

		PushedNotification noti = APNSSender.sendPush(param.isDevelopMode(),
				param.getDeviceToken(), param.getPushMessage(),
				param.getExtras());

		IOSPushResponse response = new IOSPushResponse();
		response.setSuccess(noti.isSuccessful());
		if (noti.getResponse() != null) {
			response.setStatusCode(noti.getResponse().getStatus());
		}
		response.setDeviceToken(param.getDeviceToken());

		return gson.toJson(response, IOSPushResponse.class);
	}

	/**
	 * Send APNS messages.
	 * 
	 * @param data data json string
	 * @return Response json string
	 */
	@RequestMapping(value = PayPushServletCommands.IOS_PUSHS)
	public @ResponseBody
	String iosSendPushs(@RequestParam("data") String data) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		IOSPushsParam param = gson.fromJson(data, IOSPushsParam.class);

		PushedNotifications notis = APNSSender.sendPushs(param.getPushMessage(),
				param.getDeviceTokens(), param.isDevelopMode(),
				param.getExtras());
		
		PushedNotifications fails = notis.getFailedNotifications();
		
		IOSPushsResponse response = new IOSPushsResponse();
		response.setSuccessCount(notis.getSuccessfulNotifications().size());
		response.setFailCount(fails.size());
		if (param.isMoreDetail()) {
			for (PushedNotification pushNoti : fails) {
				response.getFailData().put(
						pushNoti.getDevice()
								.getDeviceId(), 
						pushNoti.getResponse().
								 getStatus());
			}
		}

		return gson.toJson(response, IOSPushsResponse.class);
	}
}
