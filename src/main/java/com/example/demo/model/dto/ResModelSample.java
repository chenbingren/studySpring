package com.example.demo.model.dto;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.Constant;

/**
 * 返回实体类封装
 */
public class ResModelSample {

	public String responseCode;
	public Boolean response;
	public String responseMessage;
	public Object data;
	
	public ResModelSample() {
	}

	public ResModelSample(String respCode, String message) {
		if (Constant.RETURN_SUCCESS.equals(respCode)) {
			this.response = true;
		}
		this.responseCode = respCode;
		this.responseMessage = message;
	}
	
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public Boolean getResponse() {
		return response;
	}
	public void setResponse(Boolean response) {
		this.response = response;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public Object getData() {
		return data == null ? new JSONObject() : data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
