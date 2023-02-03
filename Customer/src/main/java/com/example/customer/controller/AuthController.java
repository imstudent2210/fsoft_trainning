package com.example.customer.controller;

import com.example.library.dto.CustomerDTO;
import com.example.library.model.Customer;
import com.example.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
//import org.springframework.ui.Model;

@Controller
public class AuthController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model){
        model.addAttribute("title","Login");
        return "login";
    }


    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("customerDto", new CustomerDTO());
        return "register";
    }


    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute("customerDto") CustomerDTO customerDto,
                                  BindingResult result,
                                  Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("customerDto", customerDto);
                return "register";
            }
            Customer customer = customerService.findByUsername(customerDto.getUsername());
            if(customer != null){
                model.addAttribute("username", "Username have been registered");
                model.addAttribute("customerDto",customerDto);
                return "register";
            }
            if(customerDto.getPassword().equals(customerDto.getRepeatPassword())){
                customerDto.setPassword(passwordEncoder.encode(customerDto.getPassword()));
                customerService.save(customerDto);
                model.addAttribute("success", "Register successfully");
                return "register";
            }else{
                model.addAttribute("password", "Password is not same");
                model.addAttribute("customerDto",customerDto);
                return "register";
            }
        }catch (Exception e){
            model.addAttribute("error", "Server have ran some problems");
            model.addAttribute("customerDto",customerDto);
        }
        return "register";
    }
}
