package com.baar.spring_security_jwt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles_tbl")
public class Role {
    @Id
    @Column(name = "roleId", nullable = false)
    private Integer roleId;
    private String roleName;
    private Integer userId;
}
