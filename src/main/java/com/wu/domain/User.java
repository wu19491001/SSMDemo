package com.wu.domain;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID=1L;
    private int id;
    private String username;
    private String password;
    private String email;
    private String truename;
    private String department;
    private String phone;
    private String sex;
    private String address;
    private String birthday;
    private String createdate;
    private String head_portrait;
    
    public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getHead_portrait() {
		return head_portrait;
	}

	public void setHead_portrait(String head_portrait) {
		this.head_portrait = head_portrait;
	}

	public String getEmail() {
		return email;
	}
    
	public void setEmail(String email) {
		this.email = email;
	}
	
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    @Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", truename=" + truename + ", department=" + department + ", phone=" + phone + ", sex=" + sex
				+ ", address=" + address + ", birthday=" + birthday + ", createdate=" + createdate + ", head_portrait="
				+ head_portrait + "]";
	}
}
