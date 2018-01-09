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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.teamon.manager.bean.LoginData;
import pl.teamon.manager.bean.SessionManager;
import pl.teamon.manager.entity.User;
import pl.teamon.manager.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;

	//------------------------------------- REGISTER -------------------------------------
	
	@GetMapping("/register")
	public String register(Model m) {
		m.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String registerPost(@Valid @ModelAttribute User user, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "register";
		}
		
		List<User> users = this.userRepository.findAll();
		for (User u : users) {
			if (u.getEmail().equals(user.getEmail())) {
				m.addAttribute("msg", "Podany E-mail jest już zajęty");
				return "register";
			}
		}
		this.userRepository.save(user);
		HttpSession s = SessionManager.session();
		s.setAttribute("user", user);
		return "redirect:/";
	}
	
	//------------------------------------- LOGIN -------------------------------------
	
	@GetMapping("/login")
	public String login(Model m) {
		m.addAttribute("loginData", new LoginData());
		return "login";
	}
	
	@PostMapping("/login")
	public String loginPost(@ModelAttribute LoginData loginData, Model m, RedirectAttributes ra) {
		User user = this.userRepository.findOneByEmail(loginData.getEmail());
		if (user != null && user.isPasswordCorrect(loginData.getPassword())) {
			HttpSession s = SessionManager.session();
			s.setAttribute("user", user);
			ra.addFlashAttribute("msg", "Jestes zalogowany");
			return "redirect:/";
		}
		m.addAttribute("msg", "Wprowadź poprawne dane");
		return "login";
	}
	
	//------------------------------------- MYACCOUNT -------------------------------------
	
		@GetMapping("/myaccount")
		public String myaccount(Model m) {
			HttpSession s = SessionManager.session();
			User u = (User) s.getAttribute("user");
			m.addAttribute("user", u);
			return "myaccount";
		}
	
	//------------------------------------- CHANGE -------------------------------------
	
//	@GetMapping("/myaccount/edit")
//	public String changeUser(Model m) {
//		HttpSession s = SessionManager.session();
//		User u = (User) s.getAttribute("user");
//		m.addAttribute("user", u);
//		return "editUser";
//	}
//	
//	@PostMapping("/change")
//	public String changePost(@Valid @ModelAttribute User user, BindingResult bindingResult) {
//		if (bindingResult.hasErrors()) {
//			return "redirect:/register";
//		}
//		HttpSession s = SessionManager.session();
//		User u = (User) s.getAttribute("user");
//		user.setId(u.getId());
//		this.userRepository.save(user);
//		return "redirect:/";
//	}
		
	//------------------------------------- DELETE -------------------------------------

	@GetMapping("/myaccount/delete")
	public String delete(Model m) {
		HttpSession s = SessionManager.session();
		User u = (User) s.getAttribute("user");
		m.addAttribute("user", u);
		return "deleteUser";
	}
	
	@GetMapping("/myaccount/delete/true")
	public String deletePost() {
		HttpSession s = SessionManager.session();
		User u = (User) s.getAttribute("user");
		s.invalidate();
		this.userRepository.delete(u);
		return "redirect:/";
	}
	
	//------------------------------------- LOGOUT -------------------------------------
	
	@GetMapping("/logout")
	public String logout(Model m) {
		m.addAttribute("loginData", new LoginData());
		HttpSession s = SessionManager.session();
		s.invalidate();
		return "redirect:/";
	}

}
