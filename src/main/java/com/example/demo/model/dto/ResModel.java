package com.example.demo.model.dto;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.Constant;

/**
 * 返回实体类封装
 */
public class ResModel {

	public String responseCode;
	public Boolean response;
	public String responseMessage;
	public Integer pageNum;
	public Integer pageSize;
	public Integer totalPage;
	public Integer totalSize;
	public Object data;
	
	public ResModel() {
	}

	public ResModel(String respCode, String message) {
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
	public Integer getPageNum() {
		return pageNum == null ? 0 : pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize == null ? 0 : pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPage() {
		return totalPage == null ? 0 : totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getTotalSize() {
		return totalSize == null ? 0 : totalSize;
	}
	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}
	public Object getData() {
		return data == null ? new JSONObject() : data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
