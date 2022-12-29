package com.example.admin.controller;

import com.example.library.model.Category;
import com.example.library.model.Manufacturer;
import com.example.library.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class ManufacturerController {
    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping("/manufacturer")
    public String category(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        List<Manufacturer> manufacturerList = manufacturerService.findAll();
        model.addAttribute("manufacturer", manufacturerList);
        model.addAttribute("size", manufacturerList.size());
        model.addAttribute("title","Manufacturer");
        model.addAttribute("manufacturerNew",new Manufacturer());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());
        return "manufacturer";
    }

    @PostMapping("/manufacturer/new")
    public String addNewManufacturer(@ModelAttribute("manufacturerNew") Manufacturer manufacturer, RedirectAttributes redirectAttributes){
        try{
            manufacturerService.save(manufacturer);
            redirectAttributes.addFlashAttribute("success", "Add manufacturer successfully!");
        }
        catch (DataIntegrityViolationException e){
            e.getStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Duplicate name");
        }
        catch (Exception e) {
            e.getStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Add manufacturer failed");

        }
        return "redirect:/manufacturer";
    }

    @RequestMapping(value = "/findManufacturer", method = {RequestMethod.PUT, RequestMethod.GET})
    @ResponseBody
    public Manufacturer findManufacturerById(long id){
        return manufacturerService.findById(id);
    }



    @RequestMapping(value = "/manufacturer/delete", method = {RequestMethod.PUT, RequestMethod.GET})
    public String delete(Long id, RedirectAttributes redirectAttributes){
        try {
            manufacturerService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Deleted successfully");
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Failed to deleted");
        }
        return "redirect:/manufacturer";
    }

    @RequestMapping(value = "/manufacturer/enable", method = {RequestMethod.PUT, RequestMethod.GET})
    public String enable(Long id, RedirectAttributes redirectAttributes){
        try {
            manufacturerService.enabledById(id);
            redirectAttributes.addFlashAttribute("success", "Enabled successfully");
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Failed to enabled");
        }
        return "redirect:/manufacturer";
    }


    @GetMapping("/manufacturer/update")
    public String update(Manufacturer manufacturer, RedirectAttributes redirectAttributes){
        try {
            manufacturerService.update(manufacturer);
            redirectAttributes.addFlashAttribute("success","Updated successfully");
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Failed to update because duplicate name");
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Updated fail");
        }
        return "redirect:/manufacturer";
    }

}
