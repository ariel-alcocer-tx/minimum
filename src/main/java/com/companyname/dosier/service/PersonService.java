package com.companyname.dosier.service;

import com.companyname.dosier.exception.NotValidPersonException;
import com.companyname.dosier.domain.Person;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonService {
    private List<Person> registeredPeople = new ArrayList<>();

    public Person registerPersonnel(Person person) throws NotValidPersonException {
        if(personnelHasNullValues(person) || personnelHasInvalidData(person))   {
            throw new NotValidPersonException();
        }
        person.setId(UUID.randomUUID().toString());
        person.setRegistrationDate(Calendar.getInstance().getTime());
        registeredPeople.add(person);
        return person;
    }

    public Boolean personnelHasNullValues(Person person)    {
        Boolean hasNullValues = false;
        if(person.getFullName()==null
                || person.getNationalID()==null
                || person.getBirthDate()==null
                || person.getPhone()==null
                || person.getAddress()==null
                || person.getPosition()==null) {
            hasNullValues = true;
        }
        return hasNullValues;
    }

    public Boolean personnelHasInvalidData(Person person)   {
        Boolean hasInvalidData= true;
        Pattern isCelPhone = Pattern.compile("[67]\\d{7}");
        Pattern isFullNameOrPosition = Pattern.compile("([a-zA-Záéíóú]+)([ ]+[a-zA-Záéíóú]+)*");
        Pattern isAddress = Pattern.compile("([a-zA-Z0-9.#áéíóú]+)( [a-zA-Z0-9.#áéíóú]+)*");
        Pattern isNationalID = Pattern.compile("\\d+ [a-zA-Z]{2,}");

        Matcher fullName = isFullNameOrPosition.matcher(person.getFullName());
        Matcher nationalID = isNationalID.matcher(person.getNationalID());
        Matcher phone = isCelPhone.matcher(person.getPhone().toString());
        Matcher address = isAddress .matcher(person.getAddress());
        Matcher position = isFullNameOrPosition.matcher(person.getPosition());

        if(fullName.matches()
                && nationalID.matches()
                && phone.matches()
                && address.matches()
                && position.matches())   {
            hasInvalidData = false;
        }
        return hasInvalidData;
    }

    public List<Person> retrieveRegisteredPersonnelList() {
        return registeredPeople;
    }

    public List<Person> retrieveRegisteredPersonnelList(String criteria, String order) {
//        Comparator<Person> c = (o1, o2) -> o1.getBirthDate().compareTo(o2.getBirthDate();
        Comparator<Person> comparator = null;
        switch (criteria)   {
            case "id":
                comparator = Comparator.comparing(Person::getId);
                break;
            case "fullName":
                comparator = Comparator.comparing(Person::getFullName);
                break;
            case "birthDate":
                comparator = Comparator.comparing(Person::getBirthDate);
                break;
            case "nationalID":
                comparator = Comparator.comparing(Person::getNationalID);
                break;
            case "registrationDate":
                comparator = Comparator.comparing(Person::getRegistrationDate);
                break;
        }
        Collections.sort(registeredPeople, comparator);
        if(order.equals("DESC"))
            Collections.reverse(registeredPeople);
        return registeredPeople;
    }

    public Person searchById(String s) {
        Comparator<Person> comparator = Comparator.comparing(Person::getId);
        Person target = new Person();
        target.setId(s);
        registeredPeople.sort(comparator);
        int found = Collections.binarySearch(registeredPeople, target, comparator);
        return registeredPeople.get(found);
    }

    public Person searchByFullName(String s) {
        Comparator<Person> comparator = Comparator.comparing(Person::getFullName);
        Person target = new Person();
        target.setFullName(s);
        registeredPeople.sort(comparator);
        int found = Collections.binarySearch(registeredPeople, target, comparator);
        return registeredPeople.get(found);
    }

    public Person searchByNationalID(String s) {
        Comparator<Person> comparator = Comparator.comparing(Person::getNationalID);
        Person target = new Person();
        target.setNationalID(s);
        registeredPeople.sort(comparator);
        int found = Collections.binarySearch(registeredPeople, target, comparator);
        return registeredPeople.get(found);
    }

}
