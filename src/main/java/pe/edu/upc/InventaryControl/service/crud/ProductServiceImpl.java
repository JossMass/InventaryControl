package pe.edu.upc.InventaryControl.service.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.upc.InventaryControl.model.entity.Product;
import pe.edu.upc.InventaryControl.model.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public JpaRepository<Product, Long> getRepository() {
		return productRepository;
	}

	@Override
	public List<Product> findByKeyword(String keyword) throws Exception {
		return productRepository.findByKeyword(keyword);
	}

}
