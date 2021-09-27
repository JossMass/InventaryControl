package pe.edu.upc.InventaryControl.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.InventaryControl.model.entity.Product;
import pe.edu.upc.InventaryControl.model.entity.Warehouse;
import pe.edu.upc.InventaryControl.service.crud.ProductService;
import pe.edu.upc.InventaryControl.service.crud.WarehouseService;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	Warehouse warehouse;
	
	@Autowired
	private WarehouseService warehouseService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public String listWarehouses(Model model, String keyword, Warehouse warehouseSpace) {
		try {
			if(keyword != null) {
				model.addAttribute("warehouses", warehouseService.findByKeyword(keyword));
			}else {
				model.addAttribute("warehouses", warehouseService.getAll());
			}
		}catch (Exception e) {
			e.printStackTrace();
	        System.err.println(e.getMessage());
		}
		return "products/listAllWarehouses";
	}
	
	@GetMapping("new/{id}")
	public String newProduct(Model model, @PathVariable("id") Long id) {
		try {
			Optional<Warehouse> optional = warehouseService.findById(id);
			if(optional.isPresent()) {
				Product product = new Product();
				warehouse = optional.get();
				model.addAttribute("productNew", product);
				return "products/newProduct";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/products";
	}
	
	@PostMapping("savenew")
	public String saveNew(Model model, @ModelAttribute("productNew") Product product) {
		try {
			product.setWarehouse(warehouse);
			product.setWarehouseName(warehouse.getName());
			Product productReturn = productService.create(product);
			model.addAttribute("product", productReturn);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		return "redirect:/seeProducts";
	}
	
}
