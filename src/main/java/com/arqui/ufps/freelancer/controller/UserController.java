package com.arqui.ufps.freelancer.controller;

import com.arqui.ufps.freelancer.model.entities.User;
import com.arqui.ufps.freelancer.repository.dao.IUserDao;
import com.arqui.ufps.freelancer.utils.Defines;
import com.arqui.ufps.freelancer.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.PUT})
public class UserController {

    @Autowired
    private IUserDao userDao;


    @GetMapping("/user")
    public List<User> list() {
        return userDao.findAll();
    }

    @GetMapping("user/{email}")
    public ResponseEntity<Object> getUser(@PathVariable String email) {
        User user = userDao.findByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.ok(new GenericResponse(Defines.FAILED.getSecond(), Defines.FAILED.getFirst()));
        }
    }


}

