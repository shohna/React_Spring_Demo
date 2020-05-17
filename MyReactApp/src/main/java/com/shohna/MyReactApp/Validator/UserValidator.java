package com.shohna.MyReactApp.Validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.shohna.MyReactApp.domain.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		if(user.getPassword().length()<6) {
			errors.rejectValue("password", "Length","Password must be atleast 6 characters");
			
		}
		
		if(user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword","Match","Passwords must match");
		}
		
		
	}






	
	
	

}
