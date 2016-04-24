package com.myc0058.paypush.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Spring Controller for Tests.
 *
 * @author YoungChul Mo
 * @since   2016-04-24
 */

@Controller
public class TestController {
	/**
	 * test.do method.
	 * 
	 * @return adsf
	 */
	@RequestMapping(value = "/test.do")
	public @ResponseBody
	String test() {
		Gson gson = new GsonBuilder().serializeNulls().create();
		
		String result = "return value for test 앤드 한글..";
		
		String strJson = gson.toJson(result);
		
		return strJson;
	}
}
