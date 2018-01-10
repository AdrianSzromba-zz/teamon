package pl.teamon.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.teamon.manager.entity.UserGroup;
import pl.teamon.manager.repository.UserGroupRepository;

@Controller
@RequestMapping("/group")
public class UserGroupController {

	@Autowired
	UserGroupRepository userGroupRepository;
	
	//------------------------------------- REGISTER -------------------------------------
	
	@GetMapping("")
	public String mains () {
		return "groups/group";
	}
	
	//------------------------------------- MODELS -------------------------------------
	
	@ModelAttribute("allGroups")
	public List<UserGroup> allGroups () {
		return this.userGroupRepository.findAllByOrderByIdAsc();
	}
}
