package technicalblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import technicalblog.model.Post;
import technicalblog.model.User;
import technicalblog.model.UserProfile;
import technicalblog.service.PostService;
import technicalblog.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @RequestMapping("users/registration")
    public String registration(Model model) {
        User user = new User();
        UserProfile userProfile = new UserProfile();
        user.setProfile(userProfile);
        model.addAttribute("User", user);
        return "users/registration";
    }

    @RequestMapping(value = "users/registration", method = RequestMethod.POST)
    public String registration(User user) {
        userService.registerUser(user);
        return "users/login";
    }

    @RequestMapping("users/login")
    public String login() {
        return "users/login";
    }

    @RequestMapping(value = "users/login", method = RequestMethod.POST)
    public String loginUser(User user, HttpSession session) {
        User existingUser = userService.login(user);
        if (existingUser != null) {
            session.setAttribute("loggedUser",existingUser);
            return "redirect:/posts";
        } else {
            return "users/login";
        }
    }

    @RequestMapping(value = "users/logout", method = RequestMethod.POST)
    public String logoutUser(Model model,HttpSession session) {
        session.invalidate();
        List<Post> postsLandingPage = postService.getAllPost();
        model.addAttribute("posts", postsLandingPage);
        return "index";
    }
}
