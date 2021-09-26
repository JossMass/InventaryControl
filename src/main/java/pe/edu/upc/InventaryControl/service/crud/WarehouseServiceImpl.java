package pe.edu.upc.InventaryControl.service.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.upc.InventaryControl.model.entity.Warehouse;
import pe.edu.upc.InventaryControl.model.repository.WarehouseRepository;

@Service
public class WarehouseServiceImpl implements WarehouseService {
	
	@Autowired
	private WarehouseRepository warehouseRepository;
	
	@Override
	public JpaRepository<Warehouse, Long> getRepository() {
		return warehouseRepository;
	}

	@Override
	public List<Warehouse> findByKeyword(String keyword){
		return warehouseRepository.findByKeyword(keyword);
	}

	@Override
	public List<Warehouse> findBySpaces(Integer spaces){
		return warehouseRepository.findBySpaces(spaces);
	}
	
}
