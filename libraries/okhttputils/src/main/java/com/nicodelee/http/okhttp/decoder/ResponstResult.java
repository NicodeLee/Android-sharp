package com.nicodelee.http.okhttp.decoder;


public class ResponstResult {
	private int status;//	执行的状态：1成功，0失败，2警告，3提示
	private int errorCode;//	错误或者失败的编码(先预留)
	private String message;//	消息，系统返回的提示消息。无论哪种状态都有可能返回消息。
	private Object data;//	如果是查询请求，返回的是集合（List）；如果是保存编辑请求，返回的是一个对象。
	private long total;//	本次查询请求的总记录数，用于分页计算。
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
	

}
