package com.api.rest.api.helper;

import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.api.rest.api.model.ResponseBody;
import com.api.rest.api.model.RestResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RestApiHelper {

	public static RestResponse performGetRequest(String Url, Map<String, String> headers) {
		try {
			return performGetRequest(new java.net.URI(Url), headers);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static RestResponse performGetRequest(java.net.URI uri, Map<String, String> headers) {
		HttpGet get = new HttpGet(uri);
		if (headers != null) {
			for (String str : headers.keySet()) {
				get.addHeader(str, headers.get(str));
			}
		}
		CloseableHttpResponse response = null;
		try {
			CloseableHttpClient client = HttpClientBuilder.create().build();
			response = client.execute(get);
			ResponseHandler<String> body = new BasicResponseHandler();
			return new RestResponse(response.getStatusLine().getStatusCode(), body.handleResponse(response));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (e instanceof HttpResponseException)
				return new RestResponse(response.getStatusLine().getStatusCode(), e.getCause().getMessage());
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static RestResponse performPostRequest(String Url, String Content, Map<String, String> headers) {
		CloseableHttpResponse response = null;
		HttpPost post = new HttpPost(Url);
		if (headers != null) {
			for (String key : headers.keySet()) {
				post.addHeader(key, headers.get(key));
			}
		}
		try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
			response = client.execute(post);
			ResponseHandler<String> handler = new BasicResponseHandler();
			return new RestResponse(response.getStatusLine().getStatusCode(), handler.handleResponse(response));
		} catch (Exception e) {
			if (e instanceof HttpResponseException)
				return new RestResponse(response.getStatusLine().getStatusCode(), e.getCause().getMessage());
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static ResponseBody responseBody() {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setPrettyPrinting().create();
		ResponseBody body = gson.fromJson(response.getResponseBody(), ResponseBody.class);
		return responseBody();
	}
}
