package pl.teamon.manager.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.teamon.manager.entity.Trening;
import pl.teamon.manager.entity.User;
import pl.teamon.manager.entity.UserGroup;
import pl.teamon.manager.repository.TreningRepository;
import pl.teamon.manager.repository.UserGroupRepository;
import pl.teamon.manager.repository.UserRepository;

@Controller
@RequestMapping("/trening")
public class TreningController {

	@Autowired
	UserGroupRepository userGroupRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	TreningRepository treningRepository;
	
	//------------------------------------- HOME -------------------------------------
	
	@GetMapping("")
	public String mains() {
		return "trening/treninghome";
	}
	
	//------------------------------------- HOME -------------------------------------
	
	@GetMapping("/add")
	public String addTrening(Model m) {
		m.addAttribute("trening", new Trening());
		m.addAttribute("date", new Date());
		return "trening/register";
	}

	@PostMapping("/add")
	public String registepostTreningrPost(@Valid @ModelAttribute Trening trening, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			m.addAttribute("msg", "Błędnie wypełniony formularz");
			return "trening/register";
		}
		this.treningRepository.save(trening);
		return "redirect:/trening";
	}
	
	//------------------------------------- MODELS -------------------------------------
	
		@ModelAttribute("allTrenings")
		public List<Trening> allTrenings() {
			return this.treningRepository.findAllByOrderByDateAsc();
		}
		
		@ModelAttribute("allGroups")
		public List<UserGroup> allGroups() {
			return this.userGroupRepository.findAllByOrderByIdAsc();
		}
		
//		@ModelAttribute("allUsers")
//		public List<User> allUsers() {
//			return this.userRepository.findAllByUserGroupOrderByUsername();
//		}
		
		@ModelAttribute("allUsers")
		public List<User> allUsers() {
			return this.userRepository.findAll();
		}
		
		@ModelAttribute("sportTypes")
		public Collection<String> sportTypes() {
		List<String> sportTypes = new ArrayList<>();
		sportTypes.add("Siatkówka");
		sportTypes.add("Piłka nożna");
		sportTypes.add("Rugby");
		sportTypes.add("Taniec");
		sportTypes.add("Pływanie");
		sportTypes.add("Gimnastyka");
		return sportTypes;
		}	
}
