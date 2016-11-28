package com.restsample.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.restsample.model.Person;

@Repository
public class PersonDaoImpl implements PersonDao{
	@Autowired
private SessionFactory sessionFactory;
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public List<Person> getAllPersons() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Person");
		List<Person> persons=query.list();
		session.close();
		return persons;
	}


	public Person getPersonById(int id) {
		Session session=sessionFactory.openSession();
		//select * from personinfo where id=2
		Person person=(Person)session.get(Person.class, id);
		session.close();
		return person;
	}


	public void savePerson(Person person) {
		Session session=sessionFactory.openSession();
		session.save(person);
		session.flush();
		session.close();
	}

    @Transactional
	public Person updatePerson(int id, Person person) {
    	//person -> modified value -> 226
		Session session=sessionFactory.openSession();
		//current person -> 226
		//currentPerson, person -> with same id
		//updating only variable person
		//notunique
		//select [before modification]
		Person currentPerson=(Person)session.get(Person.class, id);//persistent
		if(currentPerson==null) //id doesnt exist in the database
			return null;
		//to modify [update]
		session.merge(person); //update query where personid=?
		//select [after modification]
		Person updatedPerson=(Person)session.get(Person.class, id); //select query
		session.flush();
		session.close();
		return updatedPerson;
		
	}

	public void deletePerson(int id) {
		Session session = sessionFactory.openSession();
		// it makes the object persistent - person
		Person person = (Person)session.get(Person.class,id);
		session.delete(person);
		// transient - person
		session.flush();
		session.close();
		
	}

}
