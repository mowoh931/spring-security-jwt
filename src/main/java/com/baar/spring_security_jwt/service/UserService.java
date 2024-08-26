package com.baar.spring_security_jwt.service;


import com.baar.spring_security_jwt.model.User;

import java.util.List;

public interface UserService  {
    public User saveUser(User user);

    public User getUser( String userName);
    public List<User> getUsers();
    public User getUserDetails( String userName);
}
