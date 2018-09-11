package ng.music.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ng.music.dao.MusicDAO;
import ng.music.dao.UserDAO;


 
@Controller
public class WebController {
 
	private Boolean isAuthorized = false;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		return "loginForm";
	}
 
	@RequestMapping(value = "/login", method = RequestMethod.POST) 
	public String loginAuthentication(HttpServletRequest request, Model model) {
		
		String username = request.getParameter("name");
		String password = request.getParameter("pw");
		
		UserDAO user = new UserDAO(username, password);
		if (user.isAuthorizedUser()) {
			isAuthorized = true;
			return "redirect:searchForm";
		}
		else {
			model.addAttribute("varr", true);
			return "loginForm";
		}
	}
	
	@RequestMapping(value = "/searchForm", method = RequestMethod.GET)
	public String showSearchForm() {
		if (isAuthorized)
			return "searchForm";
		else
			return "redirect:login";
	}
	
	@RequestMapping(value = "/searchForm", method = RequestMethod.POST)
	public String searchResults(HttpServletRequest request, Model model) {
		String searchKey = request.getParameter("searchKey");
		MusicDAO musicDAO = new MusicDAO(searchKey);
		model.addAttribute("musicdata", musicDAO.getSearchResults());
		return "searchForm";
	}
	
}
















