package com.domoment.leaves.common.response;

import java.io.Serializable;

public class ResponseResult<T> implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private T data;
	
	private int status;

	private String msg;

	private String dataType;
	
	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}

	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public static ResponseResult<Object> success(Object data) {
		ResponseResult<Object> result = new ResponseResult<>();
		result.setStatus(200);
		result.setMsg("ok");
		result.setData(data);
		return result;
	}
	public static ResponseResult<Object> failed(Object data) {
		ResponseResult<Object> result = new ResponseResult<>();
		result.setStatus(400);
		result.setMsg("failed");
		result.setData(data);
		return result;
	}
	
	public static ResponseResult<Object> error(Object data) {
		ResponseResult<Object> result = new ResponseResult<>();
		result.setStatus(-1);
		result.setMsg("error");
		result.setData(data);
		return result;
	}
	
}
