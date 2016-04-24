package com.myc0058.paypush.Tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.myc0058.paypush.Params.IOSReceiptParam;
import com.myc0058.paypush.Response.IOSReceiptResponse;
import com.myc0058.paypush.Strings.PayPushServletCommands;
import com.myc0058.paypush.settings.PayPushGlobalConsts;


/**
 * Test for IOS Payment.
 * 
 * @author  YoungChul Mo
 * @since   2016-04-24
 */

public class SendIOSReceipt {

	/**
	 * Unit Test Method.
	 * 
	 * @throws Exception Exception
	 */
	@Test
	public final void test() throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
        	IOSReceiptParam param = new IOSReceiptParam();
        	param.setReceipt("asdfasdfasdfasdfasdfasdf");
        	param.setDevelopMode(true);
        	
        	Gson gson = new GsonBuilder().serializeNulls().create();
        	String jsonParam = gson.toJson(param, IOSReceiptParam.class);
        	jsonParam = URLEncoder.encode(jsonParam, "UTF-8");
        	String url = PayPushGlobalConsts.SERVER_PATH
        			+ PayPushServletCommands.IOS_RECEIPT
        			+ "?data=" + jsonParam;
        	
            HttpGet httpget = new HttpGet(url);
            System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = 
            		new ResponseHandler<String>() {
				@Override
				public String handleResponse(HttpResponse response) 
						throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
                    if (status == HttpStatus.SC_OK) {
                        HttpEntity entity = response.getEntity();
                        if (entity != null) {
							return EntityUtils.toString(entity);
						} else {
							return null;
						}
                    } else {
                        throw new ClientProtocolException(
                        		"Unexpected response status: " + status);
                    }
				}
            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            
            IOSReceiptResponse response = 
            		gson.fromJson(responseBody, IOSReceiptResponse.class);
            
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
            
            assertTrue("validation check false", response.isValidationResult());
        } finally {
        	httpclient.close();
        }
	}

}
