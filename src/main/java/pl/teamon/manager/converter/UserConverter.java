package pl.teamon.manager.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.teamon.manager.entity.User;
import pl.teamon.manager.repository.UserRepository;

public class UserConverter implements Converter<String, User>{
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User convert(String source) {
		User user = this.userRepository.findById(Long.parseLong(source));
		return user;
	}

}
