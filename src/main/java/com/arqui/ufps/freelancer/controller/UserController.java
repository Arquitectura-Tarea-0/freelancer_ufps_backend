package com.arqui.ufps.freelancer.controller;

import com.arqui.ufps.freelancer.model.entities.CurriculumVitae;
import com.arqui.ufps.freelancer.model.entities.User;
import com.arqui.ufps.freelancer.repository.dao.IUserDao;
import com.arqui.ufps.freelancer.utils.Defines;
import com.arqui.ufps.freelancer.utils.GenericResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.PUT})
public class UserController {

    @Autowired
    private IUserDao userDao;


    @GetMapping
    public List<User> list() {
        return userDao.findAll();
    }

    @GetMapping("/{email}")
    public ResponseEntity<Object> getUser(@PathVariable String email) {
        User user = userDao.findByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.ok(new GenericResponse(Defines.FAILED.getSecond(), Defines.FAILED.getFirst()));
        }
    }

    @PatchMapping
    public ResponseEntity<Object> setUser(@RequestBody User user) {
        User us = userDao.findByEmail(user.getEmail());
        if (us == null) {
            return ResponseEntity.ok(new GenericResponse(Defines.FAILED.getSecond(), Defines.FAILED.getFirst()));
        } else {
            if (user.getPhone() != null) {
                us.setPhone(user.getPhone());
            }
            if (user.getEmail() != null) {
                us.setEmail(user.getEmail());
            }
            if (user.getCurriculumVitaes() != null && user.getCurriculumVitaes().get(0).getFirstName() != null) {
                if (us.getCurriculumVitaes() == null || us.getCurriculumVitaes().isEmpty()){
                    ArrayList<CurriculumVitae> a = new ArrayList<>();
                    a.add(new CurriculumVitae());
                    us.setCurriculumVitaes(a);
                }
                us.getCurriculumVitaes().get(0).setFirstName(user.getCurriculumVitaes().get(0).getFirstName());
            }
            if (user.getCurriculumVitaes() != null && user.getCurriculumVitaes().get(0).getLastName() != null) {
                us.getCurriculumVitaes().get(0).setLastName(user.getCurriculumVitaes().get(0).getLastName());
            }

            if (user.getCurriculumVitaes() != null && user.getCurriculumVitaes().get(0).getDescription() != null) {
                us.getCurriculumVitaes().get(0).setDescription(user.getCurriculumVitaes().get(0).getDescription());
            }
            if (user.getCurriculumVitaes() != null && user.getCurriculumVitaes().get(0).getStudentEducations() != null) {
                us.getCurriculumVitaes().get(0).setStudentEducations(user.getCurriculumVitaes().get(0).getStudentEducations());
            }
            if (user.getCurriculumVitaes() != null && user.getCurriculumVitaes().get(0).getLanguages() != null) {
                us.getCurriculumVitaes().get(0).setLanguages(user.getCurriculumVitaes().get(0).getLanguages());
            }
            if (user.getCurriculumVitaes() != null && user.getCurriculumVitaes().get(0).getStudentSkills() != null) {
                us.getCurriculumVitaes().get(0).setStudentSkills(user.getCurriculumVitaes().get(0).getStudentSkills());
            }
            if (user.getCurriculumVitaes() != null && user.getCurriculumVitaes().get(0).getCertificates() != null) {
                us.getCurriculumVitaes().get(0).setCertificates(user.getCurriculumVitaes().get(0).getCertificates());
            }
            userDao.save(us);
            return ResponseEntity.ok(new GenericResponse(Defines.SUCCESS.getSecond(), Defines.SUCCESS.getFirst()));
        }
    }


}

