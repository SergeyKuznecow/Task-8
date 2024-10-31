package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.dto.UserDTO;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDTOServiceImpl implements UserDTOService {
    private final RoleService roleService;

    public UserDTOServiceImpl(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setAge(userDTO.getAge());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        Set<Role> roles = new HashSet<>();
        for(Integer roleId : userDTO.getRoles()){
            Role role = roleService.getRole(roleId);
            roles.add(role);
        }
        user.setRoles(roles);
        return user;
    }
}
