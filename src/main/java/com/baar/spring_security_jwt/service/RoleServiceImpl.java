package com.baar.spring_security_jwt.service;

import com.baar.spring_security_jwt.model.Role;
import com.baar.spring_security_jwt.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
@Service
public class RoleServiceImpl implements RoleService {
    private final Logger logger = Logger.getLogger(RoleServiceImpl.class.getName());
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role saveRole(Role role) {
        Optional<Role> optionalRole = roleRepository.findById(role.getRoleId());
        if (optionalRole.isPresent()) {
            logger.info("Role already exists");
            throw new RuntimeException("Role already exists");
        } else {
            return roleRepository.save(role);
        }
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
