package com.zyx.SSM.dao;

import java.util.List;

import com.zyx.SSM.bean.Person;

public interface PersonDao {
 
	List<Person> findAllPerson(); 
 
}
