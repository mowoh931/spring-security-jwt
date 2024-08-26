package com.baar.spring_security_jwt.service;


import com.baar.spring_security_jwt.model.Role;

import java.util.List;

public interface RoleService {
    public Role saveRole(Role role);

    public List<Role> getAllRoles();
}
