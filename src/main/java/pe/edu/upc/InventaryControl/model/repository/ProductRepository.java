package pe.edu.upc.InventaryControl.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.InventaryControl.model.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	 @Query(value = "SELECT * FROM products WHERE name ILIKE %:keyword% OR warehouse_name ILIKE %:keyword%", nativeQuery = true)
	List<Product> findByKeyword(@Param("keyword") String keyword);
}
