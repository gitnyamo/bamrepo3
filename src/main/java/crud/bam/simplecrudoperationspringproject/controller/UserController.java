package crud.bam.simplecrudoperationspringproject.controller;

import crud.bam.simplecrudoperationspringproject.exception.UserNotFoundException;
import crud.bam.simplecrudoperationspringproject.model.User;
import crud.bam.simplecrudoperationspringproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public String displayAllUsers(Model model) {
        List<User> listUsers = userService.listAllUsers();
        model.addAttribute("listUsers", listUsers);
        return "users";// returns the name of the view--- ensure you create html file name under resource folder in template and code to render the user view
    }

    @PostMapping("/users/save")
    public String saveUSer(User user, RedirectAttributes redirectAttributes) {
        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "The user has been saved successfully");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String getUserById(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            User user = userService.getUserById(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID " +id + ")");
            return "user_form";//returns the name of the form_view in html template under resource folder
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", "Invalid ID");
            return "redirect:/users";
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "User not found with ID "+id);
            return "redirect:/users";
        }

    }


}
