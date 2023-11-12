import org.example.Model.Medicine;
import org.example.Model.Pet;
import org.example.Model.Registration;
import org.example.Model.Visit;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationTest {

    Registration registration;

    @BeforeEach
    void init()
    {
        registration = new Registration();
        registration.addNewPet(1,"Dog",3,Pet.Health.SICK);
        registration.addNewPet(2,"Cat",5,Pet.Health.HEALTHY);
        registration.addNewPet(3,"Turtle",2,Pet.Health.NA);
        //registration.CreateVisit(LocalDateTime.of(2024,2,1,13,0),1);
        registration.CreateVisit(LocalDateTime.of(2024,2,7,13,45),1);
        registration.CreateVisit(LocalDateTime.of(2024,1,1,13,0),2);
        registration.CreateVisit(LocalDateTime.of(2024,1,2,13,0),2);
        registration.CreateVisit(LocalDateTime.of(2024,1,3,13,5),2);
        registration.CreateVisit(LocalDateTime.of(2024,2,1,13,0),2);
        registration.CreateVisit(LocalDateTime.of(2024,2,1,13,0),3);
        registration.CreateVisit(LocalDateTime.of(2024,3,1,14,20),3);
        registration.CreateVisit(LocalDateTime.of(2024,4,1,15,0),3);
        registration.addMedicine(1,registration.getVisits(1),new Medicine("Azaporc",20,2));
        registration.addMedicine(1,registration.getVisits(1),new Medicine("Betamox L.A.",10,1));
        registration.addMedicine(2,registration.getVisits(1),new Medicine("Betamox L.A.",10,1));
        registration.addMedicine(1,registration.getVisits(2),new Medicine("Otisur",1,1));
        registration.addMedicine(2,registration.getVisits(2),new Medicine("Hemosilate vet",15,2));
        registration.addMedicine(3,registration.getVisits(2),new Medicine("Scanopril Flavour",1,3));
        registration.addMedicine(4,registration.getVisits(2),new Medicine("Scanopril Flavour",1,3));
        registration.addMedicine(1,registration.getVisits(3),new Medicine("Azaporc",20,2));
        registration.addMedicine(1,registration.getVisits(3),new Medicine("Betamox L.A.",10,1));
        registration.addMedicine(1,registration.getVisits(3),new Medicine("Betamox L.A.",10,1));
        registration.addMedicine(2,registration.getVisits(3),new Medicine("Otisur",1,1));
        registration.addMedicine(3,registration.getVisits(3),new Medicine("Hemosilate vet",15,2));
    }


    @ParameterizedTest
    @ValueSource(ints = {5, -3, 15})
    void checkValidId(int number) {
        assertFalse(registration.checkValidId(number));
        registration.addNewPet(1,"Dog",3, Pet.Health.SICK);
    }
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 0})
    void checkInvalidId(int number) {
        assertTrue(registration.checkValidId(number));
        registration.addNewPet(1,"Dog",3, Pet.Health.SICK);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Dog", "Cat", "Horse", "2"})
    void checkAnimalName(String string) {
        assertFalse(registration.checkAnimalName(string));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", ""})
    void checkBadAnimalName(String string) {
        assertTrue(registration.checkAnimalName(string));
    }

    @org.junit.jupiter.api.Test
    void findPet() {
        var p1 = new Pet(1,"Dog",3,Pet.Health.SICK);
            assertEquals(p1.getId(),registration.findPet(1).getId());
            assertEquals(p1.getAge(),registration.findPet(1).getAge());
            assertEquals(p1.getAnimal(),registration.findPet(1).getAnimal());
    }

    @org.junit.jupiter.api.Test
    void findBadPet() {
        var p1 = new Pet(2,"Dog",3,Pet.Health.SICK);
        assertNotEquals(p1.getId(),registration.findPet(1).getId());
    }
    @org.junit.jupiter.api.Test
    void findNoPet() {
        assertNull(registration.findPet(5));
    }

    @org.junit.jupiter.api.Test
    void getVisits() {
        ArrayList<Visit> v = new ArrayList<Visit>();
        v.add(new Visit(1,LocalDateTime.of(2024,2,7, 13,45),0,new ArrayList<>()));
        assertSame(registration.getVisits(1).get(0).getId(),v.get(0).getId());

    }
    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void setQuoteTest(int id){

       registration.setQuote(id,registration.getVisits(2),30.0f);

       assertEquals(30.0f,registration.getVisits(2).get(id-1).getCost());

    }
    @org.junit.jupiter.api.Test
    void addNewPetTest()
    {
        Pet pet = new Pet(5,"Cat",3,Pet.Health.SICK);
        registration.addNewPet(pet.getId(),pet.getAnimal(),pet.getAge(),pet.getHealth());

        assertSame(pet.getId(),registration.findPet(5).getId());
        assertSame(pet.getAnimal(),registration.findPet(5).getAnimal());
        assertSame(pet.getAge(),registration.findPet(5).getAge());
        assertSame(pet.getHealth(),registration.findPet(5).getHealth());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Cat","dog","2"," " })
    void editPetTest(String s)
    {
        registration.editPet(s,registration.findPet(1));

        assertSame(s,registration.findPet(1).getAnimal());
    }
    @ParameterizedTest
    @ValueSource(ints = {0,3,12,127,-3 })  //apparently ints are max 8bit
    void editPetTest(int a)
    {
        registration.editPet(a,registration.findPet(1));

        assertSame(a,registration.findPet(1).getAge());
    }

    @org.junit.jupiter.api.Test
    void editPetTest()
    {
        registration.editPet(Pet.Health.HEALTHY,registration.findPet(1));
        assertSame(Pet.Health.HEALTHY,registration.findPet(1).getHealth());
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void registerVisitTest(int id)
    {
        registration.registerVisit(id,registration.getVisits(2));

        assertTrue(registration.getVisits(2).get(id-1).getHeld());
    }

}