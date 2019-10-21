package com.company.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.company.dao.UserDAO;
import com.company.daoimpl.UserDAOImpl;
import com.company.model.User;

public class UserNameValidator implements ConstraintValidator<UserNameConstraint, String>
{
	UserDAO userDao = new UserDAOImpl();
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) 
	{
		User user = new User();
		user.setUsername(value);
		if(userDao.displayUserByName(user)==null) {
			return true;
		} else {
			return false;
		}
	}

}
