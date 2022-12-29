package com.example.admin.controller;

import com.example.library.dto.ProductDTO;
import com.example.library.model.Category;
import com.example.library.model.Manufacturer;
import com.example.library.model.Product;
import com.example.library.service.CategoryService;
import com.example.library.service.ManufacturerService;
import com.example.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping("/product")
    public String product(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        List<ProductDTO> productDTOList = productService.findAll();
        model.addAttribute("products", productDTOList);
        model.addAttribute("size", productDTOList.size());
        model.addAttribute("title","Product");
        model.addAttribute("productNew",new Product());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());

        return "product/0";
    }

    @GetMapping("/add_product")
    public String addNewProduct(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        List<Category> categoryList = categoryService.findAllByActivated();
        List<Manufacturer> manufacturerList = manufacturerService.findAllByActivated();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());
        model.addAttribute("category", categoryList);
        model.addAttribute("manufacturer", manufacturerList);
        model.addAttribute("product", new ProductDTO());
        return "add_product";

    }

    @PostMapping("/save_product")
    public String saveProduct(@ModelAttribute ("product") ProductDTO productDTO,
                              @RequestParam("imageProduct")MultipartFile imageProduct,
                              RedirectAttributes redirectAttributes){

        try{
            productService.save(imageProduct,productDTO);
            redirectAttributes.addFlashAttribute("success", "Add successfully");

        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Add failed");
        }
        return "redirect:/product/0";
    }

    @GetMapping("/update_product/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        model.addAttribute("title", "Update product");
        List<Category> category = categoryService.findAllByActivated();
        List<Manufacturer> manufacturer = manufacturerService.findAllByActivated();
        ProductDTO productDto = productService.getById(id);
        model.addAttribute("category", category);
        model.addAttribute("manufacturer", manufacturer);
        model.addAttribute("productDto", productDto);
        return "update_product";
    }


    @PostMapping("/update_product/{id}")
    public String processUpdate(@PathVariable("id") Long id,
                                @ModelAttribute("productDto") ProductDTO productDto,
                                @RequestParam("imageProduct")MultipartFile imageProduct,
                                RedirectAttributes attributes
    ){
        try {
            productService.update(imageProduct, productDto);
            attributes.addFlashAttribute("success", "Update successfully!");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to update!");
        }
        return "redirect:/product/0";

    }

    @RequestMapping(value = "/product/enable/{id}", method = {RequestMethod.PUT , RequestMethod.GET})
    public String enabledProduct(@PathVariable("id")Long id, RedirectAttributes attributes){
        try {
            productService.enableById(id);
            attributes.addFlashAttribute("success", "Enabled successfully!");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to enabled!");
        }
        return "redirect:/product/0";
    }

    @RequestMapping(value = "/product/delete/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String deletedProduct(@PathVariable("id") Long id, RedirectAttributes attributes){
        try {
            productService.deleteById(id);
            attributes.addFlashAttribute("success", "Deleted successfully!");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to deleted");
        }
        return "redirect:/product/0";
    }



    @GetMapping("/product/{pageNo}")
    public String productsPage(@PathVariable("pageNo") int pageNo, Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        Page<ProductDTO> products = productService.pageProducts(pageNo);
        model.addAttribute("title", "Product");
        model.addAttribute("size", products.getSize());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", pageNo);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());
        model.addAttribute("products", products);
        return "product";
    }

    @GetMapping("/search/{pageNo}")
    public String searchProducts(@PathVariable("pageNo")int pageNo,
                                 @RequestParam("keyword") String keyword,
                                 Model model,
                                 Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        Page<ProductDTO> products = productService.searchProducts(pageNo, keyword);
        model.addAttribute("title", "Search");
        model.addAttribute("products", products);
        model.addAttribute("size", products.getSize());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", products.getTotalPages());
        return "search_product";
    }
}
