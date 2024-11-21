package org.example.food_api.controller;

import org.example.food_api.models.Product;
import org.example.food_api.models.User;
import org.example.food_api.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    public static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUser(){
        return userService.listAllUser();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findUser(@PathVariable("id") long id) {
        return userService.findUser(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public User findUserById(@RequestParam("id") long id) {
        return userService.findUser(id);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<User> findUserLogin(@RequestBody Map<String, String> request) {
        return userService.findUserLogin(request);
    }

}
