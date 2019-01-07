package com.zyx.SSM.service;
 
import java.util.List;

import org.springframework.stereotype.Service;

import com.zyx.SSM.bean.Person;
 
@Service 
public interface PersonService {
	
	List<Person> findAll();
	
}	