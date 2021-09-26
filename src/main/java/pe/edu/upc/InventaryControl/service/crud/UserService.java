package pe.edu.upc.InventaryControl.service.crud;

import org.springframework.security.core.userdetails.UserDetailsService;

import pe.edu.upc.InventaryControl.controller.dto.UserRegistrationDto;
import pe.edu.upc.InventaryControl.model.entity.User;

public interface UserService extends UserDetailsService {
	User save(UserRegistrationDto registartionDto);
}
