package com.energy.consumption.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.energy.consumption.exception.BusinessException;
import com.energy.consumption.form.UserForm;
import com.energy.consumption.model.Email;
import com.energy.consumption.model.Profile;
import com.energy.consumption.model.User;
import com.energy.consumption.repository.ProfileRepository;
import com.energy.consumption.repository.UserRepository;
import com.energy.consumption.util.EmailUtil;

/**
* 
* @author Wanessa Nascimento
*
*/
@Service
public class UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private EmailUtil emailUtil;
	
	@Value("${service.user.email.exist}")
	private String emailExist;
	
	private String subject = "Access Token Lets Movie API";

	public User registerNewUser(UserForm loginForm) throws BusinessException {
		LOGGER.info("Call Service register user");
		Optional<User> userExistent = repository.findByEmail(loginForm.getEmail());
		if(userExistent.isPresent()) {
			throw new BusinessException(emailExist); 
		}
		return validateNewUser(loginForm);
	}

	private User validateNewUser(UserForm loginForm) throws BusinessException {
		String passwordGenerated = generateRadomicPassword();
		User user = new User(loginForm, new BCryptPasswordEncoder().encode(passwordGenerated));
		String msg = gerEmailMsg(passwordGenerated, user);
		if(emailUtil.sendMail(new Email(user.getEmail(), subject, msg))) {
			saveProfile(user.getProfile());
			return repository.saveAndFlush(user);
		}
		throw new BusinessException("Erro to validate email .");
	}

	private void saveProfile(List<Profile> profiles) {
		for(Profile profile : profiles) {
			profileRepository.save(profile);
		}
	}

	private String gerEmailMsg(String passwordGenerated, User user) {
		return "Congratilations, " + user.getName() + " now do you have access to our amazing API. "
						+ " Find your creditials : "
						+ " Username :" + user.getEmail() + " Password :" + passwordGenerated;
	}
	
	private String generateRadomicPassword() {
		return UUID.randomUUID().toString().split("-")[0];
	}
}
