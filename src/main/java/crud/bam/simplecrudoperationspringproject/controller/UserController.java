package crud.bam.simplecrudoperationspringproject.controller;

import crud.bam.simplecrudoperationspringproject.exception.UserNotFoundException;
import crud.bam.simplecrudoperationspringproject.model.User;
import crud.bam.simplecrudoperationspringproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")//endpoint to display all users
    public String listUsers(Model model) {
        List<User> users = userService.listAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("message", "User list retrieved successfully");//This attribute maps to the user html to show the message when endpoint is accessed.
        return "users";// This corresponds to a Thymeleaf template users.html
    }

    //endpoint to display the user form
    @GetMapping("/form")
    public String userForm(Model model) {
        model.addAttribute("users", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "user_form";//Corresponds to a Thymeleaf template named user_form.html
    }

    //endpoint to process form submission and add a new user
    @PostMapping("/users/save")
    public String saveUserSubmit(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "The user has been saved successfully");
        return "redirect:/users/list";
    }

    @GetMapping("/users/edit/{id}")
    public String getUserByIdShowEditForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            User user = userService.getUserById(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID " + id + ")");
            return "user_form";//returns the name of the form_view in html template under resource folder

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", "Invalid ID");
            return "redirect:/users";
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "User not found with ID " + id);
            return "redirect:/users";
        }

    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            userService.delete(id);
            redirectAttributes.addFlashAttribute("message", "The ID " + id + " has been deleted");
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }
}
