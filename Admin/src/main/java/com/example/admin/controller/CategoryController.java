package com.example.admin.controller;

import antlr.StringUtils;
import com.example.library.model.Category;
import com.example.library.service.CategoryService;
import jdk.jfr.Registered;
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
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/category")
    public String category(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("category", categoryList);
        model.addAttribute("size", categoryList.size());
        model.addAttribute("title","Category");
        model.addAttribute("categoryNew",new Category());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());
        return "category";
    }

    @PostMapping("/category/new")
    public String addNewCatogory(@ModelAttribute("categoryNew") Category category, RedirectAttributes redirectAttributes){
        try{
            categoryService.save(category);
           redirectAttributes.addFlashAttribute("success", "Add category successfully!");
        }
        catch (DataIntegrityViolationException e){
            e.getStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Duplicate name");
        }
        catch (Exception e) {
            e.getStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Add category failed");

        }
        return "redirect:/category";
    }

    @RequestMapping(value = "/findCategory", method = {RequestMethod.PUT, RequestMethod.GET})
    @ResponseBody
    public Category findCategoryById(long id){
        return categoryService.findById(id);
    }

    @RequestMapping(value = "/category/delete", method = {RequestMethod.PUT, RequestMethod.GET})
    public String delete(Long id, RedirectAttributes redirectAttributes){
        try {
            categoryService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Deleted successfully");
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Failed to deleted");
        }
        return "redirect:/category";
    }

    @RequestMapping(value = "/category/enable", method = {RequestMethod.PUT, RequestMethod.GET})
    public String enable(Long id, RedirectAttributes redirectAttributes){
        try {
            categoryService.enabledById(id);
            redirectAttributes.addFlashAttribute("success", "Enabled successfully");
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Failed to enabled");
        }
        return "redirect:/category";
    }


    @GetMapping("/category/update")
    public String update(Category category, RedirectAttributes redirectAttributes){
        try {
            categoryService.update(category);
            redirectAttributes.addFlashAttribute("success","Updated successfully");
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Failed to update because duplicate name");
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Updated fail");
        }
        return "redirect:/category";
    }

//    @GetMapping("/category/search")
//    public String searchCategory(Model model, @RequestParam(name = "name", required = false) String name){
//        List<Category> list = null;
//        if(StringUtils.stripBack(categoryName,"name")){
//            list  = categoryService.findByNameContaining(name);
//        }else{
//            list = categoryService.findAll();
//        }
//        return "/category/search";
//    }
}
