package com.wu.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserGroup implements Serializable{
	private int code; 
	private String msg;  
	private int count;  
    private List<User> data = new ArrayList<User>();

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<User> getData() {
		return data;
	}
	public void setData(List<User> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "UserGroup [code=" + code + ", msg=" + msg + ", count=" + count + ", data=" + data + "]";
	}
    
}
