 package pe.edu.upc.InventaryControl.service.crud;

import java.util.List;

import pe.edu.upc.InventaryControl.model.entity.Product;

public interface ProductService extends CrudService<Product, Long>{
	public List<Product> findByKeyword(String keyword) throws Exception;
}
