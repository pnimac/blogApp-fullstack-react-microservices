package com.pnimac.auth.controller;

import java.security.Principal;

import javax.management.relation.RoleNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import com.pnimac.auth.model.User;
import javax.validation.Valid;
import com.pnimac.auth.model.service.CustomUserDetailsService;

@Controller
public class AuthController {

	@Autowired
	private final CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private final BCryptPasswordEncoder bcryptEncoder;
	
	 @Autowired
	private final AuthenticationManager authenticationManager;

	
	public AuthController(CustomUserDetailsService customUserDetailsService, BCryptPasswordEncoder bcryptEncoder,
			AuthenticationManager authenticationManager) {
		super();
		this.customUserDetailsService = customUserDetailsService;
		this.bcryptEncoder = bcryptEncoder;
		this.authenticationManager = authenticationManager;
	}

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("posts", "");
		return "home";
	}

	@GetMapping("/login")
	public String login(Principal principal) {
		return "login";
	}
	
	@PostMapping("/validatelogin")
    public String validateLogin(@RequestParam String username, @RequestParam String password, Model model) {
		try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            // Set authenticated user into SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
			/*
			 * boolean isAuthenticated = authentication != null &&
			 * authentication.isAuthenticated() && !(authentication instanceof
			 * AnonymousAuthenticationToken); model.addAttribute("isAuthenticated",
			 * isAuthenticated);
			 */

            return "redirect:/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid username or password.");
            return "login";
        }
	}

	@GetMapping("/signup")
	public String getRegisterForm(Model model) {
		// create new user instance and pass it to registerForm view template
		User user = new User();
		model.addAttribute("user", user);
		return "signup";
	}

	@GetMapping("/dashboard")
    public String dashboard(Authentication authentication) {
        return "dashboard";
    }
	
	/*
	 * @Valid indicate that the User object passed as a parameter (user) should be
	 * validated against validation constraints defined in the User class.
	 * 
	 * @ModelAttribute binds a method parameter or method return value to a named
	 * model attribute, user in this case. When Spring MVC processes a request, it
	 * populates this User object with data from the HTTP request parameters (form
	 * data). 
	 * 
	 * BindingResult holds the results of the validation and binding process
	 * between the HTTP request data and the User object. If there are validation
	 * errors they will be stored in bindingResult. 
	 * 
	 * SessionStatus allows you to
	 * manage session attributes and clean up the session when processing is
	 * complete. Typically, you would call sessionStatus.setComplete() after
	 * successfully registering the user. 
	 * 
	 * RoleNotFoundException Indicates that this
	 * method may throw a RoleNotFoundException. This exception is a custom
	 * exception that might be thrown if a required role for user registration is
	 * not found or is invalid.
	 */

	@PostMapping("/saveNewUser")
	public String registerNewUser(@Valid @ModelAttribute User user, BindingResult bindingResult,
			SessionStatus sessionStatus) throws RoleNotFoundException {
		
		if (customUserDetailsService.findUserByUsername(user.getUsername()) != null) {
			bindingResult.rejectValue("username", "error.username",	"Username is already registered try other one or go away");
		}
		if (bindingResult.hasErrors()) {
			return "signup";
		}
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		customUserDetailsService.saveNewUser(user);
		
		Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
		sessionStatus.setComplete();
		
		return "redirect:/";

	}

}