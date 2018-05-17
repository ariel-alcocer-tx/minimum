package com.companyname.dosier;

import com.companyname.dosier.domain.Person;
import com.companyname.dosier.exception.NotValidPersonException;
import com.companyname.dosier.service.PersonService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class App {

    public static void main(String args[]) throws NotValidPersonException {
        PersonService personService;
        Person person;
        Person person2;
        Person person3;
        Person person4;

        personService = new PersonService();
        Calendar calendar = Calendar.getInstance();

        person = new Person();
        person.setFullName("Pepito Juarez");
        person.setNationalID("654520 TJ");
        calendar.set(1999, Calendar.MAY, 6);//YYYY,MM,DD
        person.setBirthDate(calendar.getTime());
        person.setPhone(76587222);
        person.setAddress("B. Las Cuadras");
        person.setPosition("Cabo");

        person2 = new Person();
        person2.setFullName("Jon Doe");
        person2.setNationalID("212735 SC");
        calendar.set(1987, Calendar.APRIL, 7);//YYYY,MM,DD
        person2.setBirthDate(calendar.getTime());
        person2.setPhone(70770777);
        person2.setAddress("Av. Sucre #1999");
        person2.setPosition("Cabo");

        person3 = new Person();
        person3.setFullName("Marco Botton");
        person3.setNationalID("134056 CB");
        calendar.set(1989, Calendar.MAY, 13);//YYYY,MM,DD
        person3.setBirthDate(calendar.getTime());
        person3.setPhone(70770777);
        person3.setAddress("Av. Sucre #1999");
        person3.setPosition("Sargento");

        person4 = new Person();
        person4.setFullName("Valerie Liberty");
        person4.setNationalID("231256 LP");
        calendar.set(1993, Calendar.NOVEMBER, 18);//YYYY,MM,DD
        person4.setBirthDate(calendar.getTime());
        person4.setPhone(74392312);
        person4.setAddress("Av. Pando #121");
        person4.setPosition("Sargento");

        System.out.printf("++++++++++++ Operation -> Register new Person: %s ++++++++++++\n", person);
        personService.registerPersonnel(person);
        List<Person> people = personService.retrieveRegisteredPersonnelList();
        displayPersonnelList(people);

        System.out.printf("++++++++++++ Operation -> Register new Person: %s ++++++++++++\n", person2);
        personService.registerPersonnel(person2);
        people = personService.retrieveRegisteredPersonnelList();
        displayPersonnelList(people);

        System.out.printf("++++++++++++ Operation -> Register new Person: %s ++++++++++++\n", person3);
        personService.registerPersonnel(person3);
        people = personService.retrieveRegisteredPersonnelList();
        displayPersonnelList(people);

        System.out.printf("++++++++++++ Operation -> Register new Person: %s ++++++++++++\n", person4);
        personService.registerPersonnel(person4);
        people = personService.retrieveRegisteredPersonnelList();
        displayPersonnelList(people);

        System.out.printf("++++++++++++ Operation -> SortBy 'FullName', ASC ++++++++++++\n");
        people = personService.retrieveRegisteredPersonnelList("fullName", "ASC");
        displayPersonnelList(people);

        System.out.printf("++++++++++++ Operation -> SortBy 'BirthDate', DESC ++++++++++++\n");
        people = personService.retrieveRegisteredPersonnelList("birthDate", "DESC");
        displayPersonnelList(people);

        System.out.printf("++++++++++++ Operation -> Search for: 'Pepito Juarez' ++++++++++++\n");
        Person found = personService.searchByFullName("Pepito Juarez");
        List<Person> personList = new ArrayList<>(1);
        personList.add(found);
        displayPersonnelList(personList);

    }

    public static void displayPersonnelList(List<Person> people)    {
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.printf("| Id | \t Name | \t NationalID | \t BirthDate | \t Phone | \t Address | \t Position | \t Registration Date | \n");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        for(Person p : people)   {
            System.out.printf("%s \n", p);
        }
        System.out.println("\n");
    }
}
