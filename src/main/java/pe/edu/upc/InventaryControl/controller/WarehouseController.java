package pe.edu.upc.InventaryControl.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.edu.upc.InventaryControl.model.entity.Warehouse;
import pe.edu.upc.InventaryControl.service.crud.WarehouseService;

@Controller
@RequestMapping("/warehouses")
@SessionAttributes("warehouseEdit")
public class WarehouseController {
	
	@Autowired
	private WarehouseService warehouseService;
	
	@GetMapping
	public String list(Model model, String keyword) {
		try {
            List<Warehouse> warehouses = warehouseService.getAll();
            if (keyword != null) {
                model.addAttribute("warehouses", warehouseService.findByKeyword(keyword));
            }
            else{
                model.addAttribute("warehouses", warehouses);
            } 
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return "warehouses/listWarehouses";
	}
	
	@GetMapping("new")
	public String newItem(Model model) {
		try {
            Warehouse warehouse = new Warehouse();
            model.addAttribute("warehouseNew", warehouse);
            return "warehouses/newWarehouse";
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }

        return "redirect:/warehouses";
	}
	
	 @PostMapping("savenew")
	    public String saveNew(Model model, @ModelAttribute("warehouseNew") Warehouse warehouse) {
	        try {
	            Warehouse warehouseReturn = warehouseService.create(warehouse);
	            model.addAttribute("warehouse", warehouseReturn);
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.err.println(e.getMessage());
	        }
	        return "redirect:/warehouses";
	 }
	 
	 @GetMapping("{id}/editWarehouse") 
	    public String findByID(Model model, @PathVariable("id") Long id) {
	        try {
	            Optional<Warehouse> optional = warehouseService.findById(id);
	            if (optional.isPresent()) {
	                model.addAttribute("warehouseEdit", optional.get());
	                return "warehouses/editWarehouse";
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.err.println(e.getMessage());
	        }
	        return "redirect:/warehouses";
	    }
	 
	 @PostMapping("save")
	    public String saveEdit(Model model, @ModelAttribute("warehouseEdit") Warehouse warehouse) {
	        try {
	            Warehouse warehouseReturn = warehouseService.update(warehouse);
	            model.addAttribute("warehouse", warehouseReturn);
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.err.println(e.getMessage());
	        }
	        return "redirect:/warehouses";
	    }
	 
	 @GetMapping("{id}/deleteWarehouse")
	    public String deleteWarehouse(@PathVariable("id") Long id) {
	        try {
	            Optional<Warehouse> optional = warehouseService.findById(id);
	            if (optional.isPresent()) {
	                warehouseService.deleteById(id);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.err.println(e.getMessage());
	        }
	        return "redirect:/warehouses";
	    }
	
}
