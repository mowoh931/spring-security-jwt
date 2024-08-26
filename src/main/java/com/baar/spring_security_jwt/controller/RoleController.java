package com.baar.spring_security_jwt.controller;


import com.baar.spring_security_jwt.model.Role;
import com.baar.spring_security_jwt.service.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleServiceImpl roleService;

    @Autowired
    public RoleController(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hello Admin!";
    }

    @GetMapping("/user")
    public String user() {
        return "Hello User!";
    }

    @PostMapping(value = "/save", produces = "application/json", consumes = "application/json")
    public Role saveRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    @GetMapping(value = "/get/all", produces = "application/json")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }
}
