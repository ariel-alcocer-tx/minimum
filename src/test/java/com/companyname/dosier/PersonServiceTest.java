package com.companyname.dosier;

import com.companyname.dosier.domain.Person;
import com.companyname.dosier.exception.NotValidPersonException;
import com.companyname.dosier.service.PersonService;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class PersonServiceTest {
    private PersonService personnelAdmin;
    private Person personnel;
    private Person personnel2;
    private Person personnel3;
    private Person personnel4;
    private Person personnelWithNullValues;
    private Person personnelWithInvalidData;

    @Before
    public void setUp() throws Exception {
        personnelAdmin = new PersonService();
        Calendar calendar = Calendar.getInstance();

        personnel = new Person();
        personnel.setFullName("Pepito Juarez");
        personnel.setNationalID("654520 TJ");
        calendar.set(1999, Calendar.MAY, 6);//YYYY,MM,DD
        personnel.setBirthDate(calendar.getTime());
        personnel.setPhone(76587222);
        personnel.setAddress("B. Las Cuadras");
        personnel.setPosition("Cabo");

        personnel2 = new Person();
        personnel2.setFullName("Jon Doe");
        personnel2.setNationalID("212735 SC");
        calendar.set(1987, Calendar.APRIL, 7);//YYYY,MM,DD
        personnel2.setBirthDate(calendar.getTime());
        personnel2.setPhone(70770777);
        personnel2.setAddress("Av. Sucre #1999");
        personnel2.setPosition("Cabo");

        personnel3 = new Person();
        personnel3.setFullName("Marco Botton");
        personnel3.setNationalID("134056 CB");
        calendar.set(1989, Calendar.MAY, 13);//YYYY,MM,DD
        personnel3.setBirthDate(calendar.getTime());
        personnel3.setPhone(70770777);
        personnel3.setAddress("Av. Sucre #1999");
        personnel3.setPosition("Sargento");

        personnel4 = new Person();
        personnel4.setFullName("Valerie Liberty");
        personnel4.setNationalID("231256 LP");
        calendar.set(1993, Calendar.NOVEMBER, 18);//YYYY,MM,DD
        personnel4.setBirthDate(calendar.getTime());
        personnel4.setPhone(74392312);
        personnel4.setAddress("Av. Pando #121");
        personnel4.setPosition("Sargento");

        personnelWithNullValues = new Person();

        personnelWithInvalidData = new Person();
        personnelWithInvalidData.setFullName("Peter123");
        personnelWithInvalidData.setNationalID("12857263");
        calendar.set(1999, Calendar.MAY, 6);//YYYY,MM,DD
        personnelWithInvalidData.setBirthDate(calendar.getTime());
        personnelWithInvalidData.setPhone(16587222);
        personnelWithInvalidData.setAddress("4324132899839482948293");
        personnelWithInvalidData.setPosition("Cabo@Coronel.com");
    }

    //Funcionalidad 1
    @Test
    public void testRegisteredPersonnelIsReturned() throws Exception {
        assertNotNull(personnelAdmin.registerPersonnel(personnel));
    }

    @Test
    public void testRegisteredPersonnelHasId() throws Exception {
        assertNotNull((personnelAdmin.registerPersonnel(personnel)).getId());
    }

    @Test
    public void testRegisteredPersonnelHasRegistrationDate() throws Exception {
        assertNotNull((personnelAdmin.registerPersonnel(personnel)).getRegistrationDate());
    }

    @Test
    public void testRegisteredPersonnelHasUniqueId() throws Exception {
        assertNotEquals(
                personnelAdmin.registerPersonnel(personnel).getId(),
                personnelAdmin.registerPersonnel(personnel2).getId());
    }

    @Test
    public void testPersonnelToBeRegisteredDoesNotHaveNullValues() throws Exception {
        assertFalse(personnelAdmin.personnelHasNullValues(personnel));
    }

    @Test
    public void testPersonnelToBeRegisteredHasNullValues() throws Exception {
        assertTrue(personnelAdmin.personnelHasNullValues(personnelWithNullValues));
    }

    @Test
    public void testPersonnelToBeRegisteredHasValidData() throws Exception {
        assertFalse(
                "Person has valid data",
                personnelAdmin.personnelHasInvalidData(personnel));
    }

    @Test
    public void testPersonnelToBeRegisteredHasInvalidData() throws Exception {
        assertTrue(
                "Person has invalid data",
                personnelAdmin.personnelHasInvalidData(personnelWithInvalidData));
    }

    @Test(expected = NotValidPersonException.class)
    public void testExceptionThrownWhenPersonnelToBeRegisteredIsNotValid() throws Exception {
        personnelAdmin.registerPersonnel(personnelWithNullValues);
    }

    @Test(expected = NotValidPersonException.class)
    public void testExceptionThrownWhenPersonnelToBeRegisteredIsNotValid2() throws Exception {
        personnelAdmin.registerPersonnel(personnelWithInvalidData);
    }

    //Funcionalidad 2
    @Test
    public void testRegisteredPersonnelListExists() throws Exception {
        assertNotNull(personnelAdmin.retrieveRegisteredPersonnelList());
    }

    @Test
    public void testRegisteredPersonnelListIsNotEmpty() throws Exception {
        personnelAdmin.registerPersonnel(personnel);
        assertFalse(personnelAdmin.retrieveRegisteredPersonnelList().isEmpty());
    }

    @Test
    public void testRegisteredPersonnelIsOnRegisteredPersonnelList() throws Exception {
        personnelAdmin.registerPersonnel(personnel);
        personnelAdmin.registerPersonnel(personnel2);
        assertThat(personnelAdmin.retrieveRegisteredPersonnelList(),
                hasItems(personnel, personnel2));
    }

    //Funcionalidad 3
    @Test
    public void testSortByFullNameAsc() throws Exception {
        personnelAdmin.registerPersonnel(personnel);
        personnelAdmin.registerPersonnel(personnel2);
        personnelAdmin.registerPersonnel(personnel3);
        personnelAdmin.registerPersonnel(personnel4);
        assertThat(
                personnelAdmin.retrieveRegisteredPersonnelList(
                        "fullName","ASC"),
                contains(personnel2, personnel3, personnel, personnel4));
    }

    @Test
    public void testSortByNationalIDAsc() throws Exception {
        personnelAdmin.registerPersonnel(personnel);
        personnelAdmin.registerPersonnel(personnel2);
        personnelAdmin.registerPersonnel(personnel3);
        personnelAdmin.registerPersonnel(personnel4);
        assertThat(
                personnelAdmin.retrieveRegisteredPersonnelList(
                        "nationalID","ASC"),
                contains(personnel3, personnel2, personnel4, personnel));
    }

    @Test
    public void testSortByBirthDateAsc() throws Exception {
        personnelAdmin.registerPersonnel(personnel);
        personnelAdmin.registerPersonnel(personnel2);
        personnelAdmin.registerPersonnel(personnel3);
        personnelAdmin.registerPersonnel(personnel4);
        assertThat(
                personnelAdmin.retrieveRegisteredPersonnelList(
                        "birthDate","ASC"),
                contains(personnel2, personnel3, personnel4, personnel));
    }

    @Test
    public void testSortByNameDesc() throws Exception {
        personnelAdmin.registerPersonnel(personnel);
        personnelAdmin.registerPersonnel(personnel2);
        personnelAdmin.registerPersonnel(personnel3);
        personnelAdmin.registerPersonnel(personnel4);
        assertThat(
                personnelAdmin.retrieveRegisteredPersonnelList(
                        "fullName","DESC"),
                contains(personnel4, personnel, personnel3, personnel2));

    }

    @Test
    public void testSortByNationalIDDesc() throws Exception {
        personnelAdmin.registerPersonnel(personnel);
        personnelAdmin.registerPersonnel(personnel2);
        personnelAdmin.registerPersonnel(personnel3);
        personnelAdmin.registerPersonnel(personnel4);
        assertThat(
                personnelAdmin.retrieveRegisteredPersonnelList(
                        "nationalID","DESC"),
                contains(personnel, personnel4, personnel2, personnel3));
    }

    @Test
    public void testSortByBirthDateDesc() throws Exception {
        personnelAdmin.registerPersonnel(personnel);
        personnelAdmin.registerPersonnel(personnel2);
        personnelAdmin.registerPersonnel(personnel3);
        personnelAdmin.registerPersonnel(personnel4);
        assertThat(
                personnelAdmin.retrieveRegisteredPersonnelList(
                        "birthDate","DESC"),
                contains(personnel, personnel4, personnel3, personnel2));
    }

    //Funcionalidad 3
    @Test
    public void testSearchByFullName() throws Exception {
        personnelAdmin.registerPersonnel(personnel);
        personnelAdmin.registerPersonnel(personnel2);
        personnelAdmin.registerPersonnel(personnel3);
        personnelAdmin.registerPersonnel(personnel4);
        assertEquals(
                personnelAdmin.searchByFullName("Pepito Juarez").getFullName(),
                "Pepito Juarez");
    }

    @Test
    public void testSearchByFullName2() throws Exception {
        personnel = personnelAdmin.registerPersonnel(personnel);
        personnel2 = personnelAdmin.registerPersonnel(personnel2);
        personnel3 = personnelAdmin.registerPersonnel(personnel3);
        personnel4 = personnelAdmin.registerPersonnel(personnel4);
        assertEquals(
                personnelAdmin.searchByFullName("Pepito Juarez"),
                personnel);
    }

    @Test
    public void testSearchByNationalID() throws Exception {
        personnelAdmin.registerPersonnel(personnel);
        personnelAdmin.registerPersonnel(personnel2);
        personnelAdmin.registerPersonnel(personnel3);
        personnelAdmin.registerPersonnel(personnel4);
        assertEquals(
                personnelAdmin.searchByNationalID("134056 CB").getNationalID(),
                "134056 CB");
    }

    @Test
    public void testSearchByNationalID2() throws Exception {
        personnelAdmin.registerPersonnel(personnel);
        personnelAdmin.registerPersonnel(personnel2);
        personnelAdmin.registerPersonnel(personnel3);
        personnelAdmin.registerPersonnel(personnel4);
        assertEquals(
                personnelAdmin.searchByNationalID("134056 CB"),
                personnel3);
    }

}
