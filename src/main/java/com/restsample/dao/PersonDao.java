package com.restsample.dao;

import java.util.List;

import com.restsample.model.Person;

public interface PersonDao {
List<Person> getAllPersons();
Person getPersonById(int id);
void savePerson(Person person);

}
