package pe.edu.upc.InventaryControl.service.crud;

import java.util.List;

import pe.edu.upc.InventaryControl.model.entity.Warehouse;

public interface WarehouseService extends CrudService<Warehouse, Long>{
	public List<Warehouse> findByKeyword(String keyword) throws Exception;
    public List<Warehouse> findBySpaces(Integer spaces) throws Exception;
}
