package JobIT.Controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import JobIT.Model.ConfirmationToken;
import JobIT.Model.Question;
import JobIT.Model.User;
import JobIT.Repository.ConfirmationTokenRepository;
import JobIT.Repository.UserRepository;
import JobIT.Service.EmailSenderService;
import JobIT.Service.QuestionService;
import JobIT.Service.UserService;

@Controller
public class MainController {

	@Autowired
	UserService userService;

	@Autowired
	BCryptPasswordEncoder encoder;

	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	private EmailSenderService emailSenderService;

	@Autowired
	private UserRepository userRepository;

	Timestamp timestamp = new Timestamp(System.currentTimeMillis());

	private static Pattern passwordNamePattern = Pattern
			.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})");

	@Autowired
	private QuestionService questionService;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView home(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");

		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		List<Question> questions = questionService.GetAllQues();
		model.addAttribute("questions", questions);
		model.addAttribute("user", user);
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");

		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
			ModelMap modelMap) {
		user.setCreated_date(timestamp);
		user.setUpdate_date(timestamp);

		String ChangePassword = (user.getPassword());
		String role = (user.getRoleName());

		System.out.println(ChangePassword);
		System.out.println(role);

		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findByEmail(user.getEmail());

		boolean passwordCheck = PasswordCheck(ChangePassword);
		if (passwordCheck == false) {
			bindingResult.rejectValue("password", "error.user",
					"Your Password is Not String...Simple password Dhaka#1216");
		}

		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "This email is already exists");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("register");
		} else if (userService.isUserAlreadyPresent(user)) {
			modelAndView.addObject("seccessMessage", "User is already exists");
		}

		else {
			if (role.equals("SITE_USER")) {
				userService.saveUser(user);
			} else {
				userService.saveAdmin(user);
			}

			SendEmail(user, ChangePassword);
			modelAndView.addObject("email", user.getEmail());
			modelAndView.setViewName("successfulRegistration");
		}
		return modelAndView;
	}

//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public ModelAndView register(Model model) {
//		ModelAndView modelAndView = new ModelAndView();
//		User user = new User();
//		List<Question> questions = questionService.GetAllQues();
//		model.addAttribute("questions", questions);
//		modelAndView.addObject("user", user);
//		modelAndView.setViewName("register");
//
//		return modelAndView;
//	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");

		return modelAndView;
	}

	@RequestMapping(value = "/userhome", method = RequestMethod.GET)
	public ModelAndView userHome() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByEmail(auth.getName());
		modelAndView.addObject("userName", user.getUserName());
		modelAndView.setViewName("UserHome");

		return modelAndView;
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminHome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("AdminHome");

		return modelAndView;

	}

	@RequestMapping(value = "/confirm-account", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String confimationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confimationToken);

		if (token != null) {

			User user = userRepository.findByEmail(token.getUser().getEmail());
			user.setEnabled(true);

			userRepository.save(user);
			modelAndView.setViewName("accountVerified");
		} else {
			modelAndView.addObject("message", "The link is invalid or broken!");
			modelAndView.setViewName("error");
		}

		return modelAndView;
	}

	public boolean PasswordCheck(String password) {
		Matcher mtchr = passwordNamePattern.matcher(password);
		if (mtchr.matches()) {
			return true;
		}
		return false;
	}

	public void SendEmail(@Valid User user, String password) {
		ConfirmationToken confirmationToken = new ConfirmationToken(user);

		confirmationTokenRepository.save(confirmationToken);

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(user.getEmail());
		mailMessage.setSubject("Complete Registration");
		mailMessage.setFrom("m.kamrul.hasan13@gmail.com");
		if (password.equals("reset")) {
			mailMessage.setText("To complete the password reset process, please click here:"
					+ "http://localhost:8082/confirm-reset?token=" + confirmationToken.getConfirmationToken());
		} else {
			mailMessage.setText("Please click here: " + "http://localhost:8082/confirm-account?token="
					+ confirmationToken.getConfirmationToken());
		}
		emailSenderService.sendEmail(mailMessage);
	}
}
