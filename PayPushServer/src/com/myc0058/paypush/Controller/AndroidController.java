package com.myc0058.paypush.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.myc0058.paypush.Android.AndroidBillingHelper;
import com.myc0058.paypush.Android.GCMSender;
import com.myc0058.paypush.Params.AndroPushParam;
import com.myc0058.paypush.Params.AndroPushsParam;
import com.myc0058.paypush.Params.AndroReceiptParam;
import com.myc0058.paypush.Response.AndroPushResponse;
import com.myc0058.paypush.Response.AndroPushsResponse;
import com.myc0058.paypush.Response.AndroReceiptResponse;
import com.myc0058.paypush.Strings.PayPushServletCommands;
import com.myc0058.paypush.settings.PayPushGlobalConsts;

/**
 * Spring Controller for Android.
 *
 * @author YoungChul Mo
 * @since   2016-04-24
 */

@Controller
public class AndroidController {
	/**
	 * Check android receipt validation.
	 * 
	 * @param data data json string.
	 * @return Response json string.
	 */
	@RequestMapping(value = PayPushServletCommands.ANDRO_RECEIPT)
	public @ResponseBody
	String androReciept(@RequestParam("data") String data) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		AndroReceiptParam param = gson.fromJson(data, AndroReceiptParam.class);

		AndroidBillingHelper helper = new AndroidBillingHelper(
				PayPushGlobalConsts.PACKAGE_NAME,
				PayPushGlobalConsts.RSA_PUBLIC_KEY);
		AndroReceiptResponse response = new AndroReceiptResponse();
		response.setValidationResult(helper.validation(param.getSignature(),
				param.getSignedData()));
		response.setOrderid(helper.getOutOrderId());
		
		return gson.toJson(response, AndroReceiptResponse.class);
	}

	/**
	 * Send a GCM Message.
	 * 
	 * @param data data json string
	 * @return Response json string 
	 */
	@RequestMapping(value = PayPushServletCommands.ANDRO_PUSH)
	public @ResponseBody
	String androSendPush(@RequestParam("data") String data) {

		Gson gson = new GsonBuilder().serializeNulls().create();
		AndroPushParam param = gson.fromJson(data, AndroPushParam.class);

		GCMSender sender = new GCMSender();
		Result result = sender.sendMessage(param.getRegistrationID(),
				param.getDataList());

		AndroPushResponse response = new AndroPushResponse();
		response.setSuccess(result.getMessageId() != null);
		response.setRegistrationID(param.getRegistrationID());
		response.setCanonicalID(result.getCanonicalRegistrationId());

		if (response.isSuccess()) {
			//ErrorCodeName Constants : ex) Constants.ERROR_BLABLA
			response.setErrorCodeName(result.getErrorCodeName());
		}

		return gson.toJson(response, AndroPushResponse.class);
	}

	/**
	 * Send GCM messages.
	 * 
	 * @param data data json string
	 * @return Response json string
	 */
	@RequestMapping(value = PayPushServletCommands.ANDRO_PUSHS)
	public @ResponseBody
	String androSendPushs(@RequestParam("data") String data) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		AndroPushsParam param = gson.fromJson(data, AndroPushsParam.class);

		GCMSender sender = new GCMSender();
		MulticastResult result = sender.sendMessages(
				param.getRegistrationIDs(), param.getDataList());
		
		AndroPushsResponse response = new AndroPushsResponse();
		response.setSuccessCount(result.getSuccess());
		response.setFailCount(result.getFailure());
		response.setCanonicalCount(result.getCanonicalIds());
		
		if (param.isMoreDetail()) {

			List<Result> pushResults = result.getResults();

			for (int i = 0; i < pushResults.size(); i++) {
				AndroPushResponse temp = new AndroPushResponse();
				Result pushResult = pushResults.get(i);
				
				temp.setSuccess(pushResult.getMessageId() != null);
				temp.setRegistrationID(param.getRegistrationIDs().get(i));
				temp.setCanonicalID(pushResult.getCanonicalRegistrationId());

				if (temp.isSuccess()) {
					//ErrorCodeName Constants : ex) Constants.ERROR_BLABLA
					temp.setErrorCodeName(pushResult.getErrorCodeName());
				}
				response.getResults().add(temp);
			}

		}

		return gson.toJson(response, AndroPushsResponse.class);
	}
}
