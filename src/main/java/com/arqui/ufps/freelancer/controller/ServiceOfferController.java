package com.arqui.ufps.freelancer.controller;

import com.arqui.ufps.freelancer.model.entities.Category;
import com.arqui.ufps.freelancer.model.entities.ServiceOffer;
import com.arqui.ufps.freelancer.model.entities.User;
import com.arqui.ufps.freelancer.repository.dao.ICategoryDao;
import com.arqui.ufps.freelancer.repository.dao.IServiceOfferDao;
import com.arqui.ufps.freelancer.repository.dao.IUserDao;
import com.arqui.ufps.freelancer.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.arqui.ufps.freelancer.utils.Defines.*;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PATCH,RequestMethod.PUT})
@RequestMapping("/offer")
public class ServiceOfferController {

    @Autowired
    private IServiceOfferDao serviceOffer;

    private final String states [] = {"ACTIVE", "PENDING", "PAUSED"};

    @Autowired
    private IUserDao user;

    @Autowired
    private ICategoryDao category;

    @GetMapping()
    public List<ServiceOffer> getServiceOfferAll(){
        return serviceOffer.findAll();
    }

    @GetMapping("/student/{emailStudent}")
    public ResponseEntity<Object> getServiceOfferStudent(@PathVariable String emailStudent){
        User userFound = user.findByEmail(emailStudent);
        if(userFound != null){
            return new ResponseEntity<>(serviceOffer.findByUser(userFound.getIdUser()), HttpStatus.OK);
        }

        return new ResponseEntity<>(new GenericResponse(FAILED.getSecond(), USER_NOT_FOUND.getSecond(), USER_NOT_FOUND.getFirst()), HttpStatus.OK);
    }

    private boolean validateData(ServiceOffer offer){
        return offer.getUser() != null && offer.getTitle() != null && offer.getTermsAndConditions() != null && offer.getCategory() != null &&
                offer.getPrice() != null && offer.getTermsService() != null;
    }

    private Category findCategory(ServiceOffer offer){
        if(offer.getCategory().getId() == 0){
            return category.findByName(offer.getCategory().getName());
        }

        return category.findById(offer.getCategory().getId());
    }

    @PostMapping("/student/")
    public GenericResponse createServiceOffer(@RequestBody ServiceOffer offer){

        if(validateData(offer)){
            User userFound = user.findByEmail(offer.getUser().getEmail());
            Category categoryFound = findCategory(offer);

            if(userFound == null){
                return new GenericResponse(FAILED.getSecond(), USER_NOT_FOUND.getSecond(), USER_NOT_FOUND.getFirst());
            }

            if(categoryFound == null){
                return new GenericResponse(FAILED.getSecond(), CATEGORY_NOT_FOUND.getSecond(), CATEGORY_NOT_FOUND.getFirst());
            }

            offer.setState(states[0]);
            offer.setUser(userFound);
            offer.setCategory(categoryFound);
            serviceOffer.save(offer);
            return new GenericResponse(SUCCESS.getSecond(), SUCCESS.getFirst());
        }

        return new GenericResponse(FAILED.getSecond(), MISSING_DATA.getSecond(), MISSING_DATA.getFirst());
    }

    @PatchMapping()
    public GenericResponse updateServiceOffer(@RequestBody ServiceOffer offer){
        ServiceOffer offerFound = serviceOffer.findById(offer.getId());

        if(offerFound == null){
            return new GenericResponse(FAILED.getSecond(), SERVICE_OFFER_NOT_FOUND.getSecond(), SERVICE_OFFER_NOT_FOUND.getFirst());
        }

        if(offer.getUser() != null){
            User userFound = user.findByEmail(offer.getUser().getEmail());

            if(userFound == null){
                return new GenericResponse(FAILED.getSecond(), USER_NOT_FOUND.getSecond(), USER_NOT_FOUND.getFirst());
            }

            offerFound.setUser(userFound);
        }

        if(offer.getCategory() != null){
            Category categoryFound = findCategory(offer);

            if(categoryFound == null){
                return new GenericResponse(FAILED.getSecond(), CATEGORY_NOT_FOUND.getSecond(), CATEGORY_NOT_FOUND.getFirst());
            }

            offerFound.setCategory(categoryFound);
        }

        if(offer.getTitle() != null){
            offerFound.setTitle(offer.getTitle());
        }

        if(offer.getTermsAndConditions() != null){
            offerFound.setTermsAndConditions(offer.getTermsAndConditions());
        }

        if(offer.getPrice() != null){
            offerFound.setPrice(offer.getPrice());
        }

        if(offer.getState() != null){
            offerFound.setState(offer.getState());
        }

        if(offer.getTermsService() != null){
            offerFound.setTermsService(offer.getTermsService());
        }

        serviceOffer.save(offerFound);
        return new GenericResponse(SUCCESS.getSecond(), SUCCESS.getFirst());
    }

}
