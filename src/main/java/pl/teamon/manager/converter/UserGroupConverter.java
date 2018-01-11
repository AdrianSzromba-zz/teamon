package pl.teamon.manager.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.teamon.manager.entity.UserGroup;
import pl.teamon.manager.repository.UserGroupRepository;

public class UserGroupConverter implements Converter<String, UserGroup> {

	@Autowired
	UserGroupRepository userGroupRepository;

	@Override
	public UserGroup convert(String source) {
		UserGroup userGroup = this.userGroupRepository.findById(Long.parseLong(source));
		return userGroup;
	}

}
