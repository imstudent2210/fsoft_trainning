package com.example.admin.controller;

import com.example.library.dto.AdminDTO;
import com.example.library.model.Admin;
import com.example.library.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AdminServiceImpl adminService;

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("title","Login");

        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("title","Register");
        model.addAttribute("adminDTO", new AdminDTO());
        return "register";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model){
        model.addAttribute("title","Forgot Password");
        return "forgot-password";
    }

    @PostMapping("/register-new")
    public String addNewAdmin(@Valid @ModelAttribute("adminDTO")AdminDTO adminDTO,
                              BindingResult result,
                              Model model){
       try{
           if(result.hasErrors()){
               model.addAttribute("adminDTO", new AdminDTO());
               result.toString();
               return "register";
           }
           String username = adminDTO.getUserName();
           Admin admin = adminService.findByUsername(username);

           if(admin != null){
               model.addAttribute("adminDTO",adminDTO );
               model.addAttribute("emailError", "Your email has been registerd!");
               System.out.println("Admin not null!");
               return "register";
           }

           if(adminDTO.getPassWord().equals(adminDTO.getRepeatPassWord())){
               adminDTO.setPassWord(passwordEncoder.encode(adminDTO.getPassWord()));
               adminService.save(adminDTO);
               System.out.println("Register success!");
               model.addAttribute("registerSuccess", "Register successfully!");
               model.addAttribute("adminDTO",adminDTO );
           }else{
               model.addAttribute("adminDTO", adminDTO);
               model.addAttribute("passwordError","Password is not same!");
               System.out.println("Password is not same!");
               return "register";

           }

       }
       catch (Exception e){
           e.getStackTrace();
           model.addAttribute("registerError", "Register error!");

       }
        return "register";
    }


    @RequestMapping("/index")
    public String home(Model model){
        model.addAttribute("title","Home");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());
        if(authentication == null | authentication instanceof AnonymousAuthenticationToken){
            return "redirect:/login";
        }
        return "index";
    }
}
