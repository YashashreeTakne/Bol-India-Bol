package com.restsample.services;

import java.util.List;

import com.restsample.model.Person;

public interface PersonService {
List<Person> getAllPersons(); 

Person getPersonById(int id);
void savePerson(Person person);

}
