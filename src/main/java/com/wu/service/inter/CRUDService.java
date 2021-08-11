package com.wu.service.inter;

import com.wu.domain.User;

import java.util.List;

public interface CRUDService {
	public List<User> FindAllUser();

	public List<User> FindUserByID();

	public User FindUserByName(String username);

	public User FindUserByEmail(String email);
	
	public List<User> FindAllUserLimit(int page,int limit);
	
    public int FindAllUserCount();
	
	public User FindUserById(int id);

	public int UpdateUserPsd(User user);

	public void DeleteUser(User user);

	public int InsertUser(User user);
	
	public int InsertUserInfo(int id);
	
	public int UpdateUserInfo(User user);

	public User LoginUser(User user);

	public User LoginEmail(String email);

}
