package com.myc0058.paypush.IOS;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import com.myc0058.paypush.settings.PayPushGlobalConsts;

/**
 * IOS Billing Helper.
 *
 * @author YoungChul Mo
 * @since   2016-04-24
 */

public final class IOSBillingHelper {
	private static final int RECEIPT_BUFFERSIZE = 1024;
	private static final int CONNECTION_TIMEOUT = 100000;

	private IOSBillingHelper() { }
	
	/**
	 * Check ios receipt validation.
	 * 
	 * @param receipt receipt
	 * @param isDevelopMode isDevelopMode
	 * @return if receipt is validated then true or not false
	 */
	public static boolean validateReceipt(String receipt, 
			boolean isDevelopMode) {
		String base64Data = Base64.encodeBase64String(receipt.getBytes());

		String urlAddr = null;
		if (isDevelopMode) {
			urlAddr = PayPushGlobalConsts.DEV_RECEIPT_URL;
		} else {
			urlAddr = PayPushGlobalConsts.PRO_RECEIPT_URL;
		}

		URL url = null;
		HttpURLConnection httpCon = null;
		InputStream is = null;
		OutputStream os = null;
		PrintWriter pw = null;
		ByteArrayOutputStream baos = null;
		boolean isValidBill = false;

		try {
			url = new URL(urlAddr);
			httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setConnectTimeout(CONNECTION_TIMEOUT);
			httpCon.setRequestMethod("POST");
			httpCon.setDoOutput(true);

			os = httpCon.getOutputStream();
			pw = new PrintWriter(os);
			String t = toJsonObject("receipt-data", base64Data);
			pw.print(t);
			pw.flush();

			is = httpCon.getInputStream();
			baos = new ByteArrayOutputStream();
			while (true) {
				byte[] b = new byte[RECEIPT_BUFFERSIZE];
				int cnt = is.read(b);
				if (cnt < 0) {
					break;
				}
				baos.write(b);
			}

			receipt = baos.toString().trim();
			isValidBill = isValidBilling(receipt);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pw != null) {
					pw.close();
				}
				if (os != null) {
					os.close();
				}
				if (is != null) {
					is.close();
				}
				if (baos != null) {
					baos.close();
				}
				if (httpCon != null) {
					httpCon.disconnect();
				}
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
		}

		return isValidBill;
	}

	private static String toJsonObject(Object key, Object value) {
		JSONObject obj = new JSONObject();
		obj.put(key, value);
		return obj.toString();
	}

	private static boolean isValidBilling(String receipt) {
		if (receipt == null || receipt.equals("")) {
			return false;
		}

		boolean valid = false;

		JSONObject json = (JSONObject) JSONSerializer.toJSON(receipt);
		if (json.getInt("status") == 0) {
			valid = true;
		} else {
			valid = false;
		}

		return valid;
	}
}
