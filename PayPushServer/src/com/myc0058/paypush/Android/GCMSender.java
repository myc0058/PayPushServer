package com.myc0058.paypush.Android;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.myc0058.paypush.settings.PayPushGlobalConsts;

/**
 * GCM Sender.
 *
 * @author YoungChul Mo
 * @since   2016-04-24
 */

public class GCMSender {
	private Sender gcmSender;

	/**
	 * Constructor.
	 */
	public GCMSender() {
		gcmSender = new Sender(PayPushGlobalConsts.GCM_API_KEY);
	}

	/**
	 * @param registrationId registrationId
	 * @param dataList dataList
	 * @return result for sending a message to GCM Server
	 */
	public Result sendMessage(
			String registrationId, Map<String, String> dataList) {
		Message.Builder builder = new Message.Builder()
				// .collapseKey(COLLAPSE_KEY)
				.delayWhileIdle(PayPushGlobalConsts.DELAY_WHILE_IDLE)
				.timeToLive(PayPushGlobalConsts.TIME_TO_LIVE);

		Iterator<Entry<String, String>> iterMap = 
				dataList.entrySet().iterator();

		while (iterMap.hasNext()) {
			Entry<String, String> entry = iterMap.next();
			builder.addData(entry.getKey(), entry.getValue());
		}
		Message gcmMessage = builder.build();

		try {
			Result result = gcmSender.send(gcmMessage, registrationId,
					PayPushGlobalConsts.RETRY);
			return result;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * @param registrationIds registrationIds
	 * @param dataList dataList
	 * @return result for sending messages to GCM Server
	 */
	public MulticastResult sendMessages(List<String> registrationIds,
			Map<String, String> dataList) {
		Message.Builder builder = new Message.Builder()
				// .collapseKey(COLLAPSE_KEY)
				.delayWhileIdle(PayPushGlobalConsts.DELAY_WHILE_IDLE)
				.timeToLive(PayPushGlobalConsts.TIME_TO_LIVE);

		Iterator<Entry<String, String>> iterMap = 
				dataList.entrySet().iterator();

		while (iterMap.hasNext()) {
			Entry<String, String> entry = iterMap.next();
			builder.addData(entry.getKey(), entry.getValue());
		}
		Message gcmMessage = builder.build();

		try {
			MulticastResult gcmMultiResult = gcmSender.send(gcmMessage,
					registrationIds, PayPushGlobalConsts.RETRY);
			return gcmMultiResult;
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return null;
	}
}
