package com.xiaoqu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.xiaoqu.model.Person;

@Service
public class DummyService { 
  

    public List<Person> getDummyList() { 
        List<Person> list = new ArrayList<Person>(); 
        Person p1 = new Person(); 
        p1.setId(12345); 
        p1.setName("Paul"); 
        p1.setAge(27); 
        p1.setAddress("Dalaguete, Cebu"); 
  
        Person p2 = new Person(); 
        p2.setId(54321); 
        p2.setName("Sydney"); 
        p2.setAge(25); 
        p2.setAddress("Cebu City"); 
  
        list.add(p1); 
        list.add(p2); 
        return list; 
    } 
  

    public Person retrievePerson(int id) { 
        Person person = new Person(); 
        person.setId(56789); 
        person.setName("Nikki"); 
        person.setAge(63); 
        person.setAddress("Dalaguete, Cebu"); 
        return person; 
    } 

    public void savePerson(Person person) { 
        System.out.println("\n\nSaving" + person); 
    } 
}
