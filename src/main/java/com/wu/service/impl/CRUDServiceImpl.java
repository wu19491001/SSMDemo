package com.wu.service.impl;

import com.wu.Dao.CRUDUser;
import com.wu.domain.User;
import com.wu.service.inter.CRUDService;
import com.wu.tools.Tools;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
@Service("crudServiceImpl")
public  class CRUDServiceImpl implements CRUDService {
	@Autowired
    private CRUDUser crudUser;
	
    private User user;
    public List<User> FindAllUser() {
        return crudUser.FindAllUser();
    }


    public List<User> FindUserByID() {
        return null;
    }
	@Override
	public User FindUserByName(String username) {
		// TODO Auto-generated method stub
		User user=crudUser.FindUserByName(username);
		if(user!=null) {
			user.setPassword("");
		}
		return user;
		
	}


	@Override
	public User FindUserByEmail(String email) {
		// TODO Auto-generated method stub
		return crudUser.FindUserByEmail(email);
	}
    public void DeleteUser(User user) {

    }


    public int InsertUser(User user) {
    	String password=user.getPassword();
    	String passwordre=Tools.toolReverse(password);
    	StringBuilder sb=new StringBuilder();
		 sb.append(password);
		 sb.append(passwordre);
		 String pass=DigestUtils.md5DigestAsHex(sb.toString().getBytes());
		 user.setPassword(pass);
		 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String da=df.format(new Date());
		 user.setCreatedate(da);
         return crudUser.InsertUser(user);
    }


	@Override
	public User LoginUser(User user) {
		// TODO Auto-generated method stub
		String password=user.getPassword();
		String passwordre=Tools.toolReverse(password);
		StringBuilder sb=new StringBuilder();
		 sb.append(password);
		 sb.append(passwordre);
		 String pass=DigestUtils.md5DigestAsHex(sb.toString().getBytes());
		 user.setPassword(pass);
		return crudUser.LoginUser(user);
	}


	@Override
	public User LoginEmail(String email) {
		// TODO Auto-generated method stub
		if(email!=null && email.length()!=0) {
			return crudUser.LoginEmail(email);
		}	
		return null;
	}


	@Override
	public int UpdateUserPsd(User user) {
		// TODO Auto-generated method stub
		String password=user.getPassword();
		String passwordre=Tools.toolReverse(password);
		StringBuilder sb=new StringBuilder();
		 sb.append(password);
		 sb.append(passwordre);
		 String pass=DigestUtils.md5DigestAsHex(sb.toString().getBytes());
		 user.setPassword(pass);
		 return crudUser.UpdateUserPsd(user);
	}


	@Override
	public User FindUserById(int id) {
		// TODO Auto-generated method stub
		
		return crudUser.FindUserById(id);
	}


	@Override
	public int InsertUserInfo(int id) {
		// TODO Auto-generated method stub
		return crudUser.InsertUserInfo(id);
	}


	@Override
	public int UpdateUserInfo(User user) {
		// TODO Auto-generated method stub
		String hp=crudUser.FindUserById(user.getId()).getHead_portrait();
		if(hp!=null && !"".equals(hp)) {
			System.out.println(hp);
			File file=new File(hp);
			if(file.exists()) {
				file.delete();
			}
		}
		return crudUser.UpdateUserInfo(user);
	}


	@Override
	public List<User> FindAllUserLimit(@Param("page") int page,@Param("limit") int limit) {
		// TODO Auto-generated method stub
		page=(page-1)*limit;
		System.out.println("page=="+page);
		return crudUser.FindAllUserLimit(page,limit);
	}


	@Override
	public int FindAllUserCount() {
		// TODO Auto-generated method stub
		return crudUser.FindAllUserCount();
	}




}
