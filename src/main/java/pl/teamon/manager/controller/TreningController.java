package pl.teamon.manager.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
	@Transactional
	public String addTrening(Model m) {
		m.addAttribute("trening", new Trening());
		return "trening/register";
	}

	@PostMapping("/add")
	@Transactional
	public String registepostTreningrPost(@Valid @ModelAttribute Trening trening, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			m.addAttribute("msg", "Błędnie wypełniony formularz");
			return "trening/register";
		}
		
		List<User> user = trening.getUser();
		trening.setUser(null);
		
		UserGroup group = trening.getUsergroup();
		trening.setUsergroup(null);
		this.treningRepository.save(trening);
		
		
		trening.setUsergroup(group);
		trening.setUser(user);
		
//		m.addAttribute("trening",trening);	
//		m.addAttribute("group",group);	
		this.treningRepository.save(trening);
//		return "debug";
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
		sportTypes.add("Siatkowka");
		sportTypes.add("Pilka nozna");
		sportTypes.add("Rugby");
		sportTypes.add("Taniec");
		sportTypes.add("Plywanie");
		sportTypes.add("Gimnastyka");
		return sportTypes;
		}	
		
		@InitBinder     
		public void initBinder(WebDataBinder binder){
		     binder.registerCustomEditor(Date.class,  new CustomDateEditor(new SimpleDateFormat("yyyy-mm-dd"), true, 10));   
		}
}
