package com.company.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.company.dao.UserDAO;
import com.company.daoimpl.UserDAOImpl;
import com.company.model.User;

public class ValidUserNameValidator implements ConstraintValidator<ValidUserName, User>
{
//	UserDAO userDao = new UserDAOImpl();
//	public boolean isValid(String value, ConstraintValidatorContext context) 
//	{
//		User user = new User();
//		user.setUsername(value);
//		if(userDao.displayUserByName(user)==null) {
//			return true;
//		} else {
//			return false;
//		}
//	}
	
	UserDAO userDao = new UserDAOImpl();
	
    @Override
    public void initialize(ValidUserName constraintAnnotation) {
    }
	
	@Override
	public boolean isValid(User value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if(value.getUsername().equals("mn")) {
			return false;
		}
		return true;
	}

}
