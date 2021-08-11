package com.wu.Dao;

import com.wu.domain.User;

import java.io.Serializable;
import java.util.List;

public interface CRUDUser{
    
    public List<User> FindAllUser();
    public List<User> FindAllUserLimit(int page,int limit);
    public int FindAllUserCount();
    public int InsertUser(User user);
    public int InsertUserInfo(int id);
    public int UpdateUserInfo(User user);
    public int UpdateUserPsd(User user);
    public User LoginUser(User user);
    public User LoginEmail(String email);
    public User FindUserById(int id);
    public User FindUserByName(String username);
    public User FindUserByEmail(String email);
}
