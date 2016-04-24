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
import com.myc0058.paypush.Params.AndroReceiptParam;
import com.myc0058.paypush.Response.AndroReceiptResponse;
import com.myc0058.paypush.Strings.PayPushServletCommands;
import com.myc0058.paypush.settings.PayPushGlobalConsts;

/**
 * Test.
 *
 * @author YoungChul Mo
 * @since   2016-04-24
 */

public class SendAndroReceipt {

	/**
	 * Unit Test Method.
	 * 
	 * @throws Exception Exception
	 */
	@Test
	public void test() throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
        	AndroReceiptParam param = new AndroReceiptParam();
        	param.setSignature("iqXB0Gn4saVtmURy+J7d28E0HvoAzNyIiIoKig6TyGbz"
        			+ "Y6AwHtWFsx+IRj5RsbsmFWBF6OQssjuVbnIMBsB3IZ/q"
        			+ "POnmCbM1E24NVPSrZPB4To5b1Nw/VpvA4VTRUYGZov/"
        			+ "PQP1oWXkxyXxAQQCboYeSCAzxysZolLXv/Q8+YrxVUdcr"
        			+ "Plh7p3ZbriUlYSQ/qa0mTL76cqwIZi0al24V20ZmN69RBl1"
        			+ "MVrYrqQlwX/KRrR66RVm4TCjbmKNIqXR+XddJfbo4ybx7tpbDewb8"
        			+ "KO+C3i2WZXkhGHrgMMpgzyp2HeartH2lpoWlrtgtw1Ng7uOnXC5"
        			+ "QM7fkB1jtuE5j4Tgfg==");
        	
        	param.setSignedData(
        			"{\"orderId\":\"12999763169054705758.1325922859268362\","
        			+ "\"packageName\":\"com.Devnom.PuzzleSaga\","
        			+ "\"productId\":\"cash_10\","
        			+ "\"purchaseTime\":1393831756523,"
        			+ "\"purchaseState\":0,"
        			+ "\"purchaseToken\":\"bhgoxzxhueotabyuqngtiupv.AO-J1OyLF4p"
        			+ "ECMOcB-loM8ToCZKio7rbpjwLWQVreLB5LAMEbWGvk6P65zD"
        			+ "Ds6kb3N1Kw5m9PsNqinoEQuyHyMQ28HeartIy1z1gGlB"
        			+ "XpuuUt01Q4Ksi8pHeartKk\"}");
        	
        	Gson gson = new GsonBuilder().serializeNulls().create();
        	String jsonParam = gson.toJson(param, AndroReceiptParam.class);
        	jsonParam = URLEncoder.encode(jsonParam, "UTF-8");
        	String url = 
        			PayPushGlobalConsts.SERVER_PATH
        			+ PayPushServletCommands.ANDRO_RECEIPT
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
            
            AndroReceiptResponse response = 
            		gson.fromJson(responseBody, AndroReceiptResponse.class);
            
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
            
            assertTrue("validation check false", response.isValidationResult());
        } finally {
        	httpclient.close();
        }
	}

}
