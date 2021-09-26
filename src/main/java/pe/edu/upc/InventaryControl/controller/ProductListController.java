package pe.edu.upc.InventaryControl.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.edu.upc.InventaryControl.model.entity.Product;
import pe.edu.upc.InventaryControl.service.crud.ProductService;

@Controller
@RequestMapping("/seeProducts")
@SessionAttributes("editProduct")
public class ProductListController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public String listProductAdmin(Model model, String keyword) {
		try {
			List<Product> products = productService.getAll();
			if(keyword != null) {
				model.addAttribute("products", productService.findByKeyword(keyword));
			}
			else {
				model.addAttribute("products", products);
			}
		} catch(Exception e) {
			e.printStackTrace();
            System.err.println(e.getMessage());
		}
		
		return "/seeProducts/listProducts";
	}
	
	 @GetMapping("{id}/editProduct") 
	    public String findByID(Model model, @PathVariable("id") Long id) {
	        try {
	            Optional<Product> optional = productService.findById(id);
	            if (optional.isPresent()) {
	                model.addAttribute("editProduct", optional.get());
	                return "seeProducts/editProduct";
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.err.println(e.getMessage());
	        }
	        return "redirect:/seeProducts";
	    }
	
	@GetMapping("{id}/deleteProduct")
	public String deleteProduct(@PathVariable("id") Long id) {
		try {
			Optional<Product> optional = productService.findById(id);
			if(optional.isPresent()) {
				productService.deleteById(id);
			} 
		} catch (Exception e) {
			e.printStackTrace();
            System.err.println(e.getMessage());
		}
		
		return "redirect:/seeProducts";
	}
	
}
