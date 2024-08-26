package com.baar.spring_security_jwt.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@Table(name = "users_tbl")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @Column(name = "userId", nullable = false)
    private Integer userId;
    private String userName;
    private String password;
    @Column(name = "roles", nullable = true)
    @Transient
    private List<Role> roles;

    public User(Integer userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }


}