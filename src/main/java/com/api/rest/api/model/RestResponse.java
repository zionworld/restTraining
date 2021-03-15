package com.api.rest.api.model;

public class RestResponse {

	private int statusCode;
	private String responseBody;

	public int getStatusCode() {
		return statusCode;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public RestResponse(int statusCode, String responseBody) {
		// TODO Auto-generated constructor stub
		this.statusCode = statusCode;
		this.responseBody = responseBody;

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Status code : %1s Body : %2s", this.statusCode, this.responseBody);
	}
}
