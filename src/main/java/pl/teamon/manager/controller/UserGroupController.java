package pl.teamon.manager.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.teamon.manager.bean.SessionManager;
import pl.teamon.manager.entity.User;
import pl.teamon.manager.entity.UserGroup;
import pl.teamon.manager.repository.UserGroupRepository;

@Controller
@RequestMapping("/group")
public class UserGroupController {

	@Autowired
	UserGroupRepository userGroupRepository;
	
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
	//	HttpSession s = SessionManager.session();
	//	s.setAttribute("user", user);
		return "redirect:/group";
	}
	
	//------------------------------------- MODELS -------------------------------------
	
	@ModelAttribute("allGroups")
	public List<UserGroup> allGroups() {
		return this.userGroupRepository.findAllByOrderByIdAsc();
	}
}
