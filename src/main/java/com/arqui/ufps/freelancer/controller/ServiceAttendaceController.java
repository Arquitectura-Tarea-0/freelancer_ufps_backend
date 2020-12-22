package com.arqui.ufps.freelancer.controller;

import static com.arqui.ufps.freelancer.utils.Defines.ROLE_NOT_PERMITED;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arqui.ufps.freelancer.model.entities.File;
import com.arqui.ufps.freelancer.model.entities.ServiceAttendace;
import com.arqui.ufps.freelancer.model.entities.User;
import com.arqui.ufps.freelancer.repository.dao.IFileDao;
import com.arqui.ufps.freelancer.repository.dao.IServiceAttendaceDao;
import com.arqui.ufps.freelancer.repository.dao.IServiceRequestDao;
import com.arqui.ufps.freelancer.repository.dao.IUserDao;
import com.fasterxml.jackson.core.JsonProcessingException;




@RestController
@RequestMapping("/attendace")
public class ServiceAttendaceController {

	@Autowired
	IServiceAttendaceDao serviceAttendace;
	
	@Autowired
	IUserDao user;
	
	@Autowired
	IServiceRequestDao request;
	
	@Autowired
	IFileDao file;
	

	
	@Secured("ROLE_CONTRATANTE")
	@PatchMapping("/contractor/update/{id}")
	public Object updateServiceAttendaceContractor(@PathVariable(name="id") int id,@RequestParam(name="state") int state,
			@RequestParam(name="serviceDetails")String serviceDetails) {
		
		ServiceAttendace ser=serviceAttendace.findByIddolo(id);
		Map <String,Object> map=new HashMap<String,Object>();
		if(ser==null) {
			map.put("status", HttpStatus.OK);
			map.put("Mesagge", "No se encuentar el service Attendace");
			return map;
		}
		
		if(!ser.getServiceDetails().equalsIgnoreCase(serviceDetails)) {
			ser.setServiceDetails(serviceDetails);
		}
		
		if(!(ser.getState()==state)) {
			ser.setState((double)state);
		}
		 serviceAttendace.save(ser);
		 String s=ser.getServiceDetails();
		 s=s.replaceAll("\"", "");
		 ser.setServiceDetails(s);
		return ser;
	}
	
	@Secured("ROLE_ESTUDIANTE")
	@PatchMapping("/student/update/{id}")
	public Object updateServiceAttendaceStudent(@PathVariable(name="id") int id,@RequestParam(name="state") int state,
			@RequestParam(name="serviceDetails")String serviceDetails) {
		
		ServiceAttendace ser=serviceAttendace.findByIddolo(id);
		Map <String,Object> map=new HashMap<String,Object>();
		if(ser==null) {
			map.put("status", HttpStatus.OK);
			map.put("Mesagge", "No se encuentar el service Attendace");
			return map;
		}
		
		if(!ser.getServiceDetails().equalsIgnoreCase(serviceDetails)) {
			ser.setServiceDetails(serviceDetails);
		}
		
		if(!(ser.getState()==state)) {
			ser.setState((double)state);
		}
		 serviceAttendace.save(ser);
		 String s=ser.getServiceDetails();
		 s=s.replaceAll("\"", "");
		 ser.setServiceDetails(s);
		return ser;
	}
	
	public Object requestAccepted(int id,String rol) {
		
		User userFound=user.findUserById(id);
	if(userFound!=null && userFound.getRole().equalsIgnoreCase(rol)) {
			return new ResponseEntity <>(request.findByUser(userFound.getIdUser()),HttpStatus.OK);
		}
	Map<String,Object> response= new HashMap <String,Object>();
	response.put("Mensaje", ROLE_NOT_PERMITED.getSecond());
	response.put("status", HttpStatus.OK);
	 return response;
	 
		
	}
	// devuelve todos los services attendace para el del estudiante
	// sedbe enviar el id del etudiante medinate el la url
	@Secured("ROLE_ESTUDIANTE")
	@GetMapping("/student/getattendace/{id}")
	public List<ServiceAttendace> getAttendaceStudent(@PathVariable(name="id")int id){
		return getAttendaceByRol(id,"ROLE_ESTUDIANTE");
	}
	
