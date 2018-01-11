package pl.teamon.manager.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.teamon.manager.entity.User;
import pl.teamon.manager.entity.UserGroup;
import pl.teamon.manager.repository.UserGroupRepository;
import pl.teamon.manager.repository.UserRepository;

@Controller
@RequestMapping("/group")
public class UserGroupController {

	@Autowired
	UserGroupRepository userGroupRepository;
	@Autowired
	UserRepository userRepository;
	
	//------------------------------------- HOME -------------------------------------
	
	@GetMapping("")
	public String mains() {
		return "groups/group";
	}
	
	//------------------------------------- ADD -------------------------------------
	
	@GetMapping("/add")
	public String addGroup(Model m) {
		m.addAttribute("userGroup", new UserGroup());
		return "groups/register";
	}
	
	@PostMapping("/add")
	public String registerPost(@Valid @ModelAttribute UserGroup userGroup, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "groups/register";
		}
		
		List<UserGroup> userGroups = this.userGroupRepository.findAll();
		for (UserGroup u : userGroups) {
			if (u.getGroupName().equals(userGroup.getGroupName())) {
				m.addAttribute("msg", "Podana nazwa jest już zajęta");
				return "groups/register";
			}
		}
		this.userGroupRepository.save(userGroup);
		return "redirect:/group";
	}
	
	//------------------------------------- MANAGE -------------------------------------
	
	@GetMapping("/{id}/manage")
	@Transactional
	public String manageGroup(Model m, @PathVariable Long id) {
		UserGroup userGroup = this.userGroupRepository.findOneById(id);
		m.addAttribute("userGroup", userGroup);
		return "groups/manage";
	}
	
	@PostMapping(value="{id}/manage")
	public String editPost(@Valid @ModelAttribute UserGroup userGroup, BindingResult bindingResult, Model m) {
		if(bindingResult.hasErrors()) {
			m.addAttribute("msg", "Błąd w zapisie");
			return "groups/manage";
		}
		this.userGroupRepository.save(userGroup);
		return "redirect:/group";
	}	
	
	//------------------------------------- DELETE -------------------------------------
	
		@GetMapping("/{id}/delete")
		public String deleteGroup(Model m, @PathVariable Long id) {
			return "groups/delete";
		}
		
		@GetMapping("/{id}/delete/true")
		public String deletePost(@PathVariable Long id) {
			UserGroup userGroup = this.userGroupRepository.findOneById(id);
			this.userGroupRepository.delete(userGroup);
			return "redirect:/group";
		}
	
	//------------------------------------- MODELS -------------------------------------
	
	@ModelAttribute("allGroups")
	public List<UserGroup> allGroups() {
		return this.userGroupRepository.findAllByOrderByIdAsc();
	}
	
	@ModelAttribute("allUsers")
	public List<User> allUsers() {
		return this.userRepository.findAll();
	}
	
//	@ModelAttribute("notSignedUsers")
//	public List<User> getNotSignedUsers(UserGroup u) {
//		return this.userRepository.findAllByUserGroupOrderByUsername(u);
//	}
//
//	@ModelAttribute("signedUsers")
//	public List<User> getSignedUsers() {
//		return this.userRepository.findBySenderOrderByCreatedDesc(u);
//	}
	
}
