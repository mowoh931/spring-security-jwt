package com.baar.spring_security_jwt.repository;


import com.baar.spring_security_jwt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findByUserId(Integer userId);
}