	@Secured("ROLE_CONTRATANTE")
	@GetMapping("/contractor/getattendace/{id}")
	public List<ServiceAttendace> getAttendaceContractor(@PathVariable(name="id")int id){
		return getAttendaceByRol(id, "ROLE_CONTRATANTE");
	}
	
	@Secured("ROLE_CONTRATANTE")
	@GetMapping(value="/contractor/{id}")
	public Object requestAcceptedByContractor(@PathVariable(name="id") int id){
		return requestAccepted(id,"ROLE_CONTRATANTE");
	}
	
	
	public List<ServiceAttendace> getAttendaceByRol(int id,String rol){
		
		User usuario=user.findUserById(id);
		List<ServiceAttendace> service=new ArrayList<ServiceAttendace>();
		if(usuario==null || !usuario.getRole().equalsIgnoreCase(rol)) {
			return  service; }
		
		if(usuario.getRole().equalsIgnoreCase("ROLE_ESTUDIANTE")) {
			service= usuario.getServiceAttendacesStudent();
		}else if (usuario.getRole().equalsIgnoreCase("ROLE_CONTRATANTE")){
			service= usuario.getServiceAttendacesContractor();
		}
		
		for(int i=0;i<service.size();i++) {
			String s=service.get(i).getServiceDetails();
			s=s.replaceAll("\"","");
			service.get(i).setServiceDetails(s);
		}
		
		
	return service;
	}
	
	@Secured("ROLE_ESTUDIANTE")
	@GetMapping("/student/getfile/{idService}")
	public Object getFileOfServiceAttendace(@PathVariable(name="idService") int idService){
		ServiceAttendace service=serviceAttendace.findByIddolo(idService);
		Map<String,Object> map=new HashMap<String,Object>();
		if (service==null) {
			map.put("message","el Id del service no es valido");
			return map;
		}
		
		User student=service.getStudentId();
		if(student==null || !student.getRole().equalsIgnoreCase("ROLE_ESTUDIANTE") ) {
			return map.put("message","el Id del service no es valido o usted no es un estudiante");
		}
		
		return service.getFiles();
	}
	
