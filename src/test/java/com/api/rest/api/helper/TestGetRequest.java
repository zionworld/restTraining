package com.api.rest.api.helper;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.Test;
import org.junit.Assert;

import com.api.rest.api.model.ResponseBody;
import com.api.rest.api.model.RestResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestGetRequest {

	@Test
	public void testGetPingAlive() {
		String Url = "http://localhost:8080/laptop-bag/webapi/api/ping/hello";
		RestResponse response = RestApiHelper.performGetRequest(Url, null);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		Assert.assertEquals("Hi! hello", response.getResponseBody());
		System.out.println(response.getResponseBody());
	}

	@Test
	public void testGetAll() {
		String Url = "http://localhost:8080/laptop-bag/webapi/api/all";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/xml");
		RestResponse response = RestApiHelper.performGetRequest(Url, headers);
		Assert.assertTrue("Expected Status is not Preset", (HttpStatus.SC_OK == response.getStatusCode()
				|| (HttpStatus.SC_NO_CONTENT == response.getStatusCode())));
		System.out.println(response.getResponseBody());
	}

	@Test
	public void testGetFindByID() {
		String Url = "http://localhost:8080/laptop-bag/webapi/api/find/127";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/xml");
		RestResponse response = RestApiHelper.performGetRequest(Url, headers);
		Assert.assertTrue("Expected Status is not Preset", (HttpStatus.SC_OK == response.getStatusCode()
				|| (HttpStatus.SC_NOT_FOUND == response.getStatusCode())));
		System.out.println(response.getResponseBody());
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setPrettyPrinting().create();
		ResponseBody body = gson.fromJson(response.getResponseBody(), ResponseBody.class);
		
	}
}
