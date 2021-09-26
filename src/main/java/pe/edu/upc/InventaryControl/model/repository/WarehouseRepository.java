package pe.edu.upc.InventaryControl.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.InventaryControl.model.entity.Warehouse;


@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long>{
	
	@Query(value = "SELECT * FROM warehouses WHERE name ILIKE %:keyword% OR district ILIKE %:keyword%", nativeQuery = true)
    List<Warehouse> findByKeyword(@Param("keyword") String keyword);

    @Query(value = "SELECT * FROM warehouses WHERE spaces_available > 0", nativeQuery = true)
    List<Warehouse> findBySpaces(Integer spaces);
}
