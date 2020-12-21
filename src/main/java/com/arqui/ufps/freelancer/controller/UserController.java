package com.arqui.ufps.freelancer.controller;

import com.arqui.ufps.freelancer.model.entities.*;
import com.arqui.ufps.freelancer.repository.dao.*;
import com.arqui.ufps.freelancer.utils.Defines;
import com.arqui.ufps.freelancer.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.arqui.ufps.freelancer.utils.Defines.*;
import static com.arqui.ufps.freelancer.utils.Defines.SUCCESS;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.PUT})
public class UserController {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private ISkillDao skillDao;

    @Autowired
    private IEducationDao educationDao;

    @Autowired
    private ICurriculumVitaeDao curriculumVitaeDao;

    @Autowired
    private ILanguageDao languageDao;

    @Autowired
    private ICertificateDao certificateDao;

    @Autowired
    private IStudentSkillDao studentSkillDao;

    @Autowired
    private IStudentEducationDao studentEducationDao;


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

    public List<Language> validateLanguages(List<Language> languages, int idCurriculum){
        for(int i = 0; i < languages.size(); i++){
            if(languages.get(i).getName() == null || languages.get(i).getLevel() == null){
                return null;
            }
            languages.get(i).setCurriculumVitae(curriculumVitaeDao.findById(idCurriculum));
            languages.get(i).setLevel(languages.get(i).getLevel().toUpperCase());
        }
        return languages;
    }

    public List<Certificate> validateCertificates(List<Certificate> certificates, int idCurriculum){
        for(int i = 0; i < certificates.size(); i++){
            if(certificates.get(i).getName() == null || certificates.get(i).getIssueDate() == null
                    || certificates.get(i).getExpirationDate() == null){
                return null;
            }
            certificates.get(i).setCurriculumVitae(curriculumVitaeDao.findById(idCurriculum));
            certificates.get(i).setFile("");
        }
        return certificates;
    }

    public boolean validateSkill(StudentSkill studentSkill){
        return studentSkill.getLevel() != null && studentSkill.getSkill() != null;
    }

    public List<StudentSkill> addSkills(List<StudentSkill> studentSkills, int idCurriculum){
        Skill skill;
        String nameSkill;
        for(int i = 0; i < studentSkills.size(); i++){
            if(!validateSkill(studentSkills.get(i))){
                return null;
            }

            nameSkill = studentSkills.get(i).getSkill().getName().toUpperCase();
            skill = skillDao.findByName(nameSkill);
            if(skill == null){
                skill = new Skill();
                skill.setName(nameSkill);
                skill = skillDao.save(skill);
            }
            studentSkills.get(i).setSkill(skill);
            studentSkills.get(i).setCurriculumVitae(curriculumVitaeDao.findById(idCurriculum));
            studentSkills.get(i).setLevel(studentSkills.get(i).getLevel().toUpperCase());
        }
        return studentSkills;
    }

    public boolean validateEducation(StudentEducation studentEducation){
        return studentEducation.getInstitutionType() != null && studentEducation.getMajor() != null &&
                studentEducation.getGraduationYear() != null && studentEducation.getEducation() != null;
    }

    public List<StudentEducation> addEducations(List<StudentEducation> studentEducations, int idCurriculum){
        Education education;
        String nameEducation;
        for(int i = 0; i < studentEducations.size(); i++){
            if(!validateEducation(studentEducations.get(i))){
                return null;
            }

            nameEducation = studentEducations.get(i).getEducation().getInstitutionName().toUpperCase();
            education = educationDao.findByName(nameEducation);
            if(education == null){
                education = new Education();
                education.setInstitutionName(nameEducation);
                education = educationDao.save(education);
            }
            studentEducations.get(i).setEducation(education);
            studentEducations.get(i).setCurriculumVitae(curriculumVitaeDao.findById(idCurriculum));
            studentEducations.get(i).setInstitutionType(studentEducations.get(i).getInstitutionType().toUpperCase());
        }
        return studentEducations;
    }

    @PatchMapping("/{emailStudent}")
    public GenericResponse setUser(@PathVariable String emailStudent, @RequestBody User user) {
        User userFound = userDao.findByEmail(emailStudent);

        if (userFound == null) {
            return new GenericResponse(FAILED.getSecond(), USER_NOT_FOUND.getSecond(), USER_NOT_FOUND.getFirst());
        }

        if(!userFound.getRole().equalsIgnoreCase("ESTUDIANTE")){
            return new GenericResponse(FAILED.getSecond(), CONTRACTOR_FOUND.getSecond(), CONTRACTOR_FOUND.getFirst());
        }

        if (user.getPhone() != null) {
            userFound.setPhone(user.getPhone());
        }

        if (user.getEmail() != null) {
            userFound.setEmail(user.getEmail());
        }

        userDao.save(userFound);

        if(user.getCurriculumVitaes() != null && !user.getCurriculumVitaes().isEmpty()){
            CurriculumVitae curriculum = user.getCurriculumVitaes().get(0);

            if(userFound.getCurriculumVitaes().isEmpty()){
                userFound.getCurriculumVitaes().add(new CurriculumVitae());
                userFound.getCurriculumVitaes().get(0).setUser(userFound);
            }

            if(curriculum.getFirstName() != null){
                userFound.getCurriculumVitaes().get(0).setFirstName(curriculum.getFirstName());
            }

            if(curriculum.getLastName() != null){
                userFound.getCurriculumVitaes().get(0).setLastName(curriculum.getLastName());
            }

            if(curriculum.getDescription() != null){
                userFound.getCurriculumVitaes().get(0).setDescription(curriculum.getDescription());
            }

            CurriculumVitae c = curriculumVitaeDao.save(userFound.getCurriculumVitaes().get(0));

            if(curriculum.getLanguages() != null && !curriculum.getLanguages().isEmpty()){
                List<Language> languages = curriculum.getLanguages();
                if(validateLanguages(languages, c.getId()) == null){
                    return new GenericResponse(FAILED.getSecond(), MISSING_DATA.getSecond(), MISSING_DATA.getFirst());
                }

                languageDao.saveAll(languages);
            }

            if(curriculum.getCertificates() != null && !curriculum.getCertificates().isEmpty()){
                List<Certificate> certificates = curriculum.getCertificates();
                if(validateCertificates(certificates, c.getId()) == null){
                    return new GenericResponse(FAILED.getSecond(), MISSING_DATA.getSecond(), MISSING_DATA.getFirst());
                }

                certificateDao.saveAll(certificates);
            }

            if(curriculum.getStudentSkills() != null && !curriculum.getStudentSkills().isEmpty()){
                List<StudentSkill> studentSkills = addSkills(curriculum.getStudentSkills(), c.getId());
                if(studentSkills == null){
                    return new GenericResponse(FAILED.getSecond(), MISSING_DATA.getSecond(), MISSING_DATA.getFirst());
                }

                studentSkillDao.saveAll(studentSkills);
            }

            if(curriculum.getStudentEducations() != null && !curriculum.getStudentEducations().isEmpty()){
                List<StudentEducation> studentEducations = addEducations(curriculum.getStudentEducations(), c.getId());
                if(studentEducations == null){
                    return new GenericResponse(FAILED.getSecond(), MISSING_DATA.getSecond(), MISSING_DATA.getFirst());
                }

                studentEducationDao.saveAll(studentEducations);
            }
        }

        return new GenericResponse(SUCCESS.getSecond(), SUCCESS.getFirst());
    }


}

