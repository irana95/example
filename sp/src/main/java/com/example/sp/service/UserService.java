package com.example.sp.service;


import com.example.sp.dao.UserRepository;
import com.example.sp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository repository;

    public List<User> getUsers(){
        List<User> users=repository.findAll();
        return users;
    }

    public User getUserById(int id){
        Optional<User> users =repository.findById(id);

        return users.get();
    }

    public User checkLogin (String username, String password){
        User user;
        List<User> users = repository.findAllUserByUsernameAndPassword(username,password);
        if(users.size()==0) {
            user=null;
        } else{
            user=users.get(0)  ;
            System.out.println(user.getUsername()+" "+user.getPassword());
        }

        return user;
    }


    public void save(User user) {
        user.setPassword(user.getPassword());
//        user.setRoles(new HashSet<>(roleRepositoryy.findAll()));
        repository.save(user);
    }

    public Optional< User> findByUsername(String username) {
        Optional< User> user=repository.findUserByUsername(username);
//        user.get().getRoles().size();
        return user;
    }
//
//    public User getUserByUsername (String username){
//        User user;
//        Set<Authorities> roles=new HashSet<>();
//        List<Authorities> roleList=roleRepositoryy.findAll();
//        for(Authorities rol:roleList){
//            roles.add(rol);
//        }
//        Optional<User> users = repository.findUserByUsername(username);
//        user=users.get();
//        user.setAuthorities(roles);
//        user.getAuthorities().size();
//        return user;
//    }
//
//    public void save(User user) {
//        repository.save(user);
//    }


}
