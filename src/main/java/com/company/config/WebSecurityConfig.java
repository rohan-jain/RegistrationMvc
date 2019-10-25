package com.company.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@SuppressWarnings("deprecation")
	@Bean
	public WebContentInterceptor webContentInterceptor() {
	    final WebContentInterceptor interceptor = new WebContentInterceptor();
	    interceptor.setCacheSeconds(0);
	    interceptor.setUseExpiresHeader(true);
	    interceptor.setUseCacheControlNoStore(true);
	    interceptor.setUseCacheControlHeader(true);
	    return interceptor;
	}

  @Autowired
  private UserDetailsService userDetailsService;
  
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  };
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }
  


  @Override
  protected void configure(HttpSecurity http) throws Exception 
  {
	  http.authorizeRequests()
	  
	  .antMatchers("/index").permitAll()
	  .antMatchers("/loginPretty").permitAll()
	  .antMatchers("/register").permitAll() 
	  
	  .antMatchers("/forgotPassword").permitAll()
	  .antMatchers("/welcome").permitAll()
	  .antMatchers("/emailController").permitAll()
	  .antMatchers("/personaldetails").hasAuthority("user")
      .antMatchers("/userdetails").hasAuthority("admin")
      .antMatchers("/deleteorgcontroller").hasAuthority("admin")
      .antMatchers("/addorganization").hasAuthority("admin")
      .antMatchers("/OrganizationDetailsController").hasAuthority("admin")
      .antMatchers("/updateOrganizationController").hasAuthority("admin")
      .antMatchers("/organizatiocontroller").hasAuthority("admin")
      //.antMatchers("**/https://test.instamojo.com/**").permitAll()
      //.antMatchers("/**").hasAnyRole("ADMIN", "USER")
      .and().formLogin().defaultSuccessUrl("/UserDetailsController")
      .loginPage("/loginPretty").loginProcessingUrl("/loginController").permitAll()
      .and()
      .logout().logoutUrl("/logout")
      
      .invalidateHttpSession(true)
      .clearAuthentication(true)
      .deleteCookies("JESSIONID")
     
      .logoutSuccessUrl("/welcome")
      .and().csrf().disable();

  }
}