	@Secured("ROLE_CONTRATANTE")
	@GetMapping("/contractor/getfile/{idService}")
	public Object getFileOfServiceAttendaceascontractor(@PathVariable(name="idService") int idService){
		ServiceAttendace service=serviceAttendace.findByIddolo(idService);
		Map<String,Object> map=new HashMap<String,Object>();
		if (service==null) {
			map.put("message","el Id del service no es valido");
			return map;
		}
		
		User student=service.getContractorId();
		if(student==null || !student.getRole().equalsIgnoreCase("ROLE_CONTRATANTE") ) {
			return map.put("message","el Id del service no es valido o usted no es un contratante");
		}
		
		return service.getFiles();
	}
	
	
	@Secured("ROLE_ESTUDIANTE")
	@PostMapping("/student/insertfile/{idService}")
	public Object insertFileByStudent(@PathVariable(name="idService") int idService,@RequestParam(name="url") String url) {
		
		ServiceAttendace service=serviceAttendace.findByIddolo(idService);
		
	Map<String,Object> map=new HashMap<String,Object>();
		if (service==null) {
			map.put("message","el Id del service no es valido");
			return map;
		}
		User student=service.getStudentId();
		if(student==null || !student.getRole().equalsIgnoreCase("ROLE_ESTUDIANTE") ) {
			return map.put("message","el Id del service no es valido o usted no es un estudiante");
		}
		
		File fi=new File();
		fi.setServiceAttendace(service);
		fi.setUrl(url);
		fi.setCreatedAt(new Date());
		file.save(fi);
		
		return fi;
	}
	
	
	@Secured("ROLE_CONTRATANTE")
	@PostMapping("/contractor/insertfile/{idService}")
	public Object insertFileByContractor(@PathVariable(name="idService") int idService,@RequestParam(name="url") String url) {
		
		ServiceAttendace service=serviceAttendace.findByIddolo(idService);
		
	Map<String,Object> map=new HashMap<String,Object>();
		if (service==null) {
			map.put("message","el Id del service no es valido");
			return map;
		}
		
		User student=service.getContractorId();
		if(student==null || !student.getRole().equalsIgnoreCase("ROLE_CONTRATANTE") ) {
			return map.put("message","el Id del service no es valido o usted no es un estudiante");
		}
		
		File fi=new File();
		fi.setServiceAttendace(service);
		fi.setUrl(url);
		fi.setCreatedAt(new Date());
		file.save(fi);
		
		return fi;
	}
	
	
	@Secured("ROLE_ESTUDIANTE")
	@GetMapping(value="/student/{id}")
	public Object requestAcceptedByStudent(@PathVariable (name="id") int id){
		
		return requestAccepted(id,"ROLE_ESTUDIANTE");

	}
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Secured("ROLE_ESTUDIANTE")
	@PostMapping("/student/{id}")
	public  Object insertServiceAttendaceAsStudent(@RequestBody @PathVariable(name="id") int id, @RequestParam(name="idStudent") int idStudent,
			@RequestParam(name="idContractor") int idContractor,@RequestParam(name="serviceDetails") String serviceDetails) throws JsonProcessingException {
		
		Map <String, Object> map=new HashMap<String,Object>();
		User student=user.findUserById(id);
		User contractor=user.findUserById(idContractor);
		 if(student==null || id!=idStudent || !student.getRole().equalsIgnoreCase("ROLE_ESTUDIANTE")) {
			 
			 map.put("status", HttpStatus.OK);
				map.put("Message", "el id del estudiante en sesion no concide con el digitado en el campo de texto"
						+ "  o el id  del estudiante digitado no existe o no es un contratante");
				return map;
		 }
		 
		 if(contractor==null || !contractor.getRole().equalsIgnoreCase("ROLE_CONTRATANTE")) {
			 map.put("status", HttpStatus.OK);
				map.put("Message", "el id insertado en campo contratante no pertenece a un contratante");
				return map;
		 }
		 
		 
		 ServiceAttendace serviceA= new ServiceAttendace();
			serviceA.setContractorId(contractor);
			serviceA.setState(0d);
			serviceA.setStudentId(student);
			serviceA.setServiceDetails(serviceDetails);
			
			serviceAttendace.save(serviceA);
			String s=serviceA.getServiceDetails();
			s=s.replaceAll("\"", "");
			serviceA.setServiceDetails(s);
		 
		return  serviceA ;
	}
	
	// En el body se debe pasar el id del contractor y del estudent ademas y service_details 
	@Secured("ROLE_CONTRATANTE")
	@PostMapping("/contractor/{id}")
	public Object insertServiceAttendaceAsContractor (@PathVariable(name="id") int id,@RequestParam(name="idStudent") int idStudent,
			@RequestParam(name="idContractor") int idContractor,@RequestParam(name="serviceDetails") String serviceDetails) {
		
		Map <String,Object> map=new HashMap<>();
		User userContractor=user.findUserById(idContractor);
		User userStudent = user.findUserById(idStudent);
		logger.info(userContractor);
		
		if( userContractor==null || id!=idContractor || !userContractor.getRole().equalsIgnoreCase("CONTRATANTE")) {
			map.put("status", HttpStatus.OK);
			map.put("Message", "el id del contratante en sesion no concide con el digitado en el campo de texto"
					+ "  o el id  del contratante digitado no existe o no es un contratante");
			return map;
		}
		
	
		if(userStudent==null || !userStudent.getRole().equalsIgnoreCase("ROLE_ESTUDIANTE")) {
			map.put("status", HttpStatus.OK);
			map.put("Message", "el id insertado en campo estudiante no pertenece a un estudiante");
			return map;
		}
		
		ServiceAttendace serviceA= new ServiceAttendace();
		serviceA.setContractorId(userContractor);
		serviceA.setState(0d);
		serviceA.setStudentId(userStudent);
		serviceA.setServiceDetails(serviceDetails);
		
		serviceAttendace.save(serviceA);
		String s=serviceA.getServiceDetails();
		s=s.replaceAll("\"", "");
		serviceA.setServiceDetails(s);
		
		
		return serviceA;
	}
	
}
