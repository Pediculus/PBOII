package com.mycompany.birthdaysomething;

import java.time.LocalDate;

// This class represents a person with a name and birth date
public class Person {
    private String name;  // The name of the person
    private LocalDate birthDate;  // The birth date of the person (using LocalDate for date handling)

    // Constructor to initialize the name and birth date
    public Person(String name, LocalDate birthDate){
        this.name = name;
        this.birthDate = birthDate;
    }

    // Getter method for the name
    public String getName(){
        return name;
    }

    // Getter method for the birth date
    public LocalDate getBirthDate(){
        return birthDate;
    }
}
