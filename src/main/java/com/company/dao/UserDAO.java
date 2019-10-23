package com.company.dao;

import java.util.List;

import com.company.model.User;

public interface UserDAO 
{
	public boolean addUser(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(User user);
	public List<User> displayUsers();
	public User displayUserByName(User user);
	public User displayUserByEmail(String emailId);
	public User displayUserById(User user);
	@Deprecated
	public boolean isUsernameDuplicate(User user);
	@Deprecated
	public boolean isEmailDuplicate(User user);
	public List<User> getUsersByEmailOrName(User user);
	public boolean isUserLoginCorrect(User user);
}
