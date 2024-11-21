package org.example.food_api.services;

import org.example.food_api.models.User;
import org.example.food_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<List<User>> listAllUser(){
        List<User> listUser= userRepository.findAll();
        if(listUser.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(listUser, HttpStatus.OK);
    }

    public User findUser(@PathVariable("id") long id) {
        User contact= userRepository.findById(id);
        if(contact == null) {
            ResponseEntity.notFound().build();
        }
        return contact;
    }

    public User findUserById(@RequestParam("id") long id) {
        User contact= userRepository.findById(id);
        if(contact == null) {
            ResponseEntity.notFound().build();
        }
        return contact;
    }
    public ResponseEntity<User> findUserLogin(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        System.out.println(username);
        System.out.println(password);
        if (username == null || username.isEmpty() || password == null || password.isEmpty()){
            String message = "username and password are required";
            System.out.println(message);
            return null ;
        }
        User user = userRepository.findByUsername(username);
        if (user == null){
            String message = "username not found!";
            System.out.println(message);
            return null;
        }
        if (user.getPassword().equals(password)){
            System.out.println("");
            if(user.getRoleId()==User.ROLE_ADMIN){
                System.out.println("Hello ADMIN");
            }
            else{
                System.out.println("Hello customer");
            }
        }else{
            System.out.println("password does not match");
            return new ResponseEntity<User>(user, HttpStatus.UNAUTHORIZED);
        }
        System.out.println(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);

    }
}
