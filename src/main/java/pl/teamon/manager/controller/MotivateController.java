package pl.teamon.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/motivate")
public class MotivateController {

	@GetMapping("")
	public String home() {
		return "motivate/motivatehome";
	}
}
