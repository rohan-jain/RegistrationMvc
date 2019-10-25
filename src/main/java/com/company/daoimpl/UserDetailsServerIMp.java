package com.company.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.dao.UserDAO;
//import com.mySecondCompany.dao.UserDao;
//import com.mySecondCompany.model.User;
import com.company.model.User;


@Service("userDetailsService")
public class UserDetailsServerIMp implements UserDetailsService
{
//	@Autowired
	UserDAO userDAO = new UserDAOImpl();

    @Transactional(readOnly = true)
	  public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	User user = new User();
    	user.setUsername(username);
	    user = userDAO.displayUserByName(user);
	    UserBuilder builder = null;
	    if (user != null) {
	      
	      builder = org.springframework.security.core.userdetails.User.withUsername(username);
//	      builder.disabled(!user.isEnabled());
	      builder.password(user.getPassword());
	      String[] authorities = new String[]{user.getRole()} ;

	      builder.authorities(authorities);
	    } else {
	      throw new UsernameNotFoundException("User not found.");
	    }
	    return builder.build();
	  }
}
