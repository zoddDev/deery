package es.spring.deery.controller;

import es.spring.deery.entity.User;
import es.spring.deery.model.Errors;
import es.spring.deery.repository.CreatorRepository;
import es.spring.deery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class UsersController {

    UserRepository userRepository;

    CreatorRepository creatorRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setCreatorRepository(CreatorRepository creatorRepository) {
        this.creatorRepository = creatorRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "users/login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password) {
        Optional<User> user = userRepository.findAll().stream().filter(u -> u.getUsername().equals(username)).findFirst();

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            request.getSession().setAttribute("logged-user", user.get());
        } else {
            request.setAttribute("currentpage", "login");
            request.setAttribute("error", Errors.LOGIN);
            return "/errors/error";
        }

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("logged-user");
        return "redirect:/";
    }
}
