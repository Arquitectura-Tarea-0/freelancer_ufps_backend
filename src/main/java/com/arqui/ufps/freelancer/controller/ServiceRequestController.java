package com.arqui.ufps.freelancer.controller;

import static com.arqui.ufps.freelancer.utils.Defines.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.arqui.ufps.freelancer.model.entities.Category;
import com.arqui.ufps.freelancer.model.entities.ServiceRequest;
import com.arqui.ufps.freelancer.model.entities.User;
import com.arqui.ufps.freelancer.repository.dao.ICategoryDao;
import com.arqui.ufps.freelancer.repository.dao.IServiceRequestDao;
import com.arqui.ufps.freelancer.repository.dao.IUserDao;
import com.arqui.ufps.freelancer.utils.GenericResponse;


@Secured("ROLE_CONTRATANTE")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PATCH,RequestMethod.PUT})
@RequestMapping("/request")
public class ServiceRequestController {

	@Autowired
	private IServiceRequestDao serviceRequest;
	
	private final String states [] = {"ACTIVE", "PENDING", "PAUSED"};
	
	@Autowired
    private IUserDao user;

    @Autowired
    private ICategoryDao category;
    
    
    @GetMapping()
    public List<ServiceRequest> getServiceRequestAll(){
        return serviceRequest.findAll();
    }    
   
    @GetMapping("/contractor/{id_contratante}")
    public ResponseEntity<Object> getServiceRequestStudent(@PathVariable String id_contratante){
        User userFound = user.findById(Integer.parseInt(id_contratante));
        if(userFound != null){
        	System.out.println("email"+userFound.getEmail());
            return new ResponseEntity<>(serviceRequest.findByUser(userFound.getIdUser()), HttpStatus.OK);
        	
        }
        System.out.println("hasta aca bien");
        return new ResponseEntity<>(new GenericResponse(FAILED.getSecond(), USER_NOT_FOUND.getSecond(), USER_NOT_FOUND.getFirst()), HttpStatus.OK);
    }
    
    private boolean validateData(ServiceRequest request){
        return request.getUser() != null && request.getTermsAndConditions() != null && request.getCategory() != null &&
                request.getPrice() != null && request.getTermsService() != null && request.getDuration()>0;
    }
    
    private Category findCategory(ServiceRequest request){
        if(request.getCategory().getId() == 0){
            return category.findByName(request.getCategory().getName());
        }

        return category.findById(request.getCategory().getId());
    }    
   
    @PostMapping("/contractor/")
    public GenericResponse createServiceOffer(@RequestBody ServiceRequest request){

        if(validateData(request)){
            User userFound = user.findByEmail(request.getUser().getEmail());
            Category categoryFound = findCategory(request);

            if(userFound == null){
                return new GenericResponse(FAILED.getSecond(), USER_NOT_FOUND.getSecond(), USER_NOT_FOUND.getFirst());
            }

            if(categoryFound == null){
                return new GenericResponse(FAILED.getSecond(), CATEGORY_NOT_FOUND.getSecond(), CATEGORY_NOT_FOUND.getFirst());
            }

            request.setState(states[0]);
            request.setUser(userFound);
            request.setCategory(categoryFound);
            serviceRequest.save(request);
            return new GenericResponse(SUCCESS.getSecond(), SUCCESS.getFirst());
        }

        return new GenericResponse(FAILED.getSecond(), MISSING_DATA.getSecond(), MISSING_DATA.getFirst());
    }
    
    
    @PatchMapping()
    public GenericResponse updateServiceOffer(@RequestBody ServiceRequest request){
    	ServiceRequest requestFound = serviceRequest.findById(request.getId());

        if(requestFound == null){
            return new GenericResponse(FAILED.getSecond(), SERVICE_OFFER_NOT_FOUND.getSecond(), SERVICE_OFFER_NOT_FOUND.getFirst());
        }

        if(request.getUser() != null){
            User userFound = user.findByEmail(request.getUser().getEmail());

            if(userFound == null){
                return new GenericResponse(FAILED.getSecond(), USER_NOT_FOUND.getSecond(), USER_NOT_FOUND.getFirst());
            }

            requestFound.setUser(userFound);
        }

        if(request.getCategory() != null){
            Category categoryFound = findCategory(request);

            if(categoryFound == null){
                return new GenericResponse(FAILED.getSecond(), CATEGORY_NOT_FOUND.getSecond(), CATEGORY_NOT_FOUND.getFirst());
            }

            requestFound.setCategory(categoryFound);
        }

        if(request.getTermsAndConditions() != null){
            requestFound.setTermsAndConditions(request.getTermsAndConditions());
        }

        if(request.getPrice() != null){
            requestFound.setPrice(request.getPrice());
        }

        if(request.getState() != null){
            requestFound.setState(request.getState());
        }

        if(request.getTermsService() != null){
            requestFound.setTermsService(request.getTermsService());
        }

        serviceRequest.save(requestFound);
        return new GenericResponse(SUCCESS.getSecond(), SUCCESS.getFirst());
    }
    
    
}
