package com.wu.domain;

import java.io.Serializable;

public class Department implements Serializable{
 private static final long serialVersionUID=1L;
 private int id;
 private String depname;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDepname() {
	return depname;
}
public void setDepname(String depname) {
	this.depname = depname;
}
@Override
public String toString() {
	return "Department [id=" + id + ", depname=" + depname + "]";
}
 
}
