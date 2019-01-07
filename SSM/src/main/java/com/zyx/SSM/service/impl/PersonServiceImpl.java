package com.zyx.SSM.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyx.SSM.bean.Person;
import com.zyx.SSM.dao.PersonDao;
import com.zyx.SSM.service.PersonService;
 
@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonDao personDao;
	
	public PersonDao getPersonDao() {
		return personDao;
	}
 
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}
 
	public List<Person> findAll() {
		return personDao.findAllPerson();
	}
 
}